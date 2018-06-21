package basic;

import edu.duke.*;

public class PerimeterRunner {
	private double getPerimeter(Shape s) {
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

	private int getNumPoints(Shape s) {
		int numPoints = 0;
		for (Point currPt : s.getPoints()) {
			numPoints++;
		}
		return numPoints;
	}

	private double getAverageSideLength(Shape s) {
		double averageLength = 0.0;
		double totalLength = getPerimeter(s);
		int numSides = getNumPoints(s);
		averageLength = totalLength / numSides;
		return averageLength;
	}

	private double getLargestSide(Shape s) {
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

	private double getLargestX(Shape s) {
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

	public void testPerimeter(String fileName) {
		FileResource fr = new FileResource(fileName);
		Shape s = new Shape(fr);
		double length = getPerimeter(s);
		int numPoints = getNumPoints(s);
		double averageSideLength = getAverageSideLength(s);
		double largestSide = getLargestSide(s);
		double largestX = getLargestX(s);
		System.out.println("The perimeter is = " + length);
		System.out.println("Number of points is = " + numPoints);
		System.out.println("The average length of one side is = " + averageSideLength);
		System.out.println("The largetst side has a length = " + largestSide);
		System.out.println("The largest x value among points from this shape is = " + largestX);
	}

}
