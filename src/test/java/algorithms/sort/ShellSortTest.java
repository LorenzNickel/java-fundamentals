package algorithms.sort;

import junit.framework.TestCase;

/**
 * Static implementation of the shell sort algorithm
 *
 * @author AtharvaKamble
 * @see <a href="https://www.geeksforgeeks.org/shellsort/">Shell Sort (GeeksforGeeks)</a>
 * @see <a href="http://www.asciitable.com/">ASCII Table for reference</a>
 */
public class ShellSortTest extends TestCase {

  public void testShellSortShort() {
    Short[] array = new Short[]{3, 2, 5, 4, 1};

    ShellSort.shellSort(array);

    for (int i = 0; i < 5; i++) {
      assertEquals(i + 1, (short) array[i]);
    }
  }

  public void testShellSortString() {
  	String[] array = new String[]{"e", "d", "c", "b", "a"};

    ShellSort.shellSort(array);

    int asciiIndex = 97;
    for (int i = 0; i < 5; i++) {
      assertEquals(asciiIndex++, (int) array[i].charAt(0));
    }
  }

}
