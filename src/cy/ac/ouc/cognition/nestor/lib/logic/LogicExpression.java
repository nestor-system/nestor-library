package cy.ac.ouc.cognition.nestor.lib.logic;

public abstract class LogicExpression extends LogicElement {

	protected transient boolean		TitleIncluded;
	protected transient boolean		HasHead;

	protected ExpressionType		TypeOfExpression;
	protected boolean				MultiExpression;
	protected boolean				HasDocumentScope;
	protected String				Title;
	protected PredicateSet			Body;


	LogicExpression() {
		this("");
	}
	
	
	LogicExpression(String title) {
		super();
		Title = new String(title);
		TitleIncluded = getTranslationParameters().isGenerate_AddName();
		Body = new PredicateSet(PartType.BODY);
	}
	


	@Override
	protected String buildTextRepresentation() {

 		String textRepresentation = Body.getTextRepresentation();
	        
	    return textRepresentation;

	}


	public boolean arePredicatesAdded() {

		return Body.arePredicatesAdded();

	}


	
	public boolean sameAs(LogicElement logicElement, boolean ignoreNegation) {

		return this.Body.sameAs(((LogicExpression) logicElement).Body, ignoreNegation);
	
	}
	



	/**
	 * @return the typeOfExpression
	 */
	public ExpressionType getTypeOfExpression() {
		return TypeOfExpression;
	}


	/**
	 * @return the multiExpression
	 */
	public boolean isMultiExpression() {
		return MultiExpression;
	}


	/**
	 * @return the hasDocumentScope
	 */
	public boolean hasDocumentScope() {
		return HasDocumentScope;
	}


	/**
	 * @param hasDocumentScope the hasDocumentScope to set
	 */
	public void setHasDocumentScope(boolean hasDocumentScope) {
		HasDocumentScope = hasDocumentScope;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return Title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		Title = title;
	}


	/**
	 * @return the titleIncluded
	 */
	public boolean isTitleIncluded() {
		return TitleIncluded;
	}


	/**
	 * @param titleIncluded the titleIncluded to set
	 */
	public void setTitleIncluded(boolean titleIncluded) {
		TitleIncluded = titleIncluded;
	}


	/**
	 * @return the body
	 */
	public PredicateSet getBody() {
		return Body;
	}


	/**
	 * @param body the body to set
	 */
	public void setBody(PredicateSet body) {
		Body = body;
	}


	/**
	 * @return the hasHead
	 */
	public boolean hasHead() {
		return HasHead;
	}

	
	/**
	 * @@param complete the complete to set
	 */
	public void setComplete(boolean complete) {
		Body.setComplete(complete);
		super.setComplete(complete);
	}

}
