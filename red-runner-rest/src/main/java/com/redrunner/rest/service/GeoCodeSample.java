package com.redrunner.rest.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;

import com.redrunner.model.domain.Coordinates;
import com.redrunner.rest.utils.Constants;

public class GeoCodeSample {

	private static String accessToken;

	public static String getStreetName(Coordinates coords) {

		// Acquires OAuth2 token
		acquireAuthToken();

		// Gets reverse response as transient in JSON format
		return getReverseGeocode(false, "premium", coords);
	}

	/**
	 * Acquires OAuth2 token for accessing Location Intelligence APIs
	 */
	private static void acquireAuthToken() {
		String authHeader = Constants.BASIC
				+ Base64.encodeAsString(Constants.API_KEY + Constants.COLON + Constants.SECRET);

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(Constants.OAUTH2_TOKEN_URL);

		Builder builder = target.request().header(Constants.AUTH_HEADER, authHeader);
		Form form = new Form();
		form.param(Constants.GRANT_TYPE, Constants.CLIENT_CREDENTIALS);
		Response response = builder.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		String jsonResponse = response.readEntity(String.class);

		JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		accessToken = jsonObject.getString(Constants.ACCESS_TOKEN);
		accessToken = Constants.BEARER + accessToken;
	}

	/**
	 * Generic client request processor that makes a Rest POST call to Location
	 * Intelligence APIs and prints response in XML or JSON formats.
	 * 
	 * @param paramEntity
	 * @param responseTypeIsXml
	 * @param apiUrl
	 * @param persistenceType
	 * @param bundleType
	 */
	@SuppressWarnings("rawtypes")
	private static String processPOSTRequest(Entity paramEntity, boolean responseTypeIsXml, String apiUrl,
			String bundleType) {

		final String URL = (Constants.LOCATION_INTELLIGENCE_API_URL + apiUrl).replace("{bundleType}", bundleType);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL);
		Builder builder;
		if (responseTypeIsXml) {
			builder = target.request(MediaType.APPLICATION_XML).header(Constants.AUTH_HEADER, accessToken);

		} else {
			builder = target.request(MediaType.APPLICATION_JSON).header(Constants.AUTH_HEADER, accessToken);
		}
		String name = builder.post(paramEntity, String.class);
		return com.redrunner.rest.utils.JsonReader.readProperty(name, "");
	}

	/**
	 * Returns Reverse Geocode response for a given address in XML or JSON
	 * formats
	 * 
	 * @param responseTypeIsXml
	 * @param persistenceType
	 * @param bundleType
	 */
	private static String getReverseGeocode(boolean responseTypeIsXml, String bundleType, Coordinates coord) {

		String apiUrl = Constants.REVERSE_GEOCODE_API;
		String data = null;
		try {
			data = processPOSTRequest(buildGeocodeRequest(Constants.REVERSE_GEOCODE_REQUEST_SRC, coord),
					responseTypeIsXml, apiUrl, bundleType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * Builds geocode request by parsing json template
	 * 
	 * @param resourceURL
	 * @return Entity
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private static Entity buildGeocodeRequest(String resourceURL, Coordinates coord) throws IOException {

		String path = resourceURL;
		BufferedReader reader = new BufferedReader(new FileReader(path.replace("\\", "/")));
		String line = null;
		StringBuilder builder = new StringBuilder();
		String ls = System.getProperty(Constants.LINE_SEPERATOR);
		String jsonRequest = null;

		try {
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(ls);
			}

			jsonRequest = builder.toString().replaceAll(Constants.JSON_LATITUDE, coord.getLatitude())
					.replaceAll(Constants.JSON_LONGITUDE, coord.getLongitude());
			Entity paramEntity = Entity.entity(jsonRequest, MediaType.APPLICATION_JSON_TYPE);
			return paramEntity;
		} finally {
			reader.close();
		}
	}

}
