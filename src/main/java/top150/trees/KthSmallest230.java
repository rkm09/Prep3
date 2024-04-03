package top150.trees;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class KthSmallest230 {
    private static List<Integer> result;
    public static void main(String[] args) {
        TreeNode left2 = new TreeNode(2);
        TreeNode left = new TreeNode(1, null, left2);
        TreeNode right = new TreeNode(4);
        TreeNode root = new TreeNode(3, left, right);
        TreeNode r1 = new TreeNode(2);
        TreeNode root1 = new TreeNode(1, null, r1);
        System.out.println(kthSmallest1(root1, 2));
    }

//    recursive inorder traversal; time: O(n), space: O(n)
//    inorder traversal leads to an array sorted in ascending order
//    in general: dfs[preorder: n->l->r, inorder: l->n->r, postorder: l->r->n], bfs[n->l->r]
    public static int kthSmallest(TreeNode root, int k) {
        result = new ArrayList<>();
        inorder(root);
        return result.get(k - 1);
    }
    private static void inorder(TreeNode node) {
        if(node == null) return;
        inorder(node.left);
        result.add(node.val);
        inorder(node.right);
    }

//    iterative inorder traversal; time: O(H + k), space: O(H) [faster than recursive] [H-> height of the tree]
    public static int kthSmallest1(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while(true) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if(--k == 0) return node.val;
            node = node.right;
        }
    }
}

/*
Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
Example 1:
Input: root = [3,1,4,null,2], k = 1
Output: 1
Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3

Constraints:
The number of nodes in the tree is n.
1 <= k <= n <= 104
0 <= Node.val <= 104

Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?

the time complexity of these operations (insert/delete) is O(H), where H is a height of the binary tree. H=logN for the balanced tree and H=N for a skewed tree.
Hence, without any optimisation insert/delete + search of kth element has O(2H + k) complexity. How to optimize that?

That's a design question, basically, we're asked to implement a structure that contains a BST inside and optimizes the following operations :

Insert
Delete
Find kth smallest

Seems like a database description, isn't it? Let's use here the same logic as for LRU cache design, and combine an indexing structure (we could keep BST here) with a doubly-linked list.

Such a structure would provide:
O(H) time for the insert and delete.
O(k) for the search of kth smallest.

Time Complexity:
O(H + k); O(logN + k) for the average case (balanced tree), O(N + k) for the worst case (skewed tree)
Space Complexity:
O(N) to keep the Linked list;
 */