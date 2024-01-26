package cy.ac.ouc.cognition.nestor.lib.logic;

public class Implication extends LogicExpressionWithHead {
	
	public Implication() {
		this("");
	}
	
	
	public Implication(String title) {
		super(title);
		TypeOfExpression = ExpressionType.IMPLICATION;
		MultiExpression = false;
		HasDocumentScope = false;
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

}
