package algorithms.search;

import junit.framework.TestCase;

/**
 * @author Lorenz Nickel
 */
public class LinearSearchTest extends TestCase {

  public void testEmptyArray() {
    LinearSearch<Integer> linearSearch = new LinearSearch<>(new Integer[0]);
    assertEquals(linearSearch.searchFor(0), -1);
  }

  public void testIntegerArray() {
    LinearSearch<Integer> linearSearch = new LinearSearch<>(
        new Integer[]{-10, -5, -3, 0, 4, 19, 233});
    assertEquals(linearSearch.searchFor(-15), -1);
    assertEquals(linearSearch.searchFor(-10), 0);
    assertEquals(linearSearch.searchFor(-8), -1);
    assertEquals(linearSearch.searchFor(-5), 1);
    assertEquals(linearSearch.searchFor(-4), -1);
    assertEquals(linearSearch.searchFor(-3), 2);
    assertEquals(linearSearch.searchFor(-1), -1);
    assertEquals(linearSearch.searchFor(0), 3);
    assertEquals(linearSearch.searchFor(-2), -1);
    assertEquals(linearSearch.searchFor(4), 4);
    assertEquals(linearSearch.searchFor(10), -1);
    assertEquals(linearSearch.searchFor(19), 5);
    assertEquals(linearSearch.searchFor(30), -1);
    assertEquals(linearSearch.searchFor(233), 6);
    assertEquals(linearSearch.searchFor(300), -1);
  }

  public void testIntegerArrayWithNullValues() {
    LinearSearch<Integer> linearSearch = new LinearSearch<>(new Integer[]{1, 2, null, 2, null});
    try {
      linearSearch.searchFor(-1);
      fail();
    } catch (NullPointerException e) {
    }
    assertEquals(linearSearch.searchFor(2), 1);
    try {
      linearSearch.searchFor(null);
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

  public void testWithNullArray() {
    try {
      new LinearSearch<>(null);
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

}
