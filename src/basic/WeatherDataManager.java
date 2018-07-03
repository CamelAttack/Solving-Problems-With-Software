package basic;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class WeatherDataManager {
	public CSVRecord coldestHourInFile(CSVParser parser) {
		CSVRecord currentColdestRecord = null;
		for (CSVRecord currentRow : parser) {
			currentColdestRecord = selectFileWithLowestTemperature(currentRow, currentColdestRecord);
		}
		return currentColdestRecord;
	}

	public CSVRecord coldestTemperatureAmongManyFiles() {
		CSVRecord currentColdestRecord = null;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			currentColdestRecord = selectFileWithLowestTemperature(currentRow, currentColdestRecord);
		}
		return currentColdestRecord;
	}

	private CSVRecord selectFileWithLowestTemperature(CSVRecord currentRecord, CSVRecord previousRecord) {
		CSVRecord currentColdestRecord = null;
		if (previousRecord == null) {
			currentColdestRecord = currentRecord;
		} else {
			double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
			double previousTemp = Double.parseDouble(previousRecord.get("TemperatureF"));
			if (currentTemp < previousTemp) {
				currentColdestRecord = currentRecord;
			} else {
				currentColdestRecord = previousRecord;
			}
		}
		return currentColdestRecord;
	}

	public void testColdestHour() {
		FileResource fr = new FileResource("src/nc_weather/2015/weather-2015-01-01.csv");
		CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
		System.out.println("The coldest temperature recorded was " + coldestRecord.get("TemperatureF") + " at "
				+ coldestRecord.get("TimeEST"));
	}

	public void testColdestTemperatureManyFiles() {
		CSVRecord coldestRecord = coldestTemperatureAmongManyFiles();
		System.out.println("The coldest temperature recorded was " + coldestRecord.get("TemperatureF") + " at "
				+ coldestRecord.get("DateUTC"));
	}
}
