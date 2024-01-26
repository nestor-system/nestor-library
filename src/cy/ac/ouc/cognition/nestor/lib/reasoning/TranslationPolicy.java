package cy.ac.ouc.cognition.nestor.lib.reasoning;

import cy.ac.ouc.cognition.nestor.lib.base.NESTORThing;

public abstract class TranslationPolicy extends NESTORThing {
    
    protected String TranslationPolicyString;


    protected TranslationPolicy(String translationPolicyString) {
        
    	super();

    	if (translationPolicyString != null)
            TranslationPolicyString = translationPolicyString;
        
        else
            TranslationPolicyString = "";
        
    }

}
