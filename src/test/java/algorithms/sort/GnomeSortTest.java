package algorithms.sort;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import junit.framework.TestCase;


/**
 * @author Ninju
 */
public class GnomeSortTest extends TestCase {

  public void testGnomeSort(){
    final Short[] array = new Short[]{3, 2, 5, 4, 1};
    GnomeSort.gnomeSort(array);
    for (int i = 0; i < 5; i++) {
      assertEquals(i + 1, (short) array[i]);
    }
  }

  public void testSameResultAsInsertion(){
    final Short[] arrayA = new Short[]{1, 3, 5, 3, 5, 3, 2, 3, 8, -1, -12};
    final Short[] arrayB = Arrays.copyOf(arrayA, arrayA.length);
    GnomeSort.gnomeSort(arrayA);
    InsertionSort.insertionSort(arrayB);
    assertArrayEquals(arrayA, arrayB);
  }

  public void testGnomeSortEdgeCases() {
    final String[] arrayA = new String[0];
    GnomeSort.gnomeSort(arrayA);

    final Float[] arrayB = new Float[]{1.0f};
    GnomeSort.gnomeSort(arrayB);
  }

}
