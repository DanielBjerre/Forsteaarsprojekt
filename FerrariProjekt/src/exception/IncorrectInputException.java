package exception;

public class IncorrectInputException extends RuntimeException {
	public IncorrectInputException(String errorMessage) {
		super(errorMessage);
	}

}
