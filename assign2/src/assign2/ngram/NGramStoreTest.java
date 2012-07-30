package assign2.ngram;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/*
 * INN370 Assignment 2 (n7080212 & n7686854)
 * Test class for NGramStore
 */
public class NGramStoreTest {
	
	private NGramStore ngs;
	
	//Variables to set up few NGramNode objects
	private NGramNode n1 = new NGramNode();
	private NGramNode n2 = new NGramNode();
	private NGramNode n3 = new NGramNode();
	
	private final String n1Context = "query one";
	private final String n2Context = "query two";
	private final String n3Context = "query three";
	
	private final String[] n1DummyPredictions = {"first", "second", "thrid", "fourth", "fifth"};
	private final String[] n2DummyPredictions = {"one", "two", "three", "four", "five"};
		
	private final Double[] n1Dummyprobabilities = {0.4, 0.3, 0.1, 0.1, 0.1};
	private final Double[] n2Dummyprobabilities = {0.5, 0.1, 0.1, 0.1, 0.1};
	
	//the expected output of the toString method
	private final String expectedString = "query one | first : 0.4\n" +
			                              "query one | second : 0.3\n" +
			                              "query one | thrid : 0.1\n" +
			                              "query one | fourth : 0.1\n" +
			                              "query one | fifth : 0.1\n" +
			                              "\n";
			  
	/*
	 * Set up a NGramStore and NgramNodes
	 * with some valid sample or dummy variables
	 * No NGram exception should be thrown
	 */	
	@Before @Test
	public void setUp() throws NGramException {
		this.ngs = new NGramStore();
		this.n1.setContext(this.n1Context);
		this.n2.setContext(this.n2Context);
		this.n3.setContext(this.n3Context);
		this.n1.setPredictions(n1DummyPredictions);
		this.n2.setPredictions(n2DummyPredictions);
		this.n1.setProbabilities(n1Dummyprobabilities);
		this.n2.setProbabilities(n2Dummyprobabilities);
	}
	
	/*
	 * Test method for addNGram()
	 */
	@Test
	public void testAddNGram() {
		this.ngs.addNGram(this.n1);
		this.ngs.addNGram(this.n2);
		assertEquals(this.n1, ngs.getNGram(this.n1Context));
		assertEquals(this.n2, ngs.getNGram(this.n2Context));
		assertEquals(null, ngs.getNGram(this.n3Context));
	}

	/*
	 * Test method for removeNGram()
	 */
	@Test
	public void testRemoveNGram() {
		this.ngs.addNGram(this.n1);
		this.ngs.addNGram(this.n2);
		this.ngs.addNGram(this.n3);
		assertEquals(this.n3, ngs.getNGram(this.n3Context));
		this.ngs.removeNGram(n3Context);
		assertEquals(this.n1, ngs.getNGram(this.n1Context));
		assertEquals(this.n2, ngs.getNGram(this.n2Context));
		assertEquals(null, ngs.getNGram(this.n3Context));
		this.ngs.removeNGram(n2Context);
		assertEquals(null, ngs.getNGram(this.n2Context));
	}
	
	/*
	 * Test method for getNGram()
	 */
	@Test
	public void testGetNGram() {
		this.ngs.addNGram(this.n3);
		this.ngs.addNGram(this.n2);		
		assertEquals(null, ngs.getNGram(this.n1Context));
		assertEquals(this.n3, ngs.getNGram(this.n3Context));
		assertEquals(this.n2, ngs.getNGram(this.n2Context));
	}
	
	/*
	 * Test method for toString()
	 */
	@Test
	public void testToString() {
		this.ngs.addNGram(this.n1);
		assertEquals(this.expectedString, ngs.toString());
		this.ngs.addNGram(this.n2);	
		assertEquals(false, (ngs.toString().equals(expectedString)));
	}
	
}
