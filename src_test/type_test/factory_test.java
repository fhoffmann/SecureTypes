package type_test;

import static org.junit.Assert.*;
import net.fhoffmann.securetypes.exceptions.SecureTypeException;
import net.fhoffmann.securetypes.exceptions.SecureTypeNotSupported;
import net.fhoffmann.securetypes.factory.SecureFactory;
import net.fhoffmann.securetypes.interfaces.ISecureBase;
import net.fhoffmann.securetypes.types.SecureBytes;
import net.fhoffmann.securetypes.types.SecureSerializable;
import net.fhoffmann.securetypes.types.SecureString;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class factory_test {

	static SecureFactory sf = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sf = SecureFactory.getInstance();
	}

	@Test
	@Before
	public void testGetInstance() {
		assertTrue(sf != null && sf instanceof SecureFactory);
	}

	@Test
	@After
	public void testGenSecure() {
		try {
			ISecureBase isb1 = sf.genSecure(1);
			assertTrue(isb1 instanceof SecureSerializable
					&& (Integer) ((SecureSerializable) isb1).getSerializable() == 1);

			ISecureBase isb2 = sf.genSecure(1f);
			assertTrue(isb2 instanceof SecureSerializable
					&& (Float) ((SecureSerializable) isb2).getSerializable() == 1f);

			ISecureBase isb3 = sf.genSecure(1d);
			assertTrue(isb3 instanceof SecureSerializable
					&& (Double) ((SecureSerializable) isb3).getSerializable() == 1d);

			ISecureBase isb5 = sf.genSecure(true);
			assertTrue(isb5 instanceof SecureSerializable
					&& (Boolean) ((SecureSerializable) isb5).getSerializable() == true);

			ISecureBase isb4 = sf.genSecure("Hallo Welt");
			assertTrue(isb4 instanceof SecureString
					&& ((SecureString) isb4).getString().equals("Hallo Welt"));

			ISecureBase isb6 = sf.genSecure(new byte[] { 101 });
			assertTrue(isb6 instanceof SecureBytes
					&& ((SecureBytes) isb6).getBytes()[0] == 101);

			ISecureBase isb7 = sf
					.genSecure(new TestClassSerializable(5, "blub"));
			assertTrue(isb7 instanceof SecureSerializable
					&& ((TestClassSerializable) ((SecureSerializable) isb7)
							.getSerializable()).getNumber() == 5);

		} catch (SecureTypeException | SecureTypeNotSupported e) {
			e.printStackTrace();
		}
	}

}
