package cy.ac.ouc.cognition.nestor.lib.parameters;

public class TranslationParameters extends NESTORParameters {

	
    // The single instance of the NESTORParameters class
    private static TranslationParameters	TranslationParametersInstance;
    protected static String					TranslationParametersFile = "NESTORTranslation.Settings.json";


    protected TranslationParameters() {

    	super();

    }


	/********************************
	 * Natural Language Processing Parameters
	 ********************************/
    private String		NLP_CreateCorefPOS;


	/********************************
	 * Translation Policy Parameters
	 ********************************/
	private String		Policy_Language;
	private int			Policy_PredicateMode;
	private String		Policy_VersionPredicate;
	private String		Policy_VersionDataPredicate;
	private String		Policy_GeneratePredicate;
	private String		Policy_ArgumentSeparator;
	private String		Policy_PredicateSeparator;
	private String		Policy_VarPlaceholder;
	private String		Policy_DynamicVarPlaceholder;
	private String		Policy_NegationConstant;
	private String		Policy_ActionConstant;

	
	/*********************************************************
	 * Translation Policy Parameters for Prudens Java language
	 *********************************************************/
	private String		PrudensJava_RuleImportance;
	
	
	/******************************************
	 * Logic Expression Generation Parameters
	 ******************************************/
	private String		Generate_LineSeperator;
	private String		Generate_PredNameConcatChar;
	private boolean		Generate_PredNameCapitalize;
	private String		Generate_PredNameCapitalizeExceptions;
	private String		Generate_NeckSymbol;
	private String		Generate_ConflictNeckSymbol;
	private boolean		Generate_AddName;
	private String		Generate_NameSeparator;
	private String		Generate_VariableName;
	private boolean		Generate_IgnoreBodyGroups;

	

    public static synchronized TranslationParameters getInstance(String jsonString) {

    	if (TranslationParametersInstance == null) {

    		System.out.println("Translation Parameters Instance is null!");
 
        	TranslationParametersInstance = new TranslationParameters();

        	// Update configuration from a source file or jsonString
        	TranslationParametersInstance.updateValues(jsonString);
    	}
 
    	else if (jsonString != null && !jsonString.equals(""))
        	// Update configuration from jsonString
        	TranslationParametersInstance.updateValues(jsonString);

    	
    	return TranslationParametersInstance;

    }


	
	public String getParametersFile() {
		return TranslationParametersFile;
	}


	protected void initializeDefaults() {
		
		NLP_CreateCorefPOS = "NN|NNP|NNS";
		
		Policy_Language = "prudensjs-web";
		Policy_PredicateMode = 0;
		Policy_VersionPredicate = "metakbinfo(version);";
		Policy_VersionDataPredicate = "metakbinfo_data";
		Policy_GeneratePredicate = "!generate";
		Policy_ArgumentSeparator = "args";
		Policy_PredicateSeparator = "next";
		Policy_VarPlaceholder = "vph_";
		Policy_DynamicVarPlaceholder = "dvph_";
		Policy_NegationConstant = "negation";
		Policy_ActionConstant = "action";

		PrudensJava_RuleImportance = "desc";
		
		Generate_LineSeperator = System.getProperty("line.separator");
		Generate_PredNameConcatChar = "";
		Generate_PredNameCapitalize = true;
		Generate_PredNameCapitalizeExceptions = "-|!|(|)";
		Generate_NeckSymbol = "implies";
		Generate_ConflictNeckSymbol = "#";
		Generate_AddName = false;
		Generate_NameSeparator = "::";
		Generate_VariableName = "X";
		Generate_IgnoreBodyGroups = false;
		
	}

	
	
