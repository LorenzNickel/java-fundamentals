package problems.checkSubTree;

import junit.framework.TestCase;
import problems.checkSubTree.impl.Recursive;
import problems.checkSubTree.resources.TreeNode;

public class CheckSubTreeTest extends TestCase {
  // function to create a tree for testing
  private TreeNode createFromArray(int[] values) {
    int n = values.length;
    if (n == 0) {
      return new TreeNode();
    }
    TreeNode[] nodes = new TreeNode[n];
    for (int i = 0; i < n; i++) {
      if (values[i] == 0) {
        nodes[i] = null;
      }
      else {
        nodes[i] = new TreeNode(values[i]);
      }
    }

    for (int i = 0; i < n; i++) {
      if (nodes[i] == null) {
        continue;
      }
      if (i*2 + 1 < n) {
        nodes[i].left = nodes[i*2 + 1];
      }
      if (i*2 + 2 < n) {
        nodes[i].right = nodes[i*2 + 2];
      }
    }
    return nodes[0];
  }
  private static final CheckSubTree[] implementations = new CheckSubTree[]{new Recursive()};
  private static TreeNode[] trees = new TreeNode[3];
  private static TreeNode[] subTrees = new TreeNode[3];


  public void testSolutions() {
    // testcase 1
    trees[0] = createFromArray(new int[]{1,1});
    subTrees[0] = createFromArray(new int[]{1});
    // testcase 2
    trees[1] = createFromArray(new int[]{3,4,5,1,2});
    subTrees[1] = createFromArray(new int[]{4,1,2});
    // testcase 3
    trees[2] = createFromArray(new int[]{3,4,5,1,2,0,0,0,1});
    subTrees[2] = createFromArray(new int[]{4,1,2});

    for (CheckSubTree impl : implementations) {
      assertTrue(impl.isSubTree(trees[0], subTrees[0]));
      assertTrue(impl.isSubTree(trees[1], subTrees[1]));
      assertFalse(impl.isSubTree(trees[2], subTrees[2]));
    }
  }

  public void testEmptyTrees() {
    for (CheckSubTree impl : implementations) {
      assertTrue(impl.isSubTree(null, null));
      assertFalse(impl.isSubTree(new TreeNode(1), null));
      assertFalse(impl.isSubTree(null, new TreeNode(1)));
    }
  }
}
