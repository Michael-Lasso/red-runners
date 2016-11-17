package com.redrunner.geocoordinates;

import java.util.ArrayList;
import java.util.List;

import com.redrunner.utils.FileParser;

public class CoordinatesFactory {

	public static List<Coordinates> buildCoordinates(String filePath) {
		List<Coordinates> coords = new ArrayList<>();
		String allCoords = FileParser.FileReader(filePath);
		String[] array = allCoords.split("\\r?\\n");
//		System.out.println(allCoords);
		for (int i = 0; i < array.length; i++) {
			String[] list = array[i].split("/");
			Coordinates coord = new Coordinates(list[0], list[1]);
//			System.out.println(coord);
			coords.add(coord);
		}
		return coords;
	}

}
