public class Board {
    public Board(int[][] blocks)    {       // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)

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
        throw new IllegalArgumentException();
    }            // is this board the goal board?

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
