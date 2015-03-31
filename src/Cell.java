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
 * The Cell class creates an object of type Cell with private members
 * xCoord, yCoord, cellType and direction. The xCoord and yCoord are
 * int x- and y-coordinates of an ordered pair. On a rectangular plane,
 * we assume that the origin (0, 0) is at the top left corner. (i.e.
 * On a Cartesian plane, the positive x-direction is to the right and
 * the positive y-direction is down.) The cellType is an int that 
 * represents a type of cell (EMPTY(0), SNAKE(1), APPLE(2), WALL(3)).
 * The direction is an int value that represents the direction of a
 * SNAKE Cell (NORTH(0), EAST(1), SOUTH(2), WEST(3)) or uses DEFAULT(-1)
 * for any other type of Cell.  
 *
 * There class contains a default constructor, 2 overloaded constructors
 * and a copy constructor.
 * 
 * The default constructor calls an overloaded constructor which takes
 * in 4 int parameters instantiating a Cell object with xCoord = 0,
 * yCoord = 0, cellType = EMPTY and direction = DEFAULT.
 * 
 * The overloaded constructor which takes in 2 int parameters calls the
 * overloaded constructor which takes in 4 int parameters instantiating
 * a Cell object with xCoord and yCoord initialized to the int parameters
 * passed in, cellType = EMPTY and direction = DEFAULT.
 * 
 * The overloaded constructor which takes in 4 int parameters instantiates
 * a Cell object with xCoord, yCoord, cellType and direction to the int
 * parameters passed in.
 * 
 * The copy constructor takes takes in a Cell parameters and instantiates
 * a Cell object which creates a deep copy of the Cell passed in as a
 * parameter.
 * 
 * Methods in the Cell class set and retrieve xCoord, yCoord, cellType and
 * direction as well as reset all private member variables of a Cell with
 * one method call.
 * 
 */

public class Cell {
	// Public static final ints represent type and directions of Cells.
	// Cell types:
	public static final int EMPTY = 0;						// Empty Cell
	public static final int SNAKE = 1;						// Snake Cell
	public static final int APPLE = 2;						// Apple Cell
	public static final int WALL = 3;						// Wall Cell
	
	// Cell directions:
	public static final int DEFAULT = -1;					// No direction for EMPTY, APPLE and WALL Cells
	public static final int NORTH = 0;						// North
	public static final int EAST = 1;						// East
	public static final int SOUTH = 2;						// South
	public static final int WEST = 3;						// West
	
	// Private member variables of each Cell object:
	private int xCoord;										// x-coordinate
	private int yCoord;										// y-coordinate
	private int cellType;									// Type of Cell
	private int direction;									// Direction of Cells
	
	/**
	 * DEFAULT CONSTRUCTOR: Calls overloaded constructor which takes 4 int
	 * parameters instantiating a Cell object with xCoord = 0, yCoord = 0,
	 * cellType = EMPTY and direction = DEFAULT.
	 * @param none
	 * @return none
	 */
	public Cell() {
		this(0, 0, EMPTY, DEFAULT);							// Calls Cell(0, 0, EMPTY, DEFAULT)
	}
	
	/**
	 * OVERLOADED CONSTRUCTOR: Calls overloaded constructor which takes 4
	 * int parameters instantiate a Cell object with initializing xCoord
	 * and yCoord to int parameters passed in, cellType = EMPTY and 
	 * direction = DEFAULT.
	 * @param xCoord
	 * @param yCoord
	 */
	public Cell(int xCoord, int yCoord) {
		this(xCoord, yCoord, EMPTY, DEFAULT);				// Calls Cell(xCoord, yCoord, EMPTY, DEFAULT)
	}
	
	/**
	 * OVERLOADED CONSTRUCTOR: Instantiates a Cell object with xCoord,
	 * yCoord, cellType and direction to int parameters passed in.
	 * @param xCoord
	 * @param yCoord
	 * @param cellType
	 * @param direction
	 */
	public Cell(int xCoord, int yCoord, int cellType, int direction) {
		this.xCoord = xCoord;								// Initializes xCoord
		this.yCoord = yCoord;								// Initializes yCoord
		this.cellType = cellType;							// Initializes cellType
		this.direction = direction;							// Initializes direction
	}
	
	/**
	 * COPY CONSTRUCTOR: Instantiates a Cell object with the xCoord,
	 * yCoord, cellType and direction to the corresponding values of the
	 * Cell object passed in as a parameter. Creates a deep copy.
	 * @param cellToCopy
	 */
	public Cell(Cell cellToCopy) {
		this.xCoord = cellToCopy.xCoord;					// Initialize xCoord of new Cell
		this.yCoord = cellToCopy.yCoord;					// Initialize yCoord of new Cell
		this.cellType = cellToCopy.cellType;				// Initialize cellType of new Cell
		this.direction = cellToCopy.direction;				// initialize direction of new Cell
	}
	
	/**
	 * GETTER: Returns xCoord of Cell.
	 * @return xCoord
	 */
	public int getXCoord() {
		return xCoord;										// Return xCoord
	}
	
	/**
	 * SETTER: Assigns passed in int parameter to xCoord.
	 * @param xCoord
	 */
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;								// Assigns xCoord
	}
	
	/**
	 * GETTER: Returns yCoord of Cell.
	 * @return yCoord
	 */
	public int getYCoord() {
		return yCoord;										// Return yCoord
	}
	
	/**
	 * SETTER: Assigns passed in int parameter to yCoord.
	 * @param yCoord
	 */
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;								// Assigns yCoord
	}
	
	/**
	 * GETTER: Returns cellType of Cell.
	 * @return cellType
	 */
	public int getCellType() {
		return cellType;									// Return cellType
	}
	
	/**
	 * SETTER: Assigns passed in int parameter to cellType.
	 * @param cellType
	 */
	public void setCellType(int cellType) {
		this.cellType = cellType;							// Assigns cellType
	}
	
	/**
	 * GETTER: Returns direction of Cell.
	 * @return direction
	 */
	public int getDirection() {
		return direction;									// Return direction
	}
	
	/**
	 * SETTER: Assigns passed in int parameter to direction.
	 * @param xCoord
	 */
	public void setDirection(int direction) {
		this.direction = direction;							// Assigns direction
	}
	
	/**
	 * METHOD: Assigns 4 passed in int parameters to xCoord, yCoord,
	 * cellType and direction.
	 * @param xCoord
	 * @param yCoord
	 * @param cellType
	 * @param direction
	 */
	public void setCell(int xCoord, int yCoord, int cellType, int direction) {
		this.xCoord = xCoord;								// Assigns xCoord
		this.yCoord = yCoord;								// Assigns yCoord
		this.cellType = cellType;							// Assigns cellType
		this.direction = direction;							// Assigns direction
	}
	
	/**
	 * METHOD: Assigns the private variables xCoord, yCoord, cellType
	 * and direction.
	 * @param cellToSet
	 */
	public void setCell(Cell cellToSet) {
		this.xCoord = cellToSet.xCoord;						// Assigns xCoord
		this.yCoord = cellToSet.yCoord;						// Assigns yCoord
		this.cellType = cellToSet.cellType;					// Assigns cellType
		this.direction = cellToSet.direction;				// Assigns direction
	}
}