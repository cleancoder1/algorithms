import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private Point[] points;
    List<LineSegment> lineSegments = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) {

        if (points == null)
            throw new NullPointerException();

        Arrays.sort(points);
        this.points = points;

        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        LineSegment lineSegment = possibleLineSegment(points[i], points[j], points[k], points[l]);
                        if (lineSegment != null) {
                            lineSegments.add(lineSegment);
                        }


                    }
                }
            }
        }


    }   // finds all line segments containing 4 points

    public int numberOfSegments() {
        return lineSegments.size();
    }       // the number of line segments

    public LineSegment[] segments() {


        return lineSegments.toArray(new LineSegment[lineSegments.size()]);

    }


    private LineSegment possibleLineSegment(Point p1, Point p2, Point p3, Point p4) {

        double slope1 = p1.slopeTo(p2);
        double slope2 = p3.slopeTo(p4);

        boolean isCollinear = Double.compare(slope1, slope2) == 0;
        if (!isCollinear) {

            return null;
        }

        return new LineSegment(p1, p4);

    }
}
