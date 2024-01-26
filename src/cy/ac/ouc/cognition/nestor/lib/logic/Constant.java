package cy.ac.ouc.cognition.nestor.lib.logic;

public class Constant extends LogicExpression {

	public Constant() {
		this("");
	}
	
	
	public Constant(String name) {
		super(name);
		TypeOfExpression = ExpressionType.CONSTANT;
		MultiExpression = true;
		HasDocumentScope = true;
		HasHead = false;
	}
	


	@Override
	protected String buildTextRepresentation() {

 		String textRepresentation = "";
 		
 		Body.setDelimiter(" ");
 		
		if (!Body.getTextRepresentation().isEmpty())
			textRepresentation += Body.getTextRepresentation() + ".";      
        
		if (TitleIncluded)
			textRepresentation = Title + " " + getTranslationParameters().getGenerate_NameSeparator() + " " + textRepresentation;

	    return textRepresentation;

	}

}
