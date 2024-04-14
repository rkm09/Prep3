package leetdaily.easy;

import common.Pair;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class SumOfLeft404 {
    public static void main(String[] args) {
        TreeNode right2 = new TreeNode(7);
        TreeNode right1 = new TreeNode(15);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20, right1, right2);
        TreeNode root = new TreeNode(3, left, right);
        System.out.println(sumOfLeftLeaves1(root));
    }

    //    [def]; recursion; time: O(n), space: O(n)
    public static int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        return helper(root, false, sum);
    }
    private static int helper(TreeNode node, boolean isLeftChild, int sum) {
        if(node.left == null && node.right == null && isLeftChild) {
            sum += node.val;
            return sum;
        }
        if(node.left != null) {
            sum = helper(node.left, true, sum);
        }
        if(node.right != null) {
            sum = helper(node.right, false, sum);
        }
        return sum;
    }


//    [def]; iteration; time: O(n), space: O(n)
    public static int sumOfLeftLeaves1(TreeNode root) {
        Deque<Pair<TreeNode, Boolean>> queue = new ArrayDeque<>();
        int sum = 0;
        queue.offer(new Pair<>(root, false));
        while(!queue.isEmpty()) {
            Pair<TreeNode, Boolean> nodeInfo = queue.poll();
            TreeNode node = nodeInfo.getKey();
            boolean isLeftChild = nodeInfo.getValue();

            if(node.left == null && node.right == null && isLeftChild) {
                sum += node.val;
            }
            if(node.left != null) {
                queue.offer(new Pair<>(node.left, true));
            }
            if(node.right != null) {
                queue.offer(new Pair<>(node.right, false));
            }

        }
        return sum;
    }


}

/*
Given the root of a binary tree, return the sum of all left leaves.
A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.
Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 24
Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
Example 2:
Input: root = [1]
Output: 0

Constraints:
The number of nodes in the tree is in the range [1, 1000].
-1000 <= Node.val <= 1000
 */
