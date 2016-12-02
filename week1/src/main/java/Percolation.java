import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // create n-by-n grid, with all sites blocked
    private Site[][] grid;
    private int n;
    private WeightedQuickUnionUF weightedQuickUnionFindForPercolation;
    private boolean rootTopStatus[];
    private boolean rootBottomStatus[];
    private boolean percolated;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        grid = new Site[n][n];
        rootTopStatus = new boolean[n * n];
        rootBottomStatus = new boolean[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = Site.CLOSED;
            }
        }
        weightedQuickUnionFindForPercolation = new WeightedQuickUnionUF(n * n);
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
        int positionInGrid = positionInGrid(row, col);

        grid[row - 1][col - 1] = Site.OPEN;


        //check its neighbors and issue a union  if they are open
        boolean[] topNeighborRootPosition = connectTwoSites(row - 1, col, row, col);
        boolean[] bottomNeighborRootPosition = connectTwoSites(row + 1, col, row, col);
        boolean[] leftNeighborRootPosition = connectTwoSites(row, col - 1, row, col);
        boolean[] rightNeighborRootPosiiton = connectTwoSites(row, col + 1, row, col);
        int rootPosition = weightedQuickUnionFindForPercolation.find(positionInGrid);

        if (row == 1) {
            rootTopStatus[rootPosition] = true;
        }
        if (row == n) {
            rootBottomStatus[rootPosition] = true;
        }


        //merge information after opening site about
        int currentNodeRoot = rootPosition;
        rootTopStatus[currentNodeRoot] = topNeighborRootPosition[0] || bottomNeighborRootPosition[0] || leftNeighborRootPosition[0] || rightNeighborRootPosiiton[0] || rootTopStatus[currentNodeRoot];
        rootBottomStatus[currentNodeRoot] = topNeighborRootPosition[1] || bottomNeighborRootPosition[1] || leftNeighborRootPosition[1] || rightNeighborRootPosiiton[1] || rootBottomStatus[currentNodeRoot];

        //system percolates if there is any root which has both connectToTop and connectToBottom as true
        if (!percolates()) {
            percolated = rootTopStatus[currentNodeRoot] == true && rootBottomStatus[currentNodeRoot] == true;
        }

    }


    private boolean[] connectTwoSites(int row1, int col1, int row2, int col2) {
        boolean[] connectedToTopBottom = new boolean[2];
        if (isValidCoordinate(row1, col1) && isOpen(row1, col1)) {
            int i = weightedQuickUnionFindForPercolation.find(positionInGrid(row1, col1));
            connectedToTopBottom[0] = rootTopStatus[i];
            connectedToTopBottom[1] = rootBottomStatus[i];
            weightedQuickUnionFindForPercolation.union(positionInGrid(row1, col1), positionInGrid(row2, col2));
            return connectedToTopBottom;
        }
        connectedToTopBottom[0] = false;
        connectedToTopBottom[1] = false;
        return connectedToTopBottom;
    }

    private boolean isValidCoordinate(int row, int col) {
        return row <= n && col <= n && row >= 1 && col >= 1;
    }

    private int positionInGrid(int rowNumber, int colNumber) {

        return (rowNumber - 1) * n + colNumber - 1;
    }


    public boolean isOpen(int row, int col) {
        if (!isValidCoordinate(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        return grid[row - 1][col - 1] != Site.CLOSED;
    }


    public boolean isFull(int row, int col) {
        if (!isValidCoordinate(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int i = weightedQuickUnionFindForPercolation.find(positionInGrid(row, col));


        return rootTopStatus[i];
    }


    public boolean percolates() {
        return percolated;
    }


    public static void main(String[] args) {

    }

    private enum Site {
        OPEN, CLOSED
    }
}