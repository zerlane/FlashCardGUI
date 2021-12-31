package ncc1023_hw4;

/**
 * 
 * This class creates a FlashCard
 * @author Nautishay Cain
 *
 */
public class FlashCard {
	private String word, definition;
	
	public FlashCard() {
		
	}
	
	public FlashCard(String word, String definition) {
		this.word = word;
		this.definition = definition;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getDefinition() {
		return this.definition;
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	/**
	 * randomly displays either the word or definition to answer
	 * @return random choice
	 */
	public String displayQuestion(FlashCard fc) {
		int rand = (int) (Math.random() * 2 + 1);
		String choice = "";
		
		switch (rand) {
		case 1:
			choice = fc.getWord();
			break;
		case 2:
			choice = fc.getDefinition();
			break;
		}
		
		return choice;
	}
	
	@Override
	public String toString() {
		return this.getWord() + " - " + this.getDefinition();
	}
}
