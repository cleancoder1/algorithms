import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by rparsi on 9/14/2017.
 */
public class SolverTest {


    @Test
    public void name() throws Exception {
//TODO figure out relative paths, test runner working directory


        In in = new In("C:\\development\\algorithms\\week4\\build\\resources\\test\\puzzle04.txt");
//        In in = new In("C:\\development\\algorithms\\week4\\build\\resources\\test\\puzzle04.txt");

        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
// solve the puzzle
        Solver solver = new Solver(initial);
        assertEquals(solver.moves(), 4);


    }
}