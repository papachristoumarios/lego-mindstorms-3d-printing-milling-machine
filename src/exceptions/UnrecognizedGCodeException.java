package exceptions;

public class UnrecognizedGCodeException extends Exception {
	public UnrecognizedGCodeException() {};
	
	
	public void printStackTrace() {
		System.out.println("Unrecognized G Code");
	}

}
