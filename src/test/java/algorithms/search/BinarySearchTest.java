package algorithms.search;

import junit.framework.TestCase;

/**
 * @author Lorenz Nickel
 */
public class BinarySearchTest extends TestCase {

  public void testEmptyArray() {
    BinarySearch<Integer> binarySearch = new BinarySearch<>(new Integer[0]);
    assertEquals(binarySearch.searchFor(0), -1);
  }

  public void testIntegerArray() {
    BinarySearch<Integer> binarySearch = new BinarySearch<>(
        new Integer[]{-10, -5, -3, 0, 4, 19, 233});
    assertEquals(binarySearch.searchFor(-15), -1);
    assertEquals(binarySearch.searchFor(-10), 0);
    assertEquals(binarySearch.searchFor(-8), -1);
    assertEquals(binarySearch.searchFor(-5), 1);
    assertEquals(binarySearch.searchFor(-4), -1);
    assertEquals(binarySearch.searchFor(-3), 2);
    assertEquals(binarySearch.searchFor(-1), -1);
    assertEquals(binarySearch.searchFor(0), 3);
    assertEquals(binarySearch.searchFor(-2), -1);
    assertEquals(binarySearch.searchFor(4), 4);
    assertEquals(binarySearch.searchFor(10), -1);
    assertEquals(binarySearch.searchFor(19), 5);
    assertEquals(binarySearch.searchFor(30), -1);
    assertEquals(binarySearch.searchFor(233), 6);
    assertEquals(binarySearch.searchFor(300), -1);
  }

  public void testIntegerArrayWithNullValues() {
    BinarySearch<Integer> binarySearch = new BinarySearch<>(new Integer[]{1, 2, 3, null});
    try {
      binarySearch.searchFor(4);
      fail();
    } catch (NullPointerException e) {
    }
    try {
      binarySearch.searchFor(null);
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

  public void testWithNullArray() {
    try {
      new BinarySearch<>(null);
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

}
