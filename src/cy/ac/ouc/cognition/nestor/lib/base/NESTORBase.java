package cy.ac.ouc.cognition.nestor.lib.base;

import cy.ac.ouc.cognition.nestor.lib.parameters.SysParameters;
import cy.ac.ouc.cognition.nestor.lib.parameters.TranslationParameters;

public abstract class NESTORBase {
	
	public static TranslationParameters getTranslationParameters(String jsonString) {
		
		return TranslationParameters.getInstance(jsonString);
	
	}

	
	
	public static TranslationParameters getTranslationParameters() {
		
		return TranslationParameters.getInstance(null);
	
	}

	
	
	public static SysParameters getSysParameters(String jsonString) {
		
		return SysParameters.getInstance(jsonString);
	
	}
	

	public static SysParameters getSysParameters() {
		
		return SysParameters.getInstance(null);
	
	}
	

}
