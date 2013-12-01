package type_test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import net.fhoffmann.securetypes.exceptions.SecureTypeException;
import net.fhoffmann.securetypes.types.SecureBytes;
import net.fhoffmann.securetypes.types.SecureSerializable;
import net.fhoffmann.securetypes.types.SecureString;

import org.junit.Test;

public class types_test {

	@Test
	public void test_string() {

		System.out.println("::test_string::");
		String test_string = "Hallo Welt!";
		try {
			long start, end, end2, time_encrypt, time_decrypt;

			start = System.currentTimeMillis();
			SecureString secString = new SecureString(test_string);
			end = System.currentTimeMillis();
			
			String recString = secString.getString();
			end2 = System.currentTimeMillis();

			System.out.println("original: " + test_string);
			System.out.println("received: " + recString);

			time_encrypt = end - start;
			time_decrypt = end2 - end;

			System.out.println("Encrypt:\t" + time_encrypt);
			System.out.println("Decrypt:\t" + time_decrypt);
			
			System.out.println("Type encrypt time:\t" + secString.getLastEncryptTime());
			System.out.println("Type decrypt time:\t" + secString.getLastDecryptTime());
			System.out.println("Type keygen time:\t" + secString.getLastKeygenTime());
			
			assertTrue(test_string.equals(recString));

		} catch (SecureTypeException e) {
			fail("Exception received: " + e.toString());
		}
	}
	@Test
	public void test_string_destroy() {

		System.out.println("::test_string_destroy::");
		String test_string = "Hallo Welt!";
		try {
			SecureString secString = new SecureString(test_string);
			secString.destroy();
			String recString = secString.getString();

			System.out.println("original: " + test_string);
			System.out.println("received: " + recString);
			assertTrue(!test_string.equals(recString));

		} catch (SecureTypeException e) {
			fail("Exception received: " + e.toString());
		}
	}

	@Test
	public void test_base() {

		System.out.println("::test_base::");
		byte[] input = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8};
		try {
			long start, end, end2, time_encrypt, time_decrypt;
			
			start = System.currentTimeMillis();
			SecureBytes b = new SecureBytes(input);
			end = System.currentTimeMillis();

			byte[] out = b.getBytes();
			end2 = System.currentTimeMillis();
			
			time_encrypt = end - start;
			time_decrypt = end2 - end;

			System.out.println("Encrypt:\t" + time_encrypt);
			System.out.println("Decrypt:\t" + time_decrypt);

			System.out.println("Type encrypt time:\t" + b.getLastEncryptTime());
			System.out.println("Type decrypt time:\t" + b.getLastDecryptTime());
			System.out.println("Type keygen time:\t" + b.getLastKeygenTime());
			
			assertTrue(input.length == out.length);
			if(input.length == out.length) {
				for(int i=0; i<input.length; i++) {
					assertTrue(input[i] == out[i]);
				}
			}
		} catch (SecureTypeException e) {
			fail("Exception received: " + e.toString());
		}
	}
	@Test
	public void test_base2() {

		System.out.println("::test_base2::");
		byte[] input = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8};
		try {
			long start, end, end2, time_encrypt, time_decrypt;
			
			start = System.currentTimeMillis();
			SecureBytes b = new SecureBytes(input);
			end = System.currentTimeMillis();

			byte[] out = b.getBytes();
			end2 = System.currentTimeMillis();
			
			time_encrypt = end - start;
			time_decrypt = end2 - end;

			System.out.println("Encrypt:\t" + time_encrypt);
			System.out.println("Decrypt:\t" + time_decrypt);

			System.out.println("Type encrypt time:\t" + b.getLastEncryptTime());
			System.out.println("Type decrypt time:\t" + b.getLastDecryptTime());
			System.out.println("Type keygen time:\t" + b.getLastKeygenTime());
			
			assertTrue(input.length == out.length);
			if(input.length == out.length) {
				for(int i=0; i<input.length; i++) {
					assertTrue(input[i] == out[i]);
				}
			}
		} catch (SecureTypeException e) {
			fail("Exception received: " + e.toString());
		}
	}
	
	
	
	@Test
	public void test_serializable() {
		TestClassSerializable input = new TestClassSerializable(5, "blub");

		System.out.println("::test_serializable::");
		try {
			long start, end, end2, time_encrypt, time_decrypt;

			start = System.currentTimeMillis();
			SecureSerializable secSer = new SecureSerializable(input);
			end = System.currentTimeMillis();
			
			TestClassSerializable output = (TestClassSerializable) secSer.getSerializable();
			end2 = System.currentTimeMillis();

			time_encrypt = end - start;
			time_decrypt = end2 - end;
			
//			System.out.println("Start:\t" + start);
//			System.out.println("End:\t" + end);
//			System.out.println("End2:\t" + end2);
			System.out.println("Encrypt:\t" + time_encrypt);
			System.out.println("Decrypt:\t" + time_decrypt);

			System.out.println("Type encrypt time:\t" + secSer.getLastEncryptTime());
			System.out.println("Type decrypt time:\t" + secSer.getLastDecryptTime());
			System.out.println("Type keygen time:\t" + secSer.getLastKeygenTime());
			
			assertTrue(input.getNumber() == output.getNumber());
			assertTrue(input.getString().equals(output.getString()));
		} catch (SecureTypeException e) {
			fail("Exception received: " + e.toString());
		}
	}
	
	@Test
	public void test_serializable2() {
		int input = 7;
		
		System.out.println("::test_serializable2::");
		try {
			long start, end, end2, time_encrypt, time_decrypt;
			
			start = System.currentTimeMillis();
			SecureSerializable secSer = new SecureSerializable(input);
			end = System.currentTimeMillis();
			
			int output = (Integer) secSer.getSerializable();
			end2 = System.currentTimeMillis();

			time_encrypt = end - start;
			time_decrypt = end2 - end;
			
//			System.out.println("Start:\t" + start);
//			System.out.println("End:\t" + end);
//			System.out.println("End2:\t" + end2);
			System.out.println("Encrypt:\t" + time_encrypt);
			System.out.println("Decrypt:\t" + time_decrypt);

			System.out.println("Type encrypt time:\t" + secSer.getLastEncryptTime());
			System.out.println("Type decrypt time:\t" + secSer.getLastDecryptTime());
			System.out.println("Type keygen time:\t" + secSer.getLastKeygenTime());
			
			assertTrue(input == output);
		} catch (SecureTypeException e) {
			fail("Exception received: " + e.toString());
		}
	}
}
