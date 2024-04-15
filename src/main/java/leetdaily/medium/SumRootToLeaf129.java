package leetdaily.medium;

import common.Pair;
import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class SumRootToLeaf129 {
    private int sum = 0;
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(3);
        TreeNode left2 = new TreeNode(4);
        TreeNode left = new TreeNode(2, left1, left2);
        TreeNode right = new TreeNode(5);
        TreeNode root = new TreeNode(1, left, right);
        SumRootToLeaf129 s = new SumRootToLeaf129();
        System.out.println(s.sumNumbers2(root));
    }

    //    recursive preorder; time: O(n), space: O(n)
    public int sumNumbers(TreeNode root) {
        preOrder(root, 0);
        return sum;
    }
    private void preOrder(TreeNode node, int currNum) {
        if(node != null) {
            currNum = 10 * currNum + node.val;
            if(node.left == null && node.right == null) {
                sum += currNum;
            } else {
                preOrder(node.left, currNum);
                preOrder(node.right, currNum);
            }
        }
    }

//    iterative; time: O(n), space: O(n)
    public int sumNumbers1(TreeNode root) {
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, 0));
        int totalSum = 0;
        while(!stack.isEmpty()) {
            Pair<TreeNode, Integer> nodeInfo = stack.pop();
            TreeNode node = nodeInfo.getKey();
            int currNum = nodeInfo.getValue();
            if(node != null) {
                currNum = currNum * 10 + node.val;
//                if it's a leaf node, add to the main sum
                if(node.left == null && node.right == null) {
                    totalSum += currNum;
                } else {
                    stack.push(new Pair<>(node.right, currNum));
                    stack.push(new Pair<>(node.left, currNum));
                }
            }
        }
        return totalSum;
    }

//    morris traversal; time: O(n), space: O(1)
    public int sumNumbers2(TreeNode root) {
        TreeNode node = root;
        int totalSum = 0, currNum = 0, steps;

        while(node != null) {
            if(node.left != null) {
                TreeNode predecessor = node.left;
                steps = 1;
                while(predecessor.right != null && predecessor.right != node) {
                    predecessor = predecessor.right;
                    steps++;
                }
                if(predecessor.right == null) {
//                    set predecessor right and then explore left
                    currNum = 10 * currNum + node.val;
                    predecessor.right = node;
                    node = node.left;
                } else {
//                    explored already break link and continue right
//                    if on a leaf
                    if(predecessor.left == null) {
                        totalSum += currNum;
                    }
//                    backtrack num (explored already)
                    for(int i = 0 ; i < steps ; i++)
                        currNum /= 10;

                    predecessor.right = null;
                    node = node.right;
                }
            }
            else {
                currNum = 10 * currNum + node.val;
//                if this is a leaf, update total
                if(node.right == null) {
                    totalSum += currNum;
                }
                node = node.right;
            }
        }
        return totalSum;
    }
}

/*
You are given the root of a binary tree containing digits from 0 to 9 only.
Each root-to-leaf path in the tree represents a number.
For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
A leaf node is a node with no children.
Example 1:
Input: root = [1,2,3]
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:
Input: root = [4,9,0,5,1]
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.

Constraints:
The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 9
The depth of the tree will not exceed 10.
 */
