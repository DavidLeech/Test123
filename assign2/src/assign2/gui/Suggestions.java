package assign2.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

/*
 * INN370 Assignment 2 (n7080212 & n7686854)
 * Suggestions Class
 */
public class Suggestions {
	
	public Suggestions() {
		NGramGUI nGUI = new NGramGUI();
		nGUI.setSize(700, 400);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = nGUI.getSize();
		
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		
		nGUI.setLocation((screenSize.width - frameSize.width) / 2,
			(screenSize.height - frameSize.height) / 2 - 30);
			
		nGUI.setVisible(true);
	}
	
	public static void main(String args[]){
		new Suggestions();
	}
	
}