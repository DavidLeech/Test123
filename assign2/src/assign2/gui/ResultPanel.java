package assign2.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * INN370 Assignment 2 (n7080212 & n7686854)
 * ChartPanel Class
 */
@SuppressWarnings("serial")
public class ResultPanel extends JPanel {
	private JTextArea resultTextArea;

	public ResultPanel() {
		resultTextArea = new JTextArea("");
		resultTextArea.setEditable(false);
		resultTextArea.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 14));
		resultTextArea.setBackground(Color.lightGray);
		resultTextArea.setForeground(Color.black);

		this.setLayout(new GridLayout(1, 1));
		this.add(new JScrollPane(resultTextArea));
	}

	public void setText(String string) {
		resultTextArea.setText(string);
	}

	public void append(String string) {
		resultTextArea.append(string);
	}
	
}