package leetdaily.medium;

import common.TreeNode;

public class CountNodesEqualToSum1973 {
    private int count;
    public static void main(String[] args) {
        CountNodesEqualToSum1973 cnt = new CountNodesEqualToSum1973();
        TreeNode left1 = new TreeNode(2);
        TreeNode left2 = new TreeNode(1);
        TreeNode left = new TreeNode(3, left1, left2);
        TreeNode right = new TreeNode(4);
        TreeNode root = new TreeNode(10, left, right);
        System.out.println(cnt.equalToDescendants(root));
    }

//    [def]; dfs postorder traversal; time: O(n), space: O(n)
    public int equalToDescendants(TreeNode root) {
        dfs(root);
        return count;
    }
    private int dfs(TreeNode node) {
        if(node == null) return 0;
        int leftChild = dfs(node.left);
        int rightChild = dfs(node.right);
        int sum = leftChild + rightChild;
        if(node.val == sum) count++;
        return sum + node.val;
    }
}

/*
Given the root of a binary tree, return the number of nodes where the value of the node is equal to the sum of the values of its descendants.
A descendant of a node x is any node that is on the path from node x to some leaf node. The sum is considered to be 0 if the node has no descendants.
Example 1:
Input: root = [10,3,4,2,1]
Output: 2
Explanation:
For the node with value 10: The sum of its descendants is 3+4+2+1 = 10.
For the node with value 3: The sum of its descendants is 2+1 = 3.
Input: root = [2,3,null,2,null]
Output: 0
Explanation:
No node has a value that is equal to the sum of its descendants.
Input: root = [0]
Output: 1
For the node with value 0: The sum of its descendants is 0 since it has no descendants.

Constraints:
The number of nodes in the tree is in the range [1, 105].
0 <= Node.val <= 105
 */