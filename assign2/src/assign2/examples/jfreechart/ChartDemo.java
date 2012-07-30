/**
 * 
 */
package assign2.examples.jfreechart;

import javax.swing.JFrame;

/**
 * @author hogan
 *
 */
public class ChartDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 BarChart bar = new BarChart("Chart Demo","5-grams");
		 bar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 bar.pack();
		 bar.setVisible(true);
	}

}
