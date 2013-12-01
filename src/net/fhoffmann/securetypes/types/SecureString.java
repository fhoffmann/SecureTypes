package net.fhoffmann.securetypes.types;

import net.fhoffmann.securetypes.exceptions.SecureTypeException;

/**
 * Basic implementation of a SecureString.
 * This is not explicit needed, because a String is a
 * Serializable object and we can use SecureSerializable for it.
 * But this is a little bit faster.
 * @author Florian Hoffmann
 *
 */
public class SecureString extends SecureBytes {

	/**
	 * Creates a new SecureString object with the given input
	 * data, that will be encrypted.
	 * @param input
	 * The String object that will be encrypted
	 * @throws SecureTypeException
	 * If something went wrong, here you get your feedback
	 */
	public SecureString(String input) throws SecureTypeException {
		super(input.getBytes());
	}
	
	/**
	 * Creates a new SecureString object, but doesn'st encrypt
	 * anythink. You have to call the init(byte[]) method with
	 * the desired payload.
	 */
	public SecureString() {
		
	}
	
	/**
	 * Calls the super init with the byte data from the String
	 * object and encrypt it.
	 * @param input
	 * The String object that will be encrypted.
	 * @throws SecureTypeException
	 * If something went wrong, here you get your feedback
	 */
	public void init(String input) throws SecureTypeException {
		this.init(input.getBytes());
	}

	/**
	 * Decrypt the data and returns the String
	 * @return
	 * A new String with the decrypted data
	 * @throws SecureTypeException
	 * If something went wrong, here you get your feedback
	 */
	public String getString() throws SecureTypeException {
		return new String(this.getBytes());
	}
}
