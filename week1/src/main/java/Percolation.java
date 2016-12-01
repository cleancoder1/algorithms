import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // create n-by-n grid, with all sites blocked
    private Site[][] grid;
    private int n;
    private WeightedQuickUnionUF weightedQuickUnionFindForPercolation;
    private boolean systemPercolates;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        grid = new Site[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                grid[i][j] = Site.CLOSED;
            }
        }
        weightedQuickUnionFindForPercolation = new WeightedQuickUnionUF(n * n + 2);
    }

    // open Site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isValidCoordinate(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        //site already open nothing to do
        if (isOpen(row, col)) {
            return;
        }
        //top row
        if (row == 1) {
            weightedQuickUnionFindForPercolation.union(0, positionInGrid(row, col));
        }

        grid[row][col] = Site.OPEN;


        //check its neighbors and issue a union  if they are open
        connectTwoSites(row - 1, col, row, col);
        connectTwoSites(row + 1, col, row, col);
        connectTwoSites(row, col - 1, row, col);
        connectTwoSites(row, col + 1, row, col);


        if (!systemPercolates) {
            //check if any bottom row became full and set the percolation flag
            for (int j = 1; j <= n; j++) {
                if (isFull(n, j)) {
                    systemPercolates = true;
                    return;
                }
            }
        }


    }


    private void connectTwoSites(int row1, int col1, int row2, int col2) {
        if (isValidCoordinate(row1, col1) && isOpen(row1, col1)) {
            weightedQuickUnionFindForPercolation.union(positionInGrid(row1, col1), positionInGrid(row2, col2));

        }
    }

    private boolean isValidCoordinate(int row, int col) {
        return row <= n && col <= n && row >= 1 && col >= 1;
    }

    private int positionInGrid(int rowNumber, int colNumber) {

        return (rowNumber - 1) * n + colNumber;
    }


    public boolean isOpen(int row, int col) {
        if (!isValidCoordinate(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        return grid[row][col] != Site.CLOSED;
    }


    public boolean isFull(int row, int col) {
        if (!isValidCoordinate(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if (weightedQuickUnionFindForPercolation.find(positionInGrid(row, col)) == weightedQuickUnionFindForPercolation.find(0)) {
            return true;
        }
        return false;
    }


    public boolean percolates() {

        return systemPercolates;
    }


    public static void main(String[] args) {

    }

    private enum Site {
        OPEN, CLOSED
    }
}