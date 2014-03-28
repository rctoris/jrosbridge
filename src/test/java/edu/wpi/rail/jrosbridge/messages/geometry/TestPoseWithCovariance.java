package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.rail.jrosbridge.messages.Message;

public class TestPoseWithCovariance {

	private PoseWithCovariance empty, p1, p2;

	@Before
	public void setUp() {
		empty = new PoseWithCovariance();
		p1 = new PoseWithCovariance(new Pose(new Point(0.5, 1.5, 3.0),
				new Quaternion(-0.5, -1.5, -3.0, -4.5)));
		p2 = new PoseWithCovariance(new Pose(new Point(0.5, 1.5, 3.0),
				new Quaternion(-0.5, -1.5, -3.0, -4.5)), new double[] { 1, 2,
				3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35,
				36 });
	}

	@Test
	public void testConstructor() {
		assertEquals(new Pose(), empty.getPose());
		assertEquals(PoseWithCovariance.COVARIANCE_SIZE,
				empty.getCovariance().length);
		for (double d : empty.getCovariance()) {
			assertEquals(0.0, d, 0);
		}
		assertEquals(PoseWithCovariance.COVARIANCE_ROWS,
				empty.getCovarianceMatrix().length);
		for (double[] row : empty.getCovarianceMatrix()) {
			assertEquals(PoseWithCovariance.COVARIANCE_COLUMNS, row.length);
			for (double d : row) {
				assertEquals(0.0, d, 0);
			}
		}

		assertEquals("{\"pose\":{\"position\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},"
				+ "\"orientation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0,\"w\":0.0}},"
				+ "\"covariance\":[0.0,0.0,0.0,0.0,0.0,0.0"
				+ ",0.0,0.0,0.0,0.0,0.0,0.0" + ",0.0,0.0,0.0,0.0,0.0,0.0"
				+ ",0.0,0.0,0.0,0.0,0.0,0.0" + ",0.0,0.0,0.0,0.0,0.0,0.0"
				+ ",0.0,0.0,0.0,0.0,0.0,0.0]}", empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(
				new Pose(),
				Pose.fromJsonObject(empty.toJsonObject().getJsonObject(
						PoseWithCovariance.FIELD_POSE)));
		assertEquals(PoseWithCovariance.COVARIANCE_SIZE, empty.toJsonObject()
				.getJsonArray(PoseWithCovariance.FIELD_COVARIANCE).size());
		for (int i = 0; i < PoseWithCovariance.COVARIANCE_SIZE; i++) {
			assertEquals(
					0.0,
					empty.toJsonObject()
							.getJsonArray(PoseWithCovariance.FIELD_COVARIANCE)
							.getJsonNumber(i).doubleValue(), 0);
		}

		assertEquals(PoseWithCovariance.TYPE, empty.getMessageType());
	}

	@Test
	public void testPoseConstructor() {
		assertEquals(new Pose(new Point(0.5, 1.5, 3.0), new Quaternion(-0.5,
				-1.5, -3.0, -4.5)), p1.getPose());
		assertEquals(PoseWithCovariance.COVARIANCE_SIZE,
				p1.getCovariance().length);
		for (double d : p1.getCovariance()) {
			assertEquals(0.0, d, 0);
		}
		assertEquals(PoseWithCovariance.COVARIANCE_ROWS,
				p1.getCovarianceMatrix().length);
		for (double[] row : p1.getCovarianceMatrix()) {
			assertEquals(PoseWithCovariance.COVARIANCE_COLUMNS, row.length);
			for (double d : row) {
				assertEquals(0.0, d, 0);
			}
		}

		assertEquals(
				"{\"pose\":{\"position\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
						+ "\"orientation\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0,\"w\":-4.5}},"
						+ "\"covariance\":[0.0,0.0,0.0,0.0,0.0,0.0"
						+ ",0.0,0.0,0.0,0.0,0.0,0.0"
						+ ",0.0,0.0,0.0,0.0,0.0,0.0"
						+ ",0.0,0.0,0.0,0.0,0.0,0.0"
						+ ",0.0,0.0,0.0,0.0,0.0,0.0"
						+ ",0.0,0.0,0.0,0.0,0.0,0.0]}", p1.toString());

		assertEquals(2, p1.toJsonObject().size());
		assertEquals(new Pose(new Point(0.5, 1.5, 3.0), new Quaternion(-0.5,
				-1.5, -3.0, -4.5)), Pose.fromJsonObject(p1.toJsonObject()
				.getJsonObject(PoseWithCovariance.FIELD_POSE)));
		assertEquals(PoseWithCovariance.COVARIANCE_SIZE, p1.toJsonObject()
				.getJsonArray(PoseWithCovariance.FIELD_COVARIANCE).size());
		for (int i = 0; i < PoseWithCovariance.COVARIANCE_SIZE; i++) {
			assertEquals(
					0.0,
					p1.toJsonObject()
							.getJsonArray(PoseWithCovariance.FIELD_COVARIANCE)
							.getJsonNumber(i).doubleValue(), 0);
		}

		assertEquals(PoseWithCovariance.TYPE, p1.getMessageType());
	}

	@Test
	public void testPoseAndDoubleArrayConstructor() {
		assertEquals(new Pose(new Point(0.5, 1.5, 3.0), new Quaternion(-0.5,
				-1.5, -3.0, -4.5)), p2.getPose());
		assertEquals(PoseWithCovariance.COVARIANCE_SIZE,
				p2.getCovariance().length);
		for (int i = 0; i < PoseWithCovariance.COVARIANCE_SIZE; i++) {
			assertEquals((double) i + 1, p2.getCovariance()[i], 0);
		}
		assertEquals(PoseWithCovariance.COVARIANCE_ROWS,
				p2.getCovarianceMatrix().length);
		for (int i = 0; i < PoseWithCovariance.COVARIANCE_ROWS; i++) {
			assertEquals(PoseWithCovariance.COVARIANCE_COLUMNS,
					p2.getCovarianceMatrix()[i].length);
			for (int j = 0; j < PoseWithCovariance.COVARIANCE_COLUMNS; j++) {
				assertEquals((double) i * PoseWithCovariance.COVARIANCE_COLUMNS
						+ j + 1, p2.getCovarianceMatrix()[i][j], 0);
			}
		}

		assertEquals(
				"{\"pose\":{\"position\":{\"x\":0.5,\"y\":1.5,\"z\":3.0},"
						+ "\"orientation\":{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0,\"w\":-4.5}},"
						+ "\"covariance\":[1.0,2.0,3.0,4.0,5.0,6.0"
						+ ",7.0,8.0,9.0,10.0,11.0,12.0"
						+ ",13.0,14.0,15.0,16.0,17.0,18.0"
						+ ",19.0,20.0,21.0,22.0,23.0,24.0"
						+ ",25.0,26.0,27.0,28.0,29.0,30.0"
						+ ",31.0,32.0,33.0,34.0,35.0,36.0]}", p2.toString());

		assertEquals(2, p2.toJsonObject().size());
		assertEquals(new Pose(new Point(0.5, 1.5, 3.0), new Quaternion(-0.5,
				-1.5, -3.0, -4.5)), Pose.fromJsonObject(p2.toJsonObject()
				.getJsonObject(PoseWithCovariance.FIELD_POSE)));
		assertEquals(PoseWithCovariance.COVARIANCE_SIZE, p2.toJsonObject()
				.getJsonArray(PoseWithCovariance.FIELD_COVARIANCE).size());
		for (int i = 0; i < PoseWithCovariance.COVARIANCE_SIZE; i++) {
			assertEquals(
					(double) i + 1,
					p2.toJsonObject()
							.getJsonArray(PoseWithCovariance.FIELD_COVARIANCE)
							.getJsonNumber(i).doubleValue(), 0);
		}

		assertEquals(PoseWithCovariance.TYPE, p2.getMessageType());
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
		PoseWithCovariance clone = p2.clone();
		assertEquals(p2.toString(), clone.toString());
		assertEquals(p2.toJsonObject(), clone.toJsonObject());
		assertEquals(p2.getMessageType(), clone.getMessageType());
		assertEquals(p2.getPose(), clone.getPose());
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
		PoseWithCovariance p = PoseWithCovariance.fromJsonString(p2.toString());
		assertEquals(p2.toString(), p.toString());
		assertEquals(p2.toJsonObject(), p.toJsonObject());
		assertEquals(p2.getMessageType(), p.getMessageType());
		assertEquals(p2.getPose(), p.getPose());
		assertTrue(Arrays.equals(p2.getCovariance(), p.getCovariance()));
		assertTrue(Arrays.deepEquals(p2.getCovarianceMatrix(),
				p.getCovarianceMatrix()));
		assertNotSame(p2, p);
		assertNotSame(p2.toString(), p.toString());
		assertNotSame(p2.toJsonObject(), p.toJsonObject());
		assertNotSame(p2.getPose(), p.getPose());
		assertNotSame(p2.getCovariance(), p.getCovariance());
		assertNotSame(p2.getCovarianceMatrix(), p.getCovarianceMatrix());
	}

	@Test
	public void testFromMessage() {
		Message m = new Message(p2.toString());
		PoseWithCovariance p = PoseWithCovariance.fromMessage(m);
		assertEquals(p2.toString(), p.toString());
		assertEquals(p2.toJsonObject(), p.toJsonObject());
		assertEquals(p2.getMessageType(), p.getMessageType());
		assertEquals(p2.getPose(), p.getPose());
		assertTrue(Arrays.equals(p2.getCovariance(), p.getCovariance()));
		assertTrue(Arrays.deepEquals(p2.getCovarianceMatrix(),
				p.getCovarianceMatrix()));
		assertNotSame(p2, p);
		assertNotSame(p2.toString(), p.toString());
		assertNotSame(p2.toJsonObject(), p.toJsonObject());
		assertNotSame(p2.getPose(), p.getPose());
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
				.add(PoseWithCovariance.FIELD_POSE, p2.getPose().toJsonObject())
				.add(PoseWithCovariance.FIELD_COVARIANCE, array.build())
				.build();
		PoseWithCovariance p = PoseWithCovariance.fromJsonObject(jsonObject);
		assertEquals(p2.toString(), p.toString());
		assertEquals(p2.toJsonObject(), p.toJsonObject());
		assertEquals(p2.getMessageType(), p.getMessageType());
		assertEquals(p2.getPose(), p.getPose());
		assertTrue(Arrays.equals(p2.getCovariance(), p.getCovariance()));
		assertTrue(Arrays.deepEquals(p2.getCovarianceMatrix(),
				p.getCovarianceMatrix()));
		assertNotSame(p2, p);
		assertNotSame(p2.toString(), p.toString());
		assertNotSame(p2.toJsonObject(), p.toJsonObject());
		assertNotSame(p2.getPose(), p.getPose());
		assertNotSame(p2.getCovariance(), p.getCovariance());
		assertNotSame(p2.getCovarianceMatrix(), p.getCovarianceMatrix());
	}

	@Test
	public void testFromJsonObjectNoPose() {
		JsonArrayBuilder array = Json.createArrayBuilder();
		for (Double d : p2.getCovariance()) {
			array.add(d);
		}
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(PoseWithCovariance.FIELD_COVARIANCE, array.build())
				.build();
		PoseWithCovariance p = PoseWithCovariance.fromJsonObject(jsonObject);
		assertEquals(p2.getMessageType(), p.getMessageType());
		assertEquals(new Pose(), p.getPose());
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
				.add(PoseWithCovariance.FIELD_POSE, p2.getPose().toJsonObject())
				.build();
		PoseWithCovariance p = PoseWithCovariance.fromJsonObject(jsonObject);
		assertEquals(p2.getMessageType(), p.getMessageType());
		assertEquals(p2.getPose(), p.getPose());
		assertEquals(PoseWithCovariance.COVARIANCE_SIZE,
				p.getCovariance().length);
		for (double d : p.getCovariance()) {
			assertEquals(0.0, d, 0);
		}
		assertEquals(PoseWithCovariance.COVARIANCE_ROWS,
				p.getCovarianceMatrix().length);
		for (double[] row : p.getCovarianceMatrix()) {
			assertEquals(PoseWithCovariance.COVARIANCE_COLUMNS, row.length);
			for (double d : row) {
				assertEquals(0.0, d, 0);
			}
		}
		assertNotSame(p2, p);
		assertNotSame(p2.toString(), p.toString());
		assertNotSame(p2.toJsonObject(), p.toJsonObject());
		assertNotSame(p2.getPose(), p.getPose());
	}
}
