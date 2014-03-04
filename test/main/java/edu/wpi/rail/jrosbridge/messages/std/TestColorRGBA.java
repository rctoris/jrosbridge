package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestColorRGBA {

	private ColorRGBA empty, c1, c2, c3, c4;

	@Before
	public void setUp() {
		empty = new ColorRGBA();
		c1 = new ColorRGBA(0.25f);
		c2 = new ColorRGBA(0.25f, 0.5f);
		c3 = new ColorRGBA(0.25f, 0.5f, 0.75f);
		c4 = new ColorRGBA(0.25f, 0.5f, 0.75f, 0.25f);
	}

	@Test
	public void testConstructor() {
		assertEquals(0.0f, empty.getR());
		assertEquals(0.0f, empty.getG());
		assertEquals(0.0f, empty.getB());
		assertEquals(1.0f, empty.getA());

		assertEquals("{\"r\":0.0,\"g\":0.0,\"b\":0.0,\"a\":1.0}",
				empty.toString());

		assertEquals(4, empty.toJsonObject().size());
		assertEquals(0.0, empty.toJsonObject().getJsonNumber(ColorRGBA.FIELD_R)
				.doubleValue());
		assertEquals(0.0, empty.toJsonObject().getJsonNumber(ColorRGBA.FIELD_G)
				.doubleValue());
		assertEquals(0.0, empty.toJsonObject().getJsonNumber(ColorRGBA.FIELD_B)
				.doubleValue());
		assertEquals(1.0, empty.toJsonObject().getJsonNumber(ColorRGBA.FIELD_A)
				.doubleValue());

		assertEquals(ColorRGBA.TYPE, empty.getMessageType());

		assertEquals(empty.getR(), empty.toColor().getRed() / 255.0, 0);
		assertEquals(empty.getG(), empty.toColor().getGreen() / 255.0, 0);
		assertEquals(empty.getB(), empty.toColor().getBlue() / 255.0, 0);
		assertEquals(empty.getA(), empty.toColor().getAlpha() / 255.0, 0);
	}

	@Test
	public void testFloatConstructor() {
		assertEquals(0.25f, c1.getR());
		assertEquals(0.0f, c1.getG());
		assertEquals(0.0f, c1.getB());
		assertEquals(1.0f, c1.getA());

		assertEquals("{\"r\":0.25,\"g\":0.0,\"b\":0.0,\"a\":1.0}",
				c1.toString());

		assertEquals(4, c1.toJsonObject().size());
		assertEquals(0.25, c1.toJsonObject().getJsonNumber(ColorRGBA.FIELD_R)
				.doubleValue());
		assertEquals(0.0, c1.toJsonObject().getJsonNumber(ColorRGBA.FIELD_G)
				.doubleValue());
		assertEquals(0.0, c1.toJsonObject().getJsonNumber(ColorRGBA.FIELD_B)
				.doubleValue());
		assertEquals(1.0, c1.toJsonObject().getJsonNumber(ColorRGBA.FIELD_A)
				.doubleValue());

		assertEquals(ColorRGBA.TYPE, empty.getMessageType());

		assertEquals(c1.getR(), c1.toColor().getRed() / 255.0, 0.001);
		assertEquals(c1.getG(), c1.toColor().getGreen() / 255.0, 0);
		assertEquals(c1.getB(), c1.toColor().getBlue() / 255.0, 0);
		assertEquals(c1.getA(), c1.toColor().getAlpha() / 255.0, 0);
	}

	@Test
	public void testFloatAndFloatConstructor() {
		assertEquals(0.25f, c2.getR());
		assertEquals(0.5f, c2.getG());
		assertEquals(0.0f, c2.getB());
		assertEquals(1.0f, c2.getA());

		assertEquals("{\"r\":0.25,\"g\":0.5,\"b\":0.0,\"a\":1.0}",
				c2.toString());

		assertEquals(4, c2.toJsonObject().size());
		assertEquals(0.25, c2.toJsonObject().getJsonNumber(ColorRGBA.FIELD_R)
				.doubleValue());
		assertEquals(0.5, c2.toJsonObject().getJsonNumber(ColorRGBA.FIELD_G)
				.doubleValue());
		assertEquals(0.0, c2.toJsonObject().getJsonNumber(ColorRGBA.FIELD_B)
				.doubleValue());
		assertEquals(1.0, c2.toJsonObject().getJsonNumber(ColorRGBA.FIELD_A)
				.doubleValue());

		assertEquals(ColorRGBA.TYPE, empty.getMessageType());

		assertEquals(c2.getR(), c2.toColor().getRed() / 255.0, 0.001);
		assertEquals(c2.getG(), c2.toColor().getGreen() / 255.0, 0.01);
		assertEquals(c2.getB(), c2.toColor().getBlue() / 255.0, 0);
		assertEquals(c2.getA(), c2.toColor().getAlpha() / 255.0, 0);
	}

	@Test
	public void testFloatFloatAndFloatConstructor() {
		assertEquals(0.25f, c3.getR());
		assertEquals(0.5f, c3.getG());
		assertEquals(0.75f, c3.getB());
		assertEquals(1.0f, c3.getA());

		assertEquals("{\"r\":0.25,\"g\":0.5,\"b\":0.75,\"a\":1.0}",
				c3.toString());

		assertEquals(4, c3.toJsonObject().size());
		assertEquals(0.25, c3.toJsonObject().getJsonNumber(ColorRGBA.FIELD_R)
				.doubleValue());
		assertEquals(0.5, c3.toJsonObject().getJsonNumber(ColorRGBA.FIELD_G)
				.doubleValue());
		assertEquals(0.75, c3.toJsonObject().getJsonNumber(ColorRGBA.FIELD_B)
				.doubleValue());
		assertEquals(1.0, c3.toJsonObject().getJsonNumber(ColorRGBA.FIELD_A)
				.doubleValue());

		assertEquals(ColorRGBA.TYPE, empty.getMessageType());

		assertEquals(c3.getR(), c3.toColor().getRed() / 255.0, 0.001);
		assertEquals(c3.getG(), c3.toColor().getGreen() / 255.0, 0.01);
		assertEquals(c3.getB(), c3.toColor().getBlue() / 255.0, 0.001);
		assertEquals(c3.getA(), c3.toColor().getAlpha() / 255.0, 0);
	}

	@Test
	public void testFloatFloatFloatAndFloatConstructor() {
		assertEquals(0.25f, c4.getR());
		assertEquals(0.5f, c4.getG());
		assertEquals(0.75f, c4.getB());
		assertEquals(0.25f, c4.getA());

		assertEquals("{\"r\":0.25,\"g\":0.5,\"b\":0.75,\"a\":0.25}",
				c4.toString());

		assertEquals(4, c4.toJsonObject().size());
		assertEquals(0.25, c4.toJsonObject().getJsonNumber(ColorRGBA.FIELD_R)
				.doubleValue());
		assertEquals(0.5, c4.toJsonObject().getJsonNumber(ColorRGBA.FIELD_G)
				.doubleValue());
		assertEquals(0.75, c4.toJsonObject().getJsonNumber(ColorRGBA.FIELD_B)
				.doubleValue());
		assertEquals(0.25, c4.toJsonObject().getJsonNumber(ColorRGBA.FIELD_A)
				.doubleValue());

		assertEquals(ColorRGBA.TYPE, empty.getMessageType());

		assertEquals(c4.getR(), c4.toColor().getRed() / 255.0, 0.001);
		assertEquals(c4.getG(), c4.toColor().getGreen() / 255.0, 0.01);
		assertEquals(c4.getB(), c4.toColor().getBlue() / 255.0, 0.001);
		assertEquals(c4.getA(), c4.toColor().getAlpha() / 255.0, 0.001);
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(c1.toString().hashCode(), c1.hashCode());
		assertEquals(c2.toString().hashCode(), c2.hashCode());
		assertEquals(c3.toString().hashCode(), c3.hashCode());
		assertEquals(c4.toString().hashCode(), c4.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(c1));
		assertFalse(c1.equals(empty));
		assertFalse(empty.equals(c2));
		assertFalse(c2.equals(empty));
		assertFalse(empty.equals(c3));
		assertFalse(c3.equals(empty));
		assertFalse(empty.equals(c4));
		assertFalse(c4.equals(empty));

		assertFalse(c1.equals(c2));
		assertFalse(c1.equals(c3));
		assertFalse(c1.equals(c4));
		assertFalse(c2.equals(c1));
		assertFalse(c2.equals(c3));
		assertFalse(c2.equals(c4));
		assertFalse(c3.equals(c1));
		assertFalse(c3.equals(c2));
		assertFalse(c3.equals(c4));
		assertFalse(c4.equals(c1));
		assertFalse(c4.equals(c2));
		assertFalse(c4.equals(c3));

		assertTrue(empty.equals(empty));
		assertTrue(c1.equals(c1));
		assertTrue(c2.equals(c2));
		assertTrue(c3.equals(c3));
		assertTrue(c4.equals(c4));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		ColorRGBA clone = c4.clone();
		assertEquals(c4.toString(), clone.toString());
		assertEquals(c4.toJsonObject(), clone.toJsonObject());
		assertEquals(c4.getMessageType(), clone.getMessageType());
		assertEquals(c4.getR(), clone.getR());
		assertEquals(c4.getG(), clone.getG());
		assertEquals(c4.getB(), clone.getB());
		assertEquals(c4.toColor(), clone.toColor());
		assertNotSame(c4, clone);
		assertNotSame(c4.toString(), clone.toString());
		assertNotSame(c4.toJsonObject(), clone.toJsonObject());
	}
}
