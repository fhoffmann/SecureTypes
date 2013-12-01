package net.fhoffmann.securetypes.types;

import net.fhoffmann.securetypes.exceptions.SecureTypeException;

public class SecureBoolean extends SecureSerializable {

	public SecureBoolean(boolean input) throws SecureTypeException {
		super(input);
	}

	public boolean getBoolean() throws SecureTypeException {
		return (Boolean) this.getSerializable();
	}
}
