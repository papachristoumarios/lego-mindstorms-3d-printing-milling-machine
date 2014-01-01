package exceptions;

public class UnrecognizedGCodeException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public UnrecognizedGCodeException() {};
	
	public void printStackTrace() {
		System.out.println("Unrecognized G Code");
	}

}
