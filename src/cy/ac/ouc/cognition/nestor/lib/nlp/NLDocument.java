package cy.ac.ouc.cognition.nestor.lib.nlp;

import static cy.ac.ouc.cognition.nestor.lib.trace.Trace.errln;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cy.ac.ouc.cognition.nestor.lib.logic.LogicExpression;
import cy.ac.ouc.cognition.nestor.lib.reasoning.IReasoning;
import cy.ac.ouc.cognition.nestor.lib.reasoning.ITranslationPolicy;
import cy.ac.ouc.cognition.nestor.lib.reasoning.TranslationPolicyException;
import cy.ac.ouc.cognition.nestor.lib.logic.ExpressionType;

public class NLDocument extends NLElement {

	private transient String 		ls = getTranslationParameters().getGenerate_LineSeperator();

	private transient int			CurrentSentenceIndex = 0;
	private transient Object		NLPData;

	private List<Coreference>		Coreferences;
	private List<NLSentence>		DocumentSentences;
	private List<LogicExpression>	DocumentWideInferredExpressions;
	
	
	// NLDocument Constructor
	public NLDocument(String documentText) {
		super(documentText);
		Coreferences = new ArrayList<>();
		DocumentSentences = new ArrayList<>();
		DocumentWideInferredExpressions = new ArrayList<>();
	}
	
	
	// Add a new sentence object to document
	public void addSentence(NLSentence sentence) {
		CurrentSentenceIndex++;
		sentence.setIndexInDocument(CurrentSentenceIndex);
		sentence.setDocument(this);
		DocumentSentences.add(sentence);
		Complete = false;
	}

	
	
	// Add a new document-wide coreference relation object
	public void addCoreference(Coreference coreference) {
		// Add this coreference relation in the list of coreferences for this sentence
		Coreferences.add(coreference);
		Complete = false;
	}
	


	// Add a new document-wide coreference relation
	public void addCoreference(String coreferenceName, String repMention, String mention, int mentionIndex, int sentenceIndex) {
		this.addCoreference(new Coreference(coreferenceName, repMention, mention, mentionIndex, sentenceIndex));
	}
	


	// Check if a document-wide coreference relation was added to document
	public boolean coreferenceAdded(String mention, int mentionIndex, int sentenceIndex) {
		for (Coreference coref : Coreferences)
			if (coref.getMention().equals(mention) &&
				coref.getMentionIndex() == mentionIndex &&
				coref.getSentenceIndex() == sentenceIndex)
				
				return true;
		
		return false;
	}

	
	
	// Add an expression to the document wide inferred expression list
	public void addDocumentWideInferredExpression(LogicExpression inferredExpression) {
		DocumentWideInferredExpressions.add(inferredExpression);

	}

	
	
	// Clear document wide inferred expression list
	public void clearDocumentWideInferredExpressions() {
		DocumentWideInferredExpressions.clear();

	}

	
	
	// Check if the document wide inferred expression list contains an expression
	public boolean documentWideInferredExpressionsContain(LogicExpression expressionToCheck) {
		
		// Iterate through all expressions of LogicRepresentation list
		for (LogicExpression logicExpression : this.DocumentWideInferredExpressions) {
			
			// If the type of the expressions is not the same, then surely
			// they are not the same and continue to the next one
			// IMPORTANT: This check makes sure no exception is thrown by method sameAs
			// CID: Should improve sameAs to catch these exceptions and just return false
			if (logicExpression.getTypeOfExpression() != expressionToCheck.getTypeOfExpression())
				continue;
		
			// If an expression is the same as the checked expression, then
			// it means LogicRepresentation contains this expression and return true
			if (logicExpression.sameAs(expressionToCheck, false))					
					return true;
		}

		// If this point is reached, then the checked expression is not contained
		return false;

	}



