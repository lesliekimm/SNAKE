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
 * The Snake class creates an object of type Snake which is an ArrayList
 * of Cells.
 * 
 * There are 3 private static final ints that are used to represent the
 * initial length of the Snake depending on the level of the game.
 * LEVEL_1_LENGTH (1), LEVEL_2_LENGTH (5) or LEVEL_3_LENGTH (10) will
 * be selected by the constructor to initialize the ArrayList<Cell>.
 * 
 * The default constructor will call the overloaded constructor with
 * a parameter creating a Snake used for Level 1.
 * 
 * The overloaded constructor will initialize the snakeLength to the
 * corresponding level's starting length.
 * 
 * Methods allow for retrieval of the Snake and snake length as well as
 * two add methods to add a Cell to the Snake and a remove method to
 * remove a Cell from the Snake.
 *
 */

import java.util.ArrayList;

public class Snake {
	// Private static final ints represent the initial length of the Snake.
	private static final int LEVEL_1_LENGTH = 1;					// Represents initial snake length for Level 1
	private static final int LEVEL_2_LENGTH = 5;					// Represents initial snake length for Level 2
	private static final int LEVEL_3_LENGTH = 10;					// Represents initial snake length for Level 3
	
	// Private member variables of each Snake:
	private ArrayList<Cell> snake;									// ArrayList of Cells
	private int snakeLength;										// Length of snake
	
	/**
	 * DEFAULT CONSTRUCTOR: Calls the overloaded constructor with
	 * a parameter. Instantiates a Snake object with snakeLength = 10
	 * for Level 1.
	 * @param none
	 * @return none
	 */
	public Snake() {
		this(1);													// Calls Snake(1)
	}

	/**
	 * OVERLOADED CONSTRUCTOR: Instantiates a Snake object with xCoord
	 * and yCoord initialized to parameters passed in and
	 * cellType = EMPTY, direction = DEFAULT.
	 * @param level
	 */
	public Snake(int level) {
		snake = new ArrayList<Cell>();								// Initialize snake
		
		// Assign snakeLength depending on level being played.
		if (level == 1)												// If playing Level 1
			snakeLength = LEVEL_1_LENGTH;						
		else if (level == 2)										// If playing Level 2
			snakeLength = LEVEL_2_LENGTH;						
		else														// If playing Level 3
			snakeLength = LEVEL_3_LENGTH;
		
		// Add the correct number of Cells to snake. The x- and y-coordinates of the Cells will be set to start at
		// the bottom left side of the screen.
		for (int index = Board.ROWS - snakeLength; index < Board.ROWS; index++)
			snake.add(new Cell(0, index, Cell.SNAKE, Cell.NORTH));	// Add Cell to ArrayList<Cell>
	}
	
	/**
	 * GETTER: Returns snake of Cell.
	 * @return snake
	 */
	public ArrayList<Cell> getSnake() {
		return snake;												// Return snake
	}
	
	/**
	 * GETTER: Returns snakeLength of Cell.
	 * @return snakeLength
	 */
	public int getSnakeLength() {
		return snakeLength;											// Return snakeLength
	}
	
	/**
	 * METHOD: Adds Cell to the end of the snake.
	 * @param cellToAdd
	 */
	public void add(Cell cellToAdd) {
		snake.add(cellToAdd);										// Add Cell to end of snake
		snakeLength++;												// Increment snakeLength
	}
	
	/**
	 * METHOD: Adds Cell to snake at indicated index.
	 * @param cellToAdd
	 * @param index
	 */
	public void add(Cell cellToAdd, int index) {
		snake.add(index, cellToAdd);								// Add Cell to index of snake
		snakeLength++;												// Increment snakeLength
	}
	
	/**
	 * METHOD: Removes Cell from indicated index.
	 * @param index
	 */
	public void remove(int index) {
		snake.remove(index);										// Removes Cell from index of snake
		snakeLength--;												// Decrement snakeLength
	}
}