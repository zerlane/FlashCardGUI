package ncc1023_hw4;

import static org.junit.Assert.*; 
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * 
 * The BoxTest class implements JUnit testing for Box class.
 * @author Nautishay Cain
 *
 */
public class BoxTest {

	private Box box;
	private List<FlashCard> boxList;
	
	private FlashCard fc1 = new FlashCard("word1", "definition1");
	private FlashCard fc2 = new FlashCard("word2", "definition2");
	private FlashCard fc3 = new FlashCard("word3", "definition3");
	private FlashCard fc4 = new FlashCard("word4", "definition4");
	private FlashCard fc5 = new FlashCard("word5", "definition5");
	
	@Before
	public void setUpBox() {
		box = new Box();
		box.addFlashCard(fc1);
		box.addFlashCard(fc2);
		box.addFlashCard(fc3);
		box.addFlashCard(fc4);
		
	}
	
	/**
	 * 
	 * tests adding a flashcard to a list
	 */
	@Test
	public void testAddFlashCard() {
		box.addFlashCard(fc5);
		assertTrue(box.getFlashCards().contains(fc5));
	}
	
	/**
	 * 
	 * tests listing flashcards
	 */
	@Test
	public void testGetFlashCards() {
		box.getFlashCards();
		assertEquals(box.getFlashCards().size(), 4);
		System.out.println(box.getFlashCards());
	}
	
	/**
	 * 
	 * test deleting a flashcard
	 */
	@Test
	public void testDeleteFlashCard() {
		box.deleteFlashCard(fc3);
		assertTrue(!box.getFlashCards().contains(fc3));
	}

}
