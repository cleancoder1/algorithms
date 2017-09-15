import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class Solver {
    public Solver(Board initial) {

    }

    public boolean isSolvable() {
        throw new IllegalArgumentException();
    }

    public int moves() {
        if (!isSolvable()) {
            System.out.println("puzzle is solvable ");
        }
        throw new IllegalArgumentException();

    }

    public Iterable<Board> solution() {
        throw new IllegalArgumentException();

    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In("../../resources/main/puzzle04.txt");
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
// solve the puzzle
        Solver solver = new Solver(initial);
// print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}