package cy.ac.ouc.cognition.nestor.lib.pipeline;

import static cy.ac.ouc.cognition.nestor.lib.trace.Trace.errln;

import cy.ac.ouc.cognition.nestor.lib.base.NESTORBase;
import cy.ac.ouc.cognition.nestor.lib.base.NESTORException;
import cy.ac.ouc.cognition.nestor.lib.nlp.NLDocument;
import cy.ac.ouc.cognition.nestor.lib.nlp.INLProcessor;
import cy.ac.ouc.cognition.nestor.lib.nlp.corenlp.StanfordCoreNLProcessor;
import cy.ac.ouc.cognition.nestor.lib.reasoning.IReasoning;
import cy.ac.ouc.cognition.nestor.lib.reasoning.ITranslationPolicy;
import cy.ac.ouc.cognition.nestor.lib.reasoning.prudens.js.PrudensJS;
import cy.ac.ouc.cognition.nestor.lib.reasoning.prudens.java.PrudensJava;
import cy.ac.ouc.cognition.nestor.lib.reasoning.prudens.PrudensTranslationPolicy;

public class Pipeline extends NESTORBase {
	
	private static INLProcessor		NLDocumentProcessor;
	private static boolean			NLProcessorSet = false;
	private NLDocument				ProcessedDocument;


    // Pipeline constructor
	public Pipeline(String translationParamsJSONString, boolean loadNLP) throws NESTORException {
		
		super();

		getSysParameters();

		getTranslationParameters(translationParamsJSONString);

		if (loadNLP)
			setAndLoadNLProcessor(false);

	}


    // Pipeline constructor
	public Pipeline(boolean loadNLP) throws NESTORException {
		
		this(null, loadNLP);

	}


    // Pipeline constructor
	public Pipeline(String translationParamsJSONString) throws NESTORException {
		
		this(translationParamsJSONString, false);

	}


    // Pipeline constructor
	public Pipeline() throws NESTORException {
		
		this(null, false);

	}



    // Update System Parameters
	public void updateSystemParameters(String systemParamsJSONString) {

		getSysParameters().updateValues(systemParamsJSONString);

	}



    // Read System Parameters From File
	public void readSystemParameters() {

		getSysParameters().updateValues();

	}



	// Set Natural Language Processor defined by system parameter
	public void setAndLoadNLProcessor(boolean forceNew) {

		try {

			if (getSysParameters().getPipeline_NLProcessor().equals("corenlp")) {
				NLDocumentProcessor = StanfordCoreNLProcessor.getInstance(forceNew);
				NLProcessorSet = true;
			}
	
			else {
	        	errln(getSysParameters().getPipeline_NLProcessor() + " type is not supported for natural language processing");
	        	NLProcessorSet = false;
			}

		}
		
		catch (Exception e) {
        	errln(" Natural language processor error: " + e.getMessage());
        	NLProcessorSet = false;
		}

	}



    // Load Natural Language Processor
	public void LoadNLProcessor() {

		if (NLProcessorSet)
			NLDocumentProcessor.load();

	}



    // Unload Natural Language Processor
	public void unloadNLProcessor() {

		resetDocument();

		if (NLProcessorSet && NLDocumentProcessor.isLoaded())
			NLDocumentProcessor.unload();

	}



    // Reset Natural Language Processor
	public void resetNLProcessor() {

		unloadNLProcessor();
		setAndLoadNLProcessor(true);

	}
	


    // Reset Natural Language Document
	public void resetDocument() {

		ProcessedDocument = null;

		if (NLProcessorSet && NLDocumentProcessor.isLoaded())
			NLDocumentProcessor.resetNLDocument();

	}



