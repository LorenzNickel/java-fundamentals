package datastructures.math;

import junit.framework.TestCase;

/**
 * @author Lorenz Nickel
 */
public class TupleTest extends TestCase {

  public void testImmutability() {
    Tuple<Integer> tupleA = new Tuple<>(1, 2);
    assertEquals(tupleA.getA(), Integer.valueOf(1));
    assertEquals(tupleA.getB(), Integer.valueOf(2));
    Tuple<Integer> tupleB = tupleA.withA(3);
    assertEquals(tupleA.getA(), Integer.valueOf(1));
    assertEquals(tupleB.getA(), Integer.valueOf(3));
  }

}
