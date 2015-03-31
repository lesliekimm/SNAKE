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
 * The SnakeGUI class implements ActionListener and KeyListener and
 * creates an object of type SnakeGUI. It has private static final
 * ints that represent the dimension of the entire game. Each
 * SnakeGUI contains private members frame, contentPane where the
 * board and sidePanel reside on, a timer, the level and boolean values
 * that stores whether the game is currently paused or not and whether
 * the user has played at least one game.
 * 
 * The constructor calls an init function to initializes the GUI.
 *  
 * The methods of this class initialize the GUI, initialize a new
 * Board depending on the level, allows for retrieval of the timer,
 * the number of apples eaten, the score and the boolean of isDead
 * from the Board, play, pause, and end game. It also includes
 * overridden methods KeyPressed(KeyEvent) of the KeyListener class
 * and actionPerformed(ActionEvent) of the ActionListener class.
 * 
 * This is the main class that contains the main function. 
 * 
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGUI implements ActionListener, KeyListener {
	// Private static final ints represent GUI's dimensions.
	private static final int DEFAULT_WIDTH = 1200;
	private static final int DEFAULT_HEIGHT = 602;

	// Private members of SnakeGUI:
	private JFrame frame;													// GUI frame
	private JPanel contentPane;												// GUI content pane
	private Board board;													// Board of GUI
	private SidePanel sidePanel;											// Side Panel of GUI
	private Timer timer;													// Timer
	private int level;														// Level of game
	private boolean paused;													// Indication of paused gamed
	private boolean hasPlayedGame;											// Indication of first game played

	/**
	 * CONSTRUCTOR: Calls initialization function to initialize GUI.
	 * @param none
	 * @return none
	 */
	public SnakeGUI() {		
		initSnakeGUI();														// Calls initSnakeGUI()
	}
	
	/**
	 * METHOD: Initializes the GUI. Creates a JFrame of the
	 * DEFAULT_WIDTH and DEFAULT_HEIGHT dimensions, adds a Board and
	 * SidePanel to the content pane using a BorderLayout and 
	 * initializes paused to true and hasPlayedGame to false.
	 * @param none
	 * @return none
	 */
	public void initSnakeGUI() {
		frame = new JFrame("Snake");										// Initialize JFrame
		frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);						// Set size of frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				// Set default close operation of frame

		contentPane = new JPanel(new BorderLayout());						// Create BorderLayout content pane
		frame.add(contentPane);												// Add contentPane to frame

		board = new Board(this);											// Initialize board
		board.addKeyListener(this);											// Add keyListener to board
		board.setFocusable(true);											// Set focusable to the board
		contentPane.add(board, BorderLayout.CENTER);						// Add board to contentPane

		sidePanel = new SidePanel(this);									// Initialize sidePanel
		contentPane.add(sidePanel, BorderLayout.EAST);						// Add sidePanel to contentPane

		frame.setVisible(true);												// Set visible to true
		frame.setResizable(false);											// Set resizable to false

		paused = true;														// Paused is true
		hasPlayedGame = false;												// Has not played a game yet
	}

	/**
	 * METHOD: Initialize a new board of the level currently
	 * selected on the sidePanel.
	 * @param level
	 */
	public void initNewBoard(int level) {
		this.level = level;													// Assign parameter level to level
		
		if (board.getIsDead())												// If snake is dead from previous game
			timer.stop();													// Stop timer
		
		board.setIsDead(false);												// Reset isDead to false for new game
		
		// Initialize timers for each level. Higher levels will have timers that make the snake "move" faster.
		if (level == 1)
			timer = new Timer(100, this);
		else if (level == 2)
			timer = new Timer(70, this);
		else
			timer = new Timer(40, this);
		
		board.clearBoard();													// Clear the board for new game
		sidePanel.resetPauseButton();										// Reset the pause button
		board.initBoard(level);												// Initialize board for level
		board.repaint();													// Repaint the board
	}

	/**
	 * GETTER: Returns the timer.
	 * @return timer	
	 */
	public Timer getTimer() {
		return timer;														// Return timer
	}
	
	/**
	 * GETTER: Return numOfApples from board.
	 * @return board.getNumOfApples()
	 */
	public int getNumOfApples() {
		return board.getNumOfApples();										// Return number of apples eaten
	}

	/**
	 * GETTER: Return score from board.
	 * @return board.getNugetScoremOfApples()
	 */
	public int getScore() {
		return board.getScore();											// Return score of game
	}

	/**
	 * GETTER: Return isDead from board.
	 * @return board.getIsDead()
	 */
	public boolean getIsDead() {
		return board.getIsDead();											// Return isDead of game
	}
	
	/**
	 * GETTER: Return hasPlayedGame.
	 * @return hasPlayedGame
	 */
	public boolean getHasPlayedGame() {
		return hasPlayedGame;												// Return if a game has been played
	}
	
	/**
	 * METHOD: Starts timer so game can be played.
	 * @param none
	 * @return none
	 */
	public void play() {
		hasPlayedGame = true;												// Sets hasPlayedGame to true
			
		if (!board.getIsDead()) {											// If snake is not dead						
			paused = false;													// paused is false
			timer.start();													// Start timer
		}
	}

	/**
	 * METHOD: Pauses the game by stopping the timer.
	 * @param none
	 * @return none
	 */
	public void pause() {
		paused = true;														// paused is true
		timer.stop();														// Stop timer
	}
	
	/**
	 * METHOD: End the game by stopping the timer.
	 * @param none
	 * @return none
	 */
	public void endGame() {
		timer.stop();														// Stop timer
	}

	/**
	 * MAIN FUNCTION: PLAY GAME.
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		SnakeGUI game = new SnakeGUI();										// Initialize SnakeGUI which starts
																			// game.
	}

	/**
	 * UNUSED OVERRIDDEN METHOD
	 * @param e
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * OVERRIDDEN METHOD: When arrow buttons are pressed or WDSA for
	 * corresponding arrows, it moves the snake. If Enter/Return button
	 * is pressed, it starts a game. If spacebar is hit, it pauses/
	 * unpauses the game.
	 * @param event
	 */
	@Override
	public void keyPressed(KeyEvent event) {
		int keyCode = event.getKeyCode();										// Get key code of event
		int direction = 5;														// Set direction to number that
																				// isn't used by Cell's direction
		
		// If games have been played, get the direction of the first Cell of snake
		if (hasPlayedGame)
			direction = board.getSnake().get(0).getDirection();

		// For each direction, set direction to the direction pressed
		// For direction NORTH
		if ((keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) && (direction != Cell.SOUTH))
			board.getSnake().get(0).setDirection(Cell.NORTH);
		// For direction EAST
		if ((keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) && (direction != Cell.WEST))
			board.getSnake().get(0).setDirection(Cell.EAST);
		// For direction SOUTH
		if ((keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) && (direction != Cell.NORTH))
			board.getSnake().get(0).setDirection(Cell.SOUTH);
		// For direction WEST
		if ((keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) && (direction != Cell.EAST))
			board.getSnake().get(0).setDirection(Cell.WEST);
		if (keyCode == KeyEvent.VK_ENTER) {										// If you press Enter/Return
			if (hasPlayedGame)													// Only if game has been played
				timer.stop();													// Stop timer
			sidePanel.getNewGame().doClick();									// Perform click action of newGame
		}
		if (keyCode == KeyEvent.VK_SPACE) {										// If you press spacebar
			if (paused == true)													// If paused
				play();															// Continue playing
			else																// If not paused
				pause();														// Pause
			sidePanel.getPause().doClick();										// Perform click action of pause
		}
	}

	/**
	 * UNUSED OVERRIDEN METHOD
	 * @param e
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * OVERRIDDEN METHOD: Overridden method of ActionListener class.
	 * When ActionEvent occurs (starting timer), if the snake is not
	 * dead, move the Snake and repaint both the board and sidePanel.
	 * @param event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (!board.getIsDead()) {												// If snake isn't dead
			board.moveSnake(level);												// Move snake
			board.repaint();													// Repaint board
			sidePanel.repaint();												// Repaint sidePanel
		}
	}
}