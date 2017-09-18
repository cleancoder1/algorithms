import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private int[][] elements;
    private int n;

    public Board(int[][] blocks) {
        elements = blocks;
        n = blocks.length;
    }

    public int dimension() {
        return n;

    }

    public int hamming() {
        int count = 1;
        int hamming = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isLastElement(i, j) && elements[i][j] != count) {
                    hamming++;
                }
                count++;
            }
        }
        return hamming;
    }

    public int manhattan() {
        int manhattan = 0;
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isLastElement(i, j)) {
                    return manhattan;
                }
                int[] ints = positionOfElement(count);
                int xposition = ints[0];
                int yposition = ints[1];
                manhattan += Math.abs(xposition - i) + Math.abs(yposition - j);
                count++;
            }
        }

        return manhattan;
    }


    public boolean isGoal() {
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isLastElement(i, j) && elements[i][j] != 0) {
                    return false;
                }
                if (!isLastElement(i, j) && elements[i][j] != count) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    private boolean isLastElement(int i, int j) {
        return i == n - 1 && j == n - 1;
    }

    public Board twin() {
        throw new IllegalArgumentException();
    }            // a board that is obtained by exchanging any pair of blocks

    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        }
        Board other = (Board) y;
        return Arrays.deepEquals(this.elements, other.elements);

    }

    public Iterable<Board> neighbors() {

        List<Board> neighbors = new ArrayList();
        int xposition = positionOfElement(0)[0];
        int yposition = positionOfElement(0)[1];
        if (xposition > 0) {
            Board upNeighbor = swapBoard(xposition, yposition, xposition - 1, yposition);
            neighbors.add(upNeighbor);

        }
        if (yposition > 0) {
            Board rightNeighbor = swapBoard(xposition, yposition, xposition, yposition - 1);
            neighbors.add(rightNeighbor);

        }
        if (xposition < n-1) {
            Board downNeighbor = swapBoard(xposition, yposition, xposition + 1, yposition);
            neighbors.add(downNeighbor);

        }
        if (yposition < n-1) {
            Board leftNeighbor = swapBoard(xposition, yposition, xposition, yposition + 1);
            neighbors.add(leftNeighbor);

        }
        return neighbors;

    }

    private Board swapBoard(int x1, int y1, int x2, int y2) {
        //assert i ,j ,k , l are valid co-ordinates
        int[][] elementCopy = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == x1 && j == y1) {
                    elementCopy[i][j] = elements[x2][y2];
                } else if (i == x2 && j == y2) {
                    elementCopy[i][j] = elements[x1][y1];
                } else {
                    elementCopy[i][j] = elements[i][j];
                }

            }
        }
        return new Board(elementCopy);
    }

    private int[] positionOfElement(int value) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (elements[i][j] == value) {
                    int[] position = {i, j};

                    return position;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public String toString() {
        throw new IllegalArgumentException();
    }            // string representation of this board (in the output format specified below)

    public static void main(String[] args) {

    }
}
