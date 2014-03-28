package edu.wpi.rail.jrosbridge.primitives;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTimeBase {

	private TimeBase<DummyTimeBase> t1, t2, t3, t4;

	@Before
	public void setUp() {
		t1 = new DummyTimeBase("test");
		t2 = new DummyTimeBase(10.2, "test");
		t3 = new DummyTimeBase(1024, "test");
		t4 = new DummyTimeBase(10, 20, "test");
	}

	@Test
	public void testStringConstructor() {
		assertEquals(0, t1.secs);
		assertEquals(0, t1.nsecs);
		assertEquals(0, t1.getSecs());
		assertEquals(0, t1.getNsecs());
		assertTrue(t1.isZero());
		assertEquals(0.0, t1.toSec(), 0);
		assertEquals(0l, t1.toNSec());

		assertEquals("{\"secs\":0,\"nsecs\":0}", t1.toString());

		assertEquals(2, t1.toJsonObject().size());
		assertEquals(0, t1.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(0, t1.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals("test", t1.getPrimitiveType());
		assertNull(t1.add(null));
		assertNull(t1.subtract(null));
		assertNull(t1.clone());
	}

	@Test
	public void testDoubleAndStringConstructor() {
		assertEquals(10, t2.secs);
		assertEquals(200000000, t2.nsecs);
		assertEquals(10, t2.getSecs());
		assertEquals(200000000, t2.getNsecs());
		assertFalse(t2.isZero());
		assertEquals(10.2, t2.toSec(), 0);
		assertEquals(10200000000l, t2.toNSec());

		assertEquals("{\"secs\":10,\"nsecs\":200000000}", t2.toString());

		assertEquals(2, t2.toJsonObject().size());
		assertEquals(10, t2.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(200000000, t2.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals("test", t2.getPrimitiveType());
		assertNull(t2.add(null));
		assertNull(t2.subtract(null));
		assertNull(t2.clone());
	}

	@Test
	public void testLongAndStringConstructor() {
		assertEquals(0, t3.secs);
		assertEquals(1024, t3.nsecs);
		assertEquals(0, t3.getSecs());
		assertEquals(1024, t3.getNsecs());
		assertFalse(t3.isZero());
		assertEquals(1.024e-6, t3.toSec(), 0);
		assertEquals(1024l, t3.toNSec());

		assertEquals("{\"secs\":0,\"nsecs\":1024}", t3.toString());

		assertEquals(2, t3.toJsonObject().size());
		assertEquals(0, t3.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(1024, t3.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals("test", t3.getPrimitiveType());
		assertNull(t3.add(null));
		assertNull(t3.subtract(null));
		assertNull(t3.clone());
	}

	@Test
	public void testIntIntAndStringConstructor() {
		assertEquals(10, t4.secs);
		assertEquals(20, t4.nsecs);
		assertEquals(10, t4.getSecs());
		assertEquals(20, t4.getNsecs());
		assertFalse(t4.isZero());
		assertEquals(10.00000002, t4.toSec(), 0);
		assertEquals(10000000020l, t4.toNSec());

		assertEquals("{\"secs\":10,\"nsecs\":20}", t4.toString());

		assertEquals(2, t4.toJsonObject().size());
		assertEquals(10, t4.toJsonObject().getInt(TimeBase.FIELD_SECS));
		assertEquals(20, t4.toJsonObject().getInt(TimeBase.FIELD_NSECS));

		assertEquals("test", t4.getPrimitiveType());
		assertNull(t4.add(null));
		assertNull(t4.subtract(null));
		assertNull(t4.clone());
	}

	@Test
	public void testHashCode() {
		assertEquals(t1.toString().hashCode(), t1.hashCode());
		assertEquals(t2.toString().hashCode(), t2.hashCode());
		assertEquals(t3.toString().hashCode(), t3.hashCode());
		assertEquals(t4.toString().hashCode(), t4.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(t1.equals(t2));
		assertFalse(t1.equals(t3));
		assertFalse(t1.equals(t4));
		assertFalse(t2.equals(t1));
		assertFalse(t2.equals(t3));
		assertFalse(t2.equals(t4));
		assertFalse(t3.equals(t1));
		assertFalse(t3.equals(t2));
		assertFalse(t3.equals(t4));
		assertFalse(t4.equals(t1));
		assertFalse(t4.equals(t2));
		assertFalse(t4.equals(t3));

		assertTrue(t1.equals(t1));
		assertTrue(t2.equals(t2));
		assertTrue(t3.equals(t3));
		assertTrue(t4.equals(t4));
	}

	@Test
	public void testEqualsWrongObject() {
		assertFalse(t1.equals(new String(t1.toString())));
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, t1.compareTo(t1));
		assertEquals(0, t2.compareTo(t2));
		assertEquals(0, t3.compareTo(t3));
		assertEquals(0, t4.compareTo(t4));

		assertFalse(greaterThan(t1.compareTo(t2), 0));
		assertTrue(greaterThan(t4.compareTo(t1), 0));
	}

	public boolean greaterThan(int a, int b) {
		return a > b;
	}

	private class DummyTimeBase extends TimeBase<DummyTimeBase> {

		public DummyTimeBase(String type) {
			super(type);
		}

		public DummyTimeBase(double sec, String type) {
			super(sec, type);
		}

		public DummyTimeBase(long nano, String type) {
			super(nano, type);
		}

		public DummyTimeBase(int secs, int nsecs, String type) {
			super(secs, nsecs, type);
		}

		public DummyTimeBase add(DummyTimeBase t) {
			return null;
		}

		public DummyTimeBase subtract(DummyTimeBase t) {
			return null;
		}

		public DummyTimeBase clone() {
			return null;
		}

	}
}
