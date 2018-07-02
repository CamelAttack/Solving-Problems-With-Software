/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
package basic;

import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
	public void listExporters(CSVParser parser, String exportOfInterest) {
		// for each row in the CSV File
		for (CSVRecord record : parser) {
			// Look at the "Exports" column
			String export = record.get("Exports");
			// Check if it contains exportOfInterest
			if (export.contains(exportOfInterest)) {
				// If so, write down the "Country" from that row
				String country = record.get("Country");
				System.out.println(country + " exports " + exportOfInterest + ".");
			}
		}
	}

	public void countryInfo(CSVParser parser, String someCountry) {
		for (CSVRecord record : parser) {
			String recordCountry = record.get("Country");
			if (someCountry.equals(recordCountry)) {
				System.out.println(
						recordCountry + " :: " + record.get("Exports") + " :: " + record.get("Value (dollars)"));
				return;
			}
		}
		System.out.println("NO RECORD FOUND!");
	}

	public void readCountryInfo() {
		FileResource fr = new FileResource("src/basic/CSV_files/exportdata.csv");
		CSVParser parser = fr.getCSVParser();
		countryInfo(parser, "Indonesia");
	}

	public void whoExportsCoffee() {
		FileResource fr = new FileResource("src/basic/CSV_files/exportsmall.csv");
		CSVParser parser = fr.getCSVParser();
		listExporters(parser, "coffee");
	}
}
