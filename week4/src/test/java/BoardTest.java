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

    private static <E> List<E> makeCollection(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }
}