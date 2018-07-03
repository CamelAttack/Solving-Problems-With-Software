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

	public void listExportersTwoProducts(CSVParser parser, String exportOne, String exportTwo) {
		for (CSVRecord record : parser) {
			// Look at the "Exports" column
			String export = record.get("Exports");
			// Check if it contains exportOfInterest
			if (export.contains(exportOne) && export.contains(exportTwo)) {
				// If so, write down the "Country" from that row
				String country = record.get("Country");
				System.out.println(country);
			}
		}
	}

	public int numberOfExporters(CSVParser parser, String someExport) {
		int count = 0;
		for (CSVRecord record : parser) {
			String export = record.get("Exports");
			if (export.contains(someExport)) {
				count++;
			}
		}
		return count;
	}

	public void bigExporters(CSVParser parser, String exportDollars) {
		for (CSVRecord record : parser) {
			String value = record.get("Value (dollars)");
			if (value.length() > exportDollars.length()) {
				System.out.println(record.get("Country") + ":: " + value);
			}
		}
	}

	public void testBigExporters(String dollarAmount) {
		FileResource fr = new FileResource("src/basic/CSV_files/exportdata.csv");
		CSVParser parser = fr.getCSVParser();
		bigExporters(parser, dollarAmount);
	}

	public void testNumberExporters(String exportType) {
		FileResource fr = new FileResource("src/basic/CSV_files/exportdata.csv");
		CSVParser parser = fr.getCSVParser();
		int exporters = numberOfExporters(parser, exportType);
		System.out.println(exporters + " countries are exporting " + exportType);
	}

	public void testTwoExports() {
		FileResource fr = new FileResource("src/basic/CSV_files/exportdata.csv");
		CSVParser parser = fr.getCSVParser();
		listExportersTwoProducts(parser, "gold", "diamonds");
	}

	public void testCountryInfo(String someCountry) {
		FileResource fr = new FileResource("src/basic/CSV_files/exportdata.csv");
		CSVParser parser = fr.getCSVParser();
		countryInfo(parser, someCountry);
	}

	public void testExportsCoffee() {
		FileResource fr = new FileResource("src/basic/CSV_files/exportsmall.csv");
		CSVParser parser = fr.getCSVParser();
		listExporters(parser, "coffee");
	}
}
