package assign2.ngram;

import java.text.DecimalFormat;

/*
 * INN370 Assignment 2 (n7080212 & n7686854)
 * NGramNode Class
 */
public class NGramNode implements NGramContainer {
	
	private String context;
	private String[] predictions;
	private Double[] probabilities;

	public NGramNode(String[] words, String[] predictions, Double[] probabilities) throws NGramException{				
		this.setContext(words);		
		this.setPredictions(predictions);		
		this.setProbabilities(probabilities);	
	}
	
	public NGramNode(String context, String[] predictions, Double[] probabilities) throws NGramException{				
		this.setContext(context);		
		this.setPredictions(predictions);		
		this.setProbabilities(probabilities);	
	}
	
	public NGramNode() {
		
	}

	@Override
	public String getContext() {		
		return this.context;	
	}
	
	@Override
	public void setContext(String context) throws NGramException {		
		if (context == null)
			throw new NGramException("Context cannot be null.");
		else if (context.trim().length() == 0)
			throw new NGramException("Context cannot be empty.");
		else
			this.context = context;
	}
	
	@Override
	public void setContext(String[] words) throws NGramException {		
		if (words == null)
			throw new NGramException("words cannot be null.");
		else if (words.length == 0)
			throw new NGramException("words cannot be empty.");
		else if (hasNullString(words))
			throw new NGramException("words cannot contains null string.");
		else if (hasEmptyString(words))
			throw new NGramException("Context cannot contains empty string.");
		else 
			this.context = this.parseStringArrayToString(words);
	}
	
	@Override
	public String[] getPredictions() {	
		return this.predictions;
	}
	
	@Override
	public void setPredictions(String[] predictions) throws NGramException {	
		if (predictions == null)
			throw new NGramException("Predictions cannot be null.");
		else if (predictions.length == 0)
			throw new NGramException("Predictions cannot be empty.");
		else if (hasNullString(predictions))
			throw new NGramException("Predictions cannot contain null string.");
		else if (hasEmptyString(predictions))
			throw new NGramException("Predictions cannot contain empty string.");
		else 
			this.predictions = predictions;		
	}
	
	@Override
	public Double[] getProbabilities() {
		return this.probabilities;
	}
	
	@Override
	public void setProbabilities(Double[] probabilities) throws NGramException {		
		if (probabilities == null)
			throw new NGramException("probabilities cannot be null.");
		else if (probabilities.length == 0)
			throw new NGramException("probabilities cannot be empty.");
		else if (hasNullProbability(probabilities))
			throw new NGramException("probabilities cannot contain null probabilities.");
		else if (hasInvalidProbability(probabilities))
			throw new NGramException("probabilities cannot be zero, negative or greater than 1.0.");
		else if (this.predictions.length != probabilities.length)
			throw new NGramException("predictions.length cannot be different from probabilities.length.");
		else
			this.probabilities = probabilities;		
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int index = 0;
		
		for (String s : this.predictions) {
			sb.append(this.context);
			sb.append(" | ");
			sb.append(s);
			sb.append(" : ");
			sb.append(new DecimalFormat(NGramContainer.DecFormat).format(this.probabilities[index]));
			sb.append("\n");
			index++;
		}
		
		sb.append("\n");			
				
		return sb.toString();
	}
	
	/*
	 * Helper method to parse a String Array to a String
	 */
	private String parseStringArrayToString(String[] stringArray) {
		StringBuffer sb = new StringBuffer();
		
		if (stringArray.length > 0) {
			sb.append(stringArray[0]);
			for (int i = 1; i < stringArray.length; i++) {
				sb.append(" ");
				sb.append(stringArray[i]);				
			}					
		}
		
		return sb.toString();
	}
	
	/*
	 * Helper method to return true if a String Array has null elements
	 */
	private boolean hasNullString(String[] stringArray) {		
		boolean nullStringFound = false;
		
		for (String s : stringArray) {
			
			if (s == null)
				nullStringFound = true;		
			
		}
		
		return nullStringFound;
				
	}
	
	/*
	 * Helper method to return true if a String Array has Empty elements
	 */
	private boolean hasEmptyString(String[] stringArray) {
		boolean emptyStringFound = false;
		
		for (String s : stringArray) {
			
			if (s.trim().equals(""))
				emptyStringFound = true;
			
		}
		
		return emptyStringFound;		
	}	
	
	/*
	 * Helper method to return true if an Array contains invalid probability
	 */
	private boolean hasInvalidProbability(Double[] probabilities) {
		boolean invalidProbabilityFound = false;
		
		for (Double probability : probabilities) {
			
			if (probability <= 0)
				invalidProbabilityFound = true;
			else if (probability > 1)
				invalidProbabilityFound = true;
			
		}
		
		return invalidProbabilityFound;		
	}
	
	/*
	 * Helper method to return true if an Array contains Null probability
	 */
	private boolean hasNullProbability(Double[] probabilities) {
		boolean nullProbabilityFound = false;
		
		for (Double probability : probabilities) {
			
			if (probability == null)
				nullProbabilityFound = true;			
			
		}
		
		return nullProbabilityFound;
	}
	
}
