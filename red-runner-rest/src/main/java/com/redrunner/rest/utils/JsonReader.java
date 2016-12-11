package com.redrunner.rest.utils;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {

	@SuppressWarnings("unchecked")
	public static String readProperty(String jsonString, String property) {

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(jsonString);

			JSONObject jsonObject = (JSONObject) obj;
			// loop array
			JSONArray msg = (JSONArray) jsonObject.get("responses");
			Iterator<JSONObject> iterator = msg.iterator();
			while (iterator.hasNext()) {
				JSONObject factObj = (JSONObject) iterator.next();
				JSONArray candidates = (JSONArray) factObj.get("candidates");
				Iterator<JSONObject> iterator2 = candidates.iterator();
				while (iterator2.hasNext()) {
					JSONObject factObj2 = (JSONObject) iterator2.next();
					String water = (String) factObj2.get("formattedStreetAddress");
					return water;
				}
			}
			// formattedStreetAddress
		} catch (

		ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

}
