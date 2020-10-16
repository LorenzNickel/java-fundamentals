package algorithms.sort;

import java.util.Arrays;

/**
 * Static implementation of the Cocktail sort algorithm
 * @author serturx
 * @see <a href="https://en.wikipedia.org/wiki/Cocktail_shaker_sort">Cocktail Sort(Wikipedia)</a>
 */

public class CocktailSort {

  /**
   * Sorts an array with the CocktailSort algorithm
   * @param toSort List to sort
   * @param <T> Type
   */

  public static <T extends Comparable<T>> void sort(T[] toSort) {

    int startIndex = -1;
    int endIndex = toSort.length - 2;
    boolean swapped;
    T buffer;

    do {

      swapped = false;
      startIndex++;

      for (int i = startIndex; i <= endIndex; i++) {
        if (toSort[i].compareTo(toSort[i + 1]) > 0) {
          buffer = toSort[i];
          toSort[i] = toSort[i + 1];
          toSort[i + 1] = buffer;
          swapped = true;
        }
      }

      if (!swapped) {
        break;
      }

      swapped = false;

      endIndex--;

      for (int i = endIndex; i >= startIndex; i--) {
        if (toSort[i].compareTo(toSort[i + 1]) > 0) {
          buffer = toSort[i];
          toSort[i] = toSort[i + 1];
          toSort[i + 1] = buffer;
          swapped = true;
        }
      }

    } while (swapped);


  }
}