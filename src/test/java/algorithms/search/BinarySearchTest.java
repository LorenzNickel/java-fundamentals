package algorithms.search;

import junit.framework.TestCase;

/**
 * @author Lorenz Nickel
 */
public class BinarySearchTest extends TestCase {

  public void testEmptyArray() {
    BinarySearch<Integer> binarySearch = new BinarySearch<>(new Integer[0]);
    assertEquals(binarySearch.searchIteratively(0), -1);
  }

  public void testIteratively() {
    BinarySearch<Integer> binarySearch = new BinarySearch<>(
        new Integer[]{-10, -5, -3, 0, 4, 19, 233});
    assertEquals(binarySearch.searchIteratively(-15), -1);
    assertEquals(binarySearch.searchIteratively(-10), 0);
    assertEquals(binarySearch.searchIteratively(-8), -1);
    assertEquals(binarySearch.searchIteratively(-5), 1);
    assertEquals(binarySearch.searchIteratively(-4), -1);
    assertEquals(binarySearch.searchIteratively(-3), 2);
    assertEquals(binarySearch.searchIteratively(-1), -1);
    assertEquals(binarySearch.searchIteratively(0), 3);
    assertEquals(binarySearch.searchIteratively(-2), -1);
    assertEquals(binarySearch.searchIteratively(4), 4);
    assertEquals(binarySearch.searchIteratively(10), -1);
    assertEquals(binarySearch.searchIteratively(19), 5);
    assertEquals(binarySearch.searchIteratively(30), -1);
    assertEquals(binarySearch.searchIteratively(233), 6);
    assertEquals(binarySearch.searchIteratively(300), -1);
  }

  public void testRecursively() {
    BinarySearch<Integer> binarySearch = new BinarySearch<>(
        new Integer[]{-10, -5, -3, 0, 4, 19, 233});
    assertEquals(binarySearch.searchRecursively(0, 6, -15), -1);
    assertEquals(binarySearch.searchRecursively(0, 6, -10), 0);
    assertEquals(binarySearch.searchRecursively(0, 6, -8), -1);
    assertEquals(binarySearch.searchRecursively(0, 6, -5), 1);
    assertEquals(binarySearch.searchRecursively(0, 6, -4), -1);
    assertEquals(binarySearch.searchRecursively(0, 6, -3), 2);
    assertEquals(binarySearch.searchRecursively(0, 6, -1), -1);
    assertEquals(binarySearch.searchRecursively(0, 6, 0), 3);
    assertEquals(binarySearch.searchRecursively(0, 6, -2), -1);
    assertEquals(binarySearch.searchRecursively(0, 6, 4), 4);
    assertEquals(binarySearch.searchRecursively(0, 6, 10), -1);
    assertEquals(binarySearch.searchRecursively(0, 6, 19), 5);
    assertEquals(binarySearch.searchRecursively(0, 6, 30), -1);
    assertEquals(binarySearch.searchRecursively(0, 6, 233), 6);
    assertEquals(binarySearch.searchRecursively(0, 6, 300), -1);
  }

  public void testIntegerArrayWithNullValues() {
    BinarySearch<Integer> binarySearch = new BinarySearch<>(new Integer[]{1, 2, 3, null});
    try {
      binarySearch.searchIteratively(4);
      fail();
    } catch (NullPointerException e) {
    }
    try {
      binarySearch.searchRecursively(0, 3, 4);
      fail();
    } catch (NullPointerException e) {
    }
    try {
      binarySearch.searchIteratively(null);
      fail();
    } catch (IllegalArgumentException e) {
    }
    try {
      binarySearch.searchRecursively(0, 3, null);
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
