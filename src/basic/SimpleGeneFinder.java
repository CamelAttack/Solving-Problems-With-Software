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
		String stopCodones[] = { "TAG" };
		return findProteinWithCodones(dna, "ATG", stopCodones);
	}

	public String findProteinWithCodones(String dna, String startCodone, String[] stopCodones) {
		// Format the dna to all lower case to avoid case sensitive errors
		// Only use formattedDNA throughout the method until printing or return a
		// substring
		String formattedDNA = dna.toLowerCase();
		// Looking for the Start codone
		int startIndex = formattedDNA.indexOf(startCodone.toLowerCase());
		if (startIndex == -1) {
			return "NO START CODONE FOUND!";
		}
		// Looking for the first valid stop codone
		int stopIndex = findFirstValidCodone(startIndex, formattedDNA, stopCodones);

		// If there was no valid stop codone found
		if (stopIndex == -1) {
			// Return accurate failure message
			return "NO STOP CODONE FOUND!";
		}
		return dna.substring(startIndex, stopIndex + 3);
	}

	public int printMultipleGenes(String dna, String startCodone, String[] stopCodones) {
		// Format the dna to all lower case to avoid case sensitive errors
		// Only use formattedDNA throughout the method until printing or return a
		// substring
		String formattedDNA = dna.toLowerCase();
		int geneCount = 0;
		// Create startIndex at -1 so we dont skip; the beginning of the dnaString
		int startIndex = 0;
		System.out.println("Looking for Genes from the following DNA Strand: " + dna);
		while (startIndex != -1 && startIndex < dna.length()) {
			// Looking for the Start codone
			startIndex = formattedDNA.indexOf(startCodone.toLowerCase(), startIndex);
			if (startIndex != -1) {
				// Looking for the first valid stop codone
				int stopIndex = findFirstValidCodone(startIndex, formattedDNA, stopCodones);

				// If a valid stopCodone was found
				if (stopIndex != -1) {
					// Print the gene
					System.out.println("GENE FOUND: " + dna.substring(startIndex, stopIndex + 3));
					// Set the new startIndex after the last gene found
					startIndex = stopIndex + 3;
					// Increment the gene count.
					geneCount++;
				} else {
					// Set the startIndex to -1 there are no more valid genes
					startIndex = -1;
				}
			}

		}

		return geneCount;
	}

	private int findFirstValidCodone(int startIndex, String dna, String[] codones) {
		// Set the initial codone index to negative one
		int codoneIndex = -1;
		// Loop through each codone type
		for (String stopCodone : codones) {
			// Find a valid index for this codone
			int tempIndex = findCodone(startIndex, dna, stopCodone.toLowerCase());
			// If this codone index is valid and is closer to the start index
			if (tempIndex != -1 && tempIndex < codoneIndex) {
				// Replace the current codone index with this codone's index
				codoneIndex = tempIndex;
				// If a codone index has not been found yet and this is a valid codone
			} else if (codoneIndex == -1 && tempIndex != -1) {
				// Then replace the current codone index with this codone's index
				codoneIndex = tempIndex;
			}
		}
		// Return the codone index we found; Or -1 if no codone index was found
		return codoneIndex;
	}

	private int findCodone(int startIndex, String dna, String codone) {
		int currIndex = dna.indexOf(codone, startIndex + 3);
		while (currIndex != -1) {
			if (validateProtein(startIndex, currIndex + 3)) {
				return currIndex;
			} else {
				currIndex = dna.indexOf(codone, currIndex + 1);
			}
		}
		return -1;
	}

	private boolean validateProtein(int startIndex, int stopIndex) {
		if ((stopIndex - startIndex) % 3 == 0) {
			return true;
		}
		return false;
	}

	public void testing() {
		String dnaStrings[] = { "cccatggggtttaaataataataggagagagagagagagttt", "atggggtttaaataataatag", "atgcctag", "",
				"ATGCCCTAG" };
		for (String dna : dnaStrings) {
			// System.out.println("TESTING STRAND: " + dna);
			String result = findSimpleProtein(dna);
			System.out.println("Strand " + dna + " has protein " + result);
		}
		String stopCodones[] = { "ttt" };
		String result = findProteinWithCodones(dnaStrings[0], "ccc", stopCodones);
		System.out.println("Strand " + dnaStrings[0] + " has protein " + result);
		result = findProteinWithCodones(dnaStrings[1], "rbg", stopCodones);
		System.out.println("Strand " + dnaStrings[1] + " has protein " + result);
		stopCodones[0] = "TAG";
		result = findProteinWithCodones(dnaStrings[4], "CCC", stopCodones);
		System.out.println("Strand " + dnaStrings[4] + " has protein " + result);
		stopCodones[0] = "rbg";
		result = findProteinWithCodones(dnaStrings[0], "ccc", stopCodones);
		System.out.println("Strand " + dnaStrings[0] + " has protein " + result);
	}

	public void testSearchingMultipleGenes() {
		String dnaStrings[] = { "cccatggggtttaaataataataggagagagagagagagttt", "atggggtttaaataataatag", "atgcctag", "",
				"ATGCCCTAG" };
		String stopCodones[] = { "ttt" };
		printMultipleGenes(dnaStrings[0], "ccc", stopCodones);
		printMultipleGenes(dnaStrings[1], "rbg", stopCodones);
		stopCodones[0] = "TAG";
		printMultipleGenes(dnaStrings[4], "CCC", stopCodones);
		stopCodones[0] = "rbg";
		printMultipleGenes(dnaStrings[0], "ccc", stopCodones);
	}

	public void testCountingGenes() {
		String stopCodones[] = { "TAA", "TAG", "TGA" };
		String dnaStrand = "AATGCTAACTAGCTGACTAAT";
		int geneCount = printMultipleGenes(dnaStrand, "ATG", stopCodones);
		System.out.println("The total number of genes found was: " + geneCount);
	}
}
