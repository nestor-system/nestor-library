package cy.ac.ouc.cognition.nestor.lib.logic;

import cy.ac.ouc.cognition.nestor.lib.base.NESTORThing;

public abstract class LogicElement extends NESTORThing {

	protected String	TextRepresentation;


	public LogicElement() {
		super();
		TextRepresentation = new String("");
	}

	
	
	abstract protected String buildTextRepresentation();
	
	
	public boolean sameAs(LogicElement logicElement, boolean ignoreNegation) {
		return false;
	
	}

	

	
	/**
	 * @return the textRepresentation
	 */
	public String getTextRepresentation() {

		TextRepresentation = buildTextRepresentation();
 
		return TextRepresentation;
	}

}
