package top150.trees;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class InvertBinaryTree226 {
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(1);
        TreeNode left2 = new TreeNode(3);
        TreeNode right1 = new TreeNode(6);
        TreeNode right2 = new TreeNode(9);
        TreeNode left = new TreeNode(2, left1, left2);
        TreeNode right = new TreeNode(7, right1, right2);
        TreeNode root = new TreeNode(4, left, right);
        System.out.println(invertTree(root).val);
    }

//    recursion; time: O(n), space: O(n)
    public static TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

//    iterative; time: O(n), space: O(n)
    public static TreeNode invertTree1(TreeNode root) {
        if(root == null) return null;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            if(current.left != null) queue.offer(current.left);
            if(current.right != null) queue.offer(current.right);
        }
        return root;
    }
}

/*
Given the root of a binary tree, invert the tree, and return its root.
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
Example 2:
Input: root = [2,1,3]
Output: [2,3,1]
Example 3:
Input: root = []
Output: []

Constraints:
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 */
