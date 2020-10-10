package algorithms.search;

import junit.framework.TestCase;

public class JumpSearchTest extends TestCase {

  public void testEmptyArray() {
    JumpSearch<Integer> jumpSearch = new JumpSearch<>(new Integer[0]);
    assertEquals(jumpSearch.searchFor(0), -1);
  }

  public void testIntegerArray() {
    JumpSearch<Integer> jumpSearch = new JumpSearch<>(new Integer[]{5, 22, 24, 47, 57, 67, 89, 91, 95, 99});
    assertEquals(jumpSearch.searchFor( 5), 0);
    assertEquals(jumpSearch.searchFor(22), 1);
    assertEquals(jumpSearch.searchFor(24), 2);
    assertEquals(jumpSearch.searchFor(47), 3);
    assertEquals(jumpSearch.searchFor( 57), 4);
    assertEquals(jumpSearch.searchFor( 67), 5);
    assertEquals(jumpSearch.searchFor(89), 6);
    assertEquals(jumpSearch.searchFor(91), 7);
    assertEquals(jumpSearch.searchFor( 95), 8);
    assertEquals(jumpSearch.searchFor( 99), 9);
  }

  public void testIntegerArrayWithNullValues() {
    JumpSearch<Integer> jumpSearch = new JumpSearch<>(new Integer[]{1, 2, null, 2, null});
    try {
      jumpSearch.searchFor(-1);
    } catch (NullPointerException e) {
    }
    assertEquals(jumpSearch.searchFor(2), 1);
    try {
      jumpSearch.searchFor(null);
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

  public void testWithNullArray() {
    try {
      JumpSearch<Integer> jumpSearch =new JumpSearch<>(null);
      jumpSearch.searchFor(0);
      fail();
    } catch (IllegalArgumentException ignored) {
    }
  }
}
