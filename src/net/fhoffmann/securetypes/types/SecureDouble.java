package net.fhoffmann.securetypes.types;

import net.fhoffmann.securetypes.exceptions.SecureTypeException;

public class SecureDouble extends SecureSerializable {

	public SecureDouble(double input) throws SecureTypeException {
		super(input);
	}

	public double getDouble() throws SecureTypeException {
		return (Double) this.getSerializable();
	}
}
