package com.redrunner.controller;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.redrunner.geocoordinates.Coordinates;
import com.redrunner.geocoordinates.CoordinatesFactory;
import com.redrunner.utils.Constants;
import com.redrunner.utils.FileParser;

@Component
public class RandomTraffic {
	static Logger logger = Logger.getLogger(RandomTraffic.class);

	@Scheduled(fixedRate = 2000)
	public void reportCurrentTime() {
		Random rand = new Random();

		int probability = rand.nextInt(Constants.PROBABILITY) + 1;
		if (probability <= Constants.PROBABILITY_THRESHOLD) {

			List<Coordinates> coords = CoordinatesFactory.buildCoordinates(Constants.COORDS_FILE_PATH);
			Coordinates coord = coords.get(rand.nextInt(coords.size()));

			FileParser.FileWritter(Constants.TRAFFIC_FILE_PATH,
					coord.getLatitude() + Constants.SEPARATOR + coord.getLongitude());
			logger.info("red light runner: " + probability + ", at: " + coord);
		} else {
			logger.info("good driver: " + probability);
		}
//		DAO.getConnection();
	}

//	public static void main(String[] args) {
//		RandomTraffic t = new RandomTraffic();
//		t.reportCurrentTime();
//		logger.debug("this is a debug log message");
//		logger.info("this is a information log message");
//		logger.warn("this is a warning log message");
//	}
}
