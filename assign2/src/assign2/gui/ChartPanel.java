package assign2.gui;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/*
 * INN370 Assignment 2 (n7080212 & n7686854)
 * ChartPanel Class
 */
@SuppressWarnings("serial")
public class ChartPanel extends JPanel {
	
	private CategoryDataset dataset;
	private JFreeChart chart;
	private org.jfree.chart.ChartPanel chartPanel;
	
	public ChartPanel() {
        dataset = createDataset();
        chart = createChart(dataset, "");
        chartPanel = new org.jfree.chart.ChartPanel(chart);
        this.setLayout(new GridLayout(1, 1));
        this.add(chartPanel);
	}
	
	public void start(DefaultCategoryDataset defaultCategoryDataset) {
        dataset = defaultCategoryDataset;
        chart = createChart(dataset, "");
        chartPanel.setChart(chart);
	}	
	
    private  DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();        
        return dataset;
    }	

    private JFreeChart createChart(CategoryDataset dataset, String title) {    	
    	JFreeChart chart = ChartFactory.createBarChart3D(
    		title, 
     		"Phrase _____",
    		"Probability",
    		dataset, 
    		PlotOrientation.VERTICAL, 
    		true, 
    		false, 
    		false
    	);
    	
    	chart.setBackgroundPaint(Color.LIGHT_GRAY);    	
    	chart.setBorderPaint(Color.BLACK);
    	chart.getTitle().setPaint(Color.BLUE); 
    	CategoryPlot p = chart.getCategoryPlot(); 
    	p.setRangeGridlinePaint(Color.red); 
    			
        return chart;
    }
    
}