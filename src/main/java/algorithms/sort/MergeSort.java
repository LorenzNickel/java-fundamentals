package algorithms.sort;

import java.util.Arrays;

/**
 * Static implementation of the merge sort algorithm
 *
 * @author josefdcunha
 * @see <a href="https://en.wikipedia.org/wiki/Merge_sort">Merge Sort (Wikipedia)</a>
 */
public class MergeSort {

  private MergeSort() {
  }

  /**
   * Sorts the array using merge sort algorithm
   *
   * @param <T>   the type of the array
   * @param array the array which should be sorted
   * @return the array sorted
   */
  public static <T extends Comparable<T>> T[] mergeSort(T[] array) {
    if (array.length <= 1) {
      return array;
    }
    T[] left =  Arrays.copyOfRange(array, 0, array.length / 2);
    T[] right = Arrays.copyOfRange(array, array.length / 2, array.length / 2);

    left = mergeSort(left);
    right = mergeSort(right);

    return merge(array, left, right);
  }

  public static <T extends Comparable<T>> T[] merge(T[] array, T[] left, T[] right) {

    int lIndex = 0;
    int rIndex = 0;
    int resIndex = 0;

    while (lIndex < left.length && rIndex < right.length) {
      if (left[lIndex].compareTo(right[rIndex]) > 0) {
        array[resIndex++] = right[rIndex++];
      } else {
        array[resIndex++] = left[lIndex++];
      }
    }

    if (lIndex < left.length) {
      while (lIndex < left.length) {
        array[resIndex++] = left[lIndex++];
      }
    } else {
      while (rIndex < right.length) {
        array[resIndex++] = right[rIndex++];
      }
    }

    return array;
  }
}
