package com.redrunner.controller;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.redrunner.geocoordinates.Coordinates;
import com.redrunner.geocoordinates.GeoCodeSample;
import com.redrunner.utils.Constants;
import com.redrunner.utils.DAO;
import com.redrunner.utils.FileParser;

@Component
public class ScheduledTasks {
	static Logger logger = Logger.getLogger(ScheduledTasks.class);

	@Scheduled(fixedRate = 20000)
	public void reportCurrentTime() {

		boolean hasChanged = fileChanged(Constants.TRAFFIC_FILE_PATH, Constants.TIMESTAMP_FILE_PATH);
		if (hasChanged) {
			String data = FileParser.FileReader(Constants.TRAFFIC_FILE_PATH);
			String lines[] = data.trim().split("\\r?\\n");
			File trafficFile = new File(Constants.TRAFFIC_FILE_PATH);
			Long trafficTimeStamp = trafficFile.lastModified();
			FileParser.FileCleaner(Constants.TIMESTAMP_FILE_PATH);
			FileParser.FileCleaner(Constants.TRAFFIC_FILE_PATH);
			FileParser.FileWritter(Constants.TIMESTAMP_FILE_PATH, trafficTimeStamp + "");
			logger.info(
					"\n\n\t---------------------+Saving lattest batch into DB and resetting files+---------------------");

			for (int i = 0; i < lines.length; i++) {
				if (lines[i].trim().length() == 0) {
					continue;
				}
				// post to database
				Coordinates coord = new Coordinates(lines[i]);
				String street = GeoCodeSample.getStreetName(coord);
				coord.setName(street);
				coord.setTimeStamp(System.currentTimeMillis() + "");

				DAO.insertRecord(coord);
				logger.info("Saved: " + coord.toString());
			}
			logger.info("\n\n\t---------------------+Starting new batch+---------------------");

		}
	}

	private boolean fileChanged(String trafficFilePath, String timestampFile) {
		File trafficFile = new File(trafficFilePath);
		Long trafficTimeStamp = trafficFile.lastModified();
		Long timestamp = Long.parseLong(FileParser.FileReader(timestampFile));
		return !trafficTimeStamp.equals(timestamp);
	}

	// public static void main(String[] args) {
	// ScheduledTasks t = new ScheduledTasks();
	// t.reportCurrentTime();
	// }

}
