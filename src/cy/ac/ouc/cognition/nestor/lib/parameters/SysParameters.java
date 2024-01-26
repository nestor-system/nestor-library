package cy.ac.ouc.cognition.nestor.lib.parameters;

public class SysParameters extends NESTORParameters {

    // The single instance of the NESTORParameters class
    private static SysParameters	SysParametersInstance;
    protected static String			SysParametersFile = "NESTOR.Settings.json";


    protected SysParameters() {

    	super();

    }


    /********************************
	 * NESTOR Library System Parameters
	 ********************************/
	private int			System_TraceLevel;
	private String		System_DefaultTraceLevel;
	private String		System_TraceTimestampFormat;
	private boolean		System_TraceUseTimestamp;
	private int			System_MaximumGenerationGroups;
	private String		System_LineSeperator;


	/*********************************************************
	 * NESTOR Library System Parameters for PrudensJS language
	 *********************************************************/
	private boolean		PrudensJS_AlwaysPrintResponseBody;
	private String		PrudensJS_ServiceURI;
	private String		PrudensJS_ServiceContentType;
	private String		PrudensJS_ServiceAccept;
	private String		PrudensJS_ServiceUserAgent;


	/***********************************
	 * NESTOR Pipeline System Parameters
	 ***********************************/
	private String		Pipeline_NLProcessor;
    
    
    /************************************
	 * NESTOR Stanford CoreNLP Parameters
	 ************************************/
	private String		CoreNLP_Annotators;
	private String		CoreNLP_Algorithm;
	private String		CoreNLP_Split;

	

    public static synchronized SysParameters getInstance(String jsonString) {

    	if (SysParametersInstance == null) {
 
    		System.out.println("System Parameters Instance is null!");

        	SysParametersInstance = new SysParameters();

        	// Update configuration from a source file or jsonString
        	SysParametersInstance.updateValues(jsonString);
    	}

    	else if (jsonString != null && !jsonString.equals(""))
        	// Update configuration from jsonString
    		SysParametersInstance.updateValues(jsonString);


    	return SysParametersInstance;

    }



	public String getParametersFile() {
		return SysParametersFile;
	}


	protected void initializeDefaults() {
		
		System_TraceLevel = 2;
		System_DefaultTraceLevel = "NORMAL";
		System_TraceTimestampFormat = "uuuu/MM/dd HH:mm:ss.S";
		System_TraceUseTimestamp = true;
		System_MaximumGenerationGroups = 100;
		System_LineSeperator = System.getProperty("line.separator");

		PrudensJS_AlwaysPrintResponseBody = true;
		PrudensJS_ServiceURI = "https://us-central1-prudens---dev.cloudfunctions.net/app/deduce";
		PrudensJS_ServiceContentType = "application/json";
		PrudensJS_ServiceAccept = "application/json*";
		PrudensJS_ServiceUserAgent = "NESTOR/1.2";

		Pipeline_NLProcessor = "corenlp";

		// KNOWN ANNOTATORS: tokenize, ssplit, pos, lemma, ner, parse, depparse, coref, kbp, quote

		// Set annotators with no coreference annotation
		// StanfordCoreNLProcessor_Annotators = "tokenize, ssplit, pos, depparse, lemma, ner";

		// Set annotators with coreference annotation
		CoreNLP_Annotators = "tokenize, ssplit, pos, parse, depparse, lemma, ner";

		// The coref annotator is being set to use the statistical algorithm
		CoreNLP_Algorithm = "statistical";

		// Define how Stanford Core NLP will split a sentence
		CoreNLP_Split = "false";
		
	}



