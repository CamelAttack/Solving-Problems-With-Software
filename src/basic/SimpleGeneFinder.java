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
import java.util.ArrayList;

public class SimpleGeneFinder {

	private String[] standardStopCodones = { "TAA", "TGA", "TAG" };

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

	public StorageResource buildGeneResource(String dna, String startCodone, String[] stopCodones) {
		// Format the dna to all lower case to avoid case sensitive errors
		// Only use formattedDNA throughout the method until printing or return a
		// substring
		String formattedDNA = dna.toLowerCase();
		StorageResource geneStorage = new StorageResource();
		// Create startIndex at -1 so we dont skip; the beginning of the dnaString
		int startIndex = 0;
		// System.out.println("Looking for Genes from the following DNA Strand: " +
		// dna);
		while (startIndex != -1 && startIndex < dna.length()) {
			// Looking for the Start codone
			startIndex = formattedDNA.indexOf(startCodone.toLowerCase(), startIndex);
			if (startIndex != -1) {
				// Looking for the first valid stop codone
				int stopIndex = findFirstValidCodone(startIndex, formattedDNA, stopCodones);

				// If a valid stopCodone was found
				if (stopIndex != -1) {
					// Add the gene to the gene storage.
					geneStorage.add(dna.substring(startIndex, stopIndex + 3));
					// Set the new startIndex after the last gene found
					startIndex = stopIndex + 3;
				} else {
					// Set the startIndex to -1 because there are no more valid genes.
					// Also to prevent an infinite loop.
					startIndex = -1;
				}
			}

		}

		return geneStorage;
	}

	public double findCGRatio(String dna) {
		double ratio = 0;
		double letterCount = 0.00;
		double stringLength = (double) dna.length();
		String formattedDNA = dna.toLowerCase();
		letterCount += findLetterCount(formattedDNA, 'c');
		letterCount += findLetterCount(formattedDNA, 'g');
		ratio = letterCount / stringLength;
		return ratio;
	}

	private int findLetterCount(String someString, char someCharacter) {
		int count = 0;
		int currIndex = 0;
		while (currIndex != -1) {
			currIndex = someString.indexOf(someCharacter, currIndex);
			if (currIndex != -1) {
				count++;
				currIndex++;
			}
		}
		return count;
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

	public void testCGCount() {
		String dna = "ATGCCATAG";
		double ratio = findCGRatio(dna);
		System.out.println("The C and G ratio for the dna strand: " + dna + " is the value: " + ratio);
	}

	public void testGeneStorage() {
		String dnaStrand = "AATGCTAACTAGCTGACTAAT";
		StorageResource geneStorage = buildGeneResource(dnaStrand, "ATG", standardStopCodones);
		System.out.println("DNA Strang: " + dnaStrand);
		System.out.println("The following GENES were found in the DNA Strand.");
		for (String s : geneStorage.data()) {
			System.out.println("GENE: " + s);
		}
	}

	public void testProcessGenes() {
		FileResource fr = new FileResource("src/basic/dna_strings/GRch38dnapart.fa");
		// FileResource fr = new FileResource("src/basic/brca1line.fa");
		String dnaStrand = fr.asString();
		StorageResource geneStorage = buildGeneResource(dnaStrand, "ATG", standardStopCodones);
		processGenes(geneStorage);
	}

	public void processGenes(StorageResource geneStorage) {
		ArrayList<String> longerGenes = new ArrayList<String>();
		ArrayList<String> validRatioGenes = new ArrayList<String>();
		int greatestGeneLength = 0;
		for (String s : geneStorage.data()) {
			if (s.length() > 60) {
				longerGenes.add(s);
			}
			if (findCGRatio(s) > 0.35) {
				validRatioGenes.add(s);
			}
			if (s.length() > greatestGeneLength) {
				greatestGeneLength = s.length();
			}
		}
		printLine("There are: " + geneStorage.size() + " GENES in this storage resource.");
		printLine("There are: " + longerGenes.size() + " Genes longer than 60 characters:");
		printGenesfromArray(longerGenes, "GENE: ");
		printLine("-----------------------------------------------------------------------------");
		printLine("There are: " + validRatioGenes.size() + " Genes with a C and G ratio greater than 0.35:");
		printGenesfromArray(validRatioGenes, "GENE: ");
		printLine("-----------------------------------------------------------------------------");
		printLine("The length of the longest gene found is : " + greatestGeneLength);
	}

	private void printLine(String someMessage) {
		System.out.println(someMessage);
	}

	private void printGenesfromArray(ArrayList<String> geneList, String preMessage) {
		for (String s : geneList) {
			System.out.println(preMessage + s);
		}
	}
}
