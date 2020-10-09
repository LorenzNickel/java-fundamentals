import java.util.ArrayList;

/**
 * A min heap based on an array list.
 */
public class MinArrayHeap {
    ArrayList<Integer> minHeap;

    /**
     * Constructor; Creates the minHeap arrayList.
     */
    public MinArrayHeap() {
        minHeap = new ArrayList<>();
    }

    /**
     * Adds an item to the heap, while ensuring it's a MinHeap.
     * @param item - the item to be added
     */
    public void insert(int item) {
        /*
         * Understand that:
         * An element's left child: i * 2 + 1
         * An element's right child: i * 2 + 2
         * An element's parent: Math.floor((i-1) / 2)
         */

        // Add new element to the end of the array.
        minHeap.add(item);
        int lastIndex = minHeap.size() - 1;

        //Loop until new element is bigger than its parent or at root
        while (lastIndex >= 0 && minHeap.get(lastIndex) < minHeap.get((int)Math.floor((lastIndex - 1)/2))) {
            //Switch new element with parent if it is smaller than it
            int temp = minHeap.get(lastIndex);
            minHeap.set(lastIndex, minHeap.get((int) Math.floor((lastIndex - 1)/2)));
            minHeap.set((int)Math.floor((lastIndex - 1) / 2), temp);
            lastIndex = (int)Math.floor((lastIndex - 1)/2);
        }
    }

    /**
     * Removes an item to the heap, while ensuring it's a MinHeap. Returns it
     * @return int - the min value in the heap to be removed
     */
    public int remove() {
        int smallest = minHeap.get(0);

        if (minHeap.size() > 1) {
            minHeap.set(0, minHeap.get(minHeap.size() - 1));
            minHeap.remove(minHeap.size() - 1);

            //If there are only two elements, simply swap and return value.
            if (minHeap.size() == 2) {
                if (minHeap.get(0) > minHeap.get(1)) {
                    int temp = minHeap.get(0);
                    minHeap.set(0, minHeap.get(1));
                    minHeap.set(1, temp);
                }
                return smallest;
            }
            int i = 0;
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;

            //Continue swapping until the parent is smaller than both children or you reach the last level in the tree.
            while (leftChild < minHeap.size() && rightChild < minHeap.size() && (minHeap.get(i) >= minHeap.get(leftChild) || minHeap.get(i) >= minHeap.get(rightChild))) {
                //If either child of the root is smaller than it, swap the root with the child of lesser value.
                if (minHeap.get(leftChild) < minHeap.get(rightChild)) {
                    int temp = minHeap.get(i);
                    minHeap.set(i, minHeap.get(leftChild));
                    minHeap.set(leftChild, temp);
                    i = 2 * i + 1;
                }
                else {
                    int temp = minHeap.get(i);
                    minHeap.set(i, minHeap.get(rightChild));
                    minHeap.set(rightChild, temp);
                    i = 2 * i + 2;
                }
                leftChild = 2 * i + 1;
                rightChild = 2 * i + 2;
            }
        }
        else if (minHeap.size() == 1) {
            minHeap.remove(minHeap.size() - 1);
        }
        else {
            return -1;
        }
        return smallest;
    }

    /**
     * Return the minHeap arrayList
     * @return minHeap - the arrayList to be returned
     */
    public ArrayList<Integer> getMinHeap() {
        return minHeap;
    }

    /**
     * Sample tester
     * @param args
     */
    public static void main(String[] args) {
        //Sample: Expected: 10 20 25 300 30 500 29
        MinArrayHeap mh = new MinArrayHeap();
        System.out.println("Inserting 30, 300, 500, 10, 20, 25, 29...");
        mh.insert(30);
        mh.insert(300);
        mh.insert(500);
        mh.insert(10);
        mh.insert(20);
        mh.insert(25);
        mh.insert(29);

        System.out.println("Values...");
        for(int i : mh.getMinHeap()) {
            System.out.println(i);
        }
        System.out.println("Removing...");
        //Sample: Expected: 10 20 25 29 30 300 500
        while(mh.getMinHeap().size() != 0) {
            System.out.println(mh.remove());
        }
    }
}
