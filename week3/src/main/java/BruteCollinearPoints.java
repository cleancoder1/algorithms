import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private List<LineSegment> lineSegments = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) {
        Point[] pointsClone = points.clone();
        if (pointsClone == null) {
            throw new NullPointerException();
        }
        Arrays.sort(pointsClone);
        checkDuplicates(pointsClone);

        if (pointsClone.length < 4) {
            return;
        }

        for (int i = 0; i < pointsClone.length - 3; i++) {
            for (int j = i + 1; j < pointsClone.length - 2; j++) {
                for (int k = j + 1; k < pointsClone.length - 1; k++) {
                    for (int l = k + 1; l < pointsClone.length; l++) {
                        LineSegment lineSegment = possibleLineSegment(pointsClone[i], pointsClone[j], pointsClone[k], pointsClone[l]);
                        if (lineSegment != null) {
                            lineSegments.add(lineSegment);
                        }
                    }
                }
            }
        }


    }   // finds all line segments containing 4 points

    private void checkDuplicates(Point[] points) {

        if (points.length < 2) {
            return;
        }


        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

    }

    public int numberOfSegments() {
        return lineSegments.size();
    }       // the number of line segments

    public LineSegment[] segments() {


        return lineSegments.toArray(new LineSegment[lineSegments.size()]);

    }


    private LineSegment possibleLineSegment(Point p1, Point p2, Point p3, Point p4) {

        double slope1To2 = p1.slopeTo(p2);
        double slope2To3 = p2.slopeTo(p3);
        double slope3To4 = p3.slopeTo(p4);

        boolean isCollinear = Double.compare(slope1To2, slope2To3) == 0 && Double.compare(slope2To3, slope3To4) == 0 && Double.compare(slope1To2, slope3To4) == 0;
        if (!isCollinear) {
            return null;
        }

        return new LineSegment(p1, p4);

    }
}
