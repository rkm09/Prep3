package leetdaily.easy;

import common.TreeNode;

public class BalancedBinaryTree110 {
    public static void main(String[] args) {
        BalancedBinaryTree110 bn = new BalancedBinaryTree110();
        TreeNode right1 = new TreeNode(15);
        TreeNode right2 = new TreeNode(7);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20, right1, right2);
        TreeNode root = new TreeNode(3, left, right);
        System.out.println(bn.isBalanced(root));
    }

//    top down recursion; time: O(nlogn), space: O(n) 
    public boolean isBalanced1(TreeNode root) {
        if(root == null) return true;
        return (Math.abs(height(root.left) - height(root.right)) < 2)
                && isBalanced(root.left)
                && isBalanced(root.right);
    }
    private int height(TreeNode node) {
        if(node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

//    bottom up recursion; time: O(n), space: O(n)
    public boolean isBalanced(TreeNode root) {
        return treeHelper(root).isBalanced;
    }
    private TreeInfo treeHelper(TreeNode node) {
        if(node == null) return new TreeInfo(-1, true);

//        check the subtree to see if they are balanced
        TreeInfo left = treeHelper(node.left);
        if(!left.isBalanced) return new TreeInfo(-1, false);
        TreeInfo right = treeHelper(node.right);
        if(!right.isBalanced) return new TreeInfo(-1, false);

//      Use the height obtained from recursive calls to check whether the current node is also balanced
        if(Math.abs(left.height - right.height) < 2)
            return new TreeInfo(Math.max(left.height, right.height) + 1, true);

        return new TreeInfo(-1, false);
    }

//    utility class to store info from recursive calls
    final class TreeInfo {
        public final int height;
        public final boolean isBalanced;
        public TreeInfo(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }


}

/*
Given a binary tree, determine if it is
height-balanced
Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: true
Example 2:
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false
Example 3:
Input: root = []
Output: true

Constraints:
The number of nodes in the tree is in the range [0, 5000].
-104 <= Node.val <= 104
 */
