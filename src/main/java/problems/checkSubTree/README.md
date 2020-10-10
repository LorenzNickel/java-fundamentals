# Check Sub-Tree
## Problem Statement
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
### Example
    s:    3        t:     4
         / \             / \
        4   5           1   2
       / \
      1   2
  We have to check is t is a subtree of s which is true in this case hence our function should return true.
  
## Solution
Recursively traverse through tree 's' and if value of node 'n' is the same as the root of 't', check if the sub-tree with 'n' as the root is the same as the tree 't'.

Time complexity of recursively traversing the tree 't' is O(n) if 'n' be the total number nodes in it.

Time complexity of recursively traversing the tree 's' is O(m) is 'm' be the total number nodes in it.

In the worst case the isSubTree() function will be called at every node, hence time complexity of this implementation is `O(n*m)`.

