package leetdaily.medium;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BottomLeftTreeValue513 {
    private int bottomLeft;
    private int maxDepth;
    public static void main(String[] args) {
        BottomLeftTreeValue513 bot = new BottomLeftTreeValue513();
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(2, left, right);
        System.out.println(bot.findBottomLeftValue(root));
    }

//    dfs(recursion); time: O(n), space: O(n) [faster]
    public  int findBottomLeftValue(TreeNode root) {
        maxDepth = -1;
        dfs(root, 0);
        return bottomLeft;
    }
    private void dfs(TreeNode current, int depth) {
        if(current == null) return;
        if(depth > maxDepth) {
            maxDepth = depth;
            bottomLeft = current.val;
        }
        dfs(current.left, depth + 1);
        dfs(current.right, depth + 1);
    }

//    bfs (iteration); time: O(n), space: O(n)
    public  int findBottomLeftValue1(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        TreeNode current = root;
        queue.offer(current);
        while(!queue.isEmpty()) {
            current = queue.poll();
            if(current.right != null) {
                queue.offer(current.right);
            }
            if(current.left != null) {
                queue.offer(current.left);
            }
        }
        return current.val;
    }
}

/*
Given the root of a binary tree, return the leftmost value in the last row of the tree.
Input: root = [2,1,3]
Output: 1
Example 2:
Input: root = [1,2,3,4,null,5,6,null,null,7]
Output: 7
Constraints:
The number of nodes in the tree is in the range [1, 104].
-231 <= Node.val <= 231 - 1
 */