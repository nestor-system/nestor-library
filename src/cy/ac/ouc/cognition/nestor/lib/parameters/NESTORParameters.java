package cy.ac.ouc.cognition.nestor.lib.parameters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cy.ac.ouc.cognition.nestor.lib.base.NESTORBase;

public abstract class NESTORParameters extends NESTORBase {

    // Private constructor ensures no external instantiation
    protected NESTORParameters() {
    	
    }
	


    // Updated parameter values from a JSON string passed as an argument
	public void updateValues(String jsonString) {

		try {

			System.out.println("Update " + this.getClass().getSimpleName() + " from file " + this.getParametersFile());

	 		// Populate the parameters object with latest values from file
			updateFromJSONFile();
			this.printJSON();
		}

		catch (FileNotFoundException fnfe) {

			// Make sure we have valid values in parameters. Reset to default values
			this.initializeDefaults();

			System.err.println(this.getParametersFile() + " file not found: " + fnfe.getMessage());
			System.out.println("Default settings values are used!");
        }
		
		catch (IOException ioe) {

			// Make sure we have valid values in parameters. Reset to default values
			this.initializeDefaults();

	    	System.err.println("IO Error reading file " + this.getParametersFile() + ": " + ioe.getMessage());
			System.out.println("Default settings values are used!");
		}
 	
    	catch (IllegalAccessException iae2) {

			// Make sure we have valid values in parameters. Reset to default values
			this.initializeDefaults();

	    	System.err.println("Illegal access while updating values from " + this.getParametersFile() + ": " + iae2.getMessage());
	    	System.out.println("Default settings values are used!");
        }

		catch (Exception e2) {

			// Make sure we have valid values in parameters. Reset to default values
			this.initializeDefaults();

	    	System.err.println("Error while updating values " + this.getParametersFile() + ": " + e2.getMessage());
	    	System.out.println("Default settings values are used!");
		}

		
		// If the jsonString has data, try to update parameters object
		if (jsonString != null && !jsonString.equals("")) {
			
			try {

				System.out.println("Update " + this.getClass().getSimpleName() + " from string [" + jsonString + "]");
				   
				// Now try to update parameters object with the values contained in
				// the JSON string passed to this method
				updateJSONObject(jsonString);
				this.printJSON();
			}
	 	
	    	catch (IllegalAccessException iae1) {

				// We have valid values in parameters from file
		    	System.err.println("Illegal access while updating values: " + iae1.getMessage());
		    	System.out.println("Default settings values from " + this.getParametersFile() + " are used!");
	        }

			catch (Exception e1) {

				// We have valid values in parameters from file
		    	System.err.println("JSON error while updating values: " + e1.getMessage());
		    	System.out.println("Default settings values from " + this.getParametersFile() + " are used!");
			}

		}

		
		System.out.println(this.getClass().getSimpleName() + " Updated!");
		this.printValues();

	}

    
	public void updateValues() {
		
		updateValues(null);

	}


	
    // Read values from a JSON string stored in a file
	private void updateFromJSONFile() throws Exception {

		// Reset to default values before reading new ones
		this.initializeDefaults();

		// Read contents of file into a string
		String fileContent = new String(Files.readAllBytes(Paths.get(this.getParametersFile())));

		// Update parameters object from the JSON string read from the file
		updateJSONObject(fileContent);

	}


		
	// Update parameters object from a JSON string
	private void updateJSONObject(String jsonString) throws Exception {

		Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        JsonObject existingObject = JsonParser.parseString(gson.toJson(this)).getAsJsonObject();

        for (String key : jsonObject.keySet())
            existingObject.add(key, jsonObject.get(key));


        NESTORParameters updatedObject = gson.fromJson(existingObject, this.getClass());

        // Copy updated fields back to the original object
        // This is necessary because Gson creates a new instance when deserializing
        for (java.lang.reflect.Field field : this.getClass().getDeclaredFields()) {

        	field.setAccessible(true);
            field.set(this, field.get(updatedObject));	        	
        }

	}

  
    
	public abstract String getParametersFile();
    
    
	// This method sets default values for parameters. 
	// It should be overridden by each subclass to set its specific defaults.
	protected abstract void initializeDefaults();

	
	public abstract void printValues();
   
    
	public void printJSON() {

		System.out.println("");
		System.out.println(this.getClass().getSimpleName() + " JSON:");

		// Create Gson instance with pretty printing enabled
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Convert the object to JSON and pretty print it
        String json = gson.toJson(this);
        System.out.println(json);

	}

}
