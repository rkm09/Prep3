package top150.trees;

import common.TreeNode;

public class InsertIntoBinaryTree701 {
    public static void main(String[] args) {
        TreeNode right = new TreeNode(7);
        TreeNode left1 = new TreeNode(3);
        TreeNode left2 = new TreeNode(3);
        TreeNode left = new TreeNode(2, left1, left2);
        TreeNode root = new TreeNode(4, left, right);
        TreeNode node = insertIntoBST(root, 5);
    }

//    recursion stack; time: O(H) [O(N) in the worst case & O(logN) in the average case], space: O(H) [H is the height of the tree]
    public static TreeNode insertIntoBST(TreeNode root, int val) {
//        insert a new node as a child of a leaf
       if(root == null) return new TreeNode(val);
//       insert into left subTree
       if(val < root.val)
           root.left = insertIntoBST(root.left, val);
//      insert into right subTree
        else
            root.right = insertIntoBST(root.right, val);
        return root;
    }

//    iteration; time: O(H) [O(N) in the worst case & O(logN) in the average case], space: O(1)
    public static TreeNode insertIntoBST1(TreeNode root, int val) {
        TreeNode node = root;
        while(node != null) {
//            insert into the right subTree
            if(val > node.val) {
//                insert right now
                if(node.right == null) {
                    node.right = new TreeNode(val);
                    return root;
                } else {
//                  or insert later
                    node = node.right;
                }
            }
//          or insert into the left subTree
            else {
//                insert right now
                if(node.left == null) {
                    node.left = new TreeNode(val);
                    return root;
                } else {
//                  or insert later
                    node = node.left;
                }
            }
        }
        return new TreeNode(val);
    }

}

/*
BST advantage: search is O(logN);
You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
Example 1:
Input: root = [4,2,7,1,3], val = 5
Output: [4,2,7,1,3,5]
Explanation: Another accepted tree is:
Example 2:
Input: root = [40,20,60,10,30,50,70], val = 25
Output: [40,20,60,10,30,50,70,null,null,25]
Example 3:
Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
Output: [4,2,7,1,3,5]

Constraints:
The number of nodes in the tree will be in the range [0, 104].
-108 <= Node.val <= 108
All the values Node.val are unique.
-108 <= val <= 108
It's guaranteed that val does not exist in the original BST.
 */