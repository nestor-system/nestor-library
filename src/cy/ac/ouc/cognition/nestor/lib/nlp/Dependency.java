package cy.ac.ouc.cognition.nestor.lib.nlp;

public class Dependency extends NLRelation {
	
	private	transient NLToken	Governor;

	private NLToken	Dependent;

	
		
	public Dependency(String dependencyName) {
		
		super(dependencyName);

	}

	
	
	public Dependency(String dependencyName, NLToken governor, NLToken dependent) {
		
		this(dependencyName.replace(':', '_'));
		
		Governor = governor;
		Dependent = dependent;
		
		Complete = true;
	}
	


	/**
	 * @return the governor
	 */
	public NLToken getGovernor() {
		return Governor;
	}

	
	
	/**
	 * @param governor the governor to set
	 */
	public void setGovernor(NLToken governor) {
		Governor = governor;
		Complete = false;
	}
	
	
	
	/**
	 * @return the dependent
	 */
	public NLToken getDependent() {
		return Dependent;
	}
	
	
	
	/**
	 * @param dependent the dependent to set
	 */
	public void setDependent(NLToken dependent) {
		Dependent = dependent;
	}

}
