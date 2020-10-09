package algorithms.sort;

import algorithms.random.ListShuffle;

/**
 * @author thepn
 */
public class BogoSort {

  /**
   * Sorts the array using the bogosort algorithm (Array will be shuffled randomly and if it is
   * sorted, the algorithm will finish)
   *
   * @param array
   * @param <T>
   */
  public static <T extends Comparable<T>> void bogoSort(T[] array) {
    while (!isSortedAsc(array)) {
      // shuffle array by using the fisher yates algorithm
      ListShuffle.fisherYatesShuffle(array);
    }
  }

  /**
   * @param array
   * @param <T>
   * @return true when the input array is sorted ascending
   */
  private static <T extends Comparable<T>> boolean isSortedAsc(T[] array) {
    // test if array is now sorted
    for (int i = 1; i < array.length; i++) {
      if (array[i - 1].compareTo(array[i]) > 0) {
        return false;
      }
    }

    return true;
  }
}
