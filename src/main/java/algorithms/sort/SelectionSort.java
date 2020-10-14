package algorithms.sort;

/**
 * Static implementation of the selection sort algorithm
 *
 * @author AtharvaKamble
 * @see <a href="https://www.geeksforgeeks.org/selection-sort/">Selection Sort (GeeksforGeeks)</a>
 */
public class SelectionSort {

  private SelectionSort() { }

  /**
   * Sorts the array using selection sort algorithm
   *
   * @param <T>
   * @param input
   */
  public static <T extends Comparable<T>> void selectionSort(T[] input) {
    int minimum;
    for (int i = 0; i < input.length - 1; i++) {
      minimum = i;
      for (int j = i + 1; j < input.length; j++) {
        if (input[j] < input[minimum]){
          minimum = j;
        }
      }
      T temp = input[i];
      input[i] = input[minimum];
      input[minimum] = temp;
    }
  }

}
