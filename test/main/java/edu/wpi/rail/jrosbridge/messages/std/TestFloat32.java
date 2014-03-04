package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestFloat32 {

	private Float32 empty, f1;

	@Before
	public void setUp() {
		empty = new Float32();
		f1 = new Float32(1.5f);
	}

	@Test
	public void testConstructor() {
		assertEquals(0f, empty.getData());

		assertEquals("{\"data\":0.0}", empty.toString());

		assertEquals(1, empty.toJsonObject().size());
		assertEquals(0.0, empty.toJsonObject()
				.getJsonNumber(Float32.FIELD_DATA).doubleValue());

		assertEquals(Float32.TYPE, empty.getMessageType());
	}

	@Test
	public void testFloatConstructor() {
		assertEquals(1.5f, f1.getData());

		assertEquals("{\"data\":1.5}", f1.toString());

		assertEquals(1, f1.toJsonObject().size());
		assertEquals(1.5, f1.toJsonObject().getJsonNumber(Float32.FIELD_DATA)
				.doubleValue());

		assertEquals(Float32.TYPE, f1.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(f1.toString().hashCode(), f1.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(f1));
		assertFalse(f1.equals(empty));

		assertTrue(empty.equals(empty));
		assertTrue(f1.equals(f1));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		Float32 clone = f1.clone();
		assertEquals(f1.toString(), clone.toString());
		assertEquals(f1.toJsonObject(), clone.toJsonObject());
		assertEquals(f1.getMessageType(), clone.getMessageType());
		assertEquals(f1.getData(), clone.getData());
		assertNotSame(f1, clone);
		assertNotSame(f1.toString(), clone.toString());
		assertNotSame(f1.toJsonObject(), clone.toJsonObject());
	}
}
