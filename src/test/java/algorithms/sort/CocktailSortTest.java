package algorithms.sort;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Collections;
import junit.framework.TestCase;

public class CocktailSortTest extends TestCase {

  public void testCocktailSort() {
    final Integer[] array1 = {1, 3, 2, 7, 5, 9, 4, 6, 8};
    final Integer[] array2 = {5, -5, 4, 4, -7, 12, 21, 6, 14, -23, 16};
    final Integer[] array3 = Arrays.copyOf(array1, array1.length);
    final Integer[] array4 = Arrays.copyOf(array2, array2.length);

    CocktailSort.sort(array1);
    CocktailSort.sort(array2);
    Arrays.sort(array3);
    Arrays.sort(array4);

    assertArrayEquals(array3, array1);
    assertArrayEquals(array4, array2);


  }

  public void testCocktailSortEdgeCases() {
    final Integer[] array1 = {1};
    final Integer[] array2 = new Integer[0];

    CocktailSort.sort(array1);

    CocktailSort.sort(array2);

    assertArrayEquals(new Integer[] {1}, array1);
    assertArrayEquals(new Integer[] {}, array2);
  }
}
