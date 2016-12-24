/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redrunner.rest;

import java.security.Principal;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redrunner.model.domain.Coordinates;
import com.redrunner.model.domain.PitneyBowesCredentials;
import com.redrunner.model.domain.RedRunner;
import com.redrunner.model.domain.StreetIntersection;
import com.redrunner.model.repos.AccountRepository;
import com.redrunner.model.repos.RedRunnerRepository;
import com.redrunner.model.repos.StreetIntersectionRepository;
import com.redrunner.rest.security.UserNotFoundException;
import com.redrunner.rest.service.GeoCodeService;

@Component
@RestController
@RequestMapping("/redrunner")
class RedRunnerRestController {

	static Logger log = Logger.getLogger(RedRunnerRestController.class);

	@Value("${redrunner.json.path}")
	private String json;

	@Value("${redrunner.pitney.api_key}")
	private String api_key;

	@Value("${redrunner.pitney.secret}")
	private String secret;

	@Autowired
	private StreetIntersectionRepository streetIntersectionRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private RedRunnerRepository red_RunnerRepository;

	@RequestMapping(method = RequestMethod.GET)
	Resources<IntersectionResource> readBookmarks(Principal principal) {
		this.validateUser(principal);

		// List<IntersectionResource> bookmarkResourceList =
		// bookmarkRepository.findByAccountUsername(principal.getName())
		// .stream().map(IntersectionResource::new).collect(Collectors.toList());

		return new Resources<>(null);
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<StreetIntersection> add(Principal principal, @RequestBody StreetIntersection input) {
		this.validateUser(principal);
		if (input == null) {
			return new ResponseEntity<StreetIntersection>(HttpStatus.NOT_FOUND);
		} else {
			Optional<StreetIntersection> obj = streetIntersectionRepository
					.findByLongitudeAndLatitude(input.getLongitude(), input.getLatitude());
			if (obj.isPresent()) {
				log.debug("Found an intersection: " + obj.get().toString());
				long streetid = obj.get().getStreetid();
				red_RunnerRepository.save(new RedRunner(input.getTimestamp()));
			} else {
				log.debug("No intersection found, computing new one.");
				Coordinates coord = new Coordinates();
				coord.setLatitude(input.getLatitude());
				coord.setLongitude(input.getLongitude());
				PitneyBowesCredentials credentials = new PitneyBowesCredentials(api_key, secret);
				String name = GeoCodeService.getStreetName(coord, credentials);
				input.setStreetName(name);

				streetIntersectionRepository.save(
						new StreetIntersection(name, input.getLongitude(), input.getLatitude(), input.getTimestamp()));

				Optional<StreetIntersection> obj2 = streetIntersectionRepository
						.findByLongitudeAndLatitude(input.getLongitude(), input.getLatitude());

				long streetid = obj2.get().getStreetid();
				RedRunner red = new RedRunner( input.getTimestamp());
				red.setStreetIntersection(obj2.get());
				red_RunnerRepository.save(red);
			}
			return new ResponseEntity<StreetIntersection>(input, HttpStatus.OK);
		}
	}

	private void validateUser(Principal principal) {
		String userId = principal.getName();
		this.accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}
}
// end::code[]