package top150.trees;

import common.Pair;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumDepth104 {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(9);
        TreeNode right1 = new TreeNode(15);
        TreeNode right2 = new TreeNode(7);
        TreeNode right = new TreeNode(20, right1, right2);
        TreeNode root = new TreeNode(3, left, right);
        System.out.println(maxDepth(root));
    }

//   recursion dfs; time: O(n), space: O(n)
    public static int maxDepth(TreeNode root) {
        return dfs(root);
    }
    private static int dfs(TreeNode node) {
        if(node == null) return 0;
        int leftHeight = dfs(node.left);
        int rightHeight = dfs(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

//    iteration; time: O(n), space: O(n)
    public static int maxDepth1(TreeNode root) {
        if(root == null) return 0;
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, 1));
        int maxDepth = 0;
        while(!stack.isEmpty()) {
            Pair<TreeNode, Integer> nodeInfo = stack.pop();
            TreeNode node = nodeInfo.getKey();
            int depth = nodeInfo.getValue();
            maxDepth = Math.max(maxDepth, depth);
            if(node.right != null)
                stack.push(new Pair<>(node.right, depth + 1));
            if(node.left != null)
                stack.push(new Pair<>(node.left, depth + 1));
        }
        return maxDepth;
    }
}


/*
Given the root of a binary tree, return its maximum depth.
A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 3
Example 2:
Input: root = [1,null,2]
Output: 2

Constraints:
The number of nodes in the tree is in the range [0, 104].
-100 <= Node.val <= 100
 */