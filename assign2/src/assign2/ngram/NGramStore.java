package assign2.ngram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.microsoft.research.webngram.service.GenerationService;
import com.microsoft.research.webngram.service.NgramServiceFactory;
import com.microsoft.research.webngram.service.GenerationService.TokenSet;

/*
 * INN370 Assignment 2 (n7080212 & n7686854)
 * NGramStore Class
 */
public class NGramStore implements NGramMap {
	
	//a HashMap to store NGramNode as value and String(the context of that NGramNode) as key
	private HashMap<String, NGramContainer> NGramStoreMap = new HashMap<String, NGramContainer>();
	
	public NGramStore() {
		
	}
		
	@Override
	public void addNGram(NGramContainer ngram) {		
		String context = ngram.getContext();
		this.NGramStoreMap.put(context, ngram);
				
	}
	
	@Override
	public void removeNGram(String context) {
		this.NGramStoreMap.remove(context);
	}
	
	@Override
	public NGramContainer getNGram(String context) {
		return this.NGramStoreMap.get(context);
		
	}

	@Override
	public boolean getNGramsFromService(String context, int maxResults) throws NGramException {
		
		final String key = "068cc746-31ff-4e41-ae83-a2d3712d3e68"; 
		final String model = "bing-body/jun09/3";
		
		//Code stolen from assignment 2 example
		NgramServiceFactory factory = NgramServiceFactory.newInstance(key);
		
		if (factory == null) {
		     throw new NGramException("NGram Service unavailable.");
		}
		
		GenerationService service = factory.newGenerationService();		
		TokenSet tokenSet = service.generate(key, model, context, maxResults, null);
		List<String> preds = tokenSet.getWords();
		
		if (preds.size() < 1) {
			return false;
		} else {			
			List<Double> logProbs = tokenSet.getProbabilities();
			List<Double> probs = new ArrayList<Double>();
			
			for (Double x : logProbs) {
				probs.add(Math.pow(10.0,x));
			}
			
			//Set predictions for this NGramNode
			String[] stringArray = new String[preds.size()];
			preds.toArray(stringArray);
			getNGram(context).setPredictions(stringArray);
			
			//Set probabilities for this NGramNode
			Double[] doubleArray = new Double[probs.size()];
			probs.toArray(doubleArray);
			getNGram(context).setProbabilities(doubleArray);
			
			return true;				
		}	
		
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Collection<NGramContainer> nCollection = this.NGramStoreMap.values();
		Iterator<NGramContainer> i = nCollection.iterator();
		
		while (i.hasNext()){
			NGramContainer n = i.next();
			sb.append(n.toString());		
		}
		
		return sb.toString();		
	}
	
}
