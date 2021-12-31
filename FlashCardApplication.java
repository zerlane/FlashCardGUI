package ncc1023_hw4;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;

/**
 * 
 * Uses a text file to create flashcards
 * @author Nautishay Cain
 *
 */
public class FlashCardApplication {

	public static void main(String[] args) {
		// create five boxes - chances are lower to see a card in box 5 than box 1.

		String word, definition;
		ArrayList<FlashCard> flashcards = new ArrayList<FlashCard>();
		
		String filePath = "/Users/zerlane/Documents/UAB Fall21/CS203/Homework/HW4/definitions.txt";
		File file = new File(filePath);
		
		PrintWriter pw = null;
		
		//Box objects
		Box box = new Box();
		Box box1 = new Box();
		Box box2 = new Box();
		Box box3 = new Box();
		Box box4 = new Box();
		Box box5 = new Box();
		
		ArrayList<Box> boxes = new ArrayList<Box>(5);
		boxes.add(box1);
		boxes.add(box2);
		boxes.add(box3);
		boxes.add(box4);
		boxes.add(box5);
		box.setBoxList(boxes);
		
		ArrayList<FlashCard> flashcardNBox1 = flashcards;
		ArrayList<FlashCard> flashcardNBox2 = new ArrayList<FlashCard>();
		ArrayList<FlashCard> flashcardNBox3 = new ArrayList<FlashCard>();
		ArrayList<FlashCard> flashcardNBox4 = new ArrayList<FlashCard>();
		ArrayList<FlashCard> flashcardNBox5 = new ArrayList<FlashCard>();
		
		try {
			Scanner sc = new Scanner(file);
			
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] lineElem = line.split(" - ");
				
				word = lineElem[0];
				definition = lineElem[1];
				
				FlashCard fc = new FlashCard(word, definition);
				flashcards.add(fc);
			}		
			
		} catch (FileNotFoundException fNfe) {
			System.out.println("File not found!");
		}
		
		//create menu
		Scanner sc = new Scanner(System.in);
		
		boolean run = true;
		while(run) {
			System.out.println("Choose from the following options:");
			System.out.println("");
			System.out.println("To display list of FlashCards, enter 'L'.");
			System.out.println("To study, enter 'S'.");
			System.out.println("To add a FlashCard, enter 'A'.");
			System.out.println("To exit program, enter 'E'.");
			
			
			String choice = sc.nextLine();
			System.out.println(" ");
			
			switch(choice) {
			case "L":
				for (FlashCard flashcard : flashcards) {
					System.out.println(flashcard);
				}
				System.out.println("");
				break;
			case "S":
				boolean study = true;
				
				//stores flashcards in corresponding box
				box1.setFlashCards(flashcardNBox1);
				box2.setFlashCards(flashcardNBox2);
				box3.setFlashCards(flashcardNBox3);
				box4.setFlashCards(flashcardNBox4);
				box5.setFlashCards(flashcardNBox5);
				
				Box currentBox = box1;
				
				FlashCard studyCard = new FlashCard();
				List<FlashCard> currentFlashCardSet = currentBox.getFlashCards();
				
				while(study) {
					
					
					if(currentFlashCardSet.size() != -1) {
						try {
							studyCard = currentBox.chooseFlashCard(currentFlashCardSet);
						} catch(IndexOutOfBoundsException e) {
							System.out.println("Program didn't generate a proper integer. Program will try again.");
							System.out.println("");
							
						}
					}
					
					
					
					String question = studyCard.displayQuestion(studyCard); 
					word = studyCard.getWord();
					definition = studyCard.getDefinition();
					
					String answer = "";
					
					
					System.out.println(question);
					
					//flip card
					System.out.println("Flip card, 'Y' or 'N'.");
					String flipCard = sc.nextLine();
					
					if(flipCard.equals("Y")) {
						if(question.equals(studyCard.getWord())) {
							System.out.println(studyCard.getDefinition());
						} else {
							System.out.println(studyCard.getWord());
						}
						break;
					}
				
					
					boolean incorrect = true;
					
					if(question.equals(word)) {
						System.out.println("Enter definition: ");
						
						answer = sc.nextLine();
						if(answer.equals(definition)) {
							incorrect = false;
						}
						
					} else if(question.equals(definition)) {
						
						System.out.println("Enter word: ");
						
						answer = sc.nextLine();
						if(answer.equals(word)) {
							incorrect = false;
						}
					}
					
					System.out.println("");
	
					//check answer
					if(incorrect) {
						currentBox.deleteFlashCard(studyCard);
						box1.addFlashCard(studyCard);
						currentBox = box1;
						
						System.out.println("Incorrect!");
						System.out.println("");
					} else if (!incorrect) {
						
						currentBox.deleteFlashCard(studyCard);
						
						int index = boxes.indexOf(currentBox);
						
						currentBox = boxes.get(index + 1);
						currentBox.addFlashCard(studyCard);
						System.out.println("Correct!");
						System.out.println("");
					}
					
					System.out.println("Delete, Enter 'Y' or 'N'");
					
					if(sc.nextLine().equals("Y")) {
						currentBox.deleteFlashCard(studyCard);
						flashcards.remove(studyCard);
						
						//update file
						try {
							pw = new PrintWriter(file);
							
							for(FlashCard flashcard : flashcards) {
								pw.println(flashcard);
							}
							System.out.println("FlashCard has been deleted! Refresh program.");
							
						} catch (Exception e) {
							System.out.println("Could not delete FlashCard to file!");
						} finally {
							pw.close();
						}
					} 	
					
					System.out.println(" ");
					//choose box based on probability
					
					double rand = Math.random();
			        
			        if(rand < 0.05) {
			        	if(box5.getFlashCards().size() != 0) {
			        		currentBox = box5;
			        	} else {
			        		rand = Math.random();
			        	}
			            
			        } else if (0.05 <= rand && rand < 0.15) {
			        	if(box4.getFlashCards().size() != 0) {
			        		currentBox = box4;
			        	} else {
			        		rand = Math.random();
			        	}
			        } else if (0.15 <= rand && rand < 0.30) {
			        	if(box3.getFlashCards().size() != 0) {
			        		currentBox = box3;
			        	} else {
			        		rand = Math.random();
			        	}
			        } else if (0.30 <= rand && rand < 0.50) {
			        	if(box2.getFlashCards().size() != 0) {
			        		currentBox = box2;
			        	} else {
			        		rand = Math.random();
			        	}
			        } else if (0.50 <= rand && rand < 1) {
			        	if(box1.getFlashCards().size() != 0) {
			        		currentBox = box1;
			        	} 
			        }
			        
					
					
					System.out.println("Continue, 'Y' or 'N'");
					String continueStudy = sc.nextLine();
					if(continueStudy.equals("N")) {
						study = false;
						break;
					} 
					
					System.out.println("");
				}
			
				System.out.println("");
				break;
			case "A":
				System.out.println("Enter new word: ");
				word = sc.nextLine();
				
				System.out.println("Enter the definition of " + word + ":");
				definition = sc.nextLine();
				
				FlashCard newFlashCard = new FlashCard(word, definition);
				flashcards.add(newFlashCard);
				
				//update file
				try {
					pw = new PrintWriter(file);
					
					for(FlashCard flashcard : flashcards) {
						pw.println(flashcard);
					}
					System.out.println("FlashCard has been added! Refresh program.");
					
				} catch (Exception e) {
					System.out.println("Could not add new FlashCard to file!");
				} finally {
					pw.close();
				}
				
				break;
			case "E":
				run = false;
				System.out.println("See you next time!");
				break;
			}
		}
		
	}

}
