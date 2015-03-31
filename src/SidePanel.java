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
 * The SidePanel class extends the JPanel class and creates an object of
 * type SidePanel with a reference to the SnakeGUI, creates a bottom
 * menu which allows user to press buttons to start a game or pause/
 * unpause as well as select levels and has a private int member level.
 * 
 * The constructor takes a parameter which is a reference to the SnakeGUI
 * calling the side panel. This allows for the Side Panel object to use
 * methods of the SnakeGUI class for the game being played. It calls
 * an initialize function for the side panel.
 * 
 * Methods for this class include initializing the side panel, retrieval
 * of the newGame and pause JButtons and level, resetting the pause
 * JButton, overriding the paintComponent(Graphics) method of the JPanel
 * class and the actionPerformed(ActionEvent) method to implement the
 * ActionListener. 
 * 
 * NOTE: This panel was originally not included in the game. After
 * designing the game and looking up several other designs, I noticed
 * a layout I preferred for the look of the scores. This side panel is
 * not the main component of the game and was heavily influenced by the
 * source code in the following link:
 * https://github.com/PSNB92/SnakeRemake/blob/master/src/org/psnbtech/
 * SidePanel.java
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SidePanel extends JPanel implements ActionListener {
	// Private static final ints represent side panel's dimensions and the offsets of text on side panel as well
	// as font styles.
	private static final int MENU_WIDTH = 400;									// Width of side panel
	private static final int MENU_HEIGHT = 580;									// Height of side panel
	private static final int TITLE_OFFSET = 60;									// Height offset of title
	private static final int INSTRUCTIONS_OFFSET = 100;							// Height offset of instructions
	private static final int STATISTICS_OFFSET = 220;							// Height offset of statistics
	private static final int CONTROLS_OFFSET = 340;								// Height offset of controls
	private static final int ADD_LINE = 20;										// Height offset for each line
	private static final int H2_OFFSET = 30;									// Width offset for H2 headings
	private static final int H3_OFFSET = 50;									// Width offset for H3 headings
	private static final Font H1 = new Font("Arial", Font.BOLD, 20);			// Font for H1 headings
	private static final Font H2 = new Font("Arial", Font.BOLD, 16);			// Font for H2 headings
	private static final Font H3 = new Font("Arial", Font.BOLD, 12);			// Font for H3 headings
	
	// Private member variables of each SidePanel:
	private JPanel bottomMenu;													// JPanel for buttons at bottom
	private JButton newGame;													// New Game JButton
	private JButton pause;														// Pause/Play JButton
	@SuppressWarnings("rawtypes")
	private JComboBox levelList;												// Drop down list of levels
	private SnakeGUI game;														// Reference to SnakeGUI game
	private int level;															// Level of game being played

	/**
	 * CONSTRUCTOR: Takes a SnakeGUI game as a parameter to create a
	 * reference to the game in order to call game functions. Calls
	 * initSidePanel() to initialize the side panel's layout.
	 * @param game
	 */
	public SidePanel(SnakeGUI game) {
		this.game = game;														// Reference to SnakeGUI game
		
		initSidePanel();														// Initialize side panel
	}
	
	/**
	 * METHOD: Initializes side panel by adding the bottom menu of
	 * buttons drop down menu to start, pause, unpause and select
	 * level. It selects Level 1 by default. Set focusable to false
	 * for these components so that the focus is automatically on
	 * the SnakeGUI object and the Key Listener is the default
	 * action to listen for.
	 * @param none
	 * @return none
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })	
	public void initSidePanel() {
		setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));				// Set size of SidePanel
		setLayout(new BorderLayout());											// Set layout to BorderLayout
		setBackground(Color.WHITE);												// Set background color to WHITE
		
		newGame = new JButton("New Game");										// Initialize New Game button
		newGame.addActionListener(this);										// Add default action listener
		newGame.setFocusable(false);											// Set focusable to false
		
		pause = new JButton("Pause");											// Initialize Pause button
		pause.addActionListener(this);											// Add default action listener
		pause.setFocusable(false);												// Set focusable to false
		
		String[] levelStrings = { "Level 1", "Level 2", "Level 3" };			// Initialize String array of levels
		levelList = new JComboBox(levelStrings);								// Initialize drop down of levels
		levelList.setSelectedIndex(0);											// Set default to Level 1
		levelList.addActionListener(this);										// Add default action listener
		levelList.setFocusable(false);											// Set focusable to false
		
		bottomMenu = new JPanel();												// Initialize new JPanel for menu
		bottomMenu.setBackground(Color.WHITE);									// Set color to WHITE
		bottomMenu.add(newGame);												// Add New Game button to bottomMenu
		bottomMenu.add(pause);													// Add Pause button to bottomMenu
		bottomMenu.add(levelList);												// Add list of levels to bottomMenu
		
		add(bottomMenu, BorderLayout.SOUTH);									// Add bottomMenu to SidePanel

		// Retrieve the current level that is set by the SidePanel and set the SidePanel's level to the current
		// level selected by the drop down menu.
		String stringLevelSelected = (String) levelList.getSelectedItem();
		int levelSelected = Integer.parseInt(stringLevelSelected.substring(stringLevelSelected.length() - 1));
		level = levelSelected;
	}
	
	/**
	 * GETTER: Returns JButton newGame.
	 * @return newGame
	 */
	public JButton getNewGame() {
		return newGame;															// Return newGame
	}
	
	/**
	 * GETTER: Returns JButton pause.
	 * @return pause
	 */
	public JButton getPause() {
		return pause;															// Return pause
	}	
	
	/**
	 * METHOD: Resets pause button.
	 * @param none
	 * @return none
	 */
	public void resetPauseButton() {
		pause.setText("Pause");													// Resets Pause button to pause
	}
	
	/**
	 * GETTER: Returns level of Side Panel.
	 * @return level
	 */
	public int getLevel() {
		return level;															// Returns level
	}
	
	/**
	 * METHOD: Overrides paintComponent(Graphics) method of the JPanel
	 * class. The Title, Instructions, Statistics and Control
	 * Instructions are updated by this method.
	 */
	@Override
	public void paintComponent(Graphics panel) {
		super.paintComponent(panel);											// Override super method

		panel.setColor(Color.BLACK);											// Set Color to BLACK
		
		// Place "Snake" title on JPanel.
		panel.setFont(H1);														// Use H1 Font				
		String title = "Snake";													// Initialize title string
		// Draw string using size of JPanel and TITLE_OFFSET
		panel.drawString(title, getWidth() / 2 - panel.getFontMetrics().stringWidth(title) / 2, TITLE_OFFSET);
		
		// Place H2 headings on JPanel.
		panel.setFont(H2);														// Use H2 Font
		panel.drawString("Instructions", H2_OFFSET, INSTRUCTIONS_OFFSET);		// Draw "Instructions"
		panel.drawString("Statistics", H2_OFFSET, STATISTICS_OFFSET);			// Draw "Statistics"
		panel.drawString("Controls", H2_OFFSET, CONTROLS_OFFSET);				// Draw "Controls"
		
		// Place H3 headings on JPanel.
		panel.setFont(H3);														// Use H3 Font
		int changeY = INSTRUCTIONS_OFFSET;										// Use to move down a line
		// Draw instructions.
		panel.drawString("Select level and press New Game.", H3_OFFSET, changeY += ADD_LINE);
		panel.drawString("Use controls to eat apples.", H3_OFFSET, changeY += ADD_LINE);
		panel.drawString("Do not hit white walls.", H3_OFFSET, changeY += ADD_LINE);
		
		// Draw Statistics of game: includes current level, number of apples eaten and total score.
		changeY = STATISTICS_OFFSET;
		panel.drawString("Level: " + level, H3_OFFSET, changeY += ADD_LINE);
		panel.drawString("Apples Eaten: " + game.getNumOfApples(), H3_OFFSET, changeY += ADD_LINE);
		panel.drawString("Total Score: " + game.getScore(), H3_OFFSET, changeY += ADD_LINE);
		
		// Draw controls guide.
		changeY = CONTROLS_OFFSET;
		panel.drawString("Move Up: W / Up Arrowkey", H3_OFFSET, changeY += ADD_LINE);
		panel.drawString("Move Down: S / Down Arrowkey", H3_OFFSET, changeY += ADD_LINE);
		panel.drawString("Move Left: A / Left Arrowkey", H3_OFFSET, changeY += ADD_LINE);
		panel.drawString("Move Right: D / Right Arrowkey", H3_OFFSET, changeY += ADD_LINE);
		panel.drawString("Start New Game: Enter", H3_OFFSET, changeY += ADD_LINE);
		panel.drawString("Pause/Unpause: Spacebar", H3_OFFSET, changeY += ADD_LINE);
		
		// Use H2 to draw "Game over!" if snake dies
		panel.setFont(H2);														// Use H2 Font
		if (game.getIsDead())
			panel.drawString("Game over!", getWidth() / 2 - panel.getFontMetrics().stringWidth("Game over!") / 2,
					changeY += 40);
	}

	/**
	 * METHOD: Performs an action depending on which action has taken
	 * place. If New Game button is clicked, a new game is started at the
	 * current level selected. If the Pause button is clicked, it will
	 * pause or unpause the game. If a level is selected from the drop
	 * down menu, it will change the level of the game and update the
	 * level of the Statistics.
	 * RESOURCE: // http://docs.oracle.com/javase/tutorial/uiswing/
	 * components/combobox.html#listeners
	 * @param event 
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(ActionEvent event) {
		// If newGame button is clicked, start a new game.
		if (event.getSource() == newGame) {
			game.initNewBoard(level);											// Initialize the layout for level
			game.play();														// Play the game
		}
		
		// If pause button is clicked, pause or unpause the game.
		else if (event.getSource() == pause && !game.getIsDead()) {
			String pauseText = pause.getText();									// Get the text from the button
			if (pauseText == "Pause") {											// If text reads "Pause"
				pause.setText("Play");											// Set the text to "Play"
				game.pause();													// Pause the game
			}
			
			else {																// If text reads "Play"
				pause.setText("Pause");											// Set text to "Pause"	
				game.play();													// Continue play of the game
			}
		}
		
		// If a level is selected from the drop down menu, initialize a new layout for the corresponding level.
		else if (event.getSource() == levelList) {
			// Get the level selected from the drop down menu (JComboBox)
			JComboBox levelCB = (JComboBox) levelList;
			String stringLevelSelected = (String) levelCB.getSelectedItem();	
			int levelSelected = Integer.parseInt(stringLevelSelected.substring(stringLevelSelected.length() - 1));
			level = levelSelected;												// Set level to the selected level
			if (game.getHasPlayedGame())
				game.getTimer().stop();												// Stop the current timer
			game.initNewBoard(level);											// Initialize layout for level
			repaint();															// Repaint the board
		}	
	}
}