package controller;

import java.io.File;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import geocoordinates.Coordinates;
import geocoordinates.GeoCodeSample;
import utils.Constants;
import utils.DAO;
import utils.FileParser;

@Component
public class ScheduledTasks {

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
			System.out.println(
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
				System.out.println("Saved: " + coord.toString());
			}
			System.out.println("\n\n\t---------------------+Starting new batch+---------------------");
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
