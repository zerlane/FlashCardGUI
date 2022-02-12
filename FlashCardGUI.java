package ncc1023_hw4;

import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FlashCardGUI {

	public FlashCardGUI() {
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("FlashCard Program");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		frame.setSize(400, 400);
		
		
		JPanel panel = createMainPanel();
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static JPanel createMainPanel() {
		JPanel mainPanel = new JPanel();
		
		List<FlashCard> flashcards = readInFlashCards();
		List<Box> box = new ArrayList<Box>();
		
		JPanel buttonPanel = new JPanel();
		
		BoxLayout layout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
		
		JButton listFC = new JButton("List FlashCards.");
		listFC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String fcString = "";
					for(FlashCard fc : flashcards) {
						fcString += fc.toString() + "\n";
					}
					JOptionPane.showMessageDialog(mainPanel, fcString);
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(mainPanel, ex);
				}
			}
		});
		
		JButton createFC = new JButton("Create FlashCards.");
		createFC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FlashCard lastElem = flashcards.get(flashcards.size() - 1);
					String word = JOptionPane.showInputDialog(mainPanel, "Enter word");
					String definition = JOptionPane.showInputDialog(mainPanel, "Enter definition");
						
					if(!word.isBlank() || !definition.isBlank()) {
						FlashCard newFC = new FlashCard(word, definition);
						flashcards.add(newFC);
						updateFlashCardDB(flashcards);
						JOptionPane.showMessageDialog(mainPanel, "New word added!");
					} else {
						JOptionPane.showMessageDialog(mainPanel, "Null can't be added!");
					}
		
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(mainPanel, ex);
				}
			}
		});
		
		JButton studyFC = new JButton("Study FlashCards.");
			//perform randomness
			//place in box
			//perform probability for box placement
			//delete fc
		studyFC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//might have to be redone for placement of boxes.
					
					List<Box>mainBox = getStudy();
					Box currentBox = mainBox.get(5);
					List<FlashCard> currentFlashCardSet = currentBox.getFlashCards();
					FlashCard studyCard = currentBox.chooseFlashCard(currentFlashCardSet);
					System.out.println(studyCard);
					
					
					String word = studyCard.getWord();
					String definition = studyCard.getDefinition();
					String answer = "";
					
					boolean isCorrect = true;
					
					String question = studyCard.displayQuestion(studyCard);
					if(question.equals(word)) {
						answer = JOptionPane.showInputDialog(mainPanel, "What is the definition of: " + question + "?");
						
						if(!answer.equals(definition)) {
							isCorrect = false;
							JOptionPane.showMessageDialog(mainPanel, "Incorrect!");
						} else {
							JOptionPane.showMessageDialog(mainPanel, "Correct!");

						}
						
					} else {
						answer = JOptionPane.showInputDialog(mainPanel, "What word is associataed with: " + question + "?");
						
						if(!answer.equals(word)) {
							isCorrect = false;
							JOptionPane.showMessageDialog(mainPanel, "Incorrect!");
						} else {
							JOptionPane.showMessageDialog(mainPanel, "Correct!");

						}
					}
					
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(mainPanel, ex);
				}
			}
		});
		
		JButton exitFC = new JButton("Exit Program.");
		exitFC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		buttonPanel.add(listFC);
		buttonPanel.add(createFC);
		buttonPanel.add(studyFC);
		buttonPanel.add(exitFC);
		
		mainPanel.add(buttonPanel);
		
		return mainPanel;
	}
	
	public static List<FlashCard> readInFlashCards() {
		List<FlashCard> flashcards = new ArrayList<FlashCard>();
		String word, definition;
		
		String fcFilePath = "/Users/zerlane/Documents/UAB Fall21/CS203/Homework/HW4/definitions.txt";
		File fcFile = new File(fcFilePath);
		
		try {
			Scanner sc = new Scanner(fcFile);
			
			while(sc.hasNext()) {
				String line = sc.nextLine();
				String[] lineElem = line.split(" - ");
				
				word = lineElem[0];
				definition = lineElem[1];
				
				FlashCard fc = new FlashCard(word, definition);
				flashcards.add(fc);
			}
		} catch(FileNotFoundException fNfe) {
			JOptionPane.showMessageDialog(null, fNfe);
			System.err.println("Database not found!");
		}
		
		return flashcards; 
	}
	
	public static List<FlashCard> updateFlashCardDB(List<FlashCard> flashcards) {
		String fcFilePath = "/Users/zerlane/Documents/UAB Fall21/CS203/Homework/HW4/definitions.txt";
		File fcFile = new File(fcFilePath);
		
		try {
			PrintWriter pw = new PrintWriter(fcFile);
			
			for(FlashCard fc : flashcards) {
				pw.println(fc.toString());
			}
			pw.close();
		} catch (Exception ex) {
			System.err.println("Could not update database!");
		}
		
		return flashcards;
	}
	
	public static int getRandom() {
		double rand = Math.random();
		int boxNum = 1;
		
		if(rand < 0.05) {
			boxNum = 5;
		} else if(0.05 <= rand && rand < 0.15) {
			boxNum = 4;
		} else if(0.15 <= rand && rand < 0.30) {
			boxNum = 3;
		} else if (0.30 <= rand && rand < 0.50) {
			boxNum = 2;
		}
		
		return boxNum;
	}
	
	public static List<Box> getStudy() {
		
		
		int rand = getRandom();
		
		//set up boxes
		Box box1 = new Box();
		Box box2 = new Box();
		Box box3 = new Box();
		Box box4 = new Box();
		Box box5 = new Box();
		
		
		
		List<FlashCard> flashcardNBox1 = readInFlashCards();
		List<FlashCard> flashcardNBox2 = new ArrayList<FlashCard>();
		List<FlashCard> flashcardNBox3 = new ArrayList<FlashCard>();
		List<FlashCard> flashcardNBox4 = new ArrayList<FlashCard>();
		List<FlashCard> flashcardNBox5 = new ArrayList<FlashCard>();
		
		box1.setFlashCards(flashcardNBox1);
		box2.setFlashCards(flashcardNBox2);
		box3.setFlashCards(flashcardNBox3);
		box4.setFlashCards(flashcardNBox4);
		box5.setFlashCards(flashcardNBox5);
		
		Box currentBox = box1;
		List<FlashCard> currentFlashCardSet = currentBox.getFlashCards();
		FlashCard studyCard = currentBox.chooseFlashCard(currentFlashCardSet);
		
		List<Box> mainBox = new ArrayList<Box>();
		mainBox.add(box1);
		mainBox.add(box2);
		mainBox.add(box3);
		mainBox.add(box4);
		mainBox.add(box5);
		mainBox.add(currentBox);
		
		if(rand < 0.05) {
        	if(box5.getFlashCards().size() != 0) {
        		currentBox = box5;
        		System.out.println("Box 5");
        	} else {
        		rand = getRandom();
        	}
            
        } else if (0.05 <= rand && rand < 0.15) {
        	if(box4.getFlashCards().size() != 0) {
        		currentBox = box4;
        		System.out.println("Box 4");
        	} else {
        		rand = getRandom();
        	}
        } else if (0.15 <= rand && rand < 0.30) {
        	if(box3.getFlashCards().size() != 0) {
        		currentBox = box3;
        		System.out.println("Box 3");
        	} else {
        		rand = getRandom();
        	}
        } else if (0.30 <= rand && rand < 0.50) {
        	if(box2.getFlashCards().size() != 0) {
        		currentBox = box2;
        		System.out.println("Box 2");
        	} else {
        		rand = getRandom();
        	}
        } else if (0.50 <= rand && rand < 1) {
        	if(box1.getFlashCards().size() != 0) {
        		currentBox = box1;
        		System.out.println("Box 1");
        	} 
        }
		
//		Map<List<FlashCard>, Box> map = new HashMap();
//		map.put(currentFlashCardSet, currentBox);
//		
//		
		return mainBox;
	}
	
	
	public static void main(String[] args) {
		new FlashCardGUI();
	}

}
