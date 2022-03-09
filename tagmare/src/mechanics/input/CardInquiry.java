package mechanics.input;

public interface CardInquiry {

	/** Returns the same object every time. */
	CardSelection selection();
	
	/** Returns the same text every time. */
	String displayText();
	
}
