package algorithms.sequence;

/**
 * Simple implementation of the Fibonacci Sequence algorithm
 *
 * @author josefdcunha
 * @see <a href="https://en.wikipedia.org/wiki/Fibonacci_number">Fibonacci Sequence (Wikipedia)</a>
 */
public class FibonacciSequence {

  /**
   * Return the number in n position in Fibonacci Sequence (recursive)
   * @param n the position in Fibonacci Sequence
   * @return the number in postion n, if n is 0 or negative will return -1
   */
  public static int recursive(int n) {
    if (n < 0) {
      return -1;
    }
    if (n <= 1) {
      return n;
    }
    return recursive(n - 1) + recursive(n - 2);
  }

  /**
   * Return the number in n position in Fibonacci Sequence (iterative)
   * @param n the position in Fibonacci Sequence
   * @return the number in postion n, if n is 0 or negative will return -1
   */
  public static int iterative(int n) {
    int preN = 0, currN = 1, auxN;
    if (n < 0) {
      return -1;
    }
    if (n <= 1) {
      return n;
    }
    for (int i = 1; i < n; i++) {
      auxN = preN;
      preN = currN;
      currN = auxN + preN;
    }
    return currN;
  }
}
