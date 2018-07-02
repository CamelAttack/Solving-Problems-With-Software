package basic;

import edu.duke.*;

public class Main {

	public static void main(String[] args) {
		// Just for fun.
		System.out.println("Yo what up chicken. - Allegra Frank.");
		printWhiteSpace();
		testCSVExports();
		// runBasicStringSearch();
		// printWhiteSpace();
		// runSimpleGeneFinder();
		// runGeneProcessor();
	}

	public static void testCSVExports() {
		WhichCountriesExport exporter = new WhichCountriesExport();
		exporter.whoExportsCoffee();
		printWhiteSpace();
		exporter.readCountryInfo();
	}

	public static void runPerimeterCalculator(String fileName) {
		PerimeterRunner pr = new PerimeterRunner();
		pr.testPerimeter(fileName);
	}

	public static void runPerimeterQuizAssignment() {
		PerimeterAssignmentRunner par = new PerimeterAssignmentRunner();
		par.testPerimeterMultipleFiles();
		par.testFileWithLargestPerimeter();
	}

	public static void runSimpleGeneFinder() {
		SimpleGeneFinder geneFind = new SimpleGeneFinder();
		// geneFind.testing();
		// System.out.println(geneFind.findProteinWithCodones("AAATGCCCTAACTAGATTAAGAAACC",
		// "ATG", "TAA"));
		geneFind.testGeneStorage();
		printWhiteSpace();
		geneFind.testCGCount();
		printWhiteSpace();
	}

	public static void runBasicStringSearch() {
		BasicStringSearch stringSearch = new BasicStringSearch();
		// stringSearch.testHowMany();
		// stringSearch.urlFinder();
		stringSearch.testDNAForCodons();
		printWhiteSpace();
		// stringSearch.testABC();
	}

	public static void runGeneProcessor() {
		SimpleGeneFinder geneFind = new SimpleGeneFinder();
		geneFind.testProcessGenes();
	}

	public static void printWhiteSpace() {
		System.out.println(" ");
	}

}
