/*
 * Copyright 2013-2104 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package security;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.lokiz.ibatis.controller.ConnectorService;
import com.lokiz.ibatis.controller.RedRunnerConnector;
import com.lokiz.ibatis.domain.Coordinates;


/**
 * @author Dave Syer
 *
 */
// @RunWith(SpringJUnit4ClassRunner.class)
// @SpringApplicationConfiguration(classes = Application.class)
// @IntegrationTest("server.port=0")
// @WebAppConfiguration
public class ApplicationTests2 {

	@Value("${local.server.port}")
	private int port;

	@Ignore
	@Test
	public void testGrant() {
		MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
		request.set("username", "mlasso");
		request.set("password", "password");
		request.set("grant_type", "password");
		@SuppressWarnings("unchecked")
		Map<String, Object> token = new TestRestTemplate("android-bookmarks", "123456")
				.postForObject("http://localhost:8080/security/oauth/token", request, Map.class);
		assertNotNull("Wrong response: " + token, token.get("access_token"));
		/// Users/michaellasso/Documents/tomcat.keystore
		// curl -X POST -vu android-bookmarks:123456
		/// http://localhost:8080/oauth/token -H "Accept: application/json" -d
		/// "password=cordoba32&username=mlasso&grant_type=password&scope=write&client_secret=123456&client_id=android-bookmarks"
	}

	@Test
	public void testConnector() {

		ConnectorService connector = new RedRunnerConnector();
		connector.persistObject("insertCoordinates", new Coordinates());
	}

}