	public void printValues() {
		
		System.out.println("");
		System.out.println("System Parameters Values:");
		System.out.println("System_TraceLevel = [" + System_TraceLevel + "]");
		System.out.println("System_DefaultTraceLevel = [" + System_DefaultTraceLevel + "]");
		System.out.println("System_TraceTimestampFormat = [" + System_TraceTimestampFormat + "]");
		System.out.println("System_TraceUseTimestamp = [" + System_TraceUseTimestamp + "]");
		System.out.println("System_MaximumGenerationGroups = [" + System_MaximumGenerationGroups + "]");
		System.out.println("System_LineSeperator = [" + System_LineSeperator + "]");

		System.out.println("PrudensJS_AlwaysPrintResponseBody = [" + PrudensJS_AlwaysPrintResponseBody + "]");
		System.out.println("PrudensJS_ServiceURI = [" + PrudensJS_ServiceURI + "]");
		System.out.println("PrudensJS_ServiceContentType = [" + PrudensJS_ServiceContentType + "]");
		System.out.println("PrudensJS_ServiceAccept = [" + PrudensJS_ServiceAccept + "]");
		System.out.println("PrudensJS_ServiceUserAgent = [" + PrudensJS_ServiceUserAgent + "]");

		System.out.println("Pipeline_NLProcessor = [" + Pipeline_NLProcessor + "]");

		System.out.println("CoreNLP_Annotators = [" + CoreNLP_Annotators + "]");
		System.out.println("CoreNLP_Algorithm = [" + CoreNLP_Algorithm + "]");
		System.out.println("CoreNLP_Split = [" + CoreNLP_Split + "]");

	}

	
	
