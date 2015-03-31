/**
 * Project 2
 * Name: Leslie Kim
 * E-mail: lk584@georgetown.edu
 * Instructor: Singh
 * COSC 150
 * 
 * In accordance with the class policies and Georgetown's Honor Code,
 * I certify that, with the exceptions of the lecture notes and those
 * items noted below, I have neither given nor received any assistance
 * on this project.
 * 
 * DESCRIPTION:
 * The Wall class creates an object of type Wall which is an ArrayList
 * of Cells.

 * The constructor will instantiate a Wall object with 
 * different number of Cells in the ArrayList and with various x- and
 * y-coordinates depending on the level. Level 1 has no walls, Level 2
 * has a cross section wall in the center and Level 3 has the cross
 * section as well as L-shaped walls towards the corners of the screen.
 * In the upper levels, each time an apple is eaten, another random
 * "wall" (barrier) is placed on the board.
 * 
 * Methods allow for retrieval of the ArrayList<Cells> walls and size
 * of ArrayList<Cell> walls.
 *
 */

import java.util.ArrayList;
import java.util.Random;

public class Walls {
	// Private member for each wall object:
	private Cell[][] board;										// Reference to Board object used in SnakeGUI
	private Random random;										// Random object used to placing random "walls"
	private ArrayList<Cell> walls;								// ArrayList of Cells that are walls
	
	/**
	 * CONSTRUCTOR: Takes the int level as a parameter to call
	 * initWalls(level) to initialize the layout of the walls for a
	 * specific wall. Initializes an ArrayList of Cells. 
	 * @param level
	 */
	public Walls(Cell[][] board, int level) {
		this.board = board;										// Reference board
		random = new Random();									// Initialize random
		walls = new ArrayList<Cell>();							// Initialize walls
		
		initWalls(level);										// Initialize layout of walls for level
	}
	
	/**
	 * METHOD: This method takes an int level as a parameter to
	 * initialize the layout for each level. Level 1 has no walls,
	 * Level 2 has a cross-section wall in the center of the board
	 * and Level 3 adds L-shaped walls towards the corner of the
	 * board to Level 2's walls.
	 * @param level
	 */
	public void initWalls(int level) {
		int middleCol = Board.COLS / 2 - 1;						// Determine center column of board
		int middleRow = Board.ROWS / 2 - 1;						// Determine center row of board
		
		// If level passed in is 2 or 3, we set WALL Cells on the board.
		if (level == 2 || level == 3) {
			// These 2 for loops will set a cross-section on the board of WALL cells. It also adds each WALL Cell
			// to the walls ArrayList. There is one Cell added twice to the ArrayList, but it doesn't matter for
			// the purposes of this game.
			for (int index = middleCol - 15; index < middleCol + 15; index++) {
				Cell wallCell = new Cell(index, middleRow, Cell.WALL, Cell.DEFAULT);
				walls.add(wallCell);
			}
			
			for (int index = middleRow - 15; index < middleRow + 15; index++) {
				Cell wallCell = new Cell(middleCol, index, Cell.WALL, Cell.DEFAULT);
				walls.add(wallCell);
			}
			
			// If level passed in is 3, we add more WALL cells to the board.
			if (level == 3) {
				// The following for loops will create L-shaped WALL cells that are placed towards each corner
				// of the board. It also adds each WALL Cell to the walls ArrayList.
				for (int index = 9; index < 19; index++) {
					Cell wallCell = new Cell(index, 9, Cell.WALL, Cell.DEFAULT);
					walls.add(wallCell);
				}
				for (int index = 9; index < 19; index++) {
					Cell wallCell = new Cell(9, index, Cell.WALL, Cell.DEFAULT);
					walls.add(wallCell);
				}

				for (int index = 9; index < 19; index++) {
					Cell wallCell = new Cell(index, 47, Cell.WALL, Cell.DEFAULT);
					walls.add(wallCell);
				}
				for (int index = 38; index < 48; index++) {
					Cell wallCell = new Cell(9, index, Cell.WALL, Cell.DEFAULT);
					walls.add(wallCell);
				}

				for (int index = 60; index < 70; index++) {
					Cell wallCell = new Cell(index, 9, Cell.WALL, Cell.DEFAULT);
					walls.add(wallCell);
				}
				for (int index = 9; index < 19; index++) {
					Cell wallCell = new Cell(69, index, Cell.WALL, Cell.DEFAULT);
					walls.add(wallCell);
				}

				for (int index = 60; index < 70; index++) {
					Cell wallCell = new Cell(index, 47, Cell.WALL, Cell.DEFAULT);
					walls.add(wallCell);
				}
				for (int index = 38; index < 48; index++) {
					Cell wallCell = new Cell(69, index, Cell.WALL, Cell.DEFAULT);
					walls.add(wallCell);
				}
			}
		}
	}
	
	/**
	 * GETTER: Returns the ArrayList<Cell> walls.
	 * @return walls
	 */
	public ArrayList<Cell> getWalls() {
		return walls;											// Return walls
	}
	
	/**
	 * GETTER: Returns the number of cells in walls.
	 * @return walls.size()
	 */
	public int getWallsSize() {
		return walls.size();									// Return size of walls ArrayList
	}
	
	/**
	 * METHOD: This method uses the Random object to select 2 random
	 * ints to use as the coordinates for the new WALL Cell. It checks
	 * each randomly selected ordered pair to make sure the Cell is
	 * EMPTY on the Board object and randomly selects a new ordered
	 * pair if the Cell is not EMPTY. It sets the randomly selected
	 * coordinates as the x- and y-coordinates of the WALL Cell and
	 * places it on the Board object.
	 * @param none
	 * @return none 
	 */
	public void placeRandomBarrier() {
		boolean isEmpty = false;								// Used to check if Cell is EMPTY
		int barrierXCoord = 0;									// New barrier Cell xCoord
		int barrierYCoord = 0;									// New barrier Cell yCoord
		
		// While the Cell of the randomly chosen coordinates is not EMPTY, continue to randomly select coordinates.
		while (!isEmpty) {
			barrierXCoord = random.nextInt(Board.COLS - 1);		// Randomly select an x-coordinate
			barrierYCoord = random.nextInt(Board.ROWS - 1);		// Randomly select an y-coordinate
			
			// Check the Cell on the Board of the randomly selected x- and y-coordinates.
			int cellToCheckType = board[barrierYCoord][barrierXCoord].getCellType();
			
			// If the randomly selected Cell is EMPTY, set isEmpty to true to stop the while loop.
			if (cellToCheckType == Cell.EMPTY)
				isEmpty = true;									// Set isEmpty to true
		}
		
		// Instantiates new Cell to the randomly selected coordinates, type WALL and direction DEFAULT.
		Cell newBarrier = new Cell(barrierXCoord, barrierYCoord, Cell.WALL, Cell.DEFAULT);
		// Set the Cell on the Board to the barrier Cell private members xCoord, yCoord, cellType and direction.
		board[barrierYCoord][barrierXCoord].setCell(newBarrier);
		
		walls.add(newBarrier);
	}
}