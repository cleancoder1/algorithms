import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class BoardTest {


    @Test
    public void constructsBoardProperly() throws Exception {
        int[][] elements = constructABoard();
        System.out.println(elements);
        Board board = new Board(elements);
        assert board.isGoal() == true;

    }

    private int[][] constructABoard() {
        int[][] elements = new int[3][3];
        int c = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                elements[i][j] = c;
                c++;
                if (i == 2 && j == 2) {
                    elements[i][j] = 0;
                }
            }
        }
        return elements;
    }

    @Test
    public void returnsNeighbors() {

        int[][] elements = constructABoard();
        Board board = new Board(elements);

        List<Board> neighborsList = makeCollection(board.neighbors());
//        left    up
//        123     123
//        456     450
//        708     786

        Board leftNeighbor = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}});
        Board upNeighbor = new Board(new int[][]{{1, 2, 3}, {4, 5, 0}, {7, 8, 6}});
        assertThat(neighborsList, Matchers.containsInAnyOrder(leftNeighbor, upNeighbor));

    }

    @Test
    public void hammingComputation() throws Exception {
        int[][] elements = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(elements);
        assertEquals(5, board.hamming());

    }


    @Test
    public void manhattanComputation() throws Exception {
        int[][] elements = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(elements);
        assertEquals(10, board.manhattan());

    }

    @Test
    public void toStringTest() throws Exception {
        int[][] elements = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board board = new Board(elements);
        String result = board.toString();
        String expected = "3" + "\n" + " 0 1 3" + "\n" + " 4 2 5" + "\n" + " 7 8 6";
        assertEquals(expected, result);

    }

    @Test
    public void returnsTwin() throws Exception {
        int[][] elements = {{0, 2}, {3,1}};
        Board board = new Board(elements);
        Board result = board.twin();
        int[][] rElements = {{0, 2}, {1,3}};
        Board expected = new Board(rElements);
        assertEquals(expected, result);

    }



    private static <E> List<E> makeCollection(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }
}