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
 * The Board class extends the JPanel class and creates an object of
 * type Board with a reference to the SnakeGUI, a 2D array which
 * represents the board, a Snake object, an Apple object, a Walls
 * object, number of apples eaten, score of the game and whether the 
 * snake is dead or not, indicating if the game is over.
 * 
 * The constructor takes a SnakeGUI as a parameter which is a reference
 * to the SnakeGUI object calling the board. This allows for the Board
 * object to end the game if the Snake dies. It initializes a 2D array
 * of the proper ROWS and COLS dimensions and sets isDead to true, as
 * the snake is currently not in play.
 * 
 * Methods for this class include initializing the board to the correct
 * level (each level has a different layout and speed for the snake
 * (speed is controlled by the SnakeGUI object)), retrieval of the board,
 * the Snake object's ArrayList<Cell> representation, number of apples
 * eaten, score and whether or not the snake is dead. It also has methods
 * for clearing the board, setting the isDead variable, moving the snake
 * and overrides the paintComponent(Graphics) method of the JPanel class. 
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {
	// Only COLS and ROWS are public static final ints that need to be accessed by other classes.
	// All others are private static final ints representing the board dimensions, the "scale" of each
	// coordinate placed on the board (i.e. Ordered pair (2, 5) is actually a 10x10 pixel square at
	// (20, 50) spanning 10 pixels both ways to (30, 60)). The X- and Y-SPEEDS represent moving by one
	// pixel.
	private static final int DEFAULT_WIDTH = 800;							// Width of board
	private static final int DEFAULT_HEIGHT = 580;							// Height of board
	private static final int SCALE = 10;									// Scale of each ordered pair
	public static final int COLS = DEFAULT_WIDTH / SCALE;					// Columns on board
	public static final int ROWS = DEFAULT_HEIGHT / SCALE;					// Rows on board
	private static final int XSPEED = 1;									// Change in xCoord
	private static final int YSPEED = 1;									// Change in yCoord
	
	// Private member variables of each Board:
	private SnakeGUI game;													// Reference to SnakeGUI game
	private Cell[][] board;													// 2D Cell array
	private Snake snake;													// Snake object
	private Apple apple;													// Apple object
	private Walls walls;													// Walls object
	private int numOfApples;												// Number of apples eaten
	private int score;														// Score of game
	private boolean isDead;													// Whether snake is alive or dead
	
	/**
	 * CONSTRUCTOR: Takes in a SnakeGUI object to create a reference
	 * to the game. Initializes a 2D array with ROWS and COLS
	 * dimensions and sets isDead to true as game has not started.
	 * @param game
	 */
	public Board(SnakeGUI game) {
		this.game = game;													// Assign game to SnakeGUI passed in
		board = new Cell[ROWS][COLS];										// Initialize 2D Cell array
		isDead = false;														// Snake is currently not alive
		
		// Use nested for loops to create the board of Cells by initializing each Cell in 2D array.
		for (int rowsIndex = 0; rowsIndex < ROWS; rowsIndex++) {
			for (int colsIndex = 0; colsIndex < COLS; colsIndex++)
				board[rowsIndex][colsIndex] = new Cell(colsIndex, rowsIndex);
		}
	}
	
	/**
	 * METHOD: Initializes the layout of the board depending on the int
	 * level passed in as a parameter. Higher levels will initialize
	 * Snake objects with longer lengths and different Walls layouts.
	 * @param level
	 */
	public void initBoard(int level) {
		numOfApples = 0;													// Initialize apples eaten to 0
		score = 0;															// Initialize score to 0
		isDead = false;														// Snake is now alive
		snake = new Snake(level);											// Initialize snake to level passed in
		walls = new Walls(getBoard(), level);								// Initialize walls to level passed in
		
		// Place the snake on the board.
		for (int snakeIndex = 0; snakeIndex < snake.getSnakeLength(); snakeIndex++) {
			Cell cellToPlace = snake.getSnake().get(snakeIndex);
			board[cellToPlace.getYCoord()][cellToPlace.getXCoord()].setCell(cellToPlace);
		}
		
		// Place the walls on the board.
		for (int wallsIndex = 0; wallsIndex < walls.getWallsSize(); wallsIndex++) {
			Cell cellToPlace = walls.getWalls().get(wallsIndex);
			board[cellToPlace.getYCoord()][cellToPlace.getXCoord()].setCell(cellToPlace);
		}
		
		// Initialize apple after placing the snake and walls on the board so apple is not on a Cell that is taken.
		apple = new Apple(board);											// Initialize apple
		board[apple.getYCoord()][apple.getXCoord()].setCell(apple);
	}
	
	/**
	 * GETTER: Returns 2D array board.
	 * @return board
	 */
	public Cell[][] getBoard() {
		return board;														// Return board
	}
	
	/**
	 * METHOD: Clears the board by resetting private variables relative
	 * to score to 0, sets isDead to false as snake is not dead when
	 * restarting a game and sets all Cells of Board to type EMPTY and
	 * DEFAULT direction.
	 * @param none
	 * @return none
	 */
	public void clearBoard() {
		// These members will all be reset when starting a new game, but set them all to 0 anyways.
		numOfApples = 0;													// Assign apples eaten to 0
		score = 0;															// Assign score to 0
		isDead = false;														// Assign snake to being alive
		
		// Reset the board to all EMPTY cells with DEFAULT direction.
		for (int rowsIndex = 0; rowsIndex < ROWS; rowsIndex++) {
			for (int colsIndex = 0; colsIndex < COLS; colsIndex++)
				board[rowsIndex][colsIndex].setCell(colsIndex, rowsIndex, Cell.EMPTY, Cell.DEFAULT);
		}
	}
	
	/**
	 * GETTER: Returns ArrayList<Cell> snake.
	 * @return snake (as ArrayList<Cell>)
	 */
	public ArrayList<Cell> getSnake() {		
		return snake.getSnake();											// Return ArrayList<Cell> of snake
	}
	
	/**
	 * GETTER: Returns number of apples eaten.
	 * @return numOfApples
	 */
	public int getNumOfApples() {
		return numOfApples;													// Return numOfApples
	}
	
	/**
	 * GETTER: Returns score of game.
	 * @return score
	 */
	public int getScore() {
		return score;														// Return score
	}
	
	/**
	 * METHOD: Calculates the score of a game by multiplying number
	 * of apples eaten by 100.
	 * @param none
	 * @return none
	 */
	public void calculateScore() {
		score = numOfApples * 100 ;											// Arbitrary formula to calculate score
	}
	
	/**
	 * METHOD: Calculates the score of a game by multiplying number
	 * of apples eaten by 100.
	 * @return score
	 */
	public int calculateAndGetScore(int numOfApples) {
		return score = numOfApples * 100 ;									// Arbitrary formula to calculate score
	}
	
	/**
	 * GETTER: Returns whether snake is dead or not.
	 * @return isDead
	 */
	public boolean getIsDead() {
		return isDead;														// Return isDead
	}
	
	/**
	 * SETTER: Assigns whether snake is dead or not to isDead.
	 * @param isDead
	 */
	public void setIsDead(boolean isDead) {
		this.isDead = isDead;												// Assign passed in parameter to isDead
	}
	
	/**
	 * METHOD: Takes in int level to use to calculate score as each
	 * move is made. The snake moves by taking the last Cell in the
	 * snake ArrayList and removing it and adding it to the head of
	 * the ArrayList. It uses the current head's coordinates and
	 * direction to determine where to place the new head on the Board.
	 * If the snake eats an apple, the apple Cell is added to the snake
	 * at the head before removing the last Cell and adding it to the
	 * head. The board is updated as the Snake object is updated.
	 * @param level
	 */
	
	public void moveSnake(int level) {
		// To move the snake, a new Cell is created and inserted at the front of the cell using the current first
		// Cell's x- and y-coordinates as well as direction. Depending on the direction, either the x- or
		// y-coordinate is adjusted in the proper direction.
		Cell head = snake.getSnake().get(0);								// Get head (first) Cell in snake
		int headXCoord = head.getXCoord();									// Get x-coordinate of head
		int headYCoord = head.getYCoord();									// Get y-coordinate of head
		int headDirection = head.getDirection();							// Get direction of head
		int newHeadXCoord = headXCoord;										// Initialize new head xCoord
		int newHeadYCoord = headYCoord;										// Initialize new head yCoord
		Cell tail = snake.getSnake().get(snake.getSnakeLength() - 1);		// Get tail (last) Cell in snake
		int tailXCoord = tail.getXCoord();									// Get tail x-coordinate
		int tailYCoord = tail.getYCoord();									// Get tail y-coordinate
		
		// Figure out which coordinate to update for the new head Cell.
		if (headDirection == Cell.NORTH && headYCoord - YSPEED >= 0)		// If moving NORTH and doesn't hit edge
			newHeadYCoord--;												// Decrement newHeadYCoord
		else if (headDirection == Cell.EAST && headXCoord + XSPEED < COLS)	// If moving EAST and doesn't hit edge
			newHeadXCoord++;												// Increment newHeadXCoord
		else if (headDirection == Cell.SOUTH && headYCoord + YSPEED < ROWS)	// If moving SOUTH and doesn't hit edge
			newHeadYCoord++;												// Increment newHeadYCoord
		else if (headDirection == Cell.WEST && headXCoord - XSPEED >= 0)	// If moving WEST and doesn't hit edge
			newHeadXCoord--;												// Decrement newHeadXCoord
		else																// Means edge of game was hit
			isDead = true;													// Snake is dead and game is over
		
		// If snake hits itself or a wall, set isDead to true.
		if ((board[newHeadYCoord][newHeadXCoord].getCellType() == Cell.SNAKE) ||
				(board[newHeadYCoord][newHeadXCoord].getCellType() == Cell.WALL))
			isDead = true;													// Snake is dead and game is over
		
		// If snake hits apple, eat apple, grow in length by one and randomly place a new apple on the board.
		if (board[newHeadYCoord][newHeadXCoord].getCellType() == Cell.APPLE) {
			numOfApples++;													// Increment numOfApples to update score
				
			// Add the apple Cell to the snake at the head and set the Cell on the board to type SNAKE.
			snake.add(new Cell(newHeadXCoord, newHeadYCoord, Cell.SNAKE, headDirection), 0);
			board[newHeadYCoord][newHeadXCoord].setCell(newHeadXCoord, newHeadXCoord, Cell.SNAKE, headDirection);
			
			// Re-figure the new head coordinates after adding apple to front of snake
			if (headDirection == Cell.NORTH && headYCoord - YSPEED >= 0)		// If moving NORTH and doesn't hit edge
				newHeadYCoord--;												// Decrement newHeadYCoord
			else if (headDirection == Cell.EAST && headXCoord + XSPEED < COLS)	// If moving EAST and doesn't hit edge
				newHeadXCoord++;												// Increment newHeadXCoord
			else if (headDirection == Cell.SOUTH && headYCoord + YSPEED < ROWS)	// If moving SOUTH and doesn't hit edge
				newHeadYCoord++;												// Increment newHeadYCoord
			else if (headDirection == Cell.WEST && headXCoord - XSPEED >= 0)	// If moving WEST and doesn't hit edge
				newHeadXCoord--;												// Decrement newHeadXCoord
			else																// Means edge of game was hit
				isDead = true;													// Snake is dead and game is over
			
			apple.placeRandomApple();											// Place new random apple on board
			
			if (level != 1)
				walls.placeRandomBarrier();
		}
		
		calculateScore();														// Calculate score
		
		// If snake is not dead, add new Cell at front of snake and remove last Cell and update board.
		if (!isDead) {
			// Add newHead cell at the head of snake, remove the end Cell form snake and update the board.
			Cell newHead = new Cell(newHeadXCoord, newHeadYCoord, Cell.SNAKE, headDirection);
			snake.add(newHead, 0);
			snake.remove(snake.getSnakeLength() - 1);
			board[newHeadYCoord][newHeadXCoord].setCellType(Cell.SNAKE);
			board[newHeadYCoord][newHeadXCoord].setDirection(headDirection);
			board[tailYCoord][tailXCoord].setCellType(Cell.EMPTY);
			board[tailYCoord][tailXCoord].setDirection(Cell.DEFAULT);
		}
		// If snake is dead, end the game.
		else {
			game.endGame();														// End game
		}
	}

	/**
	 * METHOD: Overrides paintComponent(Graphics) method of the JPanel
	 * class. Sets the Color of each EMPTY Cell to black, SNAKE Cell
	 * to green, APPLE Cell to red and WALL Cell to white.
	 */
	@Override
	public void paintComponent(Graphics boardgame) {
		super.paintComponent(boardgame);									// Override super method
		
		// Nested for loops to go through each Cell in 2D array.
		for (int rowsIndex = 0; rowsIndex < ROWS; rowsIndex++) {
			for (int colsIndex = 0; colsIndex < COLS; colsIndex++) {
				// Get current Cell's type
				int currentCellType = board[rowsIndex][colsIndex].getCellType();
				if (currentCellType == Cell.EMPTY)							// If EMPTY Cell
					boardgame.setColor(Color.BLACK);						// Set color to BLACK
				else if (currentCellType == Cell.SNAKE)						// If SNAKE Cell
					boardgame.setColor(Color.GREEN);						// Set color to GREEN
				else if (currentCellType == Cell.APPLE)						// If APPLE Cell
					boardgame.setColor(Color.RED);							// Set Color to RED
				else if (currentCellType == Cell.WALL)						// If WALL Cell
					boardgame.setColor(Color.WHITE);						// Set Color to WHITE
				else														// All others (shouldn't happen)
					boardgame.setColor(Color.BLACK);						// Set Color to BLACK
				
				// Fill square of 10x10 with assigned color for each Cell.
				boardgame.fillRect(colsIndex * SCALE, rowsIndex * SCALE, SCALE, SCALE);
			}
		}
	}
}