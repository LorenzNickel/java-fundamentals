package algorithms.sort;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import junit.framework.TestCase;

/**
 * @author josefdcunha
 */
public class MergeSortTest extends TestCase {

  public void testMergeSortArrayOdd() {
    final Short[] arrayA = new Short[]{3, 2, 5, 4, 1};
    Short[] res = MergeSort.mergeSort(arrayA);
    Arrays.sort(arrayA);
    for (int i = 0; i < arrayA.length; i++) {
      assertEquals(res[i], arrayA[i]);
    }
  }
  public void testMergeSortArrayEven() {
    final Short[] arrayA = new Short[]{3, 6, 6, 2, 5, 4, 1, 2};
    Short[] res = MergeSort.mergeSort(arrayA);
    Arrays.sort(arrayA);
    for (int i = 0; i < arrayA.length; i++) {
      assertEquals(res[i], arrayA[i]);
    }
  }

  public void testMergeSortEdgeCases() {
    final String[] arrayA = new String[0];
    MergeSort.mergeSort(arrayA);

    final Float[] arrayB = new Float[]{1.0f};
    MergeSort.mergeSort(arrayB);
  }

}
