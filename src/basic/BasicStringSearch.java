package basic;

import edu.duke.FileResource;
import edu.duke.URLResource;

public class BasicStringSearch {

	public boolean twoOccurences(String phraseString, String subjectString) {
		String split[] = subjectString.split(phraseString);
		if (split.length >= 2) {
			return true;
		}
		return false;
	}

	public int howMany(String subsString, String subjectString) {
		String split[] = subjectString.split(subsString);
		return split.length;
	}

	public static void urlFinder() {
		String urlString = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
		URLResource url = new URLResource(urlString);
		for (String line : url.lines()) {
			String formattedLine = line.toLowerCase();
			int linkIndex = formattedLine.indexOf("<a href=");
			if (linkIndex > -1 && formattedLine.indexOf("youtube.com") > -1) {
				int startIndex = linkIndex + 9;
				int stopIndex = formattedLine.indexOf("\"", startIndex);
				System.out.println(line.substring(startIndex, stopIndex));
			}
		}
	}

	public void findAbc(String input) {
		int index = input.indexOf("abc");
		while (true) {
			if (index == -1 || index >= input.length() - 3) {
				break;
			}
			// System.out.println("INDEX Found: " + index);
			String found = input.substring(index + 1, index + 4);
			System.out.println(found);
			index = input.indexOf("abc", index + 3);
			// System.out.println("INDEX Post Update: " + index);
		}
	}

	public void testABC() {
		findAbc("abcabcabcabca");
	}

	public void testDNAForCodons() {
		FileResource fr = new FileResource("src/basic/dna_strings/GRch38dnapart.fa");
		String dnaStrand = fr.asString();
		int count = howMany(dnaStrand, "CTG");
		System.out.println("CTG appears: " + count + " times in the dna strand.");
	}

	public void testHowMany() {
		String subjectString = "Bananas";
		String subString = "a";
		System.out.println("The Subject String: " + subjectString + " contains the Substring: " + subString
				+ " this number of times: " + howMany(subString, subjectString));
	}

}
