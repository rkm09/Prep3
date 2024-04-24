package leetdaily.easy;

import common.Node;
import common.Pair;

import java.util.*;

public class MaxDepthOfNary559 {
    public static void main(String[] args) {
        Node c4 = new Node(5);
        Node c5 = new Node(6);
        List<Node> level2 = List.of(c4, c5);
        Node c1 = new Node(3, level2);
        Node c2 = new Node(2);
        Node c3 = new Node(4);
        List<Node> level1 = List.of(c1,c2,c3);
        Node root = new Node(1, level1);
        MaxDepthOfNary559 m = new MaxDepthOfNary559();
        System.out.println(m.maxDepth(root));
    }


//    recursion; time: O(n), space: O(n)
    public int maxDepth(Node root) {
        if(root == null)
            return 0;
        else if(root.children.isEmpty())
            return 1;
        else {
            List<Integer> heights = new ArrayList<>();
            for(Node child : root.children) {
                heights.add(maxDepth(child));
            }
            return Collections.max(heights) + 1;
        }
    }

//    iteration; time: O(n), space: O(n)
    public int maxDepth1(Node root) {
        if(root == null) return 0;
        int maxHeight = 0;
        Deque<Pair<Node, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair<>(root, 1));
        while(!stack.isEmpty()) {
            Pair<Node, Integer> nodeInfo = stack.pop();
            Node node = nodeInfo.getKey();
            int height = nodeInfo.getValue();
            maxHeight = Math.max(maxHeight, height);
            if(!node.children.isEmpty()) {
                for(Node child : node.children) {
                    stack.push(new Pair<>(child, height + 1));
                }
            }
        }
        return maxHeight;
    }
}

/*
Given an n-ary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
Example 1:
Input: root = [1,null,3,2,4,null,5,6]
Output: 3
Example 2:
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: 5

Constraints:
The total number of nodes is in the range [0, 104].
The depth of the n-ary tree is less than or equal to 1000.
 */
