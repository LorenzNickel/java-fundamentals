package algorithms.sort;

/**
 * Static implementation of the shell sort algorithm
 *
 * @author AtharvaKamble
 * @see <a href="https://www.geeksforgeeks.org/shellsort/">Shell Sort (GeeksforGeeks)</a>
 */
public class ShellSort {

  private ShellSort() { }

  /**
   * Sorts the array using selection sort algorithm
   *
   * @param <T>
   * @param input
   */
  public static <T extends Comparable<T>> T[] shellSort(T[] input) {
    int len = input.length;
    T temp;

    if (len == 0 || len == 1) {
      return input;
    } else if (len == 2) {
      for (int i = 0; i < len; i++) {
        T t = input[i];
        input[i] = input[len - 1];
        input[len - 1] = t;
      }
      return input;
    }

    for (int outer = len / 2; outer > 0; outer /= 2) {
      for (int i = outer; i < len; i++) {
        temp = input[i];

        int j;
        for (j = i; j >= outer && (temp.compareTo(input[j - outer]) >= 0 ? false : true); j -= outer) {
          input[j] = input[j - outer];
        }

        input[j] = temp;
      }
    }
    return input;
  }

}