	public void printValues() {
		
		System.out.println("");
		System.out.println("Translation Parameters Values:");
		System.out.println("NLP_CreateCorefPOS = [" + NLP_CreateCorefPOS + "]");

		System.out.println("Policy_Language = [" + Policy_Language + "]");
		System.out.println("Policy_PredicateMode = [" + Policy_PredicateMode + "]");
		System.out.println("Policy_VersionPredicate = [" + Policy_VersionPredicate + "]");
		System.out.println("Policy_VersionDataPredicate = [" + Policy_VersionDataPredicate + "]");
		System.out.println("Policy_GeneratePredicate = [" + Policy_GeneratePredicate + "]");
		System.out.println("Policy_ArgumentSeparator = [" + Policy_ArgumentSeparator + "]");
		System.out.println("Policy_PredicateSeparator = [" + Policy_PredicateSeparator + "]");
		System.out.println("Policy_VarPlaceholder = [" + Policy_VarPlaceholder + "]");
		System.out.println("Policy_DynamicVarPlaceholder = [" + Policy_DynamicVarPlaceholder + "]");
		System.out.println("Policy_NegationConstant = [" + Policy_NegationConstant + "]");
		System.out.println("Policy_ActionConstant = [" + Policy_ActionConstant + "]");

		System.out.println("PrudensJava_RuleImportance = [" + PrudensJava_RuleImportance + "]");

		System.out.println("Generate_LineSeperator = [" + Generate_LineSeperator + "]");
		System.out.println("Generate_PredNameConcatChar = [" + Generate_PredNameConcatChar + "]");
		System.out.println("Generate_PredNameCapitalize = [" + Generate_PredNameCapitalize + "]");
		System.out.println("Generate_PredNameCapitalizeExceptions = [" + Generate_PredNameCapitalizeExceptions + "]");
		System.out.println("Generate_NeckSymbol = [" + Generate_NeckSymbol + "]");
		System.out.println("Generate_ConflictNeckSymbol = [" + Generate_ConflictNeckSymbol + "]");
		System.out.println("Generate_AddName = [" + Generate_AddName + "]");
		System.out.println("Generate_NameSeparator = [" + Generate_NameSeparator + "]");
		System.out.println("Generate_VariableName = [" + Generate_VariableName + "]");
		System.out.println("Generate_IgnoreBodyGroups = [" + Generate_IgnoreBodyGroups + "]");

	}

	
	
