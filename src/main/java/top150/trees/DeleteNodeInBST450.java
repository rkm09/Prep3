package top150.trees;

import common.TreeNode;

public class DeleteNodeInBST450 {
    public static void main(String[] args) {
        TreeNode right2 = new TreeNode(7);
        TreeNode right = new TreeNode(6, null, right2);
        TreeNode left1 = new TreeNode(2);
        TreeNode left2 = new TreeNode(4);
        TreeNode left = new TreeNode(3, left1, left2);
        TreeNode root = new TreeNode(5, left, right);
        TreeNode node = deleteNode(root, 3);
    }

//    recursion; time: O(H), space: O(H) [H is the height of the tree, O(logN) for a balanced tree]
    public static TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
//        if val is greater, then delete from right subTree
        if(root.val < key)
            root.right = deleteNode(root.right, key);
//        else if val is smaller, delete from left subTree
        else if(root.val > key)
            root.left = deleteNode(root.left, key);
        else {
//            else if val is equal to target, delete it
            if(root.left == null && root.right == null)
                root = null;
            else if(root.right != null) {
//                if right subTree exists, replace with successor
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else if(root.left != null) {
//                or else if left subTree exists, replace with predecessor
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

//    first right and then left as much as possible
    private static Integer successor(TreeNode node) {
        node = node.right;
        while(node.left != null)
            node = node.left;
        return node.val;
    }

//    first left and then right as much as possible
    private static Integer predecessor(TreeNode node) {
        node = node.left;
        while(node.right != null)
            node = node.right;
        return node.val;
    }
}

/*
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
Basically, the deletion can be divided into two stages:
Search for a node to remove.
If the node is found, delete the node.
Example 1:
Input: root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]
Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
Example 2:
Input: root = [5,3,6,2,4,null,7], key = 0
Output: [5,3,6,2,4,null,7]
Explanation: The tree does not contain a node with value = 0.
Example 3:
Input: root = [], key = 0
Output: []

Constraints:
The number of nodes in the tree is in the range [0, 104].
-105 <= Node.val <= 105
Each node has a unique value.
root is a valid binary search tree.
-105 <= key <= 105

Follow up: Could you solve it with time complexity O(height of tree)?
 */