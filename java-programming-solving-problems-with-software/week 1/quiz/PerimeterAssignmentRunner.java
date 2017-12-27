 

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public int getNumPoints(Shape s) {
        // Put code here
        int pointCounter = 0;
        for (Point punt : s.getPoints()){
            pointCounter = pointCounter + 1;
        }
        return pointCounter;
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

    public double getAverageLength(Shape s) {
        // Put code here
        double averageLength;
        double totalLength = getPerimeter(s);
        double numberOfPoints = getNumPoints(s);
        averageLength = totalLength / numberOfPoints;
        return averageLength;
    }
    

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0.0;
                
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update prevPt to be currPt
            prevPt = currPt;
            if(currDist > largestSide){
                largestSide = currDist;
            }
        }
        return largestSide;
    }

    public void testPerimeter(){
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numberOfPoints = getNumPoints(s);
        System.out.println("number of points = " + numberOfPoints);    
        double averageLength = getAverageLength(s);
        System.out.println("average length = " + averageLength);
        double largestSide = getLargestSide(s);
        System.out.println("length of largest side = " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("largest value of X = " + largestX);
    }
    
    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0;
                
        // For each point currPt in the shape,
        for (Point pt : s.getPoints()) {
            // Retrieve x coordinate
            double xVal = pt.getX();
            if(xVal > largestX){
                largestX = xVal;
            }
        }
        return largestX;
    }   

    public double getLargestPerimeterMultipleFiles() {
        double largestMultPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double shapePerimeter = getPerimeter(s);
            if(shapePerimeter > largestMultPerimeter) {
                largestMultPerimeter = shapePerimeter;
            }
        }
        return largestMultPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    
        double largestMultPerimeter = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double shapePerimeter = getPerimeter(s);
            if(shapePerimeter > largestMultPerimeter) {
                largestMultPerimeter = shapePerimeter;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter = " + largestPerimeter);        
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String fileName = getFileWithLargestPerimeter();
        System.out.println(fileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
       Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
