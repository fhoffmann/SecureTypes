package net.fhoffmann.securetypes.factory;

import java.io.Serializable;

import net.fhoffmann.securetypes.exceptions.SecureTypeException;
import net.fhoffmann.securetypes.exceptions.SecureTypeNotSupported;
import net.fhoffmann.securetypes.interfaces.ISecureBase;
import net.fhoffmann.securetypes.types.SecureBytes;
import net.fhoffmann.securetypes.types.SecureSerializable;
import net.fhoffmann.securetypes.types.SecureString;

/**
 * A simple Factory, that helps you creating the SecureObjects.
 * @author Florian Hoffmann
 *
 */
public class SecureFactory {

	private static final SecureFactory sf = new SecureFactory();

	public static SecureFactory getInstance() {
		return sf;
	}

	private SecureFactory() {
	}

	/**
	 * Creates the object by checking the type of the input data.
	 * If no supported type will be found, you receive a SecureTypeNotSupported Exception
	 * @param input
	 * The data that will be encrypted
	 * @return
	 * A new ISecureBase object, filled with the proper SecureClass.
	 * @throws SecureTypeException
	 * If something went wrong, here you get your feedback
	 * @throws SecureTypeNotSupported
	 * If your input is not supported, you receive this exception.
	 */
	public ISecureBase genSecure(Object input) throws SecureTypeException, SecureTypeNotSupported {

		if (input instanceof byte[]) {
			return new SecureBytes((byte[]) input);
		} else if (input instanceof String) {
			return new SecureString((String) input);
		} else if (input instanceof Serializable) {
			return new SecureSerializable((Serializable) input);
		} else {
			throw new SecureTypeNotSupported(
					input.getClass().getName()
							+ " is not supported. Please use a byte[] or java.io.Serializable.");
		}
	}
}
