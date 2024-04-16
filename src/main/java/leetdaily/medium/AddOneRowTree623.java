package leetdaily.medium;

import common.Pair;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class AddOneRowTree623 {
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(3);
        TreeNode left2 = new TreeNode(1);
        TreeNode left = new TreeNode(2, left1, left2);
        TreeNode right1 = new TreeNode(5);
        TreeNode right = new TreeNode(6, right1, null);
        TreeNode root = new TreeNode(4, left, right);
        AddOneRowTree623 l = new AddOneRowTree623();
        System.out.println(l.addOneRow(root, 1,2).val);
    }

//    recursion(dfs); time : O(n), space: O(n)
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(depth == 1) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return node;
        }
        insert(root, val, depth, 1);
        return root;
    }

    private void insert(TreeNode node, int val, int depth, int level) {
        if(node == null) return;
        if(level + 1 == depth) {
            TreeNode temp = node.left;
            node.left = new TreeNode(val);
            node.left.left = temp;
            temp = node.right;
            node.right = new TreeNode(val);
            node.right.right = temp;
        } else {
            insert(node.left, val, depth, level + 1);
            insert(node.right, val, depth, level + 1);
        }
    }

    public TreeNode addOneRow1(TreeNode root, int val, int depth) {
        Deque<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.offer(new Pair<>(root, 0));
        while(!queue.isEmpty()) {
            Pair<TreeNode, Integer> nodeInfo = queue.poll();
            TreeNode node = nodeInfo.getKey();
            int level = nodeInfo.getValue();
            if(level + 1 == depth) {
                TreeNode temp = new TreeNode(val);
                temp.left = node.left;
                temp.right = node.right;
                node.left = temp;
                node.right = temp;
                break;
            } else {
                if(node.left != null)
                    queue.offer(new Pair<>(node.left, level + 1));
                if(node.right != null)
                    queue.offer(new Pair<>(node.right, level + 1));
            }
        }
        return root;
    }
}

/*
Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth.
Note that the root node is at depth 1.
The adding rule is:
Given the integer depth, for each not null tree node cur at the depth depth - 1, create two tree nodes with value val as cur's left subtree root and right subtree root.
cur's original left subtree should be the left subtree of the new left subtree root.
cur's original right subtree should be the right subtree of the new right subtree root.
If depth == 1 that means there is no depth depth - 1 at all, then create a tree node with value val as the new root of the whole original tree, and the original tree is the new root's left subtree.
Example 1:
Input: root = [4,2,6,3,1,5], val = 1, depth = 2
Output: [4,1,1,2,null,null,6,3,1,5]
Example 2:
Input: root = [4,2,null,3,1], val = 1, depth = 3
Output: [4,2,null,1,1,3,null,null,1]

Constraints:

The number of nodes in the tree is in the range [1, 104].
The depth of the tree is in the range [1, 104].
-100 <= Node.val <= 100
-105 <= val <= 105
1 <= depth <= the depth of tree + 1
 */