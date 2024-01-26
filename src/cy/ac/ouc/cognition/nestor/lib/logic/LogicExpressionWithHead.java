package cy.ac.ouc.cognition.nestor.lib.logic;

public abstract class LogicExpressionWithHead extends LogicExpression {

	protected PredicateSet	Head;

	LogicExpressionWithHead() {
		this("");
	}
	
	
	LogicExpressionWithHead(String name) {
		super(name);
		Head = new PredicateSet(PartType.HEAD);
		HasHead = true;
	}
	


	@Override
	protected String buildTextRepresentation() {

 		String textRepresentation = "";
 		
		if (!Body.getTextRepresentation().isEmpty())
			textRepresentation +=	Body.getTextRepresentation() + " " +
									getTranslationParameters().getGenerate_NeckSymbol() + " ";
		
		if (!Head.getTextRepresentation().isEmpty())
			textRepresentation += Head.getTextRepresentation() + ";";
	        
		if (TitleIncluded)
			textRepresentation = Title + " " + getTranslationParameters().getGenerate_NameSeparator() + " " + textRepresentation;

	    return textRepresentation;

	}


	public boolean arePredicatesAdded() {

		return Body.arePredicatesAdded() || Head.arePredicatesAdded();

	}


	
	public boolean sameAs(LogicElement logicElement, boolean ignoreNegation) {

		return	this.Head.sameAs(((LogicExpressionWithHead) logicElement).Body, ignoreNegation) &&
				this.Body.sameAs(((LogicExpressionWithHead) logicElement).Body, ignoreNegation);
	
	}

	

	
	/**
	 * @return the head
	 */
	public PredicateSet getHead() {
		return Head;
	}


	/**
	 * @param head the head to set
	 */
	public void setHead(PredicateSet head) {
		Head = head;
	}


	/**
	 * @@param complete the complete to set
	 */
	public void setComplete(boolean complete) {
		Body.setComplete(complete);
		Head.setComplete(complete);
		super.setComplete(complete);
	}

}
