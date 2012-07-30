package assign2.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.*;

import org.jfree.data.category.DefaultCategoryDataset;

import assign2.ngram.NGramException;
import assign2.ngram.NGramNode;
import assign2.ngram.NGramStore;

/*
 * INN370 Assignment 2 (n7080212 & n7686854)
 * NGramGUI Class
 */
@SuppressWarnings("serial")
public class NGramGUI extends JFrame {
	
	//GUI elements
	private Container c;
	private JPanel panelNorth, panelCenter, panelSouth, panelEast, panelWest;
	private JPanel panelCenter_1, panelCenter_2;
	private JPanel panelCenter_1_1, panelCenter_1_2, panelCenter_1_3, panelCenter_1_4;
	private JButton searchButton, switchButton;
	private JTextArea myTextField;
	private ResultPanel resultPanel;
	private ChartPanel chartPanel;	
	private JTextArea descriptionTextArea;
	
	//a how-to-use message that would be shown
	private final String description = 
			"How to use:\n" +
			"(1) Enter your query(5 words Max, only numeric, " +
			"alphabetical and single quote(') allowed).\n" +
			"(2) Hit 'Search' button to see results.\n"  +
			"(3) Switch between the text display " +
			"and chart display using 'Switch' button.\n" +
			"Note: You may enter multiple queries(5 queries MAX)" +
			" by inputing comma(,).";
	
	//constants stand for certain panel
	private final Integer RESULT_PANEL = 0;
	private final Integer CHART_PANEL = 1;
	
	private Integer currentPanel = RESULT_PANEL;
	
	private NGramStore ngs;

