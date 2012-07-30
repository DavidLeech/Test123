package assign2.ngram;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/*
 * INN370 Assignment 2 (n7080212 & n7686854)
 * Test class for NGramNode
 */
public class NGramNodeTest {
	
	private NGramNode n;
	
	//a few sample(good/valid) variables prepared for the test
	private final String sampleQueryString = "This is a sample query";
	private final String[] sampleQueryStringArray = new String[] {"This", "is", "a", "sample", "array"};
	private final String[] samplePredictions = new String[] {"first", "second", "thrid", "fourth", "fifth"};
	private final Double[] sampleprobabilities = new Double[] {0.4, 0.3, 0.1, 0.1, 0.1};
	
	//the expected output of the toString method
	private final String expectedString = "This is a sample query | first : 0.4\n" +
										  "This is a sample query | second : 0.3\n" +
										  "This is a sample query | thrid : 0.1\n" +
										  "This is a sample query | fourth : 0.1\n" +
										  "This is a sample query | fifth : 0.1\n" +
										  "\n";
	
	//some bad data that should make certain method throw NGram exception
	private final String nullString = null;
	private final String emptyStringOne = "";
	private final String emptyStringTwo = "    ";
	
	private final String[] nullStringArray = null;
	private final String[] emptyStringArray = new String[] {};
	private final String[] stringArrayContainsNullString = new String[] {"There", "is", "a", null, "string"};
	private final String[] stringArrayContainsEmptyStringOne = new String[] {"Oh", "", "a", "empty", "string"};
	private final String[] stringArrayContainsEmptyStringTwo = new String[] {"    ", "is", "a", "empty", "string"};
		
	private final Double[] nullProbabilities = null;
	private final Double[] emptyProbabilities = new Double[] {};
	private final Double[] probabilitiesContainsNullElement = new Double[] {0.4, null, 0.1, 0.1, 0.1};
	private final Double[] probabilitiesContainsElementGreaterThanOne = new Double[] {0.4, 0.3, 1.1, 0.1, 0.1};	
	private final Double[] probabilitiesContainsElementEqualsZero = new Double[] {0.4, 0.3, 0.1, 0.0, 0.1};
	private final Double[] probabilitiesContainsNegativeElement = new Double[] {0.4, 0.3, 0.1, 0.1, -0.1};
	private final Double[] probabilitiesWithAShorterLengthFromSamplePredictions = new Double[] {0.4, 0.3, 0.1};
	private final Double[] probabilitiesWithALongerLengthFromSamplePredictions = 
																	new Double[] {0.3, 0.1, 0.1, 0.1, 0.1 ,0.1};
	
	/*
	 * Set up a NgramNode with a valid String as contest
	 * and other valid sample(good/valid) variables
	 * No NGram exception should be thrown
	 */	
	@Before @Test
	public void setUp() throws NGramException {
		this.n = new NGramNode(this.sampleQueryString,
							   this.samplePredictions,
							   this.sampleprobabilities);
	}
	
	/*
	 * Try to set up a NgramNode with a valid String Array as context
	 * and other valid sample(good/valid) variables
	 * No NGram exception should be thrown
	 */	
	@Test
	public void testNGramNodeWithSampleQueryStringArray() throws NGramException {
		this.n = new NGramNode(this.sampleQueryStringArray, 
							   this.samplePredictions, 
							   this.sampleprobabilities);
	}
	
