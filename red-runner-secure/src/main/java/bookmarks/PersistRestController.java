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
package bookmarks;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lokiz.ibatis.controller.ConnectorService;
import com.lokiz.ibatis.controller.RedRunnerConnector;
import com.lokiz.ibatis.domain.Coordinates;

/**
 * @author Josh Long
 * @author Greg Turnquist
 */
// tag::code[]
@RestController
@RequestMapping("/persist")
class PersistRestController {

	private final AccountRepository accountRepository;
	private static ConnectorService connector = new RedRunnerConnector();

	@Autowired
	PersistRestController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(Principal principal, @RequestBody Coordinates coords) {
		this.validateUser(principal);
		return accountRepository.findByUsername(principal.getName()).map(account -> {

			connector.persistObject("", coords);

			return new ResponseEntity<String>("Successfull test", HttpStatus.CREATED);
		}).orElse(new ResponseEntity<String>("Unsuccessfull test", HttpStatus.BAD_REQUEST));
	}

	private void validateUser(Principal principal) {
		String userId = principal.getName();
		this.accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}
}
// end::code[]
