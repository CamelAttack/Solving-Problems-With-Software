package basic;

import edu.duke.*;
import java.io.File;
import java.util.Iterator;

public class PerimeterAssignmentRunner {

	private String fileDatasetArray[];
	private String fileExampleArray[];
	private String fileSource = "src/basic/perimeter_quiz/";
	private String fileExampleNames[] = {};

	public PerimeterAssignmentRunner() {
		//buildExampleStringArray();
	}

	private void buildExampleStringArray() {
		fileExampleArray = new String[4];
		for (int i = 0; i < 4; i++) {
			StringBuilder sb = new StringBuilder(fileSource);
			sb.append("example");
			sb.append(i + 1);
			sb.append(".txt");
			fileExampleArray[i] = sb.toString();
			System.out.println("File Example String " + i + " = " + fileExampleArray[i]);
		}
	}

	private void buildDatasetStringArray() {

	}

	public double getPerimeter(Shape s) {
		// Start with totalPerim = 0
		double totalPerim = 0.0;
		// Start wth prevPt = the last point
		Point prevPt = s.getLastPoint();
		// For each point currPt in the shape,
		for (Point currPt : s.getPoints()) {
			// Find distance from prevPt point to currPt
			double currDist = prevPt.distance(currPt);
			// Update totalPerim by currDist
			totalPerim = totalPerim + currDist;
			// Update prevPt to be currPt
			prevPt = currPt;
		}
		// totalPerim is the answer
		return totalPerim;
	}

	public int getNumPoints(Shape s) {
		int numPoints = 0;
		for (Point currPt : s.getPoints()) {
			numPoints++;
		}
		return numPoints;
	}

	public double getAverageSideLength(Shape s) {
		double averageLength = 0.0;
		double totalLength = getPerimeter(s);
		int numSides = getNumPoints(s);
		averageLength = totalLength / numSides;
		return averageLength;
	}

	public double getLargestSide(Shape s) {
		// Start with largestSide = 0
		double largestSide = 0.0;
		// Start wth prevPt = the last point
		Point prevPt = s.getLastPoint();
		// For each point currPt in the shape,
		for (Point currPt : s.getPoints()) {
			// Find distance from prevPt point to currPt
			double currDist = prevPt.distance(currPt);
			// Check if it's larger than the current largest side
			if (currDist > largestSide) {
				// If it is update the largest side to the currDist
				largestSide = currDist;
			}
			// Update prevPt to be currPt
			prevPt = currPt;
		}
		// totalPerim is the answer
		return largestSide;
	}

	public double getLargestX(Shape s) {
		double largestX = 0.0;
		for (Point currPt : s.getPoints()) {
			// Find distance from prevPt point to currPt
			double currX = currPt.getX();
			if (currX > largestX) {
				largestX = currX;
			}
		}
		return largestX;
	}

	public double getLargestPerimeterMultipleFiles() {
		// Put code here
		double largestPerimeter = 0.0;
		DirectoryResource dr = new DirectoryResource();
		for (File file : dr.selectedFiles()) {
			FileResource fr = new FileResource(file);
			Shape s = new Shape(fr);
			double currentPerimeter = getPerimeter(s);
			if (currentPerimeter > largestPerimeter) {
				largestPerimeter = currentPerimeter;
			}
		}
		return largestPerimeter;
	}

	public String getFileWithLargestPerimeter() {
		// Put code here
		File largestFile = null; // replace this code
		double largestPerimeter = 0.0;
		DirectoryResource dr = new DirectoryResource();
		for (File file : dr.selectedFiles()) {
			FileResource fr = new FileResource(file);
			Shape s = new Shape(fr);
			double currentPerimeter = getPerimeter(s);
			if (currentPerimeter > largestPerimeter) {
				largestPerimeter = currentPerimeter;
				largestFile = file;
			}
		}
		return largestFile.getName();
	}

	public void testPerimeter() {
		FileResource fr = new FileResource();
		Shape s = new Shape(fr);
		double length = getPerimeter(s);
		System.out.println("perimeter = " + length);
	}

	public void testPerimeterMultipleFiles() {
		System.out.println("The largest perimeter from the selected files is: " + getLargestPerimeterMultipleFiles());
	}

	public void testFileWithLargestPerimeter() {
		System.out.println("The file with the largest perimeter is: " + getFileWithLargestPerimeter());
	}

	// This method creates a triangle that you can use to test your other methods
	public void triangle() {
		Shape triangle = new Shape();
		triangle.addPoint(new Point(0, 0));
		triangle.addPoint(new Point(6, 0));
		triangle.addPoint(new Point(3, 6));
		for (Point p : triangle.getPoints()) {
			System.out.println(p);
		}
		double peri = getPerimeter(triangle);
		System.out.println("perimeter = " + peri);
	}

	// This method prints names of all files in a chosen folder that you can use to
	// test your other methods
	public void printFileNames() {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			System.out.println(f);
		}
	}

}