	/*
	 * Try to set up a NgramNode with a Null String as context
	 * and other valid sample(good/valid) variables
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testNGramNodeWithNulltString() throws NGramException {
		this.n = new NGramNode(this.nullString, 
				  			   this.samplePredictions, 
				  			   this.sampleprobabilities);
	}

	/*
	 * Try to set up a NgramNode with an Empty String as context
	 * and other valid sample(good/valid) variables
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testNGramNodeWithEmptyStingOne() throws NGramException {
		this.n = new NGramNode(this.emptyStringOne, 
							   this.samplePredictions, 
							   this.sampleprobabilities);
	}
	
	/*
	 * Try to set up a NgramNode with another Empty String as context
	 * and other valid sample(good/valid) variables
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testNGramNodeWithEmptyStingTwo() throws NGramException {
		this.n = new NGramNode(this.emptyStringTwo, 
				               this.samplePredictions, 
				               this.sampleprobabilities);
	}
	
	/*
	 * Try to set up a NgramNode with a Null String Array as context
	 * and other valid sample(good/valid) variables
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testNGramNodeWithNullStringArray() throws NGramException {
		this.n = new NGramNode(this.nullStringArray, 
				               this.samplePredictions, 
				               this.sampleprobabilities);
	}

	/*
	 * Try to set up a NgramNode with an Empty String Array as context
	 * and other valid sample(good/valid) variables
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testNGramNodeWithEmptyStringArray() throws NGramException {
		this.n = new NGramNode(this.emptyStringArray, 
				           	   this.samplePredictions, 
				           	   this.sampleprobabilities);
	}

	/*
	 * Try to set up a NgramNode with a String Array
	 * that contains Null String as context
	 * and other valid sample(good/valid) variables
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testNGramNodeWithStringArrayContainsNullString() throws NGramException {
		this.n = new NGramNode(this.stringArrayContainsNullString, 
							   this.samplePredictions, 
							   this.sampleprobabilities);
	}

	/*
	 * Try to set up a NgramNode with a String Array
	 * that contains Empty String as context
	 * and other valid sample(good/valid) variables
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testNGramNodeWithStringArrayContainsEmptyStringOne() throws NGramException {
		this.n = new NGramNode(this.stringArrayContainsEmptyStringOne,
							   this.samplePredictions,
							   this.sampleprobabilities);
	}

	/*
	 * Try to set up a NgramNode with a String Array
	 * that contains Empty String as context
	 * and other valid sample(good/valid) variables
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testNGramNodeWithStringArrayContainsEmptyStringTwo() throws NGramException {
		this.n = new NGramNode(this.stringArrayContainsEmptyStringTwo, 
							   this.samplePredictions, 
							   this.sampleprobabilities);
	}
	
	/*
	 * Test method for getContext()
	 */	
	@Test
	public void testGetContext() {
		assertEquals(this.sampleQueryString, n.getContext());
	}

	/*
	 * Try to set the context of a NgramNode with a valid String
	 * No NGram exception should be thrown
	 */	
	@Test
	public void testSetContextWithSampleQueryString() throws NGramException {
		this.n.setContext(this.sampleQueryString);
	}
	
	/*
	 * Try to set the context of a NgramNode with a valid String Array
	 * No NGram exception should be thrown
	 */	
	@Test
	public void testSetContextWithSampleQueryStringArray() throws NGramException {
		this.n.setContext(this.sampleQueryStringArray);
	}
	
	/*
	 * Try to set the context of a NgramNode with a Null String
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testSetContextWithNullString() throws NGramException {
		this.n.setContext(this.nullString);
	}
	
	/*
	 * Try to set the context of a NgramNode with an Empty String
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testSetContextWithEmptyStringOne() throws NGramException {
		this.n.setContext(this.emptyStringOne);
	}
	
	/*
	 * Try to set the context of a NgramNode with another Empty String
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testSetContextWithEmptyStringTwo() throws NGramException {
		this.n.setContext(this.emptyStringTwo);
	}
	
	/*
	 * Try to set the context of a NgramNode with a Null String Array
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testSetContextWithNullStringArray() throws NGramException {
		this.n.setContext(this.nullStringArray);
	}
	
	/*
	 * Try to set the context of a NgramNode with an Empty String Array
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testSetContextWithEmptyStringArray() throws NGramException {
		this.n.setContext(this.emptyStringArray);
	}
	
	/*
	 * Try to set the context of a NgramNode with an String Array
	 * that contains Null String
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testSetContextWithStringArrayContainsNullString() throws NGramException {
		this.n.setContext(this.stringArrayContainsNullString);
	}
	
	/*
	 * Try to set the context of a NgramNode with an String Array
	 * that contains Empty String
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testSetContextWithStringArrayContainsEmptyStringOne() throws NGramException {
		this.n.setContext(this.stringArrayContainsEmptyStringOne);
	}
	
	/*
	 * Try to set the context of a NgramNode with an String Array
	 * that contains Empty String
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testSetContextWithStringArrayContainsEmptyStringTwo() throws NGramException {
		this.n.setContext(this.stringArrayContainsEmptyStringTwo);
	}

	/*
	 * Test method for getPredictions()
	 */
	@Test
	public void testGetPredictions() {
		assertEquals(true, this.samplePredictions.equals(n.getPredictions()));
	}
	
	/*
	 * Try to set the predictions of a NgramNode with a valid String Array
	 * No NGram exception should be thrown
	 */	
	@Test
	public void testSetPredictionsWithSamplePredictions() throws NGramException {
		this.n.setPredictions(this.samplePredictions);
	}
	
