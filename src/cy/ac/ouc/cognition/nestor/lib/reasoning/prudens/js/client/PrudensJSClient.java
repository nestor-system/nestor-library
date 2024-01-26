package cy.ac.ouc.cognition.nestor.lib.reasoning.prudens.js.client;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import static cy.ac.ouc.cognition.nestor.lib.trace.Trace.outln;
import cy.ac.ouc.cognition.nestor.lib.reasoning.ReasoningObject;
import cy.ac.ouc.cognition.nestor.lib.reasoning.prudens.PrudensException;
import cy.ac.ouc.cognition.nestor.lib.reasoning.prudens.js.data.PrudensJSInference;

public class PrudensJSClient extends ReasoningObject {

	private String 	RequestBody;
	private String 	ResponseBody;
	private int		ResponseCode;

	
	
	public PrudensJSClient(String messageBody) {
		
		if (messageBody == null)
			this.RequestBody = "";
		else
			this.RequestBody = messageBody;
		
	}

	
	public String send() throws PrudensException {
		
		try {
		
        	outln("PrudensJS Web Service Request body: [" + RequestBody + "]");

        	// Create an HTTP Request Object - CID : Arguments should be parameterized
	    	HttpRequest request = HttpRequest.newBuilder()
	    			  .uri(new URI(getSysParameters().getPrudensJS_ServiceURI()))
	    			  .headers("Content-Type", getSysParameters().getPrudensJS_ServiceContentType())
	    			  .headers("Accept", getSysParameters().getPrudensJS_ServiceAccept())
	    			  .headers("User-Agent", getSysParameters().getPrudensJS_ServiceUserAgent())
	    			  .POST(HttpRequest.BodyPublishers.ofString(RequestBody))
	    			  .build();
	    	
        	// Create an HTTP Client Object
        	HttpClient client = HttpClient.newHttpClient();
        	
        	// Send HTTP Request and receive response, synchronously
        	HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        	
        	// Return the string of the response body
        	ResponseCode = response.statusCode();
        	ResponseBody = response.body();

        	outln("PrudensJS Web Service Response status code: [" + Integer.valueOf(ResponseCode).toString() + "]");
        	if (getSysParameters().isPrudensJS_AlwaysPrintResponseBody())
        		outln("PrudensJS Web Service Response body: [" + ResponseBody.replaceAll("\\s+", " ") + "]");
        	
        	if (response.statusCode() != 200)
    			throw new PrudensException("PrudensJS Web Service responed with an error code: " + response.statusCode());
        	
        	return ResponseBody;       	

		}
		
		catch (Exception e) {
			throw new PrudensException("PrudensJS Error sending request to PrudensJS service: " + e.getMessage());
		}
    	

	}
	
	
	
	public ArrayList <PrudensJSInference> extractInferencesFromResponse() throws PrudensException {
		
		try {

			Gson gson = new Gson();

			// Create a JsonObject from the response body
	    	JsonObject jsonObject = gson.fromJson(ResponseBody, JsonObject.class);

	    	// Create the correct type object for converting the list of inferences
	    	Type listOfMyClassObject = new TypeToken<ArrayList<PrudensJSInference>>(){}.getType();

	    	// Get the list of inferences from the response
	    	// Convert received list of inferences to PrudensJSInference list
	    	return gson.fromJson(jsonObject.getAsJsonArray("inferences"), listOfMyClassObject);	

		}
		
		catch (Exception e) {
			throw new PrudensException("PrudensJS Error extracting inferences from response body: " + e.getMessage());
		}
	}

	
	/**
	 * @return the requestBody
	 */
	public String getRequestBody() {
		return RequestBody;
	}

	/**
	 * @param requestBody the requestBody to set
	 */
	public void setRequestBody(String requestBody) {
		this.RequestBody = requestBody;
	}


	/**
	 * @return the responseBody
	 */
	public String getResponseBody() {
		return ResponseBody;
	}


	/**
	 * @param responseBody the responseBody to set
	 */
	public void setResponseBody(String responseBody) {
		this.ResponseBody = responseBody;
	}


	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return ResponseCode;
	}


	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.ResponseCode = responseCode;
	}

}
