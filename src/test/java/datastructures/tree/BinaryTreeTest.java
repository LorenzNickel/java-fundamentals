package datastructures.tree;

import junit.framework.TestCase;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author thepn
 */
public class BinaryTreeTest extends TestCase {

    public void testIsEmpty() {
        BinaryTree<Integer> tree = new BinaryTree<>();

        assertTrue( tree.isEmpty() );

        tree.insert( 10 );
        assertFalse( tree.isEmpty() );
    }

    public void testInsertAll() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        assertTrue( tree.insertAll( 1, 2, 3, 4, 5, 6, 7 ) );
        assertFalse( tree.insertAll( 7, 8 ) );
        assertFalse( tree.insertAll( 8 ) );
        assertTrue( tree.insertAll() );
        assertEquals( tree.size(), 8 );
    }

    public void testSize() {
        BinaryTree<Integer> tree = new BinaryTree<>();

        assertEquals( 0, tree.size() );

        tree.insert( 10 );
        assertEquals( 1, tree.size() );

        tree.insert( 10 );
        assertEquals( 1, tree.size() );

        tree.insert( 5 );
        tree.insert( 15 );
        assertEquals( 3, tree.size() );

        tree.insert( 20 );
        assertEquals( 4, tree.size() );

        tree.remove( 15 );
        assertEquals( 3, tree.size() );
    }

    public void testRemoveRight() {
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.insertAll( 10, 5, 15, 20, 7, 6, 3 );

        tree.remove( 15 );
        assertEquals( 6, tree.size() );
    }

    public void testRemoveLeft() {
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.insertAll( 10, 5, 15, 20, 7, 6, 3 );

        tree.remove( 7 );
        assertEquals( 6, tree.size() );
    }

    public void testRemoveBoth() {
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.insertAll( 10, 5, 15, 20, 7, 6, 3 );

        tree.remove( 10 );
        assertEquals( 6, tree.size() );
    }

    public void testMinimum() {
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.insertAll( 10, 5, 15, 20, 7, 6, 3 );

        assertEquals( (Integer) 3, tree.minimum() );
    }

    public void testIterator() {
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.insertAll( 10, 5, 15, 20 );

        Iterator<Integer> iterator = tree.iterator();

        assertTrue( iterator.hasNext() );
        assertEquals( 5, (int) iterator.next() );

        assertTrue( iterator.hasNext() );
        assertEquals( 10, (int) iterator.next() );

        tree.insert( 12 );
        assertEquals( 15, (int) iterator.next() );
        assertEquals( 20, (int) iterator.next() );

        try {
            iterator.next();
            fail();
        } catch ( NoSuchElementException ignored ) {

        }
    }
}
