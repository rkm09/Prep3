package leetdaily.medium;

import common.TreeNode;

public class DistributeCoins973 {
    private int moves;
    public static void main(String[] args) {
        DistributeCoins973 d = new DistributeCoins973();
        TreeNode left = new TreeNode(0);
        TreeNode right = new TreeNode(0);
        TreeNode root = new TreeNode(3, left, right);
        System.out.println(d.distributeCoins(root));
    }

//    dfs; time: O(n), space: O(n)
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return moves;
    }

    private int dfs(TreeNode current) {
//        base case
        if(current == null) return 0;

//        calculate the number of coins each subtree can provide for exchange
        int leftCoins = dfs(current.left);
        int rightCoins = dfs(current.right);

//        add the total number of exchanges to moves
        moves += Math.abs(leftCoins) + Math.abs(rightCoins);

        return (current.val - 1) + leftCoins + rightCoins;
    }
}

/*
You are given the root of a binary tree with n nodes where each node in the tree has node.val coins. There are n coins in total throughout the whole tree.
In one move, we may choose two adjacent nodes and move one coin from one node to another. A move may be from parent to child, or from child to parent.
Return the minimum number of moves required to make every node have exactly one coin.
Example 1:
Input: root = [3,0,0]
Output: 2
Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
Example 2:
Input: root = [0,3,0]
Output: 3
Explanation: From the left child of the root, we move two coins to the root [taking two moves]. Then, we move one coin from the root of the tree to the right child.

Constraints:
The number of nodes in the tree is n.
1 <= n <= 100
0 <= Node.val <= n
The sum of all Node.val is n.
 */