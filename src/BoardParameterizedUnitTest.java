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
 * DESCRIPTION: This Parameterized JUnit Test tests the
 * calculateAndGetScore(int) method of the Board class.
 * 
 */

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class BoardParameterizedUnitTest {
	// Private members for parameterized test
	private int expectedScore;
	private int numOfApples;
	
	SnakeGUI game;
	
	public BoardParameterizedUnitTest(int expectedScore, int numOfApples) {
		this.expectedScore = expectedScore;
		this.numOfApples = numOfApples;
	}
	
	@Parameters
	public static Collection<Object[]> getTestParameters() {
		Object[][] myTestParameters = new Object[][] {
				{ 300, 3 }, { 5000, 50 }, { 100, 1 }};
		return Arrays.asList(myTestParameters);
	}

	@Test
	public void testCalculateScore() {
		Board myBoard = new Board(game);
		assertEquals(expectedScore, myBoard.calculateAndGetScore(numOfApples), 0);
	}
}
