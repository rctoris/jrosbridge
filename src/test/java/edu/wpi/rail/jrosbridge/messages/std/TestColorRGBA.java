package edu.wpi.rail.jrosbridge.messages.std;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestColorRGBA {

	private ColorRGBA empty, c1, c2;

	@Before
	public void setUp() {
		empty = new ColorRGBA();
		c1 = new ColorRGBA(0.25f, 0.5f, 0.75f);
		c2 = new ColorRGBA(0.25f, 0.5f, 0.75f, 0.25f);
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
	public void testFloatFloatAndFloatConstructor() {
		assertEquals(0.25f, c1.getR());
		assertEquals(0.5f, c1.getG());
		assertEquals(0.75f, c1.getB());
		assertEquals(1.0f, c1.getA());

		assertEquals("{\"r\":0.25,\"g\":0.5,\"b\":0.75,\"a\":1.0}",
				c1.toString());

		assertEquals(4, c1.toJsonObject().size());
		assertEquals(0.25, c1.toJsonObject().getJsonNumber(ColorRGBA.FIELD_R)
				.doubleValue());
		assertEquals(0.5, c1.toJsonObject().getJsonNumber(ColorRGBA.FIELD_G)
				.doubleValue());
		assertEquals(0.75, c1.toJsonObject().getJsonNumber(ColorRGBA.FIELD_B)
				.doubleValue());
		assertEquals(1.0, c1.toJsonObject().getJsonNumber(ColorRGBA.FIELD_A)
				.doubleValue());

		assertEquals(ColorRGBA.TYPE, empty.getMessageType());

		assertEquals(c1.getR(), c1.toColor().getRed() / 255.0, 0.001);
		assertEquals(c1.getG(), c1.toColor().getGreen() / 255.0, 0.01);
		assertEquals(c1.getB(), c1.toColor().getBlue() / 255.0, 0.001);
		assertEquals(c1.getA(), c1.toColor().getAlpha() / 255.0, 0);
	}

	@Test
	public void testFloatFloatFloatAndFloatConstructor() {
		assertEquals(0.25f, c2.getR());
		assertEquals(0.5f, c2.getG());
		assertEquals(0.75f, c2.getB());
		assertEquals(0.25f, c2.getA());

		assertEquals("{\"r\":0.25,\"g\":0.5,\"b\":0.75,\"a\":0.25}",
				c2.toString());

		assertEquals(4, c2.toJsonObject().size());
		assertEquals(0.25, c2.toJsonObject().getJsonNumber(ColorRGBA.FIELD_R)
				.doubleValue());
		assertEquals(0.5, c2.toJsonObject().getJsonNumber(ColorRGBA.FIELD_G)
				.doubleValue());
		assertEquals(0.75, c2.toJsonObject().getJsonNumber(ColorRGBA.FIELD_B)
				.doubleValue());
		assertEquals(0.25, c2.toJsonObject().getJsonNumber(ColorRGBA.FIELD_A)
				.doubleValue());

		assertEquals(ColorRGBA.TYPE, empty.getMessageType());

		assertEquals(c2.getR(), c2.toColor().getRed() / 255.0, 0.001);
		assertEquals(c2.getG(), c2.toColor().getGreen() / 255.0, 0.01);
		assertEquals(c2.getB(), c2.toColor().getBlue() / 255.0, 0.001);
		assertEquals(c2.getA(), c2.toColor().getAlpha() / 255.0, 0.001);
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
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(c1));
		assertFalse(c1.equals(empty));
		assertFalse(empty.equals(c2));
		assertFalse(c2.equals(empty));

		assertFalse(c1.equals(c2));
		assertFalse(c2.equals(c1));

		assertTrue(empty.equals(empty));
		assertTrue(c1.equals(c1));
		assertTrue(c2.equals(c2));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		ColorRGBA clone = c2.clone();
		assertEquals(c2.toString(), clone.toString());
		assertEquals(c2.toJsonObject(), clone.toJsonObject());
		assertEquals(c2.getMessageType(), clone.getMessageType());
		assertEquals(c2.getR(), clone.getR());
		assertEquals(c2.getG(), clone.getG());
		assertEquals(c2.getB(), clone.getB());
		assertEquals(c2.toColor(), clone.toColor());
		assertNotSame(c2, clone);
		assertNotSame(c2.toString(), clone.toString());
		assertNotSame(c2.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromColor() {
		Color color = new Color(10, 20, 30, 40);
		ColorRGBA c = ColorRGBA.fromColor(color);
		assertEquals((float) 10 / 255.0f, c.getR());
		assertEquals((float) 20 / 255.0f, c.getG());
		assertEquals((float) 30 / 255.0f, c.getB());
		assertEquals((float) 40 / 255.0f, c.getA());
	}

	@Test
	public void testFromJsonString() {
		ColorRGBA p = ColorRGBA.fromJsonString(c2.toString());
		assertEquals(c2.toString(), p.toString());
		assertEquals(c2.toJsonObject(), p.toJsonObject());
		assertEquals(c2.getMessageType(), p.getMessageType());
		assertEquals(c2.getR(), p.getR());
		assertEquals(c2.getG(), p.getG());
		assertEquals(c2.getB(), p.getB());
		assertNotSame(c2, p);
		assertNotSame(c2.toString(), p.toString());
		assertNotSame(c2.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(c2.toString());
		ColorRGBA p = ColorRGBA.fromMessage(m);
		assertEquals(c2.toString(), p.toString());
		assertEquals(c2.toJsonObject(), p.toJsonObject());
		assertEquals(c2.getMessageType(), p.getMessageType());
		assertEquals(c2.getR(), p.getR());
		assertEquals(c2.getG(), p.getG());
		assertEquals(c2.getB(), p.getB());
		assertEquals(c2.getA(), p.getA());
		assertNotSame(c2, p);
		assertNotSame(c2.toString(), p.toString());
		assertNotSame(c2.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(ColorRGBA.FIELD_R, c2.getR())
				.add(ColorRGBA.FIELD_G, c2.getG())
				.add(ColorRGBA.FIELD_B, c2.getB())
				.add(ColorRGBA.FIELD_A, c2.getA()).build();
		ColorRGBA p = ColorRGBA.fromJsonObject(jsonObject);
		assertEquals(c2.toString(), p.toString());
		assertEquals(c2.toJsonObject(), p.toJsonObject());
		assertEquals(c2.getMessageType(), p.getMessageType());
		assertEquals(c2.getR(), p.getR());
		assertEquals(c2.getG(), p.getG());
		assertEquals(c2.getB(), p.getB());
		assertEquals(c2.getA(), p.getA());
		assertNotSame(c2, p);
		assertNotSame(c2.toString(), p.toString());
		assertNotSame(c2.toJsonObject(), p.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoR() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(ColorRGBA.FIELD_G, c2.getG())
				.add(ColorRGBA.FIELD_B, c2.getB())
				.add(ColorRGBA.FIELD_A, c2.getA()).build();
		ColorRGBA p = ColorRGBA.fromJsonObject(jsonObject);
		assertEquals(0.0f, p.getR());
		assertEquals(c2.getG(), p.getG());
		assertEquals(c2.getB(), p.getB());
		assertEquals(c2.getA(), p.getA());
	}

	@Test
	public void testFromJsonObjectNoG() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(ColorRGBA.FIELD_R, c2.getR())
				.add(ColorRGBA.FIELD_B, c2.getB())
				.add(ColorRGBA.FIELD_A, c2.getA()).build();
		ColorRGBA p = ColorRGBA.fromJsonObject(jsonObject);
		assertEquals(c2.getR(), p.getR());
		assertEquals(0.0f, p.getG());
		assertEquals(c2.getB(), p.getB());
		assertEquals(c2.getA(), p.getA());
	}

	@Test
	public void testFromJsonObjectNoB() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(ColorRGBA.FIELD_R, c2.getR())
				.add(ColorRGBA.FIELD_G, c2.getG())
				.add(ColorRGBA.FIELD_A, c2.getA()).build();
		ColorRGBA p = ColorRGBA.fromJsonObject(jsonObject);
		assertEquals(c2.getR(), p.getR());
		assertEquals(c2.getG(), p.getG());
		assertEquals(0.0f, p.getB());
		assertEquals(c2.getA(), p.getA());
	}

	@Test
	public void testFromJsonObjectNoA() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(ColorRGBA.FIELD_R, c2.getR())
				.add(ColorRGBA.FIELD_G, c2.getG())
				.add(ColorRGBA.FIELD_B, c2.getB()).build();
		ColorRGBA p = ColorRGBA.fromJsonObject(jsonObject);
		assertEquals(c2.getR(), p.getR());
		assertEquals(c2.getG(), p.getG());
		assertEquals(c2.getB(), p.getB());
		assertEquals(0.0f, p.getA());
	}
}
