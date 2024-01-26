package cy.ac.ouc.cognition.nestor.lib.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LiteralName extends LogicElement {

	protected	List<String>	NameParts;


	public LiteralName() {
		this("");
	}
	
	
	public LiteralName(String name) {
		this(new ArrayList<String>(Arrays.asList(name)));
	}
	

	public LiteralName(List<String> nameParts) {
		super();
		NameParts = nameParts;
	}


	
	protected String buildNameText(boolean ignoreNegation) {
		
		String nameText = "";
		int i = 0, doNotCapitalizeIndex = 1;
		
		for (String namePart : NameParts) {
			
			String namePartToUse = namePart.
									replaceAll("\'", "").
									replaceAll(getTranslationParameters().getPolicy_NegationConstant(), "-").
									replaceAll(getTranslationParameters().getPolicy_ActionConstant(), "!");

			i++;
			
			if (i==1 && ignoreNegation && namePartToUse.equals("-"))
				i = 0;
			
			// If current word belongs in capitalization exceptions list, do not capitalise next word, if should
			else if (namePartToUse.matches(getTranslationParameters().getGenerate_PredNameCapitalizeExceptions())) {
				doNotCapitalizeIndex = i + 1;
				nameText += namePartToUse;
			}

			else if (i==1)
				nameText += namePartToUse;

			else {
				nameText += getTranslationParameters().getGenerate_PredNameConcatChar();
				if (getTranslationParameters().isGenerate_PredNameCapitalize() && i != doNotCapitalizeIndex)
					nameText += namePartToUse.substring(0, 1).toUpperCase() + namePartToUse.substring(1);
				else
					nameText += namePartToUse;
			}
		}
		
	    return nameText;

	}


	@Override
	protected String buildTextRepresentation() {
		
		String textRepresentation = this.buildNameText(false);
	        
	    return textRepresentation;

	}
	
	
	
	public boolean sameAs(LogicElement name, boolean ignoreNegation) {
		return this.buildNameText(ignoreNegation).equals(((LiteralName) name).buildNameText(ignoreNegation));
	}

	
	
	public boolean sameAs(List<String> name, boolean ignoreNegation) {
		return this.sameAs(new LiteralName(name), ignoreNegation);
	}

	

	
	/**
	 * @return the nameParts
	 */
	public List<String> getNameParts() {
		return NameParts;
	}

	/**
	 * @param nameParts the nameParts to set
	 */
	public void setNameParts(List<String> nameParts) {
		NameParts = nameParts;
	}

	/**
	 * @param nameParts the nameParts to set
	 */
	public void setNameParts(String nameParts) {
		NameParts = new ArrayList<String>(Arrays.asList(nameParts));
	}

}
