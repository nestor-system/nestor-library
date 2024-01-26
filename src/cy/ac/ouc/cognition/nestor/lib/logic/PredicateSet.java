package cy.ac.ouc.cognition.nestor.lib.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import static cy.ac.ouc.cognition.nestor.lib.trace.Trace.outln;


public class PredicateSet extends LogicElement {

	protected transient int					CurrentPredicateIndex;
	protected transient String				Delimiter;
	protected transient boolean				PredicatesAdded;

	protected PartType						Part;				
	protected HashMap<String, LEPredicate>	Predicates;


	PredicateSet() {
		this(PartType.UNDEFINED);
	}
	

	PredicateSet(PartType part) {
		super();
		PredicatesAdded = false;
		Part = part;
		Predicates = new HashMap<String, LEPredicate>();
		CurrentPredicateIndex = 0;
		Delimiter = ", ";
	}
	

	
	public void addPredicate(String predicateIndex, LEPredicate predicate) {
		Predicates.put(predicateIndex, predicate);
		PredicatesAdded = true;
		Complete = false;
		outln(Part.toString() + ". Added predicate: " + predicate.getTextRepresentation());	
	}


	public void addPredicate(LEPredicate predicate) {
		String predicateKey = String.format("%05d", this.CurrentPredicateIndex++);	
		addPredicate(predicateKey, predicate);
	}


	public int addPredicateConditionally(
			ArrayList<String> predicateIdentifier,
			ArrayList<String> argumentIdentifiers,
			boolean buildArguments) {

		LEPredicate predicate = new LEPredicate(predicateIdentifier, argumentIdentifiers, true);
		
		/* If new predicate with the same arguments is  not already added to the set
		 * proceed to add it
		 */	
		if (!this.containsPredicateWithArguments(predicate, true)) {
	
			String predicateKey = String.format("%05d", this.CurrentPredicateIndex++);
		
			addPredicate(predicateKey, predicate);
		
			return 1;
		}
		
		return 0;

	}


	public int addPredicateConditionally(
			ArrayList<String> predicateIdentifier,
			ArrayList<LEArgument> argumentIdentifiers) {

		LEPredicate predicate = new LEPredicate(predicateIdentifier, argumentIdentifiers);
		
		/* If new predicate with the same arguments is  not already added to the set
		 * proceed to add it
		 */	
		if (!this.containsPredicateWithArguments(predicate, true)) {
	
			String predicateKey = String.format("%05d", this.CurrentPredicateIndex++);
		
			addPredicate(predicateKey, predicate);
		
			return 1;
		}

		return 0;
	}
	
	
	public void addArgumentToPredicate(LEArgument argument, String predicateIndex) {
		Predicates.get(predicateIndex).addArgument(argument);
		Complete = false;
		outln(Part.toString() + ": Added Argument to predicate " + Predicates.get(predicateIndex).getName().buildNameText(false) + "["+predicateIndex+"]: " + argument.getTextRepresentation());
	}


	public boolean containsPredicateName(LEPredicate testPred, boolean ignoreNegation) {

		for (LEPredicate predicate : Predicates.values())
			if (predicate.Name.sameAs(testPred.getName(), ignoreNegation))
				return true;

		return false;
	}

	
	public boolean containsPredicateWithArguments(LEPredicate testPred, boolean ignoreNegation) {

		for (LEPredicate predicate : Predicates.values())
			if (predicate.sameAs(testPred, ignoreNegation))
				return true;

		return false;
	}

	
	@Override
	protected String buildTextRepresentation() {

 		String textRepresentation = "";
 		
        for (String predicateKey : (new TreeMap<String, LEPredicate>(Predicates)).keySet()) {

        	LEPredicate setPredicate = Predicates.get(predicateKey);
        	String predicateString = setPredicate.getTextRepresentation();
        	
       		if (!textRepresentation.isEmpty() && !predicateString.isEmpty())
       			textRepresentation += Delimiter;
       		
       		textRepresentation += predicateString;

        }
	        
	    return textRepresentation;
	}

	
	
	public boolean sameAs(LogicElement logicElement, boolean ignoreNegation) {
		
		// If the size of the two predicate sets is not the same, then they are not the same
		if (this.Predicates.size() != ((PredicateSet) logicElement).Predicates.size())
			return false;
		
		// Make sure all predicates of the set checked are included in this set
		for (LEPredicate predicate : ((PredicateSet) logicElement).Predicates.values())
			
			// Even if one of the predicates of the set checked is not included in this set
			// then they are not the same
			if (!this.containsPredicateWithArguments(predicate, ignoreNegation))
				return false;
		
		// If we reach this point, then they are the same (all are included)
		return true;
	
	}




	/**
	 * @return the predicatesAdded
	 */
	public boolean arePredicatesAdded() {
		return PredicatesAdded;
	}


	/**
	 * @@param complete the complete to set
	 */
	public void setComplete(boolean complete) {

        for (LEPredicate setPredicate : Predicates.values())
        	setPredicate.setComplete(complete);
 				
		super.setComplete(complete);
	}


	/**
	 * @return the delimiter
	 */
	public String getDelimiter() {
		return Delimiter;
	}


	/**
	 * @param delimiter the delimiter to set
	 */
	public void setDelimiter(String delimiter) {
		Delimiter = delimiter;
	}

	
	/**
	 * @return the part
	 */
	public PartType getPart() {
		return Part;
	}


	/**
	 * @return the predicates
	 */
	public HashMap<String, LEPredicate> getPredicates() {
		return Predicates;
	}

	
	/**
	 * @param predicates the predicates to set
	 */
	public void setPredicates(HashMap<String, LEPredicate> predicates) {
		Predicates = predicates;
	}

}
