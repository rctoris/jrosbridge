package edu.wpi.rail.jrosbridge.primitives;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;

public class TestTime {

	private Time empty, t1, t2, t3;

	@Before
	public void setUp() {
		empty = new Time();
		t1 = new Time(10.2);
		t2 = new Time(1024);
		t3 = new Time(10, 20);
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
		assertFalse(empty.isValid());

		assertEquals("{\"secs\":0,\"nsecs\":0}", empty.toString());

		assertEquals(2, empty.toJsonObject().size());
		assertEquals(0, empty.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(0, empty.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals(Time.TYPE, empty.getPrimitiveType());
	}

	@Test
	public void testDoubleConstructor() {
		assertEquals(10, t1.secs);
		assertEquals(200000000, t1.nsecs);
		assertEquals(10, t1.getSecs());
		assertEquals(200000000, t1.getNsecs());
		assertFalse(t1.isZero());
		assertEquals(10.2, t1.toSec(), 0);
		assertEquals(10200000000l, t1.toNSec());
		assertTrue(t1.isValid());

		assertEquals("{\"secs\":10,\"nsecs\":200000000}", t1.toString());

		assertEquals(2, t1.toJsonObject().size());
		assertEquals(10, t1.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(200000000, t1.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals(Time.TYPE, t1.getPrimitiveType());
	}

	@Test
	public void testLongConstructor() {
		assertEquals(0, t2.secs);
		assertEquals(1024, t2.nsecs);
		assertEquals(0, t2.getSecs());
		assertEquals(1024, t2.getNsecs());
		assertFalse(t2.isZero());
		assertEquals(1.024e-6, t2.toSec(), 0);
		assertEquals(1024l, t2.toNSec());
		assertTrue(t2.isValid());

		assertEquals("{\"secs\":0,\"nsecs\":1024}", t2.toString());

		assertEquals(2, t2.toJsonObject().size());
		assertEquals(0, t2.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(1024, t2.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals(Time.TYPE, t2.getPrimitiveType());
	}

	@Test
	public void testIntAndIntConstructor() {
		assertEquals(10, t3.secs);
		assertEquals(20, t3.nsecs);
		assertEquals(10, t3.getSecs());
		assertEquals(20, t3.getNsecs());
		assertFalse(t3.isZero());
		assertEquals(10.00000002, t3.toSec(), 0);
		assertEquals(10000000020l, t3.toNSec());
		assertTrue(t3.isValid());

		assertEquals("{\"secs\":10,\"nsecs\":20}", t3.toString());

		assertEquals(2, t3.toJsonObject().size());
		assertEquals(10, t3.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(20, t3.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals(Time.TYPE, t3.getPrimitiveType());
	}

	@Test
	public void testAdd() {
		assertEquals(new Time(10000001044l), t2.add(t3));
		assertEquals(t1, empty.add(t1));
		assertEquals(t2, empty.add(t2));
		assertEquals(t3, empty.add(t3));
	}

	@Test
	public void testSubtract() {
		assertEquals(new Time(9999998996l), t3.subtract(t2));
		assertEquals(t1, t1.subtract(empty));
		assertEquals(t2, t2.subtract(empty));
		assertEquals(t3, t3.subtract(empty));
	}

	@Test
	public void testToDate() {
		Date emptyDate = empty.toDate();
		Date date1 = t1.toDate();
		Date date2 = t2.toDate();
		Date date3 = t3.toDate();

		assertEquals(0l, emptyDate.getTime());
		assertEquals(10200l, date1.getTime());
		assertEquals(0l, date2.getTime());
		assertEquals(10000l, date3.getTime());
	}

	@Test
	public void testHashCode() {
		assertEquals(empty.toString().hashCode(), empty.hashCode());
		assertEquals(t1.toString().hashCode(), t1.hashCode());
		assertEquals(t2.toString().hashCode(), t2.hashCode());
		assertEquals(t3.toString().hashCode(), t3.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(empty.equals(t1));
		assertFalse(empty.equals(t2));
		assertFalse(empty.equals(t3));
		assertFalse(t1.equals(empty));
		assertFalse(t1.equals(t2));
		assertFalse(t1.equals(t3));
		assertFalse(t2.equals(empty));
		assertFalse(t2.equals(t1));
		assertFalse(t2.equals(t3));
		assertFalse(t3.equals(empty));
		assertFalse(t3.equals(t1));
		assertFalse(t3.equals(t2));

		assertTrue(empty.equals(empty));
		assertTrue(t1.equals(t1));
		assertTrue(t2.equals(t2));
		assertTrue(t3.equals(t3));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(empty.equals(new String(empty.toString())));
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, empty.compareTo(empty));
		assertEquals(0, t1.compareTo(t1));
		assertEquals(0, t2.compareTo(t2));
		assertEquals(0, t3.compareTo(t3));

		assertFalse(greaterThan(empty.compareTo(t1), 0));
		assertTrue(greaterThan(t3.compareTo(empty), 0));
	}

	@Test
	public void testClone() {
		Time clone = t1.clone();
		assertEquals(t1.toString(), clone.toString());
		assertEquals(t1.toJsonObject(), clone.toJsonObject());
		assertEquals(t1.getPrimitiveType(), clone.getPrimitiveType());
		assertEquals(t1.getSecs(), clone.getSecs());
		assertEquals(t1.getNsecs(), clone.getNsecs());
		assertEquals(t1.toSec(), clone.toSec(), 0);
		assertEquals(t1.toNSec(), clone.toNSec());
		assertNotSame(t1, clone);
		assertNotSame(t1.toString(), clone.toString());
		assertNotSame(t1.toJsonObject(), clone.toJsonObject());
	}

	@Test
	public void testSleepUntil() {
		Time t = new Time(((double) System.currentTimeMillis() + 500) / 1000.0);
		assertTrue(Time.sleepUntil(t));
		assertTrue(greaterThanEquals(System.currentTimeMillis(), t.toDate()
				.getTime()));
	}

	@Test
	public void testSleepUntilFailed() {
		Time t = new Time(((double) System.currentTimeMillis() + 500) / 1000.0);
		Thread.currentThread().interrupt();
		assertFalse(Time.sleepUntil(t));
		assertFalse(greaterThanEquals(System.currentTimeMillis(), t.toDate()
				.getTime()));
	}

	@Test
	public void testFromSec() {
		Time t = Time.fromSec(10.2);

		assertEquals(10, t.secs);
		assertEquals(200000000, t.nsecs);
		assertEquals(10, t.getSecs());
		assertEquals(200000000, t.getNsecs());
		assertFalse(t.isZero());
		assertEquals(10.2, t.toSec(), 0);
		assertEquals(10200000000l, t.toNSec());

		assertEquals("{\"secs\":10,\"nsecs\":200000000}", t.toString());

		assertEquals(2, t.toJsonObject().size());
		assertEquals(10, t.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(200000000, t.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals(Time.TYPE, t.getPrimitiveType());
	}

	@Test
	public void testFromNano() {
		Time t = Time.fromNano(10200000000l);

		assertEquals(10, t.secs);
		assertEquals(200000000, t.nsecs);
		assertEquals(10, t.getSecs());
		assertEquals(200000000, t.getNsecs());
		assertFalse(t.isZero());
		assertEquals(10.2, t.toSec(), 0);
		assertEquals(10200000000l, t.toNSec());

		assertEquals("{\"secs\":10,\"nsecs\":200000000}", t.toString());

		assertEquals(2, t.toJsonObject().size());
		assertEquals(10, t.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(200000000, t.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals(Time.TYPE, t.getPrimitiveType());
	}

	@Test
	public void testFromDate() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(10200);
		Time t = Time.fromDate(c.getTime());

		assertEquals(10, t.secs);
		assertEquals(200000000, t.nsecs);
		assertEquals(10, t.getSecs());
		assertEquals(200000000, t.getNsecs());
		assertFalse(t.isZero());
		assertEquals(10.2, t.toSec(), 0);
		assertEquals(10200000000l, t.toNSec());

		assertEquals("{\"secs\":10,\"nsecs\":200000000}", t.toString());

		assertEquals(2, t.toJsonObject().size());
		assertEquals(10, t.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(200000000, t.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals(Time.TYPE, t.getPrimitiveType());
	}

	@Test
	public void testFromJsonString() {
		Time t = Time.fromJsonString(t1.toString());
		assertEquals(t1.toString(), t.toString());
		assertEquals(t1.toJsonObject(), t.toJsonObject());
		assertEquals(t1.getPrimitiveType(), t.getPrimitiveType());
		assertEquals(t1.getSecs(), t.getSecs());
		assertEquals(t1.getNsecs(), t.getNsecs());
		assertEquals(t1.toSec(), t.toSec(), 0);
		assertEquals(t1.toNSec(), t.toNSec());
		assertNotSame(t1, t);
		assertNotSame(t1.toString(), t.toString());
		assertNotSame(t1.toJsonObject(), t.toJsonObject());
	}

	@Test
	public void testFromJsonObject() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Time.FIELD_SECS, t1.getSecs())
				.add(Time.FIELD_NSECS, t1.getNsecs()).build();
		Time t = Time.fromJsonObject(jsonObject);
		assertEquals(t1.toString(), t.toString());
		assertEquals(t1.toJsonObject(), t.toJsonObject());
		assertEquals(t1.getPrimitiveType(), t.getPrimitiveType());
		assertEquals(t1.getSecs(), t.getSecs());
		assertEquals(t1.getNsecs(), t.getNsecs());
		assertEquals(t1.toSec(), t.toSec(), 0);
		assertEquals(t1.toNSec(), t.toNSec());
		assertNotSame(t1, t);
		assertNotSame(t1.toString(), t.toString());
		assertNotSame(t1.toJsonObject(), t.toJsonObject());
	}

	@Test
	public void testFromJsonObjectNoSecs() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Time.FIELD_NSECS, t1.getNsecs()).build();
		Time t = Time.fromJsonObject(jsonObject);
		assertEquals(0, t.getSecs());
		assertEquals(t1.getNsecs(), t.getNsecs());
		assertEquals(t1.getNsecs() / 1000000000.0, t.toSec(), 0);
		assertEquals((long) t1.getNsecs(), t.toNSec());
	}

	@Test
	public void testFromJsonObjectNoNsecs() {
		JsonObject jsonObject = Json.createObjectBuilder()
				.add(Time.FIELD_SECS, t1.getSecs()).build();
		Time t = Time.fromJsonObject(jsonObject);
		assertEquals(t1.getSecs(), t.getSecs());
		assertEquals(0, t.getNsecs());
		assertEquals((double) t1.getSecs(), t.toSec(), 0);
		assertEquals(t1.getSecs() * 1000000000l, t.toNSec());
	}

	public boolean greaterThan(long a, long b) {
		return a > b;
	}

	public boolean greaterThanEquals(long a, long b) {
		return a >= b;
	}
}
