package algorithms.math;

import junit.framework.TestCase;

/**
 * @author josefdcunha
 */
public class EuclideanTest extends TestCase {
  public void testSomeValues() {
    assertEquals(12, Euclidean.gcd(348,156));
    assertEquals(100, Euclidean.gcd(500,100));
    assertEquals(15, Euclidean.gcd(3690,285));
    assertEquals(12, Euclidean.gcd(492,12));
  }

}
