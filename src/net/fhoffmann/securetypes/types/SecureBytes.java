package net.fhoffmann.securetypes.types;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import net.fhoffmann.securetypes.exceptions.SecureTypeException;
import net.fhoffmann.securetypes.interfaces.ISecureBase;

/**
 * Basic implementation ISecureBase for bytes.
 * @author Florian Hoffmann
 *
 */
public class SecureBytes implements ISecureBase {

	/**
	 * We use AES for symmetric encrypting 
	 */
	public static final String AES_NAME = "AES";

	/**
	 * Since the jre is only shipped with AES 128 support,
	 * we can't get a higher encryption. But it's enough.
	 */
	public static final int AES_KEYGEN_SIZE = 128;

	/**
	 * Here we store the encrypted bytes
	 */
	private byte[] securedBytes = null;
	
	/**
	 * De/encrypt cipher objects
	 */
	private Cipher cipher_encrypt = null, cipher_decrypt = null;
	
	/**
	 * Longs to save the last encrypt/decrypt timings
	 */
	long last_encrypt_start = -1, last_encrypt_end = -1;
	long last_decrypt_start = -1, last_decrypt_end = -1;
	long last_keygen_start = -1, last_keygen_end = -1;

	/**
	 * Creates a new SecureBytes object, but doesn'st encrypt
	 * anythink. You have to call the init(byte[]) method with
	 * the desired payload.
	 */
	public SecureBytes() {
	}

	/**
	 * Creates a new SecureBytes object and encrypts the given data.
	 * @param input
	 * The byte-array that will be encrypted.
	 * @throws SecureTypeException
	 * If something went wrong, here you get your feedback
	 */
	public SecureBytes(byte[] input) throws SecureTypeException {
		this.init(input);
	}

	@Override
	public void init(byte[] input) throws SecureTypeException {

		try {
			KeyGenerator kg = KeyGenerator.getInstance(AES_NAME);
			kg.init(AES_KEYGEN_SIZE);
			this.last_keygen_start = System.currentTimeMillis();
			SecretKey sk = kg.generateKey();
			this.last_keygen_end = System.currentTimeMillis();
			cipher_encrypt = Cipher.getInstance(AES_NAME);
			cipher_encrypt.init(Cipher.ENCRYPT_MODE, sk);

			cipher_decrypt = Cipher.getInstance(AES_NAME);
			cipher_decrypt.init(Cipher.DECRYPT_MODE, sk);

			this.last_encrypt_start = System.currentTimeMillis();
			this.securedBytes = cipher_encrypt.doFinal(input);
			this.last_encrypt_end = System.currentTimeMillis();

		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			throw new SecureTypeException("Something went wrong: "
					+ e.toString());
		}

	}

	@Override
	public byte[] getSecuredBytes() {
		return this.securedBytes;
	}

	@Override
	public byte[] getBytes() throws SecureTypeException {
		if (this.cipher_decrypt == null) {
			return null;
		}
		try {

			this.last_decrypt_start = System.currentTimeMillis();
			byte[] ret = this.cipher_decrypt.doFinal(this.securedBytes);
			this.last_decrypt_end = System.currentTimeMillis();

			return ret;
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new SecureTypeException("Something went wrong: "
					+ e.toString());
		}
	}

	@Override
	public long getLastEncryptTime() {
		return this.last_encrypt_end - this.last_encrypt_start;
	}

	@Override
	public long getLastDecryptTime() {
		return this.last_decrypt_end - this.last_decrypt_start;
	}

	@Override
	public long getLastKeygenTime() {
		return this.last_keygen_end - this.last_keygen_start;
	}

	@Override
	public void destroy() throws SecureTypeException {
		if (this.securedBytes != null) {
			Random rand = new Random(System.currentTimeMillis());
			byte[] destroyer = new byte[this.securedBytes.length];
			rand.nextBytes(destroyer);
			this.init(destroyer);
		}
	}

}
