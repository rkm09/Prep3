package leetdaily.easy;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class SameTree100 {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(1, left, right);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        TreeNode root1 = new TreeNode(1, left1, right1);
        System.out.println(isSameTree(root, root1));
    }

//    dfs recursion; time: O(n), space: O(n)
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;
        return isSameTree(p.left, q.left) &&
                isSameTree(p.right, q.right);
    }

//    bfs iteration; time: O(n), space: O(n) [fast & less space]
    public static boolean isSameTree1(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(!isValid(p, q)) return false;
        Deque<TreeNode> deqP = new ArrayDeque<>();
        Deque<TreeNode> deqQ = new ArrayDeque<>();
        deqP.offer(p);
        deqQ.offer(q);
        while(!deqP.isEmpty()) {
            p = deqP.poll();
            q = deqQ.poll();
            if(!isValid(p, q)) return false;
            if(p != null) {
//                nulls not allowed in deque
                if(!isValid(p.left, q.left)) return false;
                if(p.left != null) {
                    deqP.offer(p.left);
                    deqQ.offer(q.left);
                }

                if(!isValid(p.right, q.right)) return false;
                if(p.right != null) {
                    deqP.offer(p.right);
                    deqQ.offer(q.right);
                }
            }
        }
        return true;
    }

    private static boolean isValid(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;
        return true;
    }
}

/*
Given the roots of two binary trees p and q, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
Input: p = [1,2,3], q = [1,2,3]
Output: true
Input: p = [1,2], q = [1,null,2]
Output: false
Input: p = [1,2,1], q = [1,1,2]
Output: false
Constraints:
The number of nodes in both trees is in the range [0, 100].
-104 <= Node.val <= 104
 */