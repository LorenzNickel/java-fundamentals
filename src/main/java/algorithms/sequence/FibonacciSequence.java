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
  public static int fibonacci(int n) {
    if (n <= 0) {
      return -1;
    }
    if (n == 1 || n == 2) {
      return n - 1;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
  }
}
