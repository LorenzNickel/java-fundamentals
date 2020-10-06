package problems.checkSubTree.impl;


import problems.checkSubTree.CheckSubTree;
import problems.checkSubTree.resources.TreeNode;

public class Recursive implements CheckSubTree {
  @Override
  public boolean isSubTree(TreeNode s, TreeNode t) {
    if (s == null && t == null) {
      return true;
    }
    if ((s != null) && (t != null)) {
      if (s.val == t.val) {
        return checkSubTree(s, t) || isSubTree(s.left, t) || isSubTree(s.right, t);
      } else {
        return isSubTree(s.left, t) || isSubTree(s.right, t);
      }
    }
    return false;
  }

  private boolean checkSubTree(TreeNode s, TreeNode t) {
    if ((s == null) && (t == null)) {
      return true;
    }
    if ((s == null) || (t == null)) {
      return false;
    }
    if (s.val != t.val) {
      return false;
    }
    return checkSubTree(s.left, t.left) && checkSubTree(s.right, t.right);
  }
}
