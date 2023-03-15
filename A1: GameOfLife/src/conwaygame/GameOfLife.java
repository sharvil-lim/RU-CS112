package conwaygame;
import java.util.ArrayList;
/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean  DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {
        StdIn.setFile(file);
        int r = StdIn.readInt();
        int c = StdIn.readInt();
        grid = new boolean[r][c];
        
        totalAliveCells = 0;

        for(int i = 0; i < r; i++) {
            for(int k = 0; k < c; k++) {
                boolean result = StdIn.readBoolean();
                if (result) {
                    grid[i][k] = ALIVE;
                    totalAliveCells++;
                } else {
                    grid[i][k] = DEAD;
                }
            }
        }  
    }

    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {
        return grid[row][col];
    }

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {
        return (totalAliveCells > 0);
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors (int row, int col) {
        int counter = 0;
        int numOfRows = grid.length;
        int numOfColumns = grid[0].length;
        int above, below, left, right;

        if(row == 0) {
            above = numOfRows - 1;
        } else {
            above = row - 1;
        }

        if(row == numOfRows - 1) {
            below = 0;
        } else {
            below = row + 1;
        }

        if(col == 0) {
            left = numOfColumns - 1;
        } else {
            left = col - 1;
        }

        if(col == numOfColumns - 1) {
            right = 0;
        } else {
            right = col + 1;
        }

        if(getCellState(above, left)) {
            counter++;
        }
        if(getCellState(above, col)) {
            counter++;
        }
        if(getCellState(above, right)) {
            counter++;
        }

        if(getCellState(row, left)) {
            counter++;
        }
        if(getCellState(row, right)) {
            counter++;
        }

        if(getCellState(below, left)) {
            counter++;
        }
        if(getCellState(below, col)) {
            counter++;
        }
        if(getCellState(below, right)) {
            counter++;
        }

        return counter;
    }

    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () {
        int rowLength = grid.length;
        int colLength = grid[0].length;
        boolean[][] newGrid = new boolean[rowLength][colLength];

        for(int row = 0; row < rowLength; row++) {
            for(int col = 0; col < colLength; col++) {
                boolean finalCellState;
                boolean intialCellState = getCellState(row, col);
                int numOfAliveNeighbors = numOfAliveNeighbors(row, col);
                
                if(intialCellState) {
                    if(numOfAliveNeighbors <= 1) {
                        finalCellState = DEAD; 
                    } else if (numOfAliveNeighbors >= 4) {
                        finalCellState = DEAD;
                    } else {
                        finalCellState = ALIVE;
                    }
                } else {
                    if (numOfAliveNeighbors == 3) {
                        finalCellState = ALIVE;
                    } else {
                        finalCellState = DEAD;
                    }
                }

                newGrid[row][col] = finalCellState;
            }
        }

        return newGrid;
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {
        totalAliveCells = 0;
        grid = computeNewGrid();

        for (boolean[] booleans : grid) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    totalAliveCells++;
                }
            }
        }
    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {
        for (int i = 0; i < n; i++) {
            nextGeneration();
        }
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {
        int numOfRows = grid.length;
        int numOfColumns = grid[0].length;
        WeightedQuickUnionUF wuf = new WeightedQuickUnionUF(numOfRows, numOfColumns);

        for(int row = 0; row < numOfRows; row++) {
            for(int col = 0; col < numOfColumns; col++) {
                if (grid[row][col]) {
                    int above, below, left, right;

                    if(row == 0) {
                        above = numOfRows - 1;
                    } else {
                        above = row - 1;
                    }

                    if(row == numOfRows - 1) {
                        below = 0;
                    } else {
                        below = row + 1;
                    }

                    if(col == 0) {
                        left = numOfColumns - 1;
                    } else {
                        left = col - 1;
                    }

                    if(col == numOfColumns - 1) {
                        right = 0;
                    } else {
                        right = col + 1;
                    }

                    if(getCellState(above, left)) {
                        wuf.union(row, col, above, left);
                    }
                    if(getCellState(above, col)) {
                        wuf.union(row, col, above, col);
                    }
                    if(getCellState(above, right)) {
                        wuf.union(row, col, above, right);
                    }

                    if(getCellState(row, left)) {
                        wuf.union(row, col, row, left);
                    }
                    if(getCellState(row, right)) {
                        wuf.union(row, col, row, right);
                    }

                    if(getCellState(below, left)) {
                        wuf.union(row, col, below, left);
                    }
                    if(getCellState(below, col)) {
                        wuf.union(row, col, below, col);
                    }
                    if(getCellState(below, right)) {
                        wuf.union(row, col, below, right);
                    }
                }
            }
        }

        int communities = 0;
        ArrayList<Integer> rootsLog = new ArrayList<Integer>();

        for(int row = 0; row < numOfRows; row++) {
            for(int col = 0; col < numOfColumns; col++) {
                if(grid[row][col]){
                    boolean alreadyExists = false;
                    int root = wuf.find(row, col);
                    for (int tempRoot : rootsLog) {
                        if (root == tempRoot) {
                            alreadyExists = true;
                        }
                    }
                    if (!alreadyExists) {
                        rootsLog.add(root);
                    }
                }
            }
        }
        return rootsLog.size();
    }
}