    // Process Natural Language Text
	public void processNL(String nlText) {
		
		if (!NLProcessorSet) {
			errln("Cannot perform NLP on document. " + getSysParameters().getPipeline_NLProcessor() + " NL processor not set!");
			return;
		}
		
		if (!NLDocumentProcessor.isLoaded()) {
			errln("Cannot perform NLP on document. " + getSysParameters().getPipeline_NLProcessor() + " NL processor not loaded!");
			return;
		}

		resetDocument();
		ProcessedDocument = NLDocumentProcessor.annotateDocument(nlText);

	}


	
	// Get NLP Data
	public String getNLPData() {
		
		if (!NLProcessorSet) {
			errln("Cannot get NLP data. " + getSysParameters().getPipeline_NLProcessor() + " NL processor not set!");
			return "";
		}
		
		if (!NLDocumentProcessor.isAnnotated()) {
			errln("Cannot get NLP data. Document is not annotated!");
			return "";
		}

		return NLDocumentProcessor.getNLPData(ProcessedDocument);

	}
	

	
	// Generate Logic Predicates
	public void generateLogicPredicates() {
		
		if (ProcessedDocument == null) {
			errln("Cannot generate logic predicates. Document object is null!");
			return;
		}

		ProcessedDocument.generateLogicPredicates();

	}
	

	
	// Get Logic Annotation Text
	public String getLogicAnnotationText() {
			
		if (ProcessedDocument == null) {
			errln("Cannot get logic annotation text. Document object is null!");
			return "";
		}

		return ProcessedDocument.buildLogicAnnotationText();

	}


	
	// Generate Logic Expressions
	public void generateLogicExpressions(String translationPolicyString) {
		
		ITranslationPolicy	translationPolicy;
		IReasoning          reasoningAgent;
		
		if (ProcessedDocument == null) {
			errln("Cannot generate logic expresssions. Document object is null!");
			return;
		}
		
		if (getTranslationParameters().getPolicy_Language().equals("prudens-java")) {
            translationPolicy = new PrudensTranslationPolicy(translationPolicyString);
            reasoningAgent = new PrudensJava();
		}
        
		else if (getTranslationParameters().getPolicy_Language().equals("prudensjs-web")) {
            translationPolicy = new PrudensTranslationPolicy(translationPolicyString);
            reasoningAgent = new PrudensJS();
		}

		else {
			errln("Translation Policy Error: Unknown Translation Policy Language [" + getTranslationParameters().getPolicy_Language() + "]");
			return;
		}

		ProcessedDocument.generateLogicExpressions(translationPolicy, reasoningAgent);

	}



	// Get Logic Representation Text
	public String getLogicBasedRepresentationText() {
		
		if (ProcessedDocument == null) {
			errln("Cannot get logic expresssions text. Document object is null!");
			return "";
		}

		return ProcessedDocument.buildLogicBasedRepresentationText();

	}


	
    // Get Natural Language Document JSON Representation
	public String getPrettyDocumentJSON() {
		
		if (ProcessedDocument == null) {
			errln("Cannot get document JSON representation. Document object is null!");
			return "";
		}

		return ProcessedDocument.toPrettyJSONString();

	}


	
    // Get Natural Language Document JSON Representation
	public String getDocumentJSON() {
		
		if (ProcessedDocument == null) {
			errln("Cannot get document JSON representation. Document object is null!");
			return "";
		}

		return ProcessedDocument.toJSONString();

	}



    // Get Translation Policy Version
	public String getTranslationPolicyVersion(String translationPolicyString) {

		ITranslationPolicy	translationPolicy;

		if (getTranslationParameters().getPolicy_Language().equals("prudens-java")) {
            translationPolicy = new PrudensTranslationPolicy(translationPolicyString);
			return translationPolicy.inferTranslationPolicyVersion(new PrudensJava());
		}
        
		else if (getTranslationParameters().getPolicy_Language().equals("prudensjs-web")) {
            translationPolicy = new PrudensTranslationPolicy(translationPolicyString);
			return translationPolicy.inferTranslationPolicyVersion(new PrudensJS());
		}

		else {
			errln("Translation Policy Error: Unknown Translation Policy Language [" + getTranslationParameters().getPolicy_Language() + "]");
		}

		return "Translation Policy Not Set";

	}

	
	
	/**
	 * @return the processedDocument
	 */
	public  NLDocument getProcessedDocument() {
		return ProcessedDocument;
	}
	

}
