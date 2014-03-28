package edu.wpi.rail.jrosbridge.primitives;

import static org.junit.Assert.*;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

public class TestDuration {

	private Duration empty, d1, d2, d3;

	@Before
	public void setUp() {
		empty = new Duration();
		d1 = new Duration(10.2);
		d2 = new Duration(1024);
		d3 = new Duration(10, 20);
	}

	@Test
	public void testConstructor() {
		assertEquals(0, empty.secs);
		assertEquals(0, empty.nsecs);
		assertEquals(0, empty.getSecs());
		assertEquals(0, empty.getNsecs());
		assertTrue(empty.isZero());
		assertEquals(0.0, empty.toSec(), 0);
		assertEquals(0l, empty.toNSec());

		assertEquals("{\"secs\":0,\"nsecs\":0}", empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(0, empty.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(0, empty.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals(Duration.TYPE, empty.getPrimitiveType());
	}

	@Test
	public void testDoubleConstructor() {
		assertEquals(10, d1.secs);
		assertEquals(200000000, d1.nsecs);
		assertEquals(10, d1.getSecs());
		assertEquals(200000000, d1.getNsecs());
		assertFalse(d1.isZero());
		assertEquals(10.2, d1.toSec(), 0);
		assertEquals(10200000000l, d1.toNSec());

		assertEquals("{\"secs\":10,\"nsecs\":200000000}", d1.toString());

		assertEquals(2, d1.toJsonObject().size());
		assertEquals(10, d1.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(200000000, d1.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals(Duration.TYPE, d1.getPrimitiveType());
	}

	@Test
	public void testLongConstructor() {
		assertEquals(0, d2.secs);
		assertEquals(1024, d2.nsecs);
		assertEquals(0, d2.getSecs());
		assertEquals(1024, d2.getNsecs());
		assertFalse(d2.isZero());
		assertEquals(1.024e-6, d2.toSec(), 0);
		assertEquals(1024l, d2.toNSec());

		assertEquals("{\"secs\":0,\"nsecs\":1024}", d2.toString());

		assertEquals(2, d2.toJsonObject().size());
		assertEquals(0, d2.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(1024, d2.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals(Duration.TYPE, d2.getPrimitiveType());
	}

	@Test
	public void testIntAndIntConstructor() {
		assertEquals(10, d3.secs);
		assertEquals(20, d3.nsecs);
		assertEquals(10, d3.getSecs());
		assertEquals(20, d3.getNsecs());
		assertFalse(d3.isZero());
		assertEquals(10.00000002, d3.toSec(), 0);
		assertEquals(10000000020l, d3.toNSec());

		assertEquals("{\"secs\":10,\"nsecs\":20}", d3.toString());

		assertEquals(2, d3.toJsonObject().size());
		assertEquals(10, d3.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(20, d3.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals(Duration.TYPE, d3.getPrimitiveType());
	}

	@Test
	public void testAdd() {
		assertEquals(new Duration(10000001044l), d2.add(d3));
		assertEquals(d1, empty.add(d1));
		assertEquals(d2, empty.add(d2));
		assertEquals(d3, empty.add(d3));
	}

	@Test
	public void testSubtract() {
		assertEquals(new Duration(9999998996l), d3.subtract(d2));
		assertEquals(d1, d1.subtract(empty));
		assertEquals(d2, d2.subtract(empty));
		assertEquals(d3, d3.subtract(empty));
	}

	@Test
	public void testSleep() {
		long t = System.nanoTime();
		assertTrue(d2.sleep());
		assertTrue(greaterThanEquals(System.nanoTime(), t + d2.toNSec()));
	}

	@Test
	public void testSleepTooManyNanos() {
		Duration d = new Duration(0, 500000000);
		long t = System.nanoTime();
		assertTrue(d.sleep());
		assertTrue(greaterThanEquals(System.nanoTime(), t + d.toNSec()));
	}

	@Test
	public void testSleepFailed() {
		long t = System.nanoTime();
		Thread.currentThread().interrupt();
		assertFalse(d3.sleep());
		assertFalse(greaterThanEquals(System.nanoTime(), t + d3.toNSec()));
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(d1.toString().hashCode(), d1.hashCode());
		assertEquals(d2.toString().hashCode(), d2.hashCode());
		assertEquals(d3.toString().hashCode(), d3.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(d1));
		assertFalse(empty.equals(d2));
		assertFalse(empty.equals(d3));
		assertFalse(d1.equals(empty));
		assertFalse(d1.equals(d2));
		assertFalse(d1.equals(d3));
		assertFalse(d2.equals(empty));
		assertFalse(d2.equals(d1));
		assertFalse(d2.equals(d3));
		assertFalse(d3.equals(empty));
		assertFalse(d3.equals(d1));
		assertFalse(d3.equals(d2));

		assertTrue(empty.equals(empty));
		assertTrue(d1.equals(d1));
		assertTrue(d2.equals(d2));
		assertTrue(d3.equals(d3));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, empty.compareTo(empty));
		assertEquals(0, d1.compareTo(d1));
		assertEquals(0, d2.compareTo(d2));
		assertEquals(0, d3.compareTo(d3));

		assertFalse(greaterThan(empty.compareTo(d1), 0));
		assertTrue(greaterThan(d3.compareTo(empty), 0));
	}

	@Test
	public void testClone() {
		Duration clone = d1.clone();
		assertEquals(d1.toString(), clone.toString());
		assertEquals(d1.toJsonObject(), clone.toJsonObject());
		assertEquals(d1.getPrimitiveType(), clone.getPrimitiveType());
		assertEquals(d1.getSecs(), clone.getSecs());
		assertEquals(d1.getNsecs(), clone.getNsecs());
		assertEquals(d1.toSec(), clone.toSec(), 0);
		assertEquals(d1.toNSec(), clone.toNSec());
		assertNotSame(d1, clone);
		assertNotSame(d1.toString(), clone.toString());
		assertNotSame(d1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testFromSec() {
		Duration d = Duration.fromSec(10.2);

		assertEquals(10, d.secs);
		assertEquals(200000000, d.nsecs);
		assertEquals(10, d.getSecs());
		assertEquals(200000000, d.getNsecs());
		assertFalse(d.isZero());
		assertEquals(10.2, d.toSec(), 0);
		assertEquals(10200000000l, d.toNSec());

		assertEquals("{\"secs\":10,\"nsecs\":200000000}", d.toString());

		assertEquals(2, d.toJsonObject().size());
		assertEquals(10, d.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(200000000, d.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals(Duration.TYPE, d.getPrimitiveType());
	}

	@Test
	public void testFromNano() {
		Duration d = Duration.fromNano(10200000000l);

		assertEquals(10, d.secs);
		assertEquals(200000000, d.nsecs);
		assertEquals(10, d.getSecs());
		assertEquals(200000000, d.getNsecs());
		assertFalse(d.isZero());
		assertEquals(10.2, d.toSec(), 0);
		assertEquals(10200000000l, d.toNSec());

		assertEquals("{\"secs\":10,\"nsecs\":200000000}", d.toString());

		assertEquals(2, d.toJsonObject().size());
		assertEquals(10, d.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(200000000, d.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals(Duration.TYPE, d.getPrimitiveType());
	}

	@Test
	public void testFromJsonString() {
		Duration d = Duration.fromJsonString(d1.toString());
		assertEquals(d1.toString(), d.toString());
		assertEquals(d1.toJsonObject(), d.toJsonObject());
		assertEquals(d1.getPrimitiveType(), d.getPrimitiveType());
		assertEquals(d1.getSecs(), d.getSecs());
		assertEquals(d1.getNsecs(), d.getNsecs());
		assertEquals(d1.toSec(), d.toSec(), 0);
		assertEquals(d1.toNSec(), d.toNSec());
		assertNotSame(d1, d);
		assertNotSame(d1.toString(), d.toString());
		assertNotSame(d1.toJsonObject(), d.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Duration.FIELD_SECS, d1.getSecs())
				.add(Duration.FIELD_NSECS, d1.getNsecs()).build();
		Duration d = Duration.fromJsonObject(jsonObject);
		assertEquals(d1.toString(), d.toString());
		assertEquals(d1.toJsonObject(), d.toJsonObject());
		assertEquals(d1.getPrimitiveType(), d.getPrimitiveType());
		assertEquals(d1.getSecs(), d.getSecs());
		assertEquals(d1.getNsecs(), d.getNsecs());
		assertEquals(d1.toSec(), d.toSec(), 0);
		assertEquals(d1.toNSec(), d.toNSec());
		assertNotSame(d1, d);
		assertNotSame(d1.toString(), d.toString());
		assertNotSame(d1.toJsonObject(), d.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoSecs() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Duration.FIELD_NSECS, d1.getNsecs()).build();
		Duration d = Duration.fromJsonObject(jsonObject);
		assertEquals(0, d.getSecs());
		assertEquals(d1.getNsecs(), d.getNsecs());
		assertEquals(d1.getNsecs() / 1000000000.0, d.toSec(), 0);
		assertEquals((long) d1.getNsecs(), d.toNSec());
	}

	@Test
	public void testFromJsonObjectNoNsecs() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Duration.FIELD_SECS, d1.getSecs()).build();
		Duration d = Duration.fromJsonObject(jsonObject);
		assertEquals(d1.getSecs(), d.getSecs());
		assertEquals(0, d.getNsecs());
		assertEquals((double) d1.getSecs(), d.toSec(), 0);
		assertEquals(d1.getSecs() * 1000000000l, d.toNSec());
	}

	public boolean greaterThan(long a, long b) {
		return a > b;
	}

	public boolean greaterThanEquals(long a, long b) {
		return a >= b;
	}
}
