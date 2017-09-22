import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Solver {
    private GameBoard solvedGameBoard;
    private GameBoard solvedGameBoardOfTwin;

    public Solver(final Board initial) {

        if (initial == null) {
            throw new NullPointerException();
        }

        MinPQ<GameBoard> priorityQueue = new MinPQ<>();
        priorityQueue.insert(new GameBoard(initial, null, 0));
        MinPQ<GameBoard> swapPriorityQueue = new MinPQ<>();
        Board twin = initial.twin();
        swapPriorityQueue.insert(new GameBoard(twin, null, 0));
        while (solvedGameBoard == null && solvedGameBoardOfTwin == null) {
            solvedGameBoard = solvedGame(priorityQueue);
            solvedGameBoardOfTwin = solvedGame(swapPriorityQueue);
        }

    }

    private GameBoard solvedGame(MinPQ<GameBoard> priorityQueue) {
        if (!priorityQueue.isEmpty()) {
            GameBoard promisingGameBoard = priorityQueue.delMin();
            if (!promisingGameBoard.getBoard().isGoal()) {
                Iterable<Board> neighbors = promisingGameBoard.getBoard().neighbors();
                for (Board neighbor : neighbors) {
                    if (promisingGameBoard.previous == null || (promisingGameBoard.previous != null && !promisingGameBoard.previous.board.equals(neighbor))) {
                        GameBoard neighborGameBoard = new GameBoard(neighbor, promisingGameBoard, promisingGameBoard.getSteps() + 1);
                        if (neighborGameBoard.getBoard().isGoal()) {
                            return neighborGameBoard;
                        }
                        priorityQueue.insert(neighborGameBoard);
                    }
                }
            } else {
                return promisingGameBoard;
            }
        }
        return null;
    }

    private static class GameBoard implements Comparable<GameBoard> {
        private final Board board;
        private final GameBoard previous;
        private final int steps;

        public GameBoard(Board board, GameBoard previous, int steps) {
            this.board = board;
            this.previous = previous;
            this.steps = steps;


        }

        public Board getBoard() {
            return board;
        }


        public int getSteps() {
            return steps;
        }

        @Override
        public int compareTo(GameBoard other) {
            Integer manhattanPriority = board.manhattan() + steps;
            Integer otherManhattanPriority = other.getBoard().manhattan() + other.steps;
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
        In in = new In("C:\\development\\algorithms\\week4\\build\\resources\\test\\puzzle00.txt");
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