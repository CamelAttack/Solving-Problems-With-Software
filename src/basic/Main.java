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
		runPerimeterCalculator("src/basic/example2.txt");
	}

	public static void runPackageTest() {
		HelloWorld lesson1 = new HelloWorld();
		lesson1.runHello();
	}

	public static void runPerimeterCalculator(String fileName) {
		PerimeterRunner pr = new PerimeterRunner();
		pr.testPerimeter(fileName);
	}

	public static void printWhiteSpace() {
		System.out.println(" ");
	}
}
