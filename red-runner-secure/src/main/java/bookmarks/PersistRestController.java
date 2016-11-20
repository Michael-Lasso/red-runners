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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Josh Long
 * @author Greg Turnquist
 */
// tag::code[]
@RestController
@RequestMapping("/persist")
class PersistRestController {


	private final AccountRepository accountRepository;

	@Autowired
	PersistRestController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

//	@RequestMapping(method = RequestMethod.POST)
//	ResponseEntity<?> add(Principal principal, @RequestBody Bookmark input) {
//		this.validateUser(principal);
//		return accountRepository.findByUsername(principal.getName()).map(account -> {
//			Bookmark bookmark = bookmarkRepository.save(new Bookmark(account, input.uri, input.description));
//
//			Link forOneBookmark = new BookmarkResource(bookmark).getLink(Link.REL_SELF);
//
//			return ResponseEntity.created(URI.create(forOneBookmark.getHref())).build();
//		}).orElse(ResponseEntity.noContent().build());
//	}

	private void validateUser(Principal principal) {
		String userId = principal.getName();
		this.accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}
}
// end::code[]
