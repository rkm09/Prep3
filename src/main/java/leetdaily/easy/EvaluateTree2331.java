package leetdaily.easy;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class EvaluateTree2331 {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(1);
        TreeNode right1 = new TreeNode(0);
        TreeNode right2 = new TreeNode(1);
        TreeNode right = new TreeNode(3, right1, right2);
        TreeNode root = new TreeNode(2, left, right);
        System.out.println(evaluateTree(root));
    }

//    dfs (recursion); def; time: O(n), space: O(n)
    public static boolean evaluateTree(TreeNode root) {
        if(root.left == null && root.right == null)
            return root.val == 1;
        boolean leftChild = evaluateTree(root.left);
        boolean rightChild = evaluateTree(root.right);
        if(root.val == 2)
            return leftChild || rightChild;
        else
            return leftChild && rightChild;
    }

//    iteration; time: O(n), space: O(n)
    public static boolean evaluateTree1(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        Map<TreeNode, Boolean> evaluated = new HashMap<>();
        while(!stack.isEmpty()) {
            TreeNode topNode = stack.peek();
            if(topNode.left == null && topNode.right == null) {
//                if node is a leaf node, store its value in the evaluated hashmap
                stack.pop();
                evaluated.put(topNode, topNode.val == 1);
                continue;
            }
            if(evaluated.containsKey(topNode.left) && evaluated.containsKey(topNode.right)) {
//                if both children are already present in evaluated hashmap, then evaluate the current operator
                stack.pop();
                if(topNode.val == 2)
                    evaluated.put(topNode, evaluated.get(topNode.left) || evaluated.get(topNode.right));
                else
                    evaluated.put(topNode, evaluated.get(topNode.left) && evaluated.get(topNode.right));
            }

            stack.push(topNode.right);
            stack.push(topNode.left);
        }

        return evaluated.get(root);
    }
}

/*
You are given the root of a full binary tree with the following properties:
Leaf nodes have either the value 0 or 1, where 0 represents False and 1 represents True.
Non-leaf nodes have either the value 2 or 3, where 2 represents the boolean OR and 3 represents the boolean AND.
The evaluation of a node is as follows:
If the node is a leaf node, the evaluation is the value of the node, i.e. True or False.
Otherwise, evaluate the node's two children and apply the boolean operation of its value with the children's evaluations.
Return the boolean result of evaluating the root node.
A full binary tree is a binary tree where each node has either 0 or 2 children.
A leaf node is a node that has zero children.

Example 1:
Input: root = [2,1,3,null,null,0,1]
Output: true
Explanation: The above diagram illustrates the evaluation process.
The AND node evaluates to False AND True = False.
The OR node evaluates to True OR False = True.
The root node evaluates to True, so we return true.
Example 2:
Input: root = [0]
Output: false
Explanation: The root node is a leaf node and it evaluates to false, so we return false.

Constraints:
The number of nodes in the tree is in the range [1, 1000].
0 <= Node.val <= 3
Every node has either 0 or 2 children.
Leaf nodes have a value of 0 or 1.
Non-leaf nodes have a value of 2 or 3.
 */