	/*
	 * Try to set the predictions of a NgramNode with a Null String Array
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testSetPredictionsWithNullStringArray() throws NGramException {
		this.n.setPredictions(this.nullStringArray);
	}
	
	/*
	 * Try to set the predictions of a NgramNode with an Empty String Array
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testSetPredictionsWithEmptyStringArray() throws NGramException {
		this.n.setPredictions(this.emptyStringArray);
	}
	
	/*
	 * Try to set the predictions of a NgramNode with a String Array
	 * that contains Null String
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testSetPredictionsWithStringArrayContainsNullString() throws NGramException {
		this.n.setPredictions(this.stringArrayContainsNullString);
	}
	
	/*
	 * Try to set the predictions of a NgramNode with a String Array
	 * that contains Empty String
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testSetPredictionsWithStringArrayContainsEmptyStringOne() throws NGramException {
		this.n.setPredictions(this.stringArrayContainsEmptyStringOne);
	}
	
	/*
	 * Try to set the predictions of a NgramNode with a String Array
	 * that contains Empty String
	 * NGram exception should be thrown
	 */	
	@Test (expected = NGramException.class)
	public void testSetPredictionsWithStringArrayContainsEmptyStringTwo() throws NGramException {
		this.n.setPredictions(this.stringArrayContainsEmptyStringTwo);
	}
	
	/*
	 * Test method for getProbabilities()
	 */
	@Test
	public void testGetProbabilities() {
		assertEquals(true, this.sampleprobabilities.equals(n.getProbabilities()));
	}
	
	/*
	 * Try to set the Probabilities of a NgramNode with a Valid Array
	 * No NGram exception should be thrown
	 */	
	@Test
	public void testSetProbabilitiesWithSampleprobabilities() throws NGramException {
		this.n.setProbabilities(this.sampleprobabilities);
	}
	
	/*
	 * Try to set the Probabilities of a NgramNode with a Null Array
	 * NGram exception should be thrown
	 */
	@Test (expected = NGramException.class)
	public void testSetProbabilitiesWithNullProbabilities() throws NGramException {
		this.n.setProbabilities(this.nullProbabilities);
	}

	/*
	 * Try to set the Probabilities of a NgramNode with an Empty Array
	 * NGram exception should be thrown
	 */
	@Test (expected = NGramException.class)
	public void testSetProbabilitiesWithEmptyProbabilities() throws NGramException {
		this.n.setProbabilities(this.emptyProbabilities);
	}
	
	/*
	 * Try to set the Probabilities of a NgramNode with an Array
	 * that contains Null element
	 * NGram exception should be thrown
	 */
	@Test (expected = NGramException.class)
	public void testSetProbabilitiesWithProbabilitiesContainsNullElement() throws NGramException {
		this.n.setProbabilities(this.probabilitiesContainsNullElement);
	}

	/*
	 * Try to set the Probabilities of a NgramNode with an Array
	 * that contains elements greater than one
	 * NGram exception should be thrown
	 */
	@Test (expected = NGramException.class)
	public void testSetProbabilitiesWithProbabilitiesContainsElementGreaterThanOne() throws NGramException {
		this.n.setProbabilities(this.probabilitiesContainsElementGreaterThanOne);
	}

	/*
	 * Try to set the Probabilities of a NgramNode with an Array
	 * that contains elements equal zero
	 * NGram exception should be thrown
	 */
	@Test (expected = NGramException.class)
	public void testSetProbabilitiesWithProbabilitiesContainsElementEqualsZero() throws NGramException {
		this.n.setProbabilities(this.probabilitiesContainsElementEqualsZero);
	}

	/*
	 * Try to set the Probabilities of a NgramNode with an Array
	 * that contains negative elements 
	 * NGram exception should be thrown
	 */
	@Test (expected = NGramException.class)
	public void testSetProbabilitiesWithProbabilitiesContainsNegativeElement() throws NGramException {
		this.n.setProbabilities(this.probabilitiesContainsNegativeElement);
	}
	
	/*
	 * Try to set the Probabilities of a NgramNode with an Array
	 * whose elements are less than the ones in predictions 
	 * NGram exception should be thrown
	 */
	@Test (expected = NGramException.class)
	public void testSetProbabilitiesWithProbabilitiesWithAShorterLengthFromSamplePredictions() throws NGramException {
		this.n.setProbabilities(this.probabilitiesWithAShorterLengthFromSamplePredictions);
	}
	
	/*
	 * Try to set the Probabilities of a NgramNode with an Array
	 * whose elements are more than the ones in predictions 
	 * NGram exception should be thrown
	 */
	@Test (expected = NGramException.class)
	public void testSetProbabilitiesWithProbabilitiesWithALongerLengthFromSamplePredictions() throws NGramException {
		this.n.setProbabilities(this.probabilitiesWithALongerLengthFromSamplePredictions);
	}
	
	/*
	 * Test method for toString()
	 */
	@Test
	public void testToString() {
		assertEquals(this.expectedString, n.toString());
	}

}
