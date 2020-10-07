package algorithms.sort;

/**
 * @author Ninju
 */
public class GnomeSort {

  /**
   * Sorts the array using gnome sort algorithm
   *
   * @param array
   * @param <T>
   */
  public static <T extends Comparable<T>> void gnomeSort(T[] array){
    int index = 0;

    while (index < array.length) {
      if (index == 0)
        index++;
      if (array[index].compareTo(array[index - 1]) >= 0)
        index++;
      else {
        T temp;
        temp = array[index];
        array[index] = array[index - 1];
        array[index - 1] = temp;
        index--;
      }
    }
  }

}
