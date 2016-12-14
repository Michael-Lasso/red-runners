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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.redrunner.model.domain.Coordinates;
import com.redrunner.model.domain.Red_Runner;
import com.redrunner.model.domain.Street_Intersection;
import com.redrunner.model.repos.AccountRepository;
import com.redrunner.model.repos.Red_RunnerRepository;
import com.redrunner.model.repos.Street_IntersectionRepository;
import com.redrunner.rest.security.UserNotFoundException;
import com.redrunner.rest.service.GeoCodeSample;

@RestController
@RequestMapping("/bookmarks")
class BookmarkRestController {

	static Logger log = Logger.getLogger(BookmarkRestController.class);

	@Value("${json.path}")
	private String json;

	@Autowired
	private Street_IntersectionRepository street_IntersectionRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private Red_RunnerRepository red_RunnerRepository;

	@RequestMapping(method = RequestMethod.GET)
	Resources<BookmarkResource> readBookmarks(Principal principal) {
		this.validateUser(principal);

		// List<BookmarkResource> bookmarkResourceList =
		// bookmarkRepository.findByAccountUsername(principal.getName())
		// .stream().map(BookmarkResource::new).collect(Collectors.toList());

		return new Resources<>(null);
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<Street_Intersection> add(Principal principal, @RequestBody Street_Intersection input) {
		this.validateUser(principal);
		if (input == null) {
			return new ResponseEntity<Street_Intersection>(HttpStatus.NOT_FOUND);
		} else {
			Optional<Street_Intersection> obj = street_IntersectionRepository
					.findByLongitudeAndLatitude(input.getLongitude(), input.getLatitude());
			if (obj.isPresent()) {
				log.info("Found an intersection: " + obj.get().toString());
				long streetid = obj.get().getStreet_intersection_id();
				red_RunnerRepository
						.save(new Red_Runner(streetid, obj.get().getTimestamp()));
			} else {
				log.info("No intersection found, computing new one.");
				Coordinates coord = new Coordinates();
				coord.setLatitude(input.getLatitude());
				coord.setLongitude(input.getLongitude());
				String name = GeoCodeSample.getStreetName(coord);
				input.setStreet_intersection(name);
				// street_IntersectionRepository.save(
				// new Street_Intersection(name, input.getLongitude(),
				// input.getLatitude(), input.getTimestamp()));
				// red_RunnerRepository
				// .save(new Red_Runner(obj.get().getStreet_intersection_id(),
				// obj.get().getTimestamp()));
			}

			return new ResponseEntity<Street_Intersection>(input, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{bookmarkId}")
	BookmarkResource readBookmark(Principal principal, @PathVariable Long bookmarkId) {
		this.validateUser(principal);
		return new BookmarkResource(this.street_IntersectionRepository.findOne(bookmarkId));
	}

	private void validateUser(Principal principal) {
		String userId = principal.getName();
		this.accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}
}
// end::code[]