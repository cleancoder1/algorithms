import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private List<LineSegment> lineSegments = new ArrayList<LineSegment>();

    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new NullPointerException();
        }
        Arrays.sort(points);
        checkDuplicates(points);

        if (points.length < 4) {
            return;
        }

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];

            Point[] others = new Point[points.length - 1];
            System.arraycopy(points, 0, others, 0, i);
            System.arraycopy(points, i + 1, others, i, others.length - i);
            Arrays.sort(others, p.slopeOrder());

            //find three points on others with same slope and add them to segment
            //Assumption  works only for input with no greater than 4 collinear points

            for (int j = 0; j < others.length - 2; j++) {

                boolean b1 = Double.compare(p.slopeTo(others[j]), p.slopeTo(others[j + 1])) == 0;
                boolean b2 = Double.compare(p.slopeTo(others[j]), p.slopeTo(others[j + 2])) == 0;
                boolean b3 = Double.compare(p.slopeTo(others[j + 1]), p.slopeTo(others[j + 2])) == 0;
                boolean equalSlope = b1 && b2 && b3;
                if (equalSlope) {

                    Point[] resultPoint = new Point[4];
                    resultPoint[0] = p;
                    resultPoint[1] = others[j];
                    resultPoint[2] = others[j + 1];
                    resultPoint[3] = others[j + 2];

                    Arrays.sort(resultPoint);
                    lineSegments.add(new LineSegment(resultPoint[0], resultPoint[3]));

                }


            }


        }
    }


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
    }                // the line segments
}
