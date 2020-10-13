package algorithms.sort;

import junit.framework.TestCase;

/**
 * Static implementation of the selection sort algorithm
 *
 * @author AtharvaKamble
 * @see <a href="https://www.geeksforgeeks.org/selection-sort/">Selection Sort (GeeksforGeeks)</a>
 */
public class SelectionSortTest extends TestCase {

  public void testSelectionSort() {
    Short[] array = new Short[]{3, 2, 5, 4, 1};

    SelectionSort.selectionSort(array);

    for (int i = 0; i < 5; i++) {
      assertEquals(i + 1, (short) array[i]);
    }
  }

}
