package cy.ac.ouc.cognition.nestor.lib.logic;

import java.util.List;

public class LEArgument extends LogicElement {

	protected LiteralName	Name;


	public LEArgument(String  name) {
		this(new LiteralName(name));
	}
	
	
	public LEArgument(LiteralName name) {
		super();
		Name = name;
	}



	@Override
	protected String buildTextRepresentation() {
		
 		String textRepresentation = Name.getTextRepresentation();
	        
	    return textRepresentation;

	}

	
	
	public boolean sameAs(LogicElement argument, boolean ignoreNegation) {
		return this.Name.sameAs(((LEArgument) argument).Name, ignoreNegation);
	}


	
	public boolean sameAs(List<String> argumentName, boolean ignoreNegation) {
		return this.Name.sameAs(argumentName, ignoreNegation);
	}

	
	
	
	/**
	 * @return the name
	 */
	public LiteralName getName() {
		return Name;
	}

	
	/**
	 * @param name the name to set
	 */
	public void setName(LiteralName name) {
		Name = name;
	}

}