	/**
	 * @return the system_TraceLevel
	 */
	public int getSystem_TraceLevel() {
		return System_TraceLevel;
	}
	/**
	 * @param system_TraceLevel the system_TraceLevel to set
	 */
	public void setSystem_TraceLevel(int system_TraceLevel) {
		System_TraceLevel = system_TraceLevel;
	}
	/**
	 * @return the system_DefaultTraceLevel
	 */
	public String getSystem_DefaultTraceLevel() {
		return System_DefaultTraceLevel;
	}
	/**
	 * @param system_DefaultTraceLevel the system_DefaultTraceLevel to set
	 */
	public void setSystem_DefaultTraceLevel(String system_DefaultTraceLevel) {
		System_DefaultTraceLevel = system_DefaultTraceLevel;
	}
	/**
	 * @return the system_TraceTimestampFormat
	 */
	public String getSystem_TraceTimestampFormat() {
		return System_TraceTimestampFormat;
	}
	/**
	 * @param system_TraceTimestampFormat the system_TraceTimestampFormat to set
	 */
	public void setSystem_TraceTimestampFormat(String system_TraceTimestampFormat) {
		System_TraceTimestampFormat = system_TraceTimestampFormat;
	}
	/**
	 * @return the system_TraceUseTimestamp
	 */
	public boolean isSystem_TraceUseTimestamp() {
		return System_TraceUseTimestamp;
	}
	/**
	 * @param system_TraceUseTimestamp the system_TraceUseTimestamp to set
	 */
	public void setSystem_TraceUseTimestamp(boolean system_TraceUseTimestamp) {
		System_TraceUseTimestamp = system_TraceUseTimestamp;
	}
	/**
	 * @return the system_MaximumGenerationGroups
	 */
	public int getSystem_MaximumGenerationGroups() {
		return System_MaximumGenerationGroups;
	}
	/**
	 * @param system_MaximumGenerationGroups the system_MaximumGenerationGroups to set
	 */
	public void setSystem_MaximumGenerationGroups(int system_MaximumGenerationGroups) {
		System_MaximumGenerationGroups = system_MaximumGenerationGroups;
	}
	/**
	 * @return the system_LineSeperator
	 */
	public String getSystem_LineSeperator() {
		return System_LineSeperator;
	}
	/**
	 * @param system_LineSeperator the system_LineSeperator to set
	 */
	public void setSystem_LineSeperator(String system_LineSeperator) {
		System_LineSeperator = system_LineSeperator;
	}
	/**
	 * @return the prudensJS_AlwaysPrintResponseBody
	 */
	public boolean isPrudensJS_AlwaysPrintResponseBody() {
		return PrudensJS_AlwaysPrintResponseBody;
	}
	/**
	 * @return the prudensJS_ServiceURI
	 */
	public String getPrudensJS_ServiceURI() {
		return PrudensJS_ServiceURI;
	}
	/**
	 * @param prudensJS_ServiceURI the prudensJS_ServiceURI to set
	 */
	public void setPrudensJS_ServiceURI(String prudensJS_ServiceURI) {
		PrudensJS_ServiceURI = prudensJS_ServiceURI;
	}
	/**
	 * @return the prudensJS_ServiceContentType
	 */
	public String getPrudensJS_ServiceContentType() {
		return PrudensJS_ServiceContentType;
	}
	/**
	 * @param prudensJS_ServiceContentType the prudensJS_ServiceContentType to set
	 */
	public void setPrudensJS_ServiceContentType(String prudensJS_ServiceContentType) {
		PrudensJS_ServiceContentType = prudensJS_ServiceContentType;
	}
	/**
	 * @return the prudensJS_ServiceAccept
	 */
	public String getPrudensJS_ServiceAccept() {
		return PrudensJS_ServiceAccept;
	}
	/**
	 * @param prudensJS_ServiceAccept the prudensJS_ServiceAccept to set
	 */
	public void setPrudensJS_ServiceAccept(String prudensJS_ServiceAccept) {
		PrudensJS_ServiceAccept = prudensJS_ServiceAccept;
	}
	/**
	 * @return the prudensJS_ServiceUserAgent
	 */
	public String getPrudensJS_ServiceUserAgent() {
		return PrudensJS_ServiceUserAgent;
	}
	/**
	 * @param prudensJS_ServiceUserAgent the prudensJS_ServiceUserAgent to set
	 */
	public void setPrudensJS_ServiceUserAgent(String prudensJS_ServiceUserAgent) {
		PrudensJS_ServiceUserAgent = prudensJS_ServiceUserAgent;
	}
	/**
	 * @param prudensJS_AlwaysPrintResponseBody the prudensJS_AlwaysPrintResponseBody to set
	 */
	public void setPrudensJS_AlwaysPrintResponseBody(boolean prudensJS_AlwaysPrintResponseBody) {
		PrudensJS_AlwaysPrintResponseBody = prudensJS_AlwaysPrintResponseBody;
	}
	/**
	 * @return the pipeline_NLProcessor
	 */
	public String getPipeline_NLProcessor() {
		return Pipeline_NLProcessor;
	}
	/**
	 * @param pipeline_NLProcessor the pipeline_NLProcessor to set
	 */
	public void setPipeline_NLProcessor(String pipeline_NLProcessor) {
		Pipeline_NLProcessor = pipeline_NLProcessor;
	}
	/**
	 * @return the coreNLP_Annotators
	 */
	public String getCoreNLP_Annotators() {
		return CoreNLP_Annotators;
	}
	/**
	 * @param coreNLP_Annotators the coreNLP_Annotators to set
	 */
	public void setCoreNLP_Annotators(String coreNLP_Annotators) {
		CoreNLP_Annotators = coreNLP_Annotators;
	}
	/**
	 * @return the coreNLP_Algorithm
	 */
	public String getCoreNLP_Algorithm() {
		return CoreNLP_Algorithm;
	}
	/**
	 * @param coreNLP_Algorithm the coreNLP_Algorithm to set
	 */
	public void setCoreNLP_Algorithm(String coreNLP_Algorithm) {
		CoreNLP_Algorithm = coreNLP_Algorithm;
	}
	/**
	 * @return the coreNLP_Split
	 */
	public String getCoreNLP_Split() {
		return CoreNLP_Split;
	}
	/**
	 * @param coreNLP_Split the coreNLP_Split to set
	 */
	public void setCoreNLP_Split(String coreNLP_Split) {
		CoreNLP_Split = coreNLP_Split;
	}

	
}
