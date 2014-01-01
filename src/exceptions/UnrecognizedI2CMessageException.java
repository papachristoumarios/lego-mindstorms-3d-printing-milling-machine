package exceptions;

public class UnrecognizedI2CMessageException extends Exception{
	
	private static final long serialVersionUID = 2L;

	public UnrecognizedI2CMessageException() {};
	
	public void printStackTrace() {
		System.err.println("Unrecognized byte");
	}

}
