package datastructures.math;

import junit.framework.TestCase;

/**
 * @author Lorenz Nickel
 */
public class TupleTest extends TestCase {

  public void testImmutability() {
    final Tuple<Integer> tupleA = new Tuple<>(1, 2);
    assertEquals(tupleA.getA(), Integer.valueOf(1));
    assertEquals(tupleA.getB(), Integer.valueOf(2));
    final Tuple<Integer> tupleB = tupleA.withA(3);
    assertEquals(tupleA.getA(), Integer.valueOf(1));
    assertEquals(tupleB.getA(), Integer.valueOf(3));
  }

  public void testEquals() {
    final Tuple<Integer> tupleA = new Tuple<>(1, 2);
    assertEquals(tupleA, new Tuple<>(1, 2));
    assertNotSame(null, tupleA);
    assertNotSame(tupleA, null);
    assertNotSame(1, tupleA);
    assertNotSame(tupleA, 1);
    assertNotSame(tupleA, new Tuple<>(2, 1));
    final Tuple tupleB = tupleA.withB(1);
    assertEquals(tupleB, tupleB);
  }

  public void testHashCode() {
    assertEquals(new Tuple<>("a", "b").hashCode(), new Tuple<>("a", "b").hashCode());
    assertNotSame(new Tuple<>("a", "b").hashCode(), new Tuple<>("b", "a").hashCode());
  }

  public void testToString() {
    assertEquals(new Tuple<>(2.0f, 1.0f).toString(), "Tuple{a=2.0, b=1.0}");
  }

}
