package leetdaily.medium;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class EvenOdd1609 {
    private static List<Integer> prev;
    public static void main(String[] args) {
        TreeNode left11 = new TreeNode(3);
        TreeNode left12 = new TreeNode(3);
        TreeNode right1 = new TreeNode(7);
        TreeNode left = new TreeNode(4, left11, left12);
        TreeNode right = new TreeNode(2, right1, null);
        TreeNode root = new TreeNode(5, left, right);
        System.out.println(isEvenOddTree(root));
    }

    //    bfs; time: O(n), space: O(n) [faster]
    public static boolean isEvenOddTree(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean isEven = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            int prev = Integer.MAX_VALUE;
            if(isEven)
                prev = Integer.MIN_VALUE;
            while(size > 0) {
                TreeNode current = queue.poll();
                if((isEven && (current.val <= prev || current.val % 2 == 0)) ||
                        !isEven && (current.val >= prev || current.val % 2 != 0))
                    return false;
                prev = current.val;

                if(current.left != null)
                    queue.offer(current.left);
                if(current.right != null)
                    queue.offer(current.right);

                size--;
            }
            isEven = !isEven;
        }
        return true;
    }

//    dfs; time: O(n), space: O(n)
    public static boolean isEvenOddTree1(TreeNode root) {
        prev = new ArrayList<>();
        return dfs(root, 0);
    }
    private static boolean dfs(TreeNode current, int level) {
        if(current == null) return true;
        if(current.val % 2 == level % 2) return false;
        if(prev.size() == level) prev.add(0);
        if(prev.get(level) != 0 &&
                ((level % 2 == 0 && current.val <= prev.get(level))
                || (level % 2 != 0 && current.val >= prev.get(level)))) {
            return false;
        }
        prev.set(level, current.val);
        return dfs(current.left, level + 1) && dfs(current.right, level + 1);
    }


}

/*
A binary tree is named Even-Odd if it meets the following conditions:
The root of the binary tree is at level index 0, its children are at level index 1, their children are at level index 2, etc.
For every even-indexed level, all nodes at the level have odd integer values in strictly increasing order (from left to right).
For every odd-indexed level, all nodes at the level have even integer values in strictly decreasing order (from left to right).
Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.
Example 1:
Input: root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
Output: true
Explanation: The node values on each level are:
Level 0: [1]
Level 1: [10,4]
Level 2: [3,7,9]
Level 3: [12,8,6,2]
Since levels 0 and 2 are all odd and increasing and levels 1 and 3 are all even and decreasing, the tree is Even-Odd.
Example 2:
Input: root = [5,4,2,3,3,7]
Output: false
Explanation: The node values on each level are:
Level 0: [5]
Level 1: [4,2]
Level 2: [3,3,7]
Node values in level 2 must be in strictly increasing order, so the tree is not Even-Odd.
Example 3:
Input: root = [5,9,1,3,5,7]
Output: false
Explanation: Node values in the level 1 should be even integers.

Constraints:
The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 106
 */