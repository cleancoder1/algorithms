public class Board {

    private int[][] elements;
    private int n;

    public Board(int[][] blocks) {       // construct a board from an n-by-n array of blocks
        // (where blocks[i][j] = block in row i, column j)
        elements = blocks;
        n = blocks.length;
    }

    public int dimension() {
        throw new IllegalArgumentException();

    }               // board dimension n

    public int hamming() {

        throw new IllegalArgumentException();

    }               // number of blocks out of place

    public int manhattan() {
        throw new IllegalArgumentException();
    }             // sum of Manhattan distances between blocks and goal

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
    }            // is this board the goal board?

    private boolean isLastElement(int i, int j) {
        return i == n - 1 && j == n - 1;
    }

    public Board twin() {
        throw new IllegalArgumentException();
    }            // a board that is obtained by exchanging any pair of blocks

    public boolean equals(Object y) {
        throw new IllegalArgumentException();

    }      // does this board equal y?

    public Iterable<Board> neighbors() {
        throw new IllegalArgumentException();
    }   // all neighboring boards

    public String toString() {
        throw new IllegalArgumentException();
    }            // string representation of this board (in the output format specified below)

    public static void main(String[] args) {

    }
}
