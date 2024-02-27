package leetdaily.easy;

import common.TreeNode;

public class DiameterOfABinaryTree243 {
    private int diameter;
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(4);
        TreeNode left2 = new TreeNode(5);
        TreeNode left = new TreeNode(2, left1, left2);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(1, left, right);
        DiameterOfABinaryTree243 dia = new DiameterOfABinaryTree243();
        System.out.println(dia.diameterOfBinaryTree(root));
    }

//    dfs; time: O(n), space: O(n)
    public int diameterOfBinaryTree(TreeNode root) {
        longestPath(root);
        return diameter;
    }
    private int longestPath(TreeNode node) {
        if(node == null) return 0;
//        recursively find the longest path in both left and right child
        int leftPath = longestPath(node.left);
        int rightPath = longestPath(node.right);
//        update the diameter if left + right path is larger
        diameter = Math.max(diameter, leftPath + rightPath);
//        return the longest one between left and right path, also add 1 for the path connecting node to its parent
        return Math.max(leftPath, rightPath) + 1;
    }
}

/*
Given the root of a binary tree, return the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
The length of a path between two nodes is represented by the number of edges between them.
Example 1:
Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
Example 2:
Input: root = [1,2]
Output: 1
Constraints:
The number of nodes in the tree is in the range [1, 104].
-100 <= Node.val <= 100

 */