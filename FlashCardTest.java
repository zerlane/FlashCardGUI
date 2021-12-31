package ncc1023_hw4;

import static org.junit.Assert.*; 
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * The FlashCardTest implements JUnit testing for the FlashCard class.
 * @author Nautishay Cain
 *
 */
public class FlashCardTest {
	FlashCard fc = new FlashCard("term", "definition");
	
	/**
	 * 
	 * tests to see if word is gotten
	 */
	@Test
	public void testGetWord() {
		assertTrue(fc.getWord() == "term");
	}
	
	
}
