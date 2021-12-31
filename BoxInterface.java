package ncc1023_hw4;

import java.util.List;
/**
 * 
 * @author Nautishay Cain
 *
 */
public interface BoxInterface {

	public void addFlashCard(FlashCard fc);
	public void deleteFlashCard(FlashCard fc);
	public FlashCard chooseFlashCard(List<FlashCard> box);
	
}
