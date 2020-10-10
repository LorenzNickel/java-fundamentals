package algorithms.sort;

/**
 * Static implementation of the insertion sort algorithm
 *
 * @author thepn
 */
public class InsertionSort {

  /**
   * Sorts the array using insertion sort algorithm
   *
   * @param <T>
   * @param input
   */
  public static <T extends Comparable<T>> void insertionSort(T[] input) {
    for (int i = 1; i < input.length; i++) {
      T temp = input[i];
      int j = i - 1;

      while (j >= 0 && input[j].compareTo(temp) > 0) {
        input[j + 1] = input[j];
        j--;
      }

      input[j + 1] = temp;
    }

  }

}
