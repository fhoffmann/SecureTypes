package net.fhoffmann.securetypes.types;

import net.fhoffmann.securetypes.exceptions.SecureTypeException;

public class SecureInteger extends SecureSerializable {

	public SecureInteger(int input) throws SecureTypeException {
		super(input);
	}

	public int getInt() throws SecureTypeException {
		return (Integer) this.getSerializable();
	}
}