	/**
	 * @return the nLP_CreateCorefPOS
	 */
	public String getNLP_CreateCorefPOS() {
		return NLP_CreateCorefPOS;
	}
	/**
	 * @param nLP_CreateCorefPOS the nLP_CreateCorefPOS to set
	 */
	public void setNLP_CreateCorefPOS(String nLP_CreateCorefPOS) {
		NLP_CreateCorefPOS = nLP_CreateCorefPOS;
	}
	/**
	 * @return the policy_Language
	 */
	public String getPolicy_Language() {
		return Policy_Language;
	}
	/**
	 * @param policy_Language the policy_Language to set
	 */
	public void setPolicy_Language(String policy_Language) {
		Policy_Language = policy_Language;
	}
	/**
	 * @return the policy_PredicateMode
	 */
	public int getPolicy_PredicateMode() {
		return Policy_PredicateMode;
	}
	/**
	 * @param policy_PredicateMode the policy_PredicateMode to set
	 */
	public void setPolicy_PredicateMode(int policy_PredicateMode) {
		Policy_PredicateMode = policy_PredicateMode;
	}
	/**
	 * @return the policy_VersionPredicate
	 */
	public String getPolicy_VersionPredicate() {
		return Policy_VersionPredicate;
	}
	/**
	 * @param policy_VersionPredicate the policy_VersionPredicate to set
	 */
	public void setPolicy_VersionPredicate(String policy_VersionPredicate) {
		Policy_VersionPredicate = policy_VersionPredicate;
	}
	/**
	 * @return the policy_VersionDataPredicate
	 */
	public String getPolicy_VersionDataPredicate() {
		return Policy_VersionDataPredicate;
	}
	/**
	 * @param policy_VersionDataPredicate the policy_VersionDataPredicate to set
	 */
	public void setPolicy_VersionDataPredicate(String policy_VersionDataPredicate) {
		Policy_VersionDataPredicate = policy_VersionDataPredicate;
	}
	/**
	 * @return the policy_GeneratePredicate
	 */
	public String getPolicy_GeneratePredicate() {
		return Policy_GeneratePredicate;
	}
	/**
	 * @param policy_GeneratePredicate the policy_GeneratePredicate to set
	 */
	public void setPolicy_GeneratePredicate(String policy_GeneratePredicate) {
		Policy_GeneratePredicate = policy_GeneratePredicate;
	}
	/**
	 * @return the policy_ArgumentSeparator
	 */
	public String getPolicy_ArgumentSeparator() {
		return Policy_ArgumentSeparator;
	}
	/**
	 * @param policy_ArgumentSeparator the policy_ArgumentSeparator to set
	 */
	public void setPolicy_ArgumentSeparator(String policy_ArgumentSeparator) {
		Policy_ArgumentSeparator = policy_ArgumentSeparator;
	}
	/**
	 * @return the policy_PredicateSeparator
	 */
	public String getPolicy_PredicateSeparator() {
		return Policy_PredicateSeparator;
	}
	/**
	 * @param policy_PredicateSeparator the policy_PredicateSeparator to set
	 */
	public void setPolicy_PredicateSeparator(String policy_PredicateSeparator) {
		Policy_PredicateSeparator = policy_PredicateSeparator;
	}
	/**
	 * @return the policy_VarPlaceholder
	 */
	public String getPolicy_VarPlaceholder() {
		return Policy_VarPlaceholder;
	}
	/**
	 * @param policy_VarPlaceholder the policy_VarPlaceholder to set
	 */
	public void setPolicy_VarPlaceholder(String policy_VarPlaceholder) {
		Policy_VarPlaceholder = policy_VarPlaceholder;
	}
	/**
	 * @return the policy_DynamicVarPlaceholder
	 */
	public String getPolicy_DynamicVarPlaceholder() {
		return Policy_DynamicVarPlaceholder;
	}
	/**
	 * @param policy_DynamicVarPlaceholder the policy_DynamicVarPlaceholder to set
	 */
	public void setPolicy_DynamicVarPlaceholder(String policy_DynamicVarPlaceholder) {
		Policy_DynamicVarPlaceholder = policy_DynamicVarPlaceholder;
	}
	/**
	 * @return the policy_NegationConstant
	 */
	public String getPolicy_NegationConstant() {
		return Policy_NegationConstant;
	}
	/**
	 * @param policy_NegationConstant the policy_NegationConstant to set
	 */
	public void setPolicy_NegationConstant(String policy_NegationConstant) {
		Policy_NegationConstant = policy_NegationConstant;
	}
	/**
	 * @return the policy_ActionConstant
	 */
	public String getPolicy_ActionConstant() {
		return Policy_ActionConstant;
	}
	/**
	 * @param policy_ActionConstant the policy_ActionConstant to set
	 */
	public void setPolicy_ActionConstant(String policy_ActionConstant) {
		Policy_ActionConstant = policy_ActionConstant;
	}
	/**
	 * @return the prudensJava_RuleImportance
	 */
	public String getPrudensJava_RuleImportance() {
		return PrudensJava_RuleImportance;
	}
	/**
	 * @param prudensJava_RuleImportance the prudensJava_RuleImportance to set
	 */
	public void setPrudensJava_RuleImportance(String prudensJava_RuleImportance) {
		PrudensJava_RuleImportance = prudensJava_RuleImportance;
	}
	/**
	 * @return the generate_LineSeperator
	 */
	public String getGenerate_LineSeperator() {
		return Generate_LineSeperator;
	}
	/**
	 * @param generate_LineSeperator the generate_LineSeperator to set
	 */
	public void setGenerate_LineSeperator(String generate_LineSeperator) {
		Generate_LineSeperator = generate_LineSeperator;
	}
	/**
	 * @return the generate_PredNameConcatChar
	 */
	public String getGenerate_PredNameConcatChar() {
		return Generate_PredNameConcatChar;
	}
	/**
	 * @param generate_PredNameConcatChar the generate_PredNameConcatChar to set
	 */
	public void setGenerate_PredNameConcatChar(String generate_PredNameConcatChar) {
		Generate_PredNameConcatChar = generate_PredNameConcatChar;
	}
	/**
	 * @return the generate_PredNameCapitalize
	 */
	public boolean isGenerate_PredNameCapitalize() {
		return Generate_PredNameCapitalize;
	}
	/**
	 * @param generate_PredNameCapitalize the generate_PredNameCapitalize to set
	 */
	public void setGenerate_PredNameCapitalize(boolean generate_PredNameCapitalize) {
		Generate_PredNameCapitalize = generate_PredNameCapitalize;
	}
	/**
	 * @return the generate_PredNameCapitalizeExceptions
	 */
	public String getGenerate_PredNameCapitalizeExceptions() {
		return Generate_PredNameCapitalizeExceptions;
	}
	/**
	 * @param generate_PredNameCapitalizeExceptions the generate_PredNameCapitalizeExceptions to set
	 */
	public void setGenerate_PredNameCapitalizeExceptions(String generate_PredNameCapitalizeExceptions) {
		Generate_PredNameCapitalizeExceptions = generate_PredNameCapitalizeExceptions;
	}
	/**
	 * @return the generate_NeckSymbol
	 */
	public String getGenerate_NeckSymbol() {
		return Generate_NeckSymbol;
	}
	/**
	 * @param generate_NeckSymbol the generate_NeckSymbol to set
	 */
	public void setGenerate_NeckSymbol(String generate_NeckSymbol) {
		Generate_NeckSymbol = generate_NeckSymbol;
	}
	/**
	 * @return the generate_ConflictNeckSymbol
	 */
	public String getGenerate_ConflictNeckSymbol() {
		return Generate_ConflictNeckSymbol;
	}
	/**
	 * @param generate_ConflictNeckSymbol the generate_ConflictNeckSymbol to set
	 */
	public void setGenerate_ConflictNeckSymbol(String generate_ConflictNeckSymbol) {
		Generate_ConflictNeckSymbol = generate_ConflictNeckSymbol;
	}
	/**
	 * @return the generate_AddName
	 */
	public boolean isGenerate_AddName() {
		return Generate_AddName;
	}
	/**
	 * @param generate_AddName the generate_AddName to set
	 */
	public void setGenerate_AddName(boolean generate_AddName) {
		Generate_AddName = generate_AddName;
	}
	/**
	 * @return the generate_NameSeparator
	 */
	public String getGenerate_NameSeparator() {
		return Generate_NameSeparator;
	}
	/**
	 * @param generate_NameSeparator the generate_NameSeparator to set
	 */
	public void setGenerate_NameSeparator(String generate_NameSeparator) {
		Generate_NameSeparator = generate_NameSeparator;
	}
	/**
	 * @return the generate_VariableName
	 */
	public String getGenerate_VariableName() {
		return Generate_VariableName;
	}
	/**
	 * @param generate_VariableName the generate_VariableName to set
	 */
	public void setGenerate_VariableName(String generate_VariableName) {
		Generate_VariableName = generate_VariableName;
	}

	/**
	 * @return the generate_IgnoreBodyGroups
	 */
	public boolean isGenerate_IgnoreBodyGroups() {
		return Generate_IgnoreBodyGroups;
	}

	/**
	 * @param generate_IgnoreBodyGroups the generate_IgnoreBodyGroups to set
	 */
	public void setGenerate_IgnoreBodyGroups(boolean generate_IgnoreBodyGroups) {
		Generate_IgnoreBodyGroups = generate_IgnoreBodyGroups;
	}

	
}
