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
 * of the Cell class.
 * 
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTest {
	// Cells to use for tests:
	private Cell defaultTestCell;						// Instantiate using default constructor
	private Cell overloadedTestCell;					// Instantiate using overloaded constructor with 2 parameters
	private Cell overloadedTestCell2;					// Instantiate using overloaded constructor with 4 parameters
	private Cell copyTestCell;							// Instantiate using copy constructor
	

	@Before
	public void setUp() {
		// Instantiate all Cells before each Test.
		defaultTestCell = new Cell();									// Default constructor
		overloadedTestCell = new Cell(3, 10);							// Overloaded constructor, 2 parameters
		overloadedTestCell2 = new Cell(1, 3, Cell.SNAKE, Cell.SOUTH);	// Overloaded constructor, 4 parameters
		copyTestCell = new Cell(overloadedTestCell);					// Copy constructor
	}
	
	@Test
	public void testDefault() {
		// Tests default constructor and all getter methods.
		assertEquals(defaultTestCell.getXCoord() + defaultTestCell.getYCoord() + defaultTestCell.getCellType()
				+ defaultTestCell.getDirection(), -1, 0);
	}
	
	@Test
	public void testOverloaded() {
		// Tests overloaded constructor with 2 parameters and all getter methods.
		assertEquals(overloadedTestCell.getXCoord() + overloadedTestCell.getYCoord()
				+ overloadedTestCell.getCellType() + overloadedTestCell.getDirection(), 12, 0);
	}
	
	@Test
	public void testOverloaded2() {
		// Tests overloaded constructor with 4 parameters and all getter methods.
		assertEquals(overloadedTestCell2.getXCoord() + overloadedTestCell2.getYCoord()
				+ overloadedTestCell2.getCellType() + overloadedTestCell2.getDirection(), 7, 0);
	}
	
	@Test
	public void testCopy() {
		// Tests copy constructor and all getter methods.
		assertEquals(copyTestCell.getXCoord() + copyTestCell.getYCoord() + copyTestCell.getCellType()
				+ copyTestCell.getDirection(), 12, 0);
	}
	
	@Test
	public void testSetters() {
		// Tests all setters.
		defaultTestCell.setXCoord(2);									// Assign xCoord
		defaultTestCell.setYCoord(10);									// Assign yCoord
		defaultTestCell.setCellType(Cell.APPLE);						// Assign cellType
		defaultTestCell.setDirection(Cell.WEST);						// Assign direction
		
		assertEquals(defaultTestCell.getXCoord() + defaultTestCell.getYCoord() + defaultTestCell.getCellType()
				+ defaultTestCell.getDirection(), 17, 0);
	}
	
	@Test
	public void testSetCell() {
		// Tests method setCell(int, int, int, int).
		overloadedTestCell2.setCell(2, 5, Cell.EMPTY, Cell.DEFAULT);	// Assign xCoord, yCoord, cellType, direction
		
		assertEquals(overloadedTestCell2.getXCoord() + overloadedTestCell2.getYCoord()
				+ overloadedTestCell2.getCellType() + overloadedTestCell2.getDirection(), 6, 0);
	}
	
	@Test
	public void testSetCell2() {
		// Tests method setCell(Cell);
		copyTestCell.setCell(overloadedTestCell2);						// Assign xCoord, yCoord, cellType, direction
																		// of overloadedTestCell2 to copyTestCell
		assertEquals(copyTestCell.getXCoord() + copyTestCell.getYCoord() + copyTestCell.getCellType()
				+ copyTestCell.getDirection(), 7, 0);
	}
}