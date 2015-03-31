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
 * The Apple class is an extension of the Cell class that creates an
 * object of type Apple with private members board, random, apple,
 * appleXCoord and appleYCoord. The Apple class contains a reference
 * to the Board object used in the SnakeGUI in order to place the apple
 * Cell on the Board object.
 * 
 * The constructor takes a Board object as a parameter and assigns it to
 * the private member of the Apple object's board to create a reference
 * to the Board object used in SnakeGUI. This allows for placing the
 * apple Cell of the Apple class on the referenced Board object. It also
 * places the apple on the Board object the first time at randomly
 * selected coordinates.
 * 
 * The methods in this class allow for retrieval of the private apple
 * member, placing the apple in at a randomly ordered pair and resetting
 * the apple member's cellType and direction to EMPTY and DEFAULT.
 *
 */

import java.util.Random;

public class Apple extends Cell {
	// Private members of each apple:
	private Cell[][] board;							// Reference to Board object used in SnakeGUI
	private Random random;							// Random used to select coordinates for Apple object
	private Cell apple;								// Cell instantiated to be type APPLE
	private int appleXCoord;						// x-coordinate of private member apple
	private int appleYCoord;						// y-coordinate of private member apple
	
	/**
	 * CONSTRUCTOR: Instantiates Apple object that takes in a Board as
	 * a parameter and assigns it to the private board member of the
	 * Apple class to reference the Board object passed in. Also
	 * instantiates a new Random object used to select random ints for
	 * private member apple coordinates and instantiates apple as a
	 * new Cell.
	 * @param board
	 */
	public Apple(Cell[][] board) {
		this.board = board;							// Make private board a reference to Board object passed in
		random = new Random();						// Instantiate new Random object
		apple = new Cell();							// Instantiate new Cell object
		appleXCoord = apple.getXCoord();			// Initialize appleXCoord to 0
		appleYCoord = apple.getYCoord();			// Initialize appleYCoord to 0
		
		placeRandomApple();							// Place apple Cell at a random point on the Board object
	}
	
	/**
	 * GETTER: Returns apple.
	 * @return apple
	 */
	public Cell getApple() {
		return apple;								// Return apple
	}
	
	/**
	 * METHOD: This method uses the Random object to select 2 random
	 * ints to use as the coordinates for the apple Cell. It checks
	 * each randomly selected ordered pair to make sure the Cell is
	 * EMPTY on the Board object and randomly selects a new ordered
	 * pair if the Cell is not EMPTY. It sets the randomly selected
	 * coordinates as the x- and y-coordinates of the apple Cell and
	 * places it on the Board object.
	 * @param none
	 * @return none 
	 */
	public void placeRandomApple() {
		boolean isEmpty = false;					// Used to check if Cell at random coordinates is EMPTY
//		int appleXCoord = 0;
//		int appleYCoord = 0;
		
		// While the Cell of the randomly chosen coordinates is not EMPTY, continue to randomly select coordinates.
		while (!isEmpty) {
			appleXCoord = random.nextInt(Board.COLS - 1);		// Randomly select an x-coordinate
			appleYCoord = random.nextInt(Board.ROWS - 1);		// Randomly select an y-coordinate
			
			// Check the Cell on the Board of the randomly selected x- and y-coordinates.
			int cellToCheckType = board[appleYCoord][appleXCoord].getCellType();
			
			// If the randomly selected Cell is EMPTY, set isEmpty to true to stop the while loop.
			if (cellToCheckType == Cell.EMPTY)
				isEmpty = true;									// Set isEmpty to true
		}
		
		// Set the private member Cell apple to the randomly selected coordinates, type APPLE and direction DEFAULT.
		apple.setCell(appleXCoord, appleYCoord, Cell.APPLE, Cell.DEFAULT);
		// Set the Cell on the Board to the apple's private members xCoord, yCoord, cellType and direction.
		board[appleYCoord][appleXCoord].setCell(apple);
	}
	
	/**
	 * METHOD: This method clears the apple Cell on the board and
	 * resets that Cell to type EMPTY with direction DEFAULT.
	 * @param none
	 * @return none
	 */
	public void clearApple() {
//		int appleXCoord = apple.getXCoord();
//		int appleYCoord = apple.getYCoord();
		
		// Sets apple Cell to type EMPTY and direction DEFAULT.
		apple.setCell(appleXCoord,  appleYCoord, Cell.EMPTY, Cell.DEFAULT);
	}
}