package net.fhoffmann.securetypes.types;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import net.fhoffmann.securetypes.exceptions.SecureTypeException;

/**
 * This class can be used to encrypt everything that implements
 * the java.io.Serializable Interface. So you can store primitve types
 * and your own Class-Objects.
 * @author Florian Hoffmann
 *
 */
public class SecureSerializable extends SecureBytes {

	/**
	 * Creates a new SecureSerializable object with the given input
	 * data, that will be encrypted.
	 * @param input
	 * A java.io.Serializable that will be encrypted
	 * @throws SecureTypeException
	 * If something went wrong, here you get your feedback
	 */
	public SecureSerializable(Serializable input) throws SecureTypeException {
		super();
		
		this.init(input);
	}
	
	/**
	 * Creates a new SecureSerializable object, but doesn'st encrypt
	 * anythink. You have to call the init(byte[]) method with
	 * the desired payload.
	 */
	public SecureSerializable() {
		
	}
	
	/**
	 * Calls the super init with the byte data from the Serializable
	 * object and encrypt it.
	 * @param input
	 * The String object that will be encrypted.
	 * @throws SecureTypeException
	 * If something went wrong, here you get your feedback
	 */
	public void init(Serializable input) throws SecureTypeException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			
			oos.writeObject(input);
			oos.flush();
			oos.close();
			this.init(baos.toByteArray());
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Decrypt the data and returns the Serializable-Object
	 * @return
	 * A new Serializable-Object, from the decrypted data 
	 * @throws SecureTypeException
	 * If something went wrong, here you get your feedback
	 */
	public Serializable getSerializable() throws SecureTypeException {
		Serializable ret = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(this.getBytes());
			ObjectInputStream in = new ObjectInputStream(bais);
			ret = (Serializable) in.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			throw new SecureTypeException(e.toString());
		}
		
		return ret;
	}
	
	/**
	 * Sets the Serializable data object to a new one.
	 * @param input
	 * The new data.
	 * @throws SecureTypeException
	 * If something went wrong, here you get your feedback
	 */
	public void setSerializable(Serializable input) throws SecureTypeException {
		this.init(input);
	}
}
