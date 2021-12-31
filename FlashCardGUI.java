package ncc1023_hw4;

import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
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
					
					FlashCard newFC = new FlashCard(word, definition);
					flashcards.add(newFC);
					
					updateFlashCardDB(flashcards);
					
					JOptionPane.showMessageDialog(mainPanel, "New word added!");
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
	
	public static void main(String[] args) {
		new FlashCardGUI();
	}

}
