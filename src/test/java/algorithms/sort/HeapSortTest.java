package algorithms.sort;

import java.util.*;
import junit.framework.TestCase;

/*
Author: @iamvs-2002
*/

public class HeapSortTest extends TestCase{
    public void testHeapSort() {
        Short[] array = new Short[]{1, 10, 16, 19, 3, 5};
    
        HeapSort.heapSort(array);
    
        for (int i = 0; i < array.length; i++) {
          assertEquals(i + 1, (short) array[i]); //[1, 3, 5, 10, 16, 19]
        }
    }
}