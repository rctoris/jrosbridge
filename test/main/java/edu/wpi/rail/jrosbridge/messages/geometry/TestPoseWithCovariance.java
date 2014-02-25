package edu.wpi.rail.jrosbridge.messages.geometry;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestPoseWithCovariance {

	private PoseWithCovariance empty, p1, p2, p3;

	@Before
	public void setUp() {
		empty = new PoseWithCovariance();
		p1 = new PoseWithCovariance(new Pose(new Point(0.5, 1.5, 3.0),
				new Quaternion(-0.5, -1.5, -3.0, -4.5)));
		p2 = new PoseWithCovariance(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9,
				10, 11, 12, 13, 14, 15, 16 });
		p3 = new PoseWithCovariance(new Pose(new Point(0.5, 1.5, 3.0),
				new Quaternion(-0.5, -1.5, -3.0, -4.5)), new double[] { 1, 2,
				3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 });
	}

	@Test
	public void testConstructor() {
		assertEquals(PoseWithCovariance.COVARIANCE_SIZE,
				empty.getCovariance().length);
		for (double d : empty.getCovariance()) {
			assertEquals(0.0, d);
		}
		assertEquals(PoseWithCovariance.COVARIANCE_ROWS,
				empty.getCovarianceMatrix().length);
		for (double[] row : empty.getCovarianceMatrix()) {
			assertEquals(PoseWithCovariance.COVARIANCE_COLUMNS, row.length);
			for (double d : row) {
				assertEquals(0.0, d);
			}
		}

		assertEquals(
				"{\"pose\":{\"position\":{\"x\":0.0,\"y\":0.0,\"z\":0.0},"
						+ "\"orientation\":{\"x\":0.0,\"y\":0.0,\"z\":0.0,\"w\":0.0}},"
						+ "\"covariance\":[0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0]}",
				empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		// TODO

		assertEquals(PoseWithCovariance.TYPE, empty.getMessageType());
	}

	// @Test
	// public void testPoint32ArrayConstructor() {
	// assertEquals(new Point32[] { new Point32(0.5f, 1.5f, 3.0f),
	// new Point32(-0.5f, -1.5f, -3.0f) }, p1.getPoints());
	// assertEquals(2, p1.size());
	// assertEquals(new Point32(0.5f, 1.5f, 3.0f), p1.get(0));
	// assertEquals(new Point32(-0.5f, -1.5f, -3.0f), p1.get(1));
	//
	// assertEquals(
	// "{\"points\":[{\"x\":0.5,\"y\":1.5,\"z\":3.0},{\"x\":-0.5,\"y\":-1.5,\"z\":-3.0}]}",
	// p1.toString());
	//
	// assertEquals(1, p1.toJsonObject().size());
	// assertEquals(2,
	// p1.toJsonObject().getJsonArray(PoseWithCovariance.FIELD_POINTS)
	// .size());
	// assertEquals(3,
	// p1.toJsonObject().getJsonArray(PoseWithCovariance.FIELD_POINTS)
	// .getJsonObject(0).size());
	// assertEquals(0.5,
	// p1.toJsonObject().getJsonArray(PoseWithCovariance.FIELD_POINTS)
	// .getJsonObject(0).getJsonNumber(Point32.FIELD_X)
	// .doubleValue());
	// assertEquals(1.5,
	// p1.toJsonObject().getJsonArray(PoseWithCovariance.FIELD_POINTS)
	// .getJsonObject(0).getJsonNumber(Point32.FIELD_Y)
	// .doubleValue());
	// assertEquals(3.0,
	// p1.toJsonObject().getJsonArray(PoseWithCovariance.FIELD_POINTS)
	// .getJsonObject(0).getJsonNumber(Point32.FIELD_Z)
	// .doubleValue());
	// assertEquals(3,
	// p1.toJsonObject().getJsonArray(PoseWithCovariance.FIELD_POINTS)
	// .getJsonObject(1).size());
	// assertEquals(-0.5,
	// p1.toJsonObject().getJsonArray(PoseWithCovariance.FIELD_POINTS)
	// .getJsonObject(1).getJsonNumber(Point32.FIELD_X)
	// .doubleValue());
	// assertEquals(-1.5,
	// p1.toJsonObject().getJsonArray(PoseWithCovariance.FIELD_POINTS)
	// .getJsonObject(1).getJsonNumber(Point32.FIELD_Y)
	// .doubleValue());
	// assertEquals(-3.0,
	// p1.toJsonObject().getJsonArray(PoseWithCovariance.FIELD_POINTS)
	// .getJsonObject(1).getJsonNumber(Point32.FIELD_Z)
	// .doubleValue());
	//
	// assertEquals(PoseWithCovariance.TYPE, p1.getMessageType());
	// }

	@Test
	public void testSetType() {
		empty.setMessageType("test");
		assertEquals("test", empty.getMessageType());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(p1.toString().hashCode(), p1.hashCode());
		assertEquals(p2.toString().hashCode(), p2.hashCode());
		assertEquals(p3.toString().hashCode(), p3.hashCode());
	}

	// @Test
	// public void testEquals() {
	// assertFalse(empty.equals(p1));
	// assertFalse(p1.equals(empty));
	// assertFalse(empty.equals(p2));
	// assertFalse(p2.equals(empty));
	// assertFalse(empty.equals(p3));
	// assertFalse(p3.equals(empty));
	//
	// assertFalse(p1.equals(p2));
	// assertFalse(p1.equals(p3));
	// assertFalse(p2.equals(p1));
	// assertFalse(p2.equals(p3));
	// assertFalse(p3.equals(p1));
	// assertFalse(p3.equals(p2));
	//
	// assertTrue(empty.equals(empty));
	// assertTrue(p1.equals(p1));
	// assertTrue(p2.equals(p2));
	// assertTrue(p3.equals(p3));
	// }

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	// @Test
	// public void testClone() {
	// PoseWithCovariance clone = p3.clone();
	// assertEquals(p3.toString(), clone.toString());
	// assertEquals(p3.toJsonObject(), clone.toJsonObject());
	// assertEquals(p3.getMessageType(), clone.getMessageType());
	// assertEquals(p3.getPosition(), clone.getPosition());
	// assertEquals(p3.getOrientation(), clone.getOrientation());
	// assertNotSame(p3, clone);
	// assertNotSame(p3.toString(), clone.toString());
	// assertNotSame(p3.toJsonObject(), clone.toJsonObject());
	// }
}
