package cy.ac.ouc.cognition.nestor.lib.reasoning;

import cy.ac.ouc.cognition.nestor.lib.base.INESTORInterface;
import cy.ac.ouc.cognition.nestor.lib.nlp.NLSentence;

public interface ITranslationPolicy extends INESTORInterface {

	public String inferTranslationPolicyVersion(IReasoning prudensAgent);
	public void inferTranslation(NLSentence nlSentence, IReasoning prudensAgent) throws TranslationPolicyException;
}
