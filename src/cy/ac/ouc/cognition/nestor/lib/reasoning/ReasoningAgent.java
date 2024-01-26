package cy.ac.ouc.cognition.nestor.lib.reasoning;

import cy.ac.ouc.cognition.nestor.lib.base.NESTORThing;

public abstract class ReasoningAgent extends NESTORThing {

	protected String	InferenceData;

	
	
	/**
	 * @return the inferenceData
	 */
	public String getInferenceData() {
		return InferenceData;
	}

	
	
	/**
	 * @param inferenceData the inferenceData to set
	 */
	public void setInferenceData(String inferenceData) {
		InferenceData = inferenceData;
	}

}
