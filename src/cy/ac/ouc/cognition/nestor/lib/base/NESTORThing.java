package cy.ac.ouc.cognition.nestor.lib.base;

public abstract class NESTORThing extends NESTORBase {
	
	protected transient boolean	Complete;


	public NESTORThing() {

		Complete = false;

	}

	
	
	/**
	 * @return the complete
	 */
	public  boolean isComplete() {
		return Complete;
	}
	

	/**
	 * @@param complete the complete to set
	 */
	public void setComplete(boolean complete) {
		Complete = complete;
	}

}
