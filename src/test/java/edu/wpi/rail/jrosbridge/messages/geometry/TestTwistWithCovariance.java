package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestTwistWithCovariance {

	private TwistWithCovariance empty, p1, p2;

	@Before
	public void setUp() {
		empty = new TwistWithCovariance();
		p1 = new TwistWithCovariance(new Twist(new Vector3(0.5, 1.5, 3.0),
				new Vector3(-0.5, -1.5, -3.0)));
		p2 = new TwistWithCovariance(new Twist(new Vector3(0.5, 1.5, 3.0),
				new Vector3(-0.5, -1.5, -3.0)), new double[] { 1, 2, 3, 4, 5,
				6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
				23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 });
	}

	@Test
	public void testConstructor() {
		assertEquals(new Twist(), empty.getTwist());
		assertEquals(TwistWithCovariance.COVARIANCE_SIZE,
				empty.getCovariance().length);
		for (double d : empty.getCovariance()) {
			assertEquals(0.0, d, 0);
		}
		assertEquals(TwistWithCovariance.COVARIANCE_ROWS,
				empty.getCovarianceMatrix().length);
		for (double[] row : empty.getCovarianceMatrix()) {
			assertEquals(TwistWithCovariance.COVARIANCE_COLUMNS, row.length);
			for (double d : row) {
				assertEquals(0.0, d, 0);
			}
		}

		assertEquals("{\"twist\":{\"linear\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},"
				+ "\"angular\":{\"x\":0.0,\"y\":0.0,\"z\":0.0}},"
				+ "\"covariance\":[0.0,0.0,0.0,0.0,0.0,0.0"
				+ ",0.0,0.0,0.0,0.0,0.0,0.0" + ",0.0,0.0,0.0,0.0,0.0,0.0"
				+ ",0.0,0.0,0.0,0.0,0.0,0.0" + ",0.0,0.0,0.0,0.0,0.0,0.0"
				+ ",0.0,0.0,0.0,0.0,0.0,0.0]}", empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Twist(),
				Twist.fromJsonObject(empty.toJsonObject().getJsonObject(
						TwistWithCovariance.FIELD_TWIST)));
		assertEquals(TwistWithCovariance.COVARIANCE_SIZE, empty.toJsonObject()
				.getJsonArray(TwistWithCovariance.FIELD_COVARIANCE).size());
		for (int i = 0; i < TwistWithCovariance.COVARIANCE_SIZE; i++) {
			assertEquals(
					0.0,
					empty.toJsonObject()
							.getJsonArray(TwistWithCovariance.FIELD_COVARIANCE)
							.getJsonNumber(i).doubleValue(), 0);
		}

		assertEquals(TwistWithCovariance.TYPE, empty.getMessageType());
	}

	@Test
	public void testTwistConstructor() {
		assertEquals(new Twist(new Vector3(0.5, 1.5, 3.0), new Vector3(-0.5,
				-1.5, -3.0)), p1.getTwist());
		assertEquals(TwistWithCovariance.COVARIANCE_SIZE,
				p1.getCovariance().length);
		for (double d : p1.getCovariance()) {
			assertEquals(0.0, d, 0);
		}
		assertEquals(TwistWithCovariance.COVARIANCE_ROWS,
				p1.getCovarianceMatrix().length);
		for (double[] row : p1.getCovarianceMatrix()) {
			assertEquals(TwistWithCovariance.COVARIANCE_COLUMNS, row.length);
			for (double d : row) {
				assertEquals(0.0, d, 0);
			}
		}

		assertEquals("{\"twist\":{\"linear\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
				+ "\"angular\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0}},"
				+ "\"covariance\":[0.0,0.0,0.0,0.0,0.0,0.0"
				+ ",0.0,0.0,0.0,0.0,0.0,0.0" + ",0.0,0.0,0.0,0.0,0.0,0.0"
				+ ",0.0,0.0,0.0,0.0,0.0,0.0" + ",0.0,0.0,0.0,0.0,0.0,0.0"
				+ ",0.0,0.0,0.0,0.0,0.0,0.0]}", p1.toString());

		assertEquals(2, p1.toJsonObject().size());
		assertEquals(new Twist(new Vector3(0.5, 1.5, 3.0), new Vector3(-0.5,
				-1.5, -3.0)), Twist.fromJsonObject(p1.toJsonObject()
				.getJsonObject(TwistWithCovariance.FIELD_TWIST)));
		assertEquals(TwistWithCovariance.COVARIANCE_SIZE, p1.toJsonObject()
				.getJsonArray(TwistWithCovariance.FIELD_COVARIANCE).size());
		for (int i = 0; i < TwistWithCovariance.COVARIANCE_SIZE; i++) {
			assertEquals(
					0.0,
					p1.toJsonObject()
							.getJsonArray(TwistWithCovariance.FIELD_COVARIANCE)
							.getJsonNumber(i).doubleValue(), 0);
		}

		assertEquals(TwistWithCovariance.TYPE, p1.getMessageType());
	}

	@Test
	public void testTwistAndDoubleArrayConstructor() {
		assertEquals(new Twist(new Vector3(0.5, 1.5, 3.0), new Vector3(-0.5,
				-1.5, -3.0)), p2.getTwist());
		assertEquals(TwistWithCovariance.COVARIANCE_SIZE,
				p2.getCovariance().length);
		for (int i = 0; i < TwistWithCovariance.COVARIANCE_SIZE; i++) {
			assertEquals((double) i + 1, p2.getCovariance()[i], 0);
		}
		assertEquals(TwistWithCovariance.COVARIANCE_ROWS,
				p2.getCovarianceMatrix().length);
		for (int i = 0; i < TwistWithCovariance.COVARIANCE_ROWS; i++) {
			assertEquals(TwistWithCovariance.COVARIANCE_COLUMNS,
					p2.getCovarianceMatrix()[i].length);
			for (int j = 0; j < TwistWithCovariance.COVARIANCE_COLUMNS; j++) {
				assertEquals((double) i
						* TwistWithCovariance.COVARIANCE_COLUMNS + j + 1,
						p2.getCovarianceMatrix()[i][j], 0);
			}
		}

		assertEquals("{\"twist\":{\"linear\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
				+ "\"angular\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0}},"
				+ "\"covariance\":[1.0,2.0,3.0,4.0,5.0,6.0"
				+ ",7.0,8.0,9.0,10.0,11.0,12.0"
				+ ",13.0,14.0,15.0,16.0,17.0,18.0"
				+ ",19.0,20.0,21.0,22.0,23.0,24.0"
				+ ",25.0,26.0,27.0,28.0,29.0,30.0"
				+ ",31.0,32.0,33.0,34.0,35.0,36.0]}", p2.toString());

		assertEquals(2, p2.toJsonObject().size());
		assertEquals(new Twist(new Vector3(0.5, 1.5, 3.0), new Vector3(-0.5,
				-1.5, -3.0)), Twist.fromJsonObject(p2.toJsonObject()
				.getJsonObject(TwistWithCovariance.FIELD_TWIST)));
		assertEquals(TwistWithCovariance.COVARIANCE_SIZE, p2.toJsonObject()
				.getJsonArray(TwistWithCovariance.FIELD_COVARIANCE).size());
		for (int i = 0; i < TwistWithCovariance.COVARIANCE_SIZE; i++) {
			assertEquals(
					(double) i + 1,
					p2.toJsonObject()
							.getJsonArray(TwistWithCovariance.FIELD_COVARIANCE)
							.getJsonNumber(i).doubleValue(), 0);
		}

		assertEquals(TwistWithCovariance.TYPE, p2.getMessageType());
	}

	@Test
	public void testSetMessageType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(p1.toString().hashCode(), p1.hashCode());
		assertEquals(p2.toString().hashCode(), p2.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(p1));
		assertFalse(p1.equals(empty));
		assertFalse(empty.equals(p2));
		assertFalse(p2.equals(empty));

		assertFalse(p1.equals(p2));
		assertFalse(p2.equals(p1));

		assertTrue(empty.equals(empty));
		assertTrue(p1.equals(p1));
		assertTrue(p2.equals(p2));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testClone() {
		TwistWithCovariance clone = p2.clone();
		assertEquals(p2.toString(), clone.toString());
		assertEquals(p2.toJsonObject(), clone.toJsonObject());
		assertEquals(p2.getMessageType(), clone.getMessageType());
		assertEquals(p2.getTwist(), clone.getTwist());
		assertTrue(Arrays.equals(p2.getCovariance(), clone.getCovariance()));
		assertTrue(Arrays.deepEquals(p2.getCovarianceMatrix(),
				clone.getCovarianceMatrix()));
		assertNotSame(p2, clone);
		assertNotSame(p2.toString(), clone.toString());
		assertNotSame(p2.toJsonObject(), clone.toJsonObject());
		assertNotSame(p2.getCovariance(), clone.getCovariance());
		assertNotSame(p2.getCovarianceMatrix(), clone.getCovarianceMatrix());
	}

	@Test
	public void testFromJsonString() {
		TwistWithCovariance p = TwistWithCovariance.fromJsonString(p2
				.toString());
		assertEquals(p2.toString(), p.toString());
		assertEquals(p2.toJsonObject(), p.toJsonObject());
		assertEquals(p2.getMessageType(), p.getMessageType());
		assertEquals(p2.getTwist(), p.getTwist());
		assertTrue(Arrays.equals(p2.getCovariance(), p.getCovariance()));
		assertTrue(Arrays.deepEquals(p2.getCovarianceMatrix(),
				p.getCovarianceMatrix()));
		assertNotSame(p2, p);
		assertNotSame(p2.toString(), p.toString());
		assertNotSame(p2.toJsonObject(), p.toJsonObject());
		assertNotSame(p2.getTwist(), p.getTwist());
		assertNotSame(p2.getCovariance(), p.getCovariance());
		assertNotSame(p2.getCovarianceMatrix(), p.getCovarianceMatrix());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(p2.toString());
		TwistWithCovariance p = TwistWithCovariance.fromMessage(m);
		assertEquals(p2.toString(), p.toString());
		assertEquals(p2.toJsonObject(), p.toJsonObject());
		assertEquals(p2.getMessageType(), p.getMessageType());
		assertEquals(p2.getTwist(), p.getTwist());
		assertTrue(Arrays.equals(p2.getCovariance(), p.getCovariance()));
		assertTrue(Arrays.deepEquals(p2.getCovarianceMatrix(),
				p.getCovarianceMatrix()));
		assertNotSame(p2, p);
		assertNotSame(p2.toString(), p.toString());
		assertNotSame(p2.toJsonObject(), p.toJsonObject());
		assertNotSame(p2.getTwist(), p.getTwist());
		assertNotSame(p2.getCovariance(), p.getCovariance());
		assertNotSame(p2.getCovarianceMatrix(), p.getCovarianceMatrix());
	}

	@Test
	public void testFromJsonObject() {
		JsonArrayBuilder array = Json.createArrayBuilder();
		for (Double d : p2.getCovariance()) {
			array.add(d);
		}
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(TwistWithCovariance.FIELD_TWIST,
						p2.getTwist().toJsonObject())
				.add(TwistWithCovariance.FIELD_COVARIANCE, array.build())
				.build();
		TwistWithCovariance p = TwistWithCovariance.fromJsonObject(jsonObject);
		assertEquals(p2.toString(), p.toString());
		assertEquals(p2.toJsonObject(), p.toJsonObject());
		assertEquals(p2.getMessageType(), p.getMessageType());
		assertEquals(p2.getTwist(), p.getTwist());
		assertTrue(Arrays.equals(p2.getCovariance(), p.getCovariance()));
		assertTrue(Arrays.deepEquals(p2.getCovarianceMatrix(),
				p.getCovarianceMatrix()));
		assertNotSame(p2, p);
		assertNotSame(p2.toString(), p.toString());
		assertNotSame(p2.toJsonObject(), p.toJsonObject());
		assertNotSame(p2.getTwist(), p.getTwist());
		assertNotSame(p2.getCovariance(), p.getCovariance());
		assertNotSame(p2.getCovarianceMatrix(), p.getCovarianceMatrix());
	}

	@Test
	public void testFromJsonObjectNoTwist() {
		JsonArrayBuilder array = Json.createArrayBuilder();
		for (Double d : p2.getCovariance()) {
			array.add(d);
		}
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(TwistWithCovariance.FIELD_COVARIANCE, array.build())
				.build();
		TwistWithCovariance p = TwistWithCovariance.fromJsonObject(jsonObject);
		assertEquals(p2.getMessageType(), p.getMessageType());
		assertEquals(new Twist(), p.getTwist());
		assertTrue(Arrays.equals(p2.getCovariance(), p.getCovariance()));
		assertTrue(Arrays.deepEquals(p2.getCovarianceMatrix(),
				p.getCovarianceMatrix()));
		assertNotSame(p2, p);
		assertNotSame(p2.toString(), p.toString());
		assertNotSame(p2.toJsonObject(), p.toJsonObject());
		assertNotSame(p2.getCovariance(), p.getCovariance());
		assertNotSame(p2.getCovarianceMatrix(), p.getCovarianceMatrix());
	}

	@Test
	public void testFromJsonObjectNoCovariance() {
		JsonObject jsonObject = Json
				.createObjectBuilder()
				.add(TwistWithCovariance.FIELD_TWIST,
						p2.getTwist().toJsonObject()).build();
		TwistWithCovariance p = TwistWithCovariance.fromJsonObject(jsonObject);
		assertEquals(p2.getMessageType(), p.getMessageType());
		assertEquals(p2.getTwist(), p.getTwist());
		assertEquals(TwistWithCovariance.COVARIANCE_SIZE,
				p.getCovariance().length);
		for (double d : p.getCovariance()) {
			assertEquals(0.0, d, 0);
		}
		assertEquals(TwistWithCovariance.COVARIANCE_ROWS,
				p.getCovarianceMatrix().length);
		for (double[] row : p.getCovarianceMatrix()) {
			assertEquals(TwistWithCovariance.COVARIANCE_COLUMNS, row.length);
			for (double d : row) {
				assertEquals(0.0, d, 0);
			}
		}
		assertNotSame(p2, p);
		assertNotSame(p2.toString(), p.toString());
		assertNotSame(p2.toJsonObject(), p.toJsonObject());
		assertNotSame(p2.getTwist(), p.getTwist());
	}
}
