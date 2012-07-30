/**
 * 
 */
package assign2.examples.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import assign2.examples.jfreechart.BarChart;

/**
 * @author hogan
 *
 */
public class SimpleFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;

	private JPanel btmPanel;
	private JPanel textPanel;
	private JTextArea textDisplay;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 SimpleFrame gui = new SimpleFrame("Swing GUI Demo");
		 gui.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		 gui.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		 gui.pack();
		 gui.setVisible(true);
	}
	
	
	/**
	 * @param arg0 - the Frame Title
	 */
	public SimpleFrame(String arg0) throws HeadlessException {
		super(arg0);
		createGUI();
	}


	// helper method to construct the GUI 
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
    
	    textDisplay = new JTextArea("This is a default text message");
	    textDisplay.setEditable(true);
	    
	    textPanel = new JPanel(); 
	    textPanel.setBackground(Color.LIGHT_GRAY);
	    textPanel.setLayout(new BorderLayout());
	    textPanel.add(textDisplay,BorderLayout.CENTER);
	    
	    this.getContentPane().add(textPanel,BorderLayout.CENTER);

	    btmPanel = new JPanel();
	    btmPanel.setBackground(Color.LIGHT_GRAY);
        btmPanel.setLayout(new FlowLayout());

 	    JButton blueButton = new JButton("Blue");
	    blueButton.setBackground(Color.WHITE);
	    blueButton.addActionListener(this);
	    btmPanel.add(blueButton);

	    JButton blackButton = new JButton("Black");
	    blackButton.setBackground(Color.WHITE);
	    blackButton.addActionListener(this);
	    btmPanel.add(blackButton);

	    this.getContentPane().add(btmPanel, BorderLayout.SOUTH);	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonString = e.getActionCommand();

		  if (buttonString.equals("Blue")) {
			  this.textDisplay.setBackground(Color.BLUE);
			  this.textDisplay.setForeground(Color.WHITE);
		  } else if (buttonString.equals("Black")) {
			  this.textDisplay.setBackground(Color.BLACK);
			  this.textDisplay.setForeground(Color.GREEN);
		  }
	}

}
