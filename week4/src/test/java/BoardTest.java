import org.junit.Test;

public class BoardTest {


    @Test
    public void constructsBoardProperly() throws Exception {
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
        System.out.println(elements);
        Board board = new Board(elements);
        assert board.isGoal() == true;

    }
}