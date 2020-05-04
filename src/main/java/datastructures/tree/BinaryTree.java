package datastructures.tree;


import java.io.Serializable;
import java.util.Iterator;


/**
 * @author thepn
 */
public class BinaryTree<T extends Comparable<T>> implements Iterable<T>, Serializable {

    private static final long serialVersionUID = 678497165651219702L;

    private BinaryTree<T> left;
    private BinaryTree<T> right;

    private T value;

    public BinaryTree() {
        this.value = null;
        this.left = null;
        this.right = null;
    }

    public BinaryTree( T value ) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    /**
     * Inserts an element in the proper subtree. Duplicates will not be inserted.
     *
     * @param value
     * @return true if an element was inserted.
     */
    public boolean insert( T value ) {
        if ( this.value == null ) {
            this.value = value;
            return true;
        }

        if ( this.value.equals( value ) ) {
            return false;
        }

        if ( this.value.compareTo( value ) > 0 ) {
            if ( this.left == null ) {
                left = new BinaryTree<>( value );
            } else {
                return this.left.insert( value );
            }
        } else {
            if ( this.right == null ) {
                right = new BinaryTree<>( value );
            } else {
                return this.right.insert( value );
            }
        }

        return true;
    }

    /**
     * Inserts an array of elements in the tree using the insert(T) method
     *
     * @param values array of elements which will be inserted
     * @return true if all elements have been inserted, false if at least one element was not inserted
     * successfully
     */
    public boolean insertAll(T... values) {
        boolean result = true;
        for (T value : values) {
            result &= this.insert(value);
        }
        return result;
    }

    /**
     * Removes the element value from the tree
     *
     * @param value
     * @return the deleted element
     */
    public T remove( T value ) {
        if ( value == null ) {
            return null;
        }

        int compare = this.value.compareTo( value );

        if ( compare > 0 ) {
            this.value = left.remove( value );
        } else if ( compare < 0 ) {
            this.value = right.remove( value );
        } else {
            if ( left == null && right == null ) {
                return null;
            } else if ( left == null ) {
                T rightValue = right.value;
                right.value = null;
                return rightValue;
            } else if ( right == null ) {
                T leftValue = left.value;
                left.value = null;
                return leftValue;
            } else {
                this.value = right.minimum();
                right.value = right.remove( this.value );
            }
        }

        return value;
    }

    /**
     * @return the minimum value of the tree
     */
    public T minimum() {
        if ( left != null ) {
            return left.minimum();
        }

        return value;
    }

    /**
     * @return true if the tree is empty
     */
    public boolean isEmpty() {
        return this.value == null;
    }

    /**
     * @return the size of the binary tree
     */
    public int size() {
        if ( isEmpty() ) {
            return 0;
        }

        int left = 0;
        int right = 0;

        if ( this.left != null ) {
            left = this.left.size();
        }

        if ( this.right != null ) {
            right = this.right.size();
        }

        return left + right + 1;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "left=" + left +
                ", right=" + right +
                ", value=" + value +
                '}';
    }

    public Iterator<T> iterator() {
        return null;
    }
}
