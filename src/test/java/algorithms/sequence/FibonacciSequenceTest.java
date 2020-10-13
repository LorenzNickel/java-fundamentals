package algorithms.sequence;

import junit.framework.TestCase;

/**
 * @author josefdcunha
 */
public class FibonacciSequenceTest extends TestCase {

  public void testInitialValues() {
    assertEquals(0, FibonacciSequence.fibonacci(0));
    assertEquals(1, FibonacciSequence.fibonacci(1));
  }

  public void testInvalidValues() {
    assertEquals(-1 , FibonacciSequence.fibonacci(-1));
    assertEquals(-1, FibonacciSequence.fibonacci(-3));
  }

  public void testSequence() {
    assertEquals(1, FibonacciSequence.fibonacci(2));
    assertEquals(3, FibonacciSequence.fibonacci(4));
    assertEquals(8, FibonacciSequence.fibonacci(6));
    assertEquals(21, FibonacciSequence.fibonacci(8));
  }
}
