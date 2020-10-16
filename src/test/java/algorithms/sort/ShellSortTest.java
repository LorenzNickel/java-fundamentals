package algorithms.sort;

import junit.framework.TestCase;

/**
 * Static implementation of the shell sort algorithm
 *
 * @author AtharvaKamble
 * @see <a href="https://www.geeksforgeeks.org/shellsort/">Shell Sort (GeeksforGeeks)</a>
 */
public class ShellSortTest extends TestCase {

  public void testShellSort() {
    Short[] array = new Short[]{3, 2, 5, 4, 1};

    ShellSort.shellSort(array);

    for (int i = 0; i < 5; i++) {
      assertEquals(i + 1, (short) array[i]);
    }
  }

}
