package cy.ac.ouc.cognition.nestor.lib.nlp;

import static cy.ac.ouc.cognition.nestor.lib.trace.Trace.errln;
import static cy.ac.ouc.cognition.nestor.lib.trace.Trace.outln;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import cy.ac.ouc.cognition.nestor.lib.logic.LogicExpression;
import cy.ac.ouc.cognition.nestor.lib.logic.LogicAnnotation;


public class NLSentence extends NLElement {

	private transient String 			ls = getTranslationParameters().getGenerate_LineSeperator();
	
	transient boolean					PredicatesGenerated;
	transient boolean					AnExpressionIsInferred;

	private transient NLDocument		Document;
	private transient Object			NLPData;
	private transient List<Dependency>	Dependencies;
	
	private int							IndexInDocument;

	private HashMap<Integer, NLToken>	Tokens;
	private List<Coreference>			Coreferences;
	private LogicAnnotation				SentenceAnnotation;
	private List<LogicExpression>		InferredExpressions;
	private	Object						InferenceData;
	

	
	public NLSentence(String sentenceText) {
		
		this(sentenceText, 0);
		
	}


	
	public NLSentence(String sentenceText, int indexInDocument) {

		super(sentenceText);
		IndexInDocument = indexInDocument;
		Tokens = new HashMap<Integer, NLToken>();
		Dependencies = new ArrayList<>();
		Coreferences = new ArrayList<>();
		SentenceAnnotation = new LogicAnnotation();
		InferredExpressions = new ArrayList<>();
		PredicatesGenerated = false;
		AnExpressionIsInferred = false;

	}
	
	
	
	public void addToken(int index, NLToken nlpToken) {

		Tokens.put(index, nlpToken);
		PredicatesGenerated = false;
		AnExpressionIsInferred = false;
		Complete = false;

	}

	
	
	public void addToken(int index, String originalText, String lemma, String ner, String tag) {
		
		this.addToken(index, new NLToken(index, originalText, lemma, ner, tag));

	}
	

	
	public void addDependency(Dependency dependency) {

		// Create a dependency relation tree between tokens
		dependency.getGovernor().addDependant(dependency.getRelationName(), dependency.getDependent());

		// Add this dependency relation in the list of dependencies for this sentence
		Dependencies.add(dependency);

		PredicatesGenerated = false;
		AnExpressionIsInferred = false;
		Complete = false;

	}

	
	
	public void addDependency(String dependencyName, NLToken governor, NLToken dependent) {
		
		this.addDependency(new Dependency(	dependencyName, governor, dependent));
		
	}

	
	
	public void addDependency(String dependencyName, int governorIndex, int dependentIndex) {

		this.addDependency(dependencyName, Tokens.get(governorIndex), Tokens.get(dependentIndex));

	}
	
	
	
	public void addCoreference(Coreference coreference) {

		// Add this coreference relation in the list of coreferences for this sentence
		Coreferences.add(coreference);

		PredicatesGenerated = false;
		AnExpressionIsInferred = false;
		Complete = false;

	}
	
	
	
	public void addCoreference(String coreferenceName, String repMention, String mention, int mentionIndex, int sentenceIndex) {

		this.addCoreference(new Coreference(coreferenceName, repMention, mention, mentionIndex, sentenceIndex));

	}
	
	
	
	public void generateLogicPredicates() {
		
		if (Complete) {

			outln("Generating Logic Predicates...");
			
			SentenceAnnotation = new LogicAnnotation();
			
			/* Add a predicate for the sentence index */
			SentenceAnnotation.addAnnotation(IndexInDocument);

			/* Add NLToken predicates */
			for (NLToken nlToken : Tokens.values())
				SentenceAnnotation.addAnnotation(nlToken);			

			/* Add Dependency predicates */
			for (Dependency nlDependency : Dependencies) 
				SentenceAnnotation.addAnnotation(nlDependency);

			/* Add Sentence Coreference predicates */
			for (Coreference nlCoreference : Coreferences) 
				SentenceAnnotation.addAnnotation(nlCoreference);

			/* Add Document Coreference predicates */
			for (Coreference nlCoreference : Document.getCoreferences()) 
				SentenceAnnotation.addAnnotation(nlCoreference);
		
			SentenceAnnotation.setComplete(true);
			PredicatesGenerated = true;
		}
		else
			errln("Logic Predicates cannot be generated. Sentence is not complete!");

	}

	
	
	/**
	 * @return the indexInDocument
	 */
	public int getIndexInDocument() {
		return IndexInDocument;
	}



	/**
	 * @param indexInDocument the indexInDocument to set
	 */
	public void setIndexInDocument(int indexInDocument) {
		IndexInDocument = indexInDocument;
	}

	/**
	 * @return the document
	 */
	public NLDocument getDocument() {
		return Document;
	}
	

	/**
	 * @param document the document to set
	 */
	public void setDocument(NLDocument document) {
		Document = document;
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
	 * @return the sentenceAnnotation
	 */
	public LogicAnnotation getSentenceAnnotation() {
		return SentenceAnnotation;
	}

	
	public void addInferredExpression(LogicExpression inferredExpression) {
		
		InferredExpressions.add(inferredExpression);
		
		// Add expressions that are considered to be global for a document (like a CONSTANT)
		// into document logic representation list. These expressions should be added only once
		if (inferredExpression.hasDocumentScope() &&
			!Document.documentWideInferredExpressionsContain(inferredExpression))

			Document.addDocumentWideInferredExpression(inferredExpression);						

		AnExpressionIsInferred = true;

	}

	/**
	 * @return the inferredExpressions
	 */
	public List<LogicExpression> getInferredExpressions() {
		return InferredExpressions;
	}

	public boolean anExpressionIsInferred( ) {
		return AnExpressionIsInferred;
	}

	public void clearInferredExpressions( ) {
		InferredExpressions.clear();
	}

	
	public String buildLogicBasedRepresentationText() {

		// Get Logic Based Representation String	
		String logicBasedRepresentationText = "";

		/* Create sentence logic annotation text */
		if (this.isComplete())
			for (LogicExpression expression : this.getInferredExpressions())
				logicBasedRepresentationText += expression.getTextRepresentation() + ls;
	
		else
        	errln("Cannot get logic based representation text data for sentence [" +
        			this.IndexInDocument +
        			"]. Processing not completed!");

		
		return logicBasedRepresentationText;

	}

	
	/**
	 * @return the tokens
	 */
	public  HashMap<Integer, NLToken> getTokens() {
		return Tokens;
	}


	
	/**
	 * @return the dependencies
	 */
	public List<Dependency> getDependencies() {
		return Dependencies;
	}

	
	
	/**
	 * @return the coreferences
	 */
	public List<Coreference> getCoreferences() {
		return Coreferences;
	}

	
	public String buildLogicAnnotationText() {

		// Get Logic Annotation String
		
		String logicAnnotationText = "";

		/* Create sentence logic annotation text */
		if (this.isComplete())
			logicAnnotationText += this.SentenceAnnotation.getTextRepresentation();
		
		else
        	errln("Cannot get logic annotation text data for sentence [" +
        			this.IndexInDocument +
        			"]. Processing not completed!");

		
		return logicAnnotationText;

	}


    /**
	 * @return the inferenceData
	 */
	public Object getInferenceData() {
		return InferenceData;
	}



	/**
	 * @param object the inferenceData to set
	 */
	public void setInferenceData(Object inferenceData) {
		InferenceData = inferenceData;
	}
	

}
