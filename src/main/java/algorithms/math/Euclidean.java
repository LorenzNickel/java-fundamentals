package algorithms.math;

/**
 * Simple implementation of the Euclidean algorithm (recursive)
 *
 * @author josefdcunha
 * @see <a href="https://en.wikipedia.org/wiki/Euclidean_algorithm">Euclidean algorithm (Wikipedia)</a>
 */
public class Euclidean {

  /**
   * Return the greatest common divisor by Euclidean algorithm (recursive)
   * @param a number
   * @param b number
   * @return greatest common divisor
   */
  public static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    } else {
      return gcd(b, a % b);
    }
  }
}