	// Rebuild the document wide inferred expression list
	public boolean rebuildDocumentWideInferredExpressions() {
		
		if (this.isComplete()) {

			DocumentWideInferredExpressions.clear();

			for (NLSentence nlSentence : DocumentSentences)

				if (nlSentence.anExpressionIsInferred())

					for (LogicExpression inferredExpression : nlSentence.getInferredExpressions())
						
						if (inferredExpression.getTypeOfExpression() != ExpressionType.CONSTANT)
							DocumentWideInferredExpressions.add(inferredExpression);
			
						else if (!this.documentWideInferredExpressionsContain(inferredExpression))
							DocumentWideInferredExpressions.add(inferredExpression);						
							
						
			return true;
		}

		return false;

	}
	
	
	
	// Generate Logic Predicates
	public void generateLogicPredicates() {
		
		if (!this.isComplete()) {
        	errln("Cannot generate logic predicates. Document is not ready!");
    		return;
		}

		for (NLSentence nlSentence : this.getDocumentSentences()) 

			// Generate sentence logic predicates
			nlSentence.generateLogicPredicates();

	}


	
	// Get Logic Annotation Text
	public String buildLogicAnnotationText() {

		String logicAnnotationText = "";

		if (!this.isComplete()) {
        	errln("Cannot get logic annotation text. Document is not ready!");
    		return logicAnnotationText;
		}

		for (NLSentence nlSentence : this.getDocumentSentences())
			
			// Get sentence logic annotation text
			logicAnnotationText += nlSentence.buildLogicAnnotationText() + ls;


        return logicAnnotationText;		

	}


	
	// Generate Logic Expressions
	public void generateLogicExpressions(ITranslationPolicy	translationPolicy, IReasoning reasoningAgent) {
		
		if (!this.isComplete()) {
        	errln("Cannot generate logic expressions. Document is not ready!");
    		return;
		}

		// Clear Logic representation of the document
		this.clearDocumentWideInferredExpressions();

		for (NLSentence nlSentence : this.getDocumentSentences()) {
				
			try {

				translationPolicy.inferTranslation(nlSentence, reasoningAgent);
				nlSentence.setInferenceData(reasoningAgent.getInferences());
			}
			
			catch (TranslationPolicyException e) {

				errln("Error generating logic expresssions for sentence " + nlSentence.getIndexInDocument() + ": " + e.getMessage());
			}

		}

	}


	// Build Logic Representation Text
	public String buildLogicBasedRepresentationText() {
		
		String	translationText = "";
		
		if (!this.isComplete()) {
        	errln("Cannot get logic expresssions text. Document is not ready!");
    		return translationText;
		}

		// Generate text for the logic expressions of each sentence of the document
		for (NLSentence nlSentence : this.getDocumentSentences())
			translationText += nlSentence.buildLogicBasedRepresentationText();

		// Generate text for the logic expressions global for the whole document
		for (LogicExpression expression : this.getLogicRepresentation())
			translationText += expression.getTextRepresentation() + ls;

		return translationText;

	}
	

	
	/**
	 * @return the NLPData
	 */
	public Object getNLPData() {
		return NLPData;
	}



	/**
	 * @param NLPData the NLPData to set
	 */
	public void setNLPData(Object nlpData) {
		NLPData = nlpData;
	}


	
	/**
	 * @return the coreferences
	 */
	public List<Coreference> getCoreferences() {
		return Coreferences;
	}



	/**
	 * @return the documentSentences
	 */
	public List<NLSentence> getDocumentSentences() {
		return DocumentSentences;
	}



	/**
	 * @return the logicRepresentation
	 */
	public List<LogicExpression> getLogicRepresentation() {
		return DocumentWideInferredExpressions;
	}



    // Get Document JSON Representation
    public String toJSONString() {
    	
		if (!this.isComplete()) {
        	errln("Cannot get document JSON representation. Document is not ready!");
    		return "";
		}

        Gson gson = new GsonBuilder().create();

        // Convert the object to JSON and pretty print it
        return gson.toJson(this);

    }



    // Get Document JSON Pretty Representation
    public String toPrettyJSONString() {
    	
		if (!this.isComplete()) {
        	errln("Cannot get document JSON representation. Document is not ready!");
    		return "";
		}

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Convert the object to JSON and pretty print it
        return gson.toJson(this);

    }

    
}
