package leetdaily.medium;

import common.Pair;
import common.TreeNode;

import java.util.*;


public class SmallestString988 {
    private int smallest = Integer.MAX_VALUE;
    public static void main(String[] args) {
        TreeNode right2 = new TreeNode(4);
        TreeNode right1 = new TreeNode(3);
        TreeNode left2 = new TreeNode(4);
        TreeNode left1 = new TreeNode(3);
        TreeNode right = new TreeNode(2, right1, right2);
        TreeNode left = new TreeNode(1, left1, left2);
        TreeNode root = new TreeNode(0, left, right);
        SmallestString988 s = new SmallestString988();
        System.out.println(s.smallestFromLeaf(root));
    }

//    dfs; time: O(n), space: O(n)
    public String smallestFromLeaf(TreeNode root) {
        Deque<Pair<TreeNode, String>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root,""));
        List<String> words = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            Pair<TreeNode, String> nodeInfo = stack.pop();
            TreeNode node = nodeInfo.getKey();
            String str = nodeInfo.getValue();
            sb.append(str);
            sb.append((char) (node.val + 97));
            if(node.left == null && node.right == null) {
                words.add(sb.toString());
                sb.setLength(0);
            }
            if(node.right != null) {
                stack.push(new Pair<>(node.right, str));
            }
            if(node.left != null) {
                stack.push(new Pair<>(node.left, str));
            }
        }
        Collections.sort(words);
        System.out.println(words);
        words.sort(Comparator.comparingInt(String::length));
        return words.get(0);
    }

    public String smallestFromLeaf1(TreeNode root) {
        char[] arr = new char[26];
        for(int i = 0 ; i < 26 ; i++) {
            arr[i] = (char) (i + 97);
        }

        return "";
    }

    private int helper(TreeNode node) {
        if(node == null) return 0;

        if(node.left == null && node.right == null) {
            return node.val;
        }

        int left = helper(node.left);
        int right = helper(node.right);

        smallest = Math.min(smallest, Math.min(left, right));
        return smallest;
    }

}

/*
You are given the root of a binary tree where each node has a value in the range [0, 25] representing the letters 'a' to 'z'.
Return the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
As a reminder, any shorter prefix of a string is lexicographically smaller.
For example, "ab" is lexicographically smaller than "aba".
A leaf of a node is a node that has no children.
Example 1:
Input: root = [0,1,2,3,4,3,4]
Output: "dba"
Example 2:
Input: root = [25,1,3,1,3,0,2]
Output: "adz"
Example 3:
Input: root = [2,2,1,null,1,0,null,0]
Output: "abc"

Constraints:

The number of nodes in the tree is in the range [1, 8500].
0 <= Node.val <= 25
 */
