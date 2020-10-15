package algorithms.search.astar.model;

import junit.framework.TestCase;

public class Vector2Test extends TestCase {

  public void testVector2Constructor() {
    try {
      new Vector2(-1, 1);
      fail();
    } catch (IllegalArgumentException e) { }

    try {
      new Vector2(0, -5);
      fail();
    } catch (IllegalArgumentException e) { }

    try {
      new Vector2(0, 1);
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new Vector2(-1, -5);
      fail();
    } catch (IllegalArgumentException e) { }
  }

  public void testAllInRange() {
    Vector2 v1 = new Vector2(5, 2);
    Vector2 v2 = new Vector2(4, 7);
    Vector2 v3 = new Vector2(5, 23);

    assertFalse(v1.allInRange(3, 4));
    assertTrue(v2.allInRange(2, 10));
    assertFalse(v3.allInRange(0, 1));
    try {
      v3.allInRange(3, 3);
      fail();
    } catch (IllegalArgumentException e) { }

    try {
      v3.allInRange(4, 3);
      fail();
    } catch (IllegalArgumentException e) { }

    try {
      v3.allInRange(-1, 4);
      fail();
    } catch (IllegalArgumentException e) { }

  }

  public void testToString() {
    Vector2 v1 = new Vector2(2, 3);
    Vector2 v2 = new Vector2(6, 1);

    assertEquals("x: 2, y: 3", v1.toString());
    assertEquals("x: 6, y: 1", v2.toString());
  }

  public void testGet() {
    Vector2 v1 = new Vector2(8, 6);
    Vector2 v2 = new Vector2(4, 0);

    assertEquals(8, v1.getX());
    assertEquals(6, v1.getY());
    assertEquals(4, v2.getX());
    assertEquals(0, v2.getY());
  }

  public void testSet() {
    Vector2 v1 = new Vector2(0, 0);

    v1.setX(2);
    assertEquals(2, v1.getX());
    v1.setY(1);
    assertEquals(1, v1.getY());
  }
}
