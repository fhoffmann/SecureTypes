package net.fhoffmann.securetypes.exceptions;

public class SecureTypeNotSupported extends Exception {

	private static final long serialVersionUID = 499966125023598950L;

	public SecureTypeNotSupported() {
	}

	public SecureTypeNotSupported(String arg0) {
		super(arg0);
	}

	public SecureTypeNotSupported(Throwable arg0) {
		super(arg0);
	}

	public SecureTypeNotSupported(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SecureTypeNotSupported(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
