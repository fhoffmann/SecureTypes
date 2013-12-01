package net.fhoffmann.securetypes.exceptions;

public class SecureTypeException extends Exception {

	private static final long serialVersionUID = -4938850156732879785L;

	public SecureTypeException() {
	}

	public SecureTypeException(String arg0) {
		super(arg0);
	}

	public SecureTypeException(Throwable arg0) {
		super(arg0);
	}

	public SecureTypeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SecureTypeException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
