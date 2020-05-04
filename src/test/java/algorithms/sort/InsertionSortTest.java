package algorithms.sort;

import junit.framework.TestCase;

/**
 * @author thepn
 */
public class InsertionSortTest extends TestCase {

    public void testInsertionSort() {
        Short[] array = new Short[]{ 3, 2, 5, 4, 1 };

        InsertionSort.insertionSort( array );

        for ( int i = 0; i < 5; i++ ) {
            assertEquals( i + 1, (short) array[i] );
        }
    }

}
