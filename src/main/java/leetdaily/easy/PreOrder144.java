package leetdaily.easy;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PreOrder144 {
    private static List<Integer> result = new ArrayList<>();
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(3);
        TreeNode left2 = new TreeNode(4);
        TreeNode left = new TreeNode(2, left1, left2);
        TreeNode right = new TreeNode(5);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(preorderTraversal1(root));
    }


//    iteration; time: O(n), space: O(n) root->left->right
    public static List<Integer> preorderTraversal(TreeNode root) {
        if(root == null)
            return result;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
//            note: you need to push right to stack before left(opp order  because it is a stack)
            if(node.right != null)
                stack.push(node.right);
            if(node.left != null)
                stack.push(node.left);
        }
        return result;
    }

//    morris traversal; time: O(n), space: O(1)
    public static List<Integer> preorderTraversal1(TreeNode root) {
        if(root == null)
            return result;
        TreeNode node = root;
        while(node != null) {
            if(node.left == null) {
                result.add(node.val);
                node = node.right;
            }
            else {
                TreeNode predecessor = node.left;
                while(predecessor.right != null && predecessor.right != node)
                    predecessor = predecessor.right;

                if(predecessor.right == null) {
                    result.add(node.val);
                    predecessor.right = node;
                    node = node.left;
                } else {
                    predecessor.right = null;
                    node = node.right;
                }
            }
        }
        return result;
    }

//    recursion; time: O(n), space: O(n)
    public static List<Integer> preorderTraversal2(TreeNode root) {
        preOrder(root);
        return result;
    }
    private static void preOrder(TreeNode node) {
        if(node == null)
            return;
        result.add(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

}

/*
Given the root of a binary tree, return the preorder traversal of its nodes' values.
Example 1:
Input: root = [1,null,2,3]
Output: [1,2,3]
Example 2:
Input: root = []
Output: []
Example 3:
Input: root = [1]
Output: [1]

Constraints:
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100

Follow up: Recursive solution is trivial, could you do it iteratively?
 */