package controller;

import java.util.List;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import geocoordinates.Coordinates;
import geocoordinates.CoordinatesFactory;
import utils.Constants;
import utils.DAO;
import utils.FileParser;

@Component
public class RandomTraffic {

	@Scheduled(fixedRate = 2000)
	public void reportCurrentTime() {
		Random rand = new Random();

		int probability = rand.nextInt(Constants.PROBABILITY) + 1;
		if (probability <= Constants.PROBABILITY_THRESHOLD) {

			List<Coordinates> coords = CoordinatesFactory.buildCoordinates(Constants.COORDS_FILE_PATH);
			Coordinates coord = coords.get(rand.nextInt(coords.size()));

			FileParser.FileWritter(Constants.TRAFFIC_FILE_PATH,
					coord.getLatitude() + Constants.SEPARATOR + coord.getLongitude());
			System.out.println("red light runner: " + probability + ", at: " + coord);
		} else {
			System.out.println("good driver: " + probability);
		}
		DAO.getConnection();
	}

	// public static void main(String[] args) {
	// RandomTraffic t = new RandomTraffic();
	// t.reportCurrentTime();
	// }
}
