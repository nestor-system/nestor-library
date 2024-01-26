package cy.ac.ouc.cognition.nestor.lib.reasoning.prudens.js;

import java.util.ArrayList;

import cy.ac.ouc.cognition.nestor.lib.reasoning.ReasoningAgent;
import cy.ac.ouc.cognition.nestor.lib.reasoning.prudens.IPrudensReasoning;
import cy.ac.ouc.cognition.nestor.lib.reasoning.prudens.PrudensException;
import cy.ac.ouc.cognition.nestor.lib.reasoning.prudens.js.client.PrudensJSClient;
import cy.ac.ouc.cognition.nestor.lib.reasoning.prudens.js.client.PrudensJSRequest;
import cy.ac.ouc.cognition.nestor.lib.reasoning.prudens.js.data.PrudensJSArgument;
import cy.ac.ouc.cognition.nestor.lib.reasoning.prudens.js.data.PrudensJSInference;

import static cy.ac.ouc.cognition.nestor.lib.trace.Trace.errln;


public class PrudensJS extends ReasoningAgent implements IPrudensReasoning {
	
	private Version					PrudensVersion = Version.PRUDENSJS;

	// JSON Fields
	ArrayList <PrudensJSInference>	Inferences;



	/**
	 * @return the inferences
	 */
	public ArrayList<PrudensJSInference> getInferences() {
		return Inferences;
	}


	
    public void infer(String logicalAnnotationText, String translationPolicyString) throws PrudensException {

		try {
			
        	// Build Request Body using translation policy (for knowledgebase) and
        	// logical annotation text as context
			PrudensJSRequest prudensJSRequest = new PrudensJSRequest(translationPolicyString, logicalAnnotationText);
			
			PrudensJSClient prudensJSClient = new PrudensJSClient(prudensJSRequest.toJson());
			
			prudensJSClient.send();
			
        	// Convert received JSON Response into internal PrudensJS object
        	
        	this.Inferences = prudensJSClient.extractInferencesFromResponse();
        	this.InferenceData = prudensJSClient.getResponseBody();

		}
		
		catch (PrudensException pe) {
			errln(pe.getMessage());
			throw pe;
		}

		catch (Exception e) {
			errln("PrudensJS Error inferring facts from Translation Policy: " + e.getMessage());
			throw new PrudensException("PrudensJS Error inferring facts from Translation Policy: " + e.getMessage());
		}

    }

	
    public boolean inferencesListIsEmpty() {

    	if (Inferences != null && !Inferences.isEmpty())
    		return false;

    	else
    		return true;

    }

	
    public int numberOfInferences() {
    	
    	if (Inferences == null)
    		return 0;
    	
    	return Inferences.size();
    	
    }
    
	public Object getInference(int expressionIndex) {
		
		return Inferences.get(expressionIndex);
	}

	public Version getAgentVersion() {
		return PrudensVersion;
	}
	
	public String getNameForItem(int expressionIndex) {
		
		return Inferences.get(expressionIndex).getName();
	}

	public String getPredicateNameForItem(int expressionIndex) {
		
		return Inferences.get(expressionIndex).getName();
	}

	public String getExpressionTextForItem(int expressionIndex) {
		
		String expressionText = getPredicateNameForItem(expressionIndex) + "(";

		int i = 1;
		for (PrudensJSArgument argument : Inferences.get(expressionIndex).getArgs()) {
			if (i > 1)
				expressionText += ", ";
			expressionText += argument.getValue();
			i++;
		}
		expressionText += ");";
		
		return expressionText;
	}

	public ArrayList<String> getInstantiatedArgumentsForItem(int expressionIndex) {
		
		ArrayList<String> arguments = new ArrayList<String>();

		for (PrudensJSArgument argument : Inferences.get(expressionIndex).getArgs())
			arguments.add(argument.getValue());
		
		return arguments;
	}

}