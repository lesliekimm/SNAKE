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
 * DESCRIPTION: This JUnit Test File tests all constructors and methods
 * of the Walls class.
 * 
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WallsUnitTest {
	// Walls to use for tests:
	private Cell[][] board;
	private Walls wallsLevel1;
	private Walls wallsLevel2;
	private Walls wallsLevel3;
	
	@Before
	public void setUp() {
		board = new Cell[Board.ROWS][Board.COLS];
		// Instantiate Walls for test cases.
		wallsLevel1 = new Walls(board, 1);				// Instantiate wallsTest1: Overloaded constructor, Level 1
		wallsLevel2 = new Walls(board, 2);				// Instantiate wallsTest2: Overloaded constructor, Level 2
		wallsLevel3 = new Walls(board, 3);				// Instantiate wallsTest3: Overloaded constructor, Level 3
	}
	
	@Test
	public void testWalls() {
		// Tests constructor for each possible level as well as getWalls() method.
		assertEquals(wallsLevel1.getWalls().size(), 0, 0);			// Level1 test
		assertEquals(wallsLevel2.getWalls().size(), 60, 0);			// Level 2 test
		assertEquals(wallsLevel3.getWalls().size(), 140, 0);		// Level 3 test
	}
	
	@Test
	public void testGetWallsSize() {
		// Tests getWallSize() method.
		assertEquals(wallsLevel3.getWallsSize(), 140, 0);			// Level 3 test
	}
}