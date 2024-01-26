package cy.ac.ouc.cognition.nestor.lib.logic;

public class Conflict extends LogicExpressionWithHead {
	
	public Conflict() {
		this("");
	}
	
	
	public Conflict(String name) {
		super(name);
		TypeOfExpression = ExpressionType.CONFLICT;
		MultiExpression = false;
		HasDocumentScope = false;
	}
	


	@Override
	protected String buildTextRepresentation() {

 		String textRepresentation = "";
 		
		if (!Body.getTextRepresentation().isEmpty())
			textRepresentation +=	Body.getTextRepresentation() + " " +
 									getTranslationParameters().getGenerate_ConflictNeckSymbol() + " ";
		
		if (!Head.getTextRepresentation().isEmpty())
			textRepresentation += Head.getTextRepresentation() + ";";
	        
		if (TitleIncluded)
			textRepresentation = Title + " " + getTranslationParameters().getGenerate_NameSeparator() + " " + textRepresentation;
	        
	    return textRepresentation;

	}

}
