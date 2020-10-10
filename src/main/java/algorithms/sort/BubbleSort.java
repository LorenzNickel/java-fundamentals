package algorithms.sort;

/**
 * Static implementation of the bubble sort algorithm
 *
 * @author Lorenz Nickel
 * @see <a href="https://en.wikipedia.org/wiki/Bubble_sort">Bubble Sort (Wikipedia)</a>
 */
public class BubbleSort {

  /**
   * Sorts the array using bubble sort algorithm
   *
   * @param <T>   the type of the array
   * @param array the array which should be sorted
   */
  public static <T extends Comparable<T>> void bubbleSort(T[] array) {
    T temp;
    for (int i = 0; i < array.length - 1; i++) {
      for (int j = 1; j < array.length - i; j++) {
        if (array[j - 1].compareTo(array[j]) > 0) {
          temp = array[j - 1];
          array[j - 1] = array[j];
          array[j] = temp;
        }
      }
    }
  }

}
