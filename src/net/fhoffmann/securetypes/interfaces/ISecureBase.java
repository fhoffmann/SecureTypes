package net.fhoffmann.securetypes.interfaces;

import net.fhoffmann.securetypes.exceptions.SecureTypeException;

/**
 * Basic Interface to use. A class that implements this, must encrypt the given
 * data and only store the decrypted version.
 * The destroy method must overwrite the data with garbage. It can re-encrypt
 * the data, but doesn't have to.
 * 
 * @author Florian Hoffmann
 * 
 */
public interface ISecureBase {

	/**
	 * Initialize the object, if no input data was given to the constructor.
	 * This initializes the cipher objects, generates the keys and stores the
	 * encrypted data.
	 * @param input
	 * Byte to encrypt.
	 * @throws SecureTypeException
	 * If something went wrong, here you get your feedback
	 */
	public void init(byte[] input) throws SecureTypeException;

	/**
	 * Returns the encrypted data.
	 * @return
	 * Encrypted Byte-Array
	 */
	public byte[] getSecuredBytes();

	/**
	 * This Method decrypt the encrypted data and returns the decrypted version.
	 * @return
	 * A new byte array with the decrypted data
	 * @throws SecureTypeException
	 * If something went wrong, here you get your feedback
	 */
	public byte[] getBytes() throws SecureTypeException;

	/**
	 * Changes the data of the current object and saves it encrypted.
	 * The implementation can, but not have to, encrypt it. Either with
	 * a new key, or with the old.
	 * @param input
	 * The new byte-array data
	 * @throws SecureTypeException
	 * If something went wrong, here you get your feedback
	 */
	public void setBytes(byte[] input) throws SecureTypeException;
	
	/**
	 * Gives you the last encrypting time in ms.
	 * @return
	 * A long with the encrypting time in ms.
	 */
	public long getLastEncryptTime();

	/**
	 * Gives you the last decrypting time in ms.
	 * @return
	 * A long with the decrypting time in ms.
	 */
	public long getLastDecryptTime();

	/**
	 * Gives you the last keygen time in ms.
	 * @return
	 * A long with the keygen time in ms.
	 */
	public long getLastKeygenTime();

	/**
	 * This method destroys the data in the ram.
	 * It have to oberwrite the data with garbage.
	 * It can, but not have to, encrypt the data again,
	 * so that valid encrypted secure data is available.
	 * It also has to change the cipher/key.
	 * @throws SecureTypeException
	 * If something went wrong, here you get your feedback
	 */
	public void destroy() throws SecureTypeException;
}
