package exception;

public class IncorrectInputException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncorrectInputException(String errorMessage) {
		super(errorMessage);
	}

}