	public NGramGUI() {		
		addComponentListener(new ComponentHandler());
		
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		setTitle("NGram Suggestions");

		panelNorth = new JPanel();
		panelCenter = new JPanel();
		panelSouth = new JPanel();
		panelEast = new JPanel();
		panelWest = new JPanel();
		panelCenter.setLayout(new BorderLayout());
		
		//the how-to-use message on the top
		descriptionTextArea = new JTextArea();
		descriptionTextArea.setEditable(false);
		descriptionTextArea.setForeground(Color.black);
		descriptionTextArea.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 14));		
		descriptionTextArea.setText(description);
		
		panelNorth.setLayout(new GridLayout());
		panelNorth.add(descriptionTextArea);
		
		//the search button to send the queries
		searchButton = new JButton(" Search ");		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentPanel == CHART_PANEL) {
					panelCenter_2.removeAll();
					panelCenter_2.add(resultPanel);
					panelCenter_2.repaint();
					validate();
					currentPanel = RESULT_PANEL;
				}
				
				switchButton.setEnabled(false);
				doConsole();
			}
		});
		
		//the switch button to switch between panels
		switchButton = new JButton(" Switch ");
		switchButton.setEnabled(false);
		switchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel();
			}
		});
		
		//the text field for user input
		myTextField = new JTextArea("");
		myTextField.setEditable(true);
		myTextField.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 16));
		myTextField.setForeground(Color.BLUE);
		
		panelCenter_1_1 = new JPanel();
		panelCenter_1_1.setLayout(new GridLayout());
		panelCenter_1_1.add(new JScrollPane(myTextField));
		
		panelCenter_1_2 = new JPanel();
		panelCenter_1_2.setLayout(new GridLayout(1, 2, 3, 3));
		panelCenter_1_2.add(searchButton);
		panelCenter_1_2.add(switchButton);
		
		panelCenter_1_3 = new JPanel();
		panelCenter_1_4 = new JPanel();
		
		panelCenter_1 = new JPanel();		
		panelCenter_1.setLayout(new BorderLayout());
		panelCenter_1.add(panelCenter_1_1, BorderLayout.CENTER);
		panelCenter_1.add(panelCenter_1_2, BorderLayout.EAST);
		panelCenter_1.add(panelCenter_1_3, BorderLayout.NORTH);
		panelCenter_1.add(panelCenter_1_4, BorderLayout.SOUTH);
		
		resultPanel = new ResultPanel();
		chartPanel = new ChartPanel();
		
		panelCenter_2 = new JPanel();		
		panelCenter_2.setLayout(new GridLayout(1, 1));
		panelCenter_2.add(resultPanel);
		
		panelCenter.add(panelCenter_1, BorderLayout.NORTH);
		panelCenter.add(panelCenter_2, BorderLayout.CENTER);

		c = getContentPane();
		c.add(panelNorth, BorderLayout.NORTH);
		c.add(panelCenter, BorderLayout.CENTER);
		c.add(panelSouth, BorderLayout.SOUTH);
		c.add(panelWest, BorderLayout.WEST);
		c.add(panelEast, BorderLayout.EAST);
		
		ngs = new NGramStore();
	}
	
	private void doConsole() {
		resultPanel.setText("");
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.clear();
		
		String[] queries = null;
		
		try {
			//parse input to queries array
			queries = parseInputToQueries(myTextField. getText());
			
			//send and try to display results for each query in the queries array
			for (String query : queries) {
				String[] phrasesInQuery = parseQueryToPharses(query);
				NGramNode n = new NGramNode();
				n.setContext(phrasesInQuery);
				ngs.addNGram(n);
				displayQuery(n);
				
				//set the result to display or display no result when no result
				if (ngs.getNGramsFromService(n.getContext(), 5)) {					
					
					for(int i = 0; i < n.getProbabilities().length; i++) {
						dataset.setValue(n.getProbabilities()[i], n.getContext(), n.getPredictions()[i]);						
					}//end for
					
					resultPanel.append(n.toString());					
					switchButton.setEnabled(true);
				} else {
					displayNoResults();	
				}//end if
				
			}//end for
			
			chartPanel.start(dataset);
		} catch (NGramException e) {
			resultPanel.setText(e.toString());
		}
	}
	
	/*
	 * Helper method to parse input String to String Array
	 */
	private String[] parseInputToQueries(String input) throws NGramException {
		if (input == null || input.trim().equals(""))
			throw new NGramException("Input field is empty.");
		else if (!isMeaningful(input))
			throw new NGramException("Input has no meaning.");
		else if (isInvalidInput(input))
			throw new NGramException("Input can only be numeric," +
									 " alphabetic, space( ), single quote(') or comma(,).");
		
		String[] tempArray = parseStringToStringArray(input, "[,]+");

		if (tempArray.length > 5)
			throw new NGramException("The number of queries can only be 5 or less.");		
			
		return tempArray;
	}
	
	/*
	 * Helper method to return true if input is reasonably valid but not meaningful
	 * ",, ,,'''", for instance, has valid symbols yet dose not make any sense 
	 */
	private boolean isMeaningful(String input) {
		String regex = "[^ ,']";
		Pattern p = Pattern.compile(regex);
		
		return p.matcher(input).find();		
		
	}
	
	/*
	 * Helper method to return true if input is invalid
	 * valid input only includes A-Z a-z 0-9 and space( ) comma(,) single quote(')
	 */
	private boolean isInvalidInput(String input) {		
		String regex = "[^ ,'A-Za-z0-9]";
		Pattern p = Pattern.compile(regex);
		
		return p.matcher(input).find();
	}
	
	/*
	 * Helper method to parse query String to Array(single word as each elements)
	 */
	private String[] parseQueryToPharses(String query) throws NGramException {		
		String[] tempArray = parseStringToStringArray(query, "[ ]+");		
		
		if (tempArray.length > 5)
			throw new NGramException("The number of phrase in each query can only be 5 or less.");
					
		return tempArray;
	}
	
	/*
	 * Helper method to parse String to String Array split by certain delimiter
	 * can deal with Empty elements
	 * e.g. "a, ,b, ,,c" => {"a", "b", "c"}
	 */
	private String[] parseStringToStringArray(String originalString, String delimiter) {
		ArrayList<String> tempList = new ArrayList<String>();
		String[] tempArray = originalString.trim().split(delimiter);
		
		for (int i = 0; i < tempArray.length; i++) {
			tempArray[i] = tempArray[i].trim(); 
			
			if (!tempArray[i].equals(""))
				tempList.add(tempArray[i]);
				
		}//end for
			
		return tempList.toArray(new String[tempList.size()]);
	}
		
	
	private void displayQuery(NGramNode n) {
		StringBuffer sb = new StringBuffer();
		sb.append("NGram Results for Query: ");
		sb.append(n.getContext());
		sb.append("\n\n");
		resultPanel.append(sb.toString());
	}
	
	private void displayNoResults() {
		StringBuffer sb = new StringBuffer();
		sb.append("No ngram predictions were returned.\n");
		sb.append("Please try another query.\n\n");	
		resultPanel.append(sb.toString());		
	}
	
	private void switchPanel() {
		if (currentPanel == 0) {
			panelCenter_2.removeAll();
			panelCenter_2.add(chartPanel);
			panelCenter_2.repaint();
			validate();
			currentPanel = 1;
		} else {
			panelCenter_2.removeAll();
			panelCenter_2.add(resultPanel);
			panelCenter_2.repaint();
			validate();
			currentPanel = 0;
		}
	}

	private class ComponentHandler extends ComponentAdapter {
		public void componentResized(ComponentEvent e) {
			if (((JFrame) e.getSource()).getWidth() < 700 || ((JFrame) e.getSource()).getHeight() < 400) {
				((JFrame) e.getSource()).setSize(700, 400);
			}
		}
	}
}