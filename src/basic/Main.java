package basic;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Yo what up chicken. - Allegra Frank.");
		printWhiteSpace();

		// Lesson Basics with their Package
		// runPackageTest();
		// printWhiteSpace();
		// Perimeter Assignment
		// runPerimeterCalculator("src/basic/perimeter_quiz/datatest4.txt");
		// runPerimeterQuizAssignment();
		// runSimpleGeneFinder();
		runGeneFinderWithFiles();
	}

	public static void runPackageTest() {
		HelloWorld lesson1 = new HelloWorld();
		lesson1.runHello();
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
		geneFind.testing();
	}

	public static void runGeneFinderWithFiles() {
		SimpleGeneFinder geneFind = new SimpleGeneFinder();
		//geneFind.testWithFiles();
		//printWhiteSpace();
		if (geneFind.twoOccurences("by", "A story by Abby Long")) {
			System.out.println("Two occurences of the phrase string were found in the subject string!");
		}
	}

	public static void printWhiteSpace() {
		System.out.println(" ");
	}

}
