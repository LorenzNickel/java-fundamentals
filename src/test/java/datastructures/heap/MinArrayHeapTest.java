package datastructures.heap;

import junit.framework.TestCase;

/**
 * Created by ychan052 on 08.10.2020.
 */
public class MinArrayHeapTest extends TestCase {

  public void testInsert() {
    MinArrayHeap mh = new MinArrayHeap();
    mh.insert(30);
    mh.insert(300);
    mh.insert(500);
    mh.insert(10);
    mh.insert(20);
    mh.insert(25);
    mh.insert(29);
    assertEquals("MinArrayHeap{arrayList=[10,20,25,300,30,500,29]}", mh.toString());
  }

  public void testRemove() {
    MinArrayHeap mh = new MinArrayHeap();
    mh.insert(30);
    mh.insert(300);
    mh.insert(500);
    mh.insert(10);
    assertEquals(10, mh.remove());
    assertEquals(30, mh.remove());
    assertEquals(300, mh.remove());
    assertEquals(500, mh.remove());
  }
}
