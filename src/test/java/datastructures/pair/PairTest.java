package datastructures.pair;

import junit.framework.TestCase;

/**
 * @author thepn
 */
public class PairTest extends TestCase {

  public void testEquals(){
    final Pair<Integer, Integer> a = new Pair<>(1, 2);
    final Pair<Integer, Integer> b = new Pair<>(1, 2);
    final Pair<Integer, Integer> c = new Pair<>(2, 1);

    assertTrue(a.equals(b));
    assertFalse(a.equals(c));
    assertFalse(a.equals(null));
  }

  public void testKey(){
    final Pair<Integer, Integer> integerIntegerPair = new Pair<>(10, 4);
    final int key = integerIntegerPair.getKey();

    assertEquals(10, key);
    assertNotSame(10, integerIntegerPair.getValue());
  }

  public void testValue(){
    final Pair<Integer, Integer> integerIntegerPair = new Pair<>(10, 4);
    final int value = integerIntegerPair.getValue();

    assertEquals(4, value);
    assertNotSame(4, integerIntegerPair.getKey());
  }
}
