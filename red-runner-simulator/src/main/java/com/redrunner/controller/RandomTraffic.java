package com.redrunner.controller;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.redrunner.geocoordinates.Coordinates;
import com.redrunner.geocoordinates.CoordinatesFactory;
import com.redrunner.utils.Constants;
import com.redrunner.utils.FileParser;

@Component
@ConditionalOnProperty(prefix = "com.redrunner.controller", name = "traffic")
public class RandomTraffic {
	static Logger log = Logger.getLogger(RandomTraffic.class);

	@Value("${com.redrunner.controller.flag}")
	private String flag;

	@Scheduled(cron = "${com.redrunner.controller.cron}")
	public void reportCurrentTime() {
		Random rand = new Random();
		log.info(flag);
		int probability = rand.nextInt(Constants.PROBABILITY) + 1;
		if (probability <= Constants.PROBABILITY_THRESHOLD) {

			List<Coordinates> coords = CoordinatesFactory.buildCoordinates(Constants.COORDS_FILE_PATH);
			Coordinates coord = coords.get(rand.nextInt(coords.size()));

			FileParser.FileWritter(Constants.TRAFFIC_FILE_PATH,
					coord.getLatitude() + Constants.SEPARATOR + coord.getLongitude());
			log.info("red light runner: " + probability + ", at: " + coord);
		} else {
			log.info("good driver: " + probability);
		}
	}
}
