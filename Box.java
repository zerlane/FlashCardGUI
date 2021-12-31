package ncc1023_hw4;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 * Creates a box to store FlashCards
 * @author Nautishay Cain
 *
 */
public class Box implements BoxInterface {
	private List<FlashCard> box;
	private List<Box> allBoxes; 
	
	public Box() {
		this.box = new ArrayList<FlashCard>();
		this.allBoxes = new ArrayList<Box>(5);
	}
	
	

	public void addFlashCard(FlashCard fc) {
		box.add(fc);
	}
	
	public void deleteFlashCard(FlashCard fc) {
		box.remove(fc);
	}
	
	public List<FlashCard> getFlashCards() {
		return this.box;
	}
	
	public void setFlashCards(List<FlashCard> flashcards) {
		this.box = flashcards;
	}
	
	public List<Box> getBox() {
		return allBoxes;
	}
	
	public void setBoxList(List<Box> box) {
		this.allBoxes = box;
	}
	
	/**
	 * 
	 * chooseFlashCards chooses a random flashcard from the correlating box to study
	 * @param box contains a list of box
	 * @return a random flashcard inside of the current box
	 */
	public FlashCard chooseFlashCard(List<FlashCard> box) {
		int boxSize = box.size();	
		int rand = (int) (Math.random() * (boxSize - 1) + 1);
		
		FlashCard flashcard = box.get(rand);
		
		return flashcard;
	}

}
