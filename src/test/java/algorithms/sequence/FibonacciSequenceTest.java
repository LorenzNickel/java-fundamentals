package algorithms.sequence;

import junit.framework.TestCase;

/**
 * @author josefdcunha
 */
public class FibonacciSequenceTest extends TestCase {

  public void testInitialValuesRecursive() {
    assertEquals(0, FibonacciSequence.recursive(0));
    assertEquals(1, FibonacciSequence.recursive(1));
  }

  public void testInvalidValuesRecursive() {
    assertEquals(-1 , FibonacciSequence.recursive(-1));
    assertEquals(-1, FibonacciSequence.recursive(-3));
  }

  public void testSequenceRecursive() {
    assertEquals(1, FibonacciSequence.recursive(2));
    assertEquals(3, FibonacciSequence.recursive(4));
    assertEquals(8, FibonacciSequence.recursive(6));
    assertEquals(21, FibonacciSequence.recursive(8));
  }

  public void testInitialValuesIterative() {
    assertEquals(0, FibonacciSequence.iterative(0));
    assertEquals(1, FibonacciSequence.iterative(1));
  }

  public void testInvalidValuesIterative() {
    assertEquals(-1 , FibonacciSequence.iterative(-1));
    assertEquals(-1, FibonacciSequence.iterative(-3));
  }

  public void testSequenceIterative() {
    assertEquals(1, FibonacciSequence.iterative(2));
    assertEquals(3, FibonacciSequence.iterative(4));
    assertEquals(8, FibonacciSequence.iterative(6));
    assertEquals(21, FibonacciSequence.iterative(8));
  }
}
