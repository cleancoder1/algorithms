import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Solver {
    private GameBoard solvedGameBoard;
    private GameBoard solvedGameBoardOfTwin;

    public Solver(Board initial) {

        MinPQ<GameBoard> priorityQuueue = new MinPQ<>();
        priorityQuueue.insert(new GameBoard(initial, null, 0));
        MinPQ<GameBoard> swapPriorityQuueue = new MinPQ<>();
        Board twin = initial.twin();
        swapPriorityQuueue.insert(new GameBoard(twin, null, 0));
        while (solvedGameBoard == null && solvedGameBoardOfTwin == null) {
            if (!priorityQuueue.isEmpty()) {


                GameBoard min1 = priorityQuueue.delMin();
                if (!min1.getBoard().isGoal()) {
                    Iterable<Board> neighbors1 = min1.getBoard().neighbors();
                    for (Board neighbor1 : neighbors1) {
                        if (min1.previous == null || (min1.previous != null && !min1.previous.board.equals(neighbor1))) {
                            GameBoard x1 = new GameBoard(neighbor1, min1, min1.getSteps() + 1);
                            if (x1.getBoard().isGoal()) {
                                solvedGameBoard = x1;
                                break;
                            }
                            priorityQuueue.insert(x1);
                        }
                    }
                }
            }
            if (!swapPriorityQuueue.isEmpty()) {
                GameBoard min = swapPriorityQuueue.delMin();
                if (!min.getBoard().isGoal()) {
                    Iterable<Board> neighbors = min.getBoard().neighbors();
                    for (Board neighbor : neighbors) {
                        if (min == null || (min.previous != null && !min.previous.board.equals(neighbor))) {
                            GameBoard x = new GameBoard(neighbor, min, min.getSteps() + 1);
                            if (x.getBoard().isGoal()) {
                                solvedGameBoardOfTwin = x;
                                break;
                            }
                            swapPriorityQuueue.insert(x);
                        }
                    }
                }
            }


        }

    }

    private static class GameBoard implements Comparable<GameBoard> {
        private Board board;
        private GameBoard previous;
        private int steps;

        public GameBoard(Board board, GameBoard previous, int steps) {
            this.board = board;
            this.previous = previous;
            this.steps = steps;


        }

        public Board getBoard() {
            return board;
        }

        public void setBoard(Board board) {
            this.board = board;
        }

        public GameBoard getPrevious() {
            return previous;
        }

        public void setPrevious(GameBoard previous) {
            this.previous = previous;
        }

        public int getSteps() {
            return steps;
        }

        @Override
        public int compareTo(GameBoard o) {
            Integer manhattanPriority = board.manhattan() + steps;
            Integer otherManhattanPriority = o.getBoard().manhattan() + o.steps;
            return manhattanPriority.compareTo(otherManhattanPriority);
        }
    }

    public boolean isSolvable() {
        return solvedGameBoard != null;
    }

    public int moves() {
        if (isSolvable()) {
            return solvedGameBoard.steps;
        }
        return -1;
    }

    public Iterable<Board> solution() {
        List<Board> boardList = new ArrayList<>();
        GameBoard lastBoard = solvedGameBoard;
        while (lastBoard.previous != null) {
            boardList.add(lastBoard.board);
            lastBoard = lastBoard.previous;

        }
        if (lastBoard.previous == null) {
            boardList.add(lastBoard.board);
        }
        Collections.reverse(boardList);
        return boardList;

    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In("C:\\development\\algorithms\\week4\\build\\resources\\test\\puzzle04.txt");
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