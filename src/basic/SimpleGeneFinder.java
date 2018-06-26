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
		String formattedDNA = dna.toLowerCase();

		int start = formattedDNA.indexOf("atg");
		if (start == -1) {
			return "NO START CODONE FOUND!";
		}
		int stop = formattedDNA.indexOf("tag", start + 3);
		if (stop == -1) {
			return "NO STOP CODONE FOUND!";
		}
		if ((stop - start) % 3 == 0) {
			return dna.substring(start, stop + 3);
		} else {
			return "INVALID PROTEIN GROUP!";
		}
	}

	public String findProteinWithCodones(String dna, String startCodone, String stopCodone) {
		String result = "";
		String formattedDNA = dna.toLowerCase();
		int startIndex = formattedDNA.indexOf(startCodone.toLowerCase());
		if (startIndex == -1) {
			return "NO START CODONE FOUND!";
		}
		int stopIndex = formattedDNA.indexOf(stopCodone.toLowerCase(), startIndex + 3);
		if (stopIndex == -1) {
			return "NO STOP CODONE FOUND!";
		}
		int proteinCheck = (stopIndex - startIndex) % 3;
		if (proteinCheck != 0) {
			return "INVALID PROTEIN LENGTH!";
		}
		return dna.substring(startIndex, stopIndex + 3);
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
			String result = findSimpleProtein(dna);
			System.out.println("Strand " + dna + " has protein " + result);
		}

		String result = findProteinWithCodones(dnaStrings[0], "ccc", "ttt");
		System.out.println("Strand " + dnaStrings[0] + " has protein " + result);
		result = findProteinWithCodones(dnaStrings[1], "rbg", "ttt");
		System.out.println("Strand " + dnaStrings[1] + " has protein " + result);
		result = findProteinWithCodones(dnaStrings[4], "CCC", "TAG");
		System.out.println("Strand " + dnaStrings[4] + " has protein " + result);
	}

}
