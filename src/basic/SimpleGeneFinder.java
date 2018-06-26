/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team 
 */
package basic;

import edu.duke.*;
import java.io.*;

public class SimpleGeneFinder {

	public String findSimpleProtein(String dna) {
		return findProteinWithCodones(dna, "ATG", "TAG");
	}

	public String findProteinWithCodones(String dna, String startCodone, String stopCodone) {

		String formattedDNA = dna.toLowerCase();
		// Looking for the Start codone
		int startIndex = formattedDNA.indexOf(startCodone.toLowerCase());
		if (startIndex == -1) {
			return "NO START CODONE FOUND!";
		}
		// Looking for the stop codone
		boolean searchingForValidProtein = true;
		int stopIndex = startIndex + 3;

		while (searchingForValidProtein) {
			stopIndex = formattedDNA.indexOf(stopCodone.toLowerCase(), stopIndex);
			// System.out.println("STOP INDEX: " + stopIndex);
			if (stopIndex == -1) {
				searchingForValidProtein = false;
			} else if (validateProtein(startIndex, stopIndex + 3)) {
				searchingForValidProtein = false;
			} else {
				stopIndex++;
			}
		}

		// If there was no valid stop codone found
		if (stopIndex == -1) {
			// Return accurate failure message
			return "NO STOP CODONE FOUND!";
		}
		return dna.substring(startIndex, stopIndex + 3);
	}

	public boolean validateProtein(int startIndex, int stopIndex) {
		if ((stopIndex - startIndex) % 3 == 0) {
			return true;
		}
		return false;
	}

	public boolean twoOccurences(String phraseString, String subjectString) {
		String split[] = subjectString.split(phraseString);
		if (split.length >= 2) {
			return true;
		}
		return false;
	}

	public void testWithFiles() {
		int count = 0;
		DirectoryResource dr = new DirectoryResource();
		for (File file : dr.selectedFiles()) {
			FileResource fr = new FileResource(file);
			String result = findSimpleProtein(fr.asString());
			count++;
			System.out.println("RESULTS FOR FILE " + count);
			System.out.println(result);
		}
	}

	public void testing() {
		String dnaStrings[] = { "cccatggggtttaaataataataggagagagagagagagttt", "atggggtttaaataataatag", "atgcctag", "",
				"ATGCCCTAG" };
		for (String dna : dnaStrings) {
			// System.out.println("TESTING STRAND: " + dna);
			String result = findSimpleProtein(dna);
			System.out.println("Strand " + dna + " has protein " + result);
		}

		String result = findProteinWithCodones(dnaStrings[0], "ccc", "ttt");
		System.out.println("Strand " + dnaStrings[0] + " has protein " + result);
		result = findProteinWithCodones(dnaStrings[1], "rbg", "ttt");
		System.out.println("Strand " + dnaStrings[1] + " has protein " + result);
		result = findProteinWithCodones(dnaStrings[4], "CCC", "TAG");
		System.out.println("Strand " + dnaStrings[4] + " has protein " + result);
		result = findProteinWithCodones(dnaStrings[0], "ccc", "rbg");
		System.out.println("Strand " + dnaStrings[0] + " has protein " + result);
	}

}
