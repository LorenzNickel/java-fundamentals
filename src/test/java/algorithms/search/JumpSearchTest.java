package algorithms.search;

import junit.framework.TestCase;

public class JumpSearchTest extends TestCase {

  public void testEmptyArray() {
    JumpSearch jumpSearch = new JumpSearch();
    assertEquals(jumpSearch.searchFor(new int[0],0), -1);
  }

  public void testIntegerArray() {
    JumpSearch jumpSearch = new JumpSearch();
    assertEquals(jumpSearch.searchFor(new int[]{5, 22, 24, 47, 57, 67, 89, 91, 95, 99}, 5), 0);
    assertEquals(jumpSearch.searchFor(new int[]{5, 22, 24, 47, 57, 67, 89, 91, 95, 99}, 22), 1);
    assertEquals(jumpSearch.searchFor(new int[]{5, 22, 24, 47, 57, 67, 89, 91, 95, 99}, 24), 2);
    assertEquals(jumpSearch.searchFor(new int[]{5, 22, 24, 47, 57, 67, 89, 91, 95, 99}, 47), 3);
    assertEquals(jumpSearch.searchFor(new int[]{5, 22, 24, 47, 57, 67, 89, 91, 95, 99}, 57), 4);
    assertEquals(jumpSearch.searchFor(new int[]{5, 22, 24, 47, 57, 67, 89, 91, 95, 99}, 67), 5);
    assertEquals(jumpSearch.searchFor(new int[]{5, 22, 24, 47, 57, 67, 89, 91, 95, 99}, 89), 6);
    assertEquals(jumpSearch.searchFor(new int[]{5, 22, 24, 47, 57, 67, 89, 91, 95, 99}, 91), 7);
    assertEquals(jumpSearch.searchFor(new int[]{5, 22, 24, 47, 57, 67, 89, 91, 95, 99}, 95), 8);
    assertEquals(jumpSearch.searchFor(new int[]{5, 22, 24, 47, 57, 67, 89, 91, 95, 99}, 99), 9);
  }

  public void testWithNullArray() {
    try {
      JumpSearch jumpSearch =new JumpSearch();
      jumpSearch.searchFor(null,0);
      fail();
    } catch (IllegalArgumentException ignored) {
    }
  }

}
