import static org.junit.Assert.*;

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
 * This JUnit Test tests all Constructors and methods of the Snake class.
 *
 */

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SnakeUnitTest {
	// Snakes to use for tests:
	private Snake snakeNoSpecifiedLevel;
	private Snake snakeLevel1;
	private Snake snakeLevel2;
	private Snake snakeLevel3;
	
	@Before
	public void setup() {
		// Instantiate Snakes for test cases.
		snakeNoSpecifiedLevel = new Snake();			// Instantiate snakeNoSpecifiedLevel: Default constructor
		snakeLevel1 = new Snake(1);						// Instantiate snakeLevel1: Overloaded constructor, Level 1
		snakeLevel2 = new Snake(2);						// Instantiate snakeLevel2: Overloaded constructor, Level 2
		snakeLevel3 = new Snake(3);						// Instantiate snakeLevel3: Overloaded constructor, Level 3
	}
	
	@Test
	public void testConstructorsAndGetSnakeLength() {
		// Test constructors and getSnakeLength() method.
		// Each assertEquals tests a constructor for a given level and the getSnakeLength() method.
		assertEquals(snakeNoSpecifiedLevel.getSnakeLength(), 1, 0);		// Test default constructor
		assertEquals(snakeLevel1.getSnakeLength(), 1, 0);					// Test overloaded constructor, Level 1
		assertEquals(snakeLevel2.getSnakeLength(), 5, 0);					// Test overloaded constructor, Level 2
		assertEquals(snakeLevel3.getSnakeLength(), 10, 0);					// Test overloaded constructor, Level 3
	}
	
	@Test
	public void testGetSnakeAndAdd() {
		// Tests getSnake() and Add(int) methods.
		
		// Create ArrayList<Cell> that Snake object for Level 2 should be.
		ArrayList<Cell> snakeComparer = new ArrayList<Cell>();
		for (int index = Board.ROWS - snakeLevel2.getSnakeLength(); index < Board.ROWS; index++)
			snakeComparer.add(new Cell(0, index, Cell.SNAKE, Cell.NORTH));	// Test add(int) method
		
		// Test getSnake() method.
		for (int index = 0; index < 5; index++) {
			assertEquals(snakeLevel2.getSnake().get(index).getXCoord(), snakeComparer.get(index).getXCoord());
			assertEquals(snakeLevel2.getSnake().get(index).getYCoord(), snakeComparer.get(index).getYCoord());
			assertEquals(snakeLevel2.getSnake().get(index).getCellType(), snakeComparer.get(index).getCellType());
			assertEquals(snakeLevel2.getSnake().get(index).getDirection(), snakeComparer.get(index).getDirection());
		}
	}

	@Test
	public void testAddTwoParams() {
		// Tests add(int, int) method.
		Cell cellToAdd = new Cell(5, 2, Cell.WALL, Cell.DEFAULT);			// Instantiate Cell object to add
		snakeLevel3.add(cellToAdd, 4);										// Insert at specified index
		
		// Test add method by checking to see that Cell was inserted at correct index and that snakeLegth was
		// incremented.
		assertEquals(snakeLevel3.getSnake().get(4).getXCoord() + snakeLevel3.getSnake().get(4).getYCoord()
				+ snakeLevel3.getSnake().get(4).getCellType() + snakeLevel3.getSnake().get(4).getDirection(), 9, 0);
		assertEquals(snakeLevel3.getSnakeLength(), 11, 0);
	}
	
	@Test
	public void testRemove() {
		// Test remove(int) method.
		snakeLevel2.remove(2);												// Remove at specified index
		
		assertEquals(snakeLevel2.getSnakeLength(), 4, 0);					// Test remove (int) method
	}
}