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
            maxChain(p, others);
//            for (int j = 0; j < others.length - 2; j++) {
//
//                //instead of this assumption of no more than 4 collinear points try to find max chain
//
//                boolean b1 = isSlopeWIthElementsSame(p.slopeTo(others[j]), p.slopeTo(others[j + 1]));
//                boolean b2 = isSlopeWIthElementsSame(p.slopeTo(others[j]), p.slopeTo(others[j + 2]));
//                boolean b3 = isSlopeWIthElementsSame(p.slopeTo(others[j + 1]), p.slopeTo(others[j + 2]));
//                boolean equalSlope = b1 && b2 && b3;
//                if (equalSlope) {
//
//                    Point[] resultPoint = new Point[4];
//                    resultPoint[0] = p;
//                    resultPoint[1] = others[j];
//                    resultPoint[2] = others[j + 1];
//                    resultPoint[3] = others[j + 2];
//
//                    Arrays.sort(resultPoint);
//                    LineSegment e = new LineSegment(resultPoint[0], resultPoint[3]);
//                    if (!isAlreadyAdded(e)) {
//
//                        lineSegments.add(e);
//                    }
//
//                }
//
//
//            }


        }
    }

    private void maxChain(Point p, Point[] others) {

        int chainLength = 1;
        for (int j = 0; j < others.length - 1; j++) {
            boolean endOfChain = false;
            Double d1 = p.slopeTo(others[j]);
            Double d2 = p.slopeTo(others[j + 1]);
            if (Double.compare(d1, d2) == 0) {
                chainLength++;
            } else {
                endOfChain = true;
            }

            if (chainLength >= 3) {

                if (endOfChain) {
                    Point[] resultPoint = new Point[chainLength + 1];
                    for (int i = 1; i <= chainLength; i++) {
                        resultPoint[i] = others[j + 1 - i];
                    }
                    resultPoint[0] = p;
                    Arrays.sort(resultPoint);
                    LineSegment e = new LineSegment(resultPoint[0], resultPoint[chainLength]);
                    if (!isAlreadyAdded(e)) {
                        lineSegments.add(e);
                    }
                } else {

                    if (j == others.length - 2) {
                        Point[] resultPoint = new Point[chainLength + 1];

                        for (int i = 1; i <= chainLength; i++) {
                            resultPoint[i] = others[j + 2 - i];
                        }
                        resultPoint[0] = p;
                        Arrays.sort(resultPoint);
                        LineSegment e = new LineSegment(resultPoint[0], resultPoint[3]);
                        if (!isAlreadyAdded(e)) {
                            lineSegments.add(e);
                        }

                    }

                }
            }
        }
    }

    private boolean isSlopeWIthElementsSame(double d1, double d2) {
        return Double.compare(d1, d2) == 0;
    }


    private boolean isAlreadyAdded(LineSegment lineSegment) {

        for (LineSegment aLineSegment : lineSegments) {
            if (aLineSegment.toString().equals(lineSegment.toString())) {
                return true;
            }

        }
        return false;
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
