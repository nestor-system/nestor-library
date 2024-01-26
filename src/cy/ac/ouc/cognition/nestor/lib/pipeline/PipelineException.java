package cy.ac.ouc.cognition.nestor.lib.pipeline;

import cy.ac.ouc.cognition.nestor.lib.base.NESTORException;

public class PipelineException extends NESTORException {
	
	int PipelineErrorCode;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2071232490803382072L;

	
	public PipelineException(String message) {
		this(-1, message);
	}

	public PipelineException(int errorCode, String message) {
		super("Pipeline error " + errorCode + " --> " + message);
		PipelineErrorCode = errorCode;
	}

	
	/**
	 * @return the pipelineErrorCode
	 */
	public int getPipelineErrorCode() {
		return PipelineErrorCode;
	}


}
