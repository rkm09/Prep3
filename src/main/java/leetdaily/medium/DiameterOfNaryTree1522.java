package leetdaily.medium;

import common.Node;

import java.util.List;

public class DiameterOfNaryTree1522 {
    private int diameter;
    public static void main(String[] args) {
        DiameterOfNaryTree1522 dia = new DiameterOfNaryTree1522();
        Node node1 = new Node(3);
        Node node2 = new Node(2);
        Node node11 = new Node(5);
        Node node22 = new Node(6);
        List<Node> children1 = List.of(node11, node22);
        Node node3 = new Node(4, children1);
        List<Node> children = List.of(node1, node2, node3);
        Node root = new Node(1, children);
        System.out.println(dia.diameter(root));
    }

//    Insight1: The longest path in a tree can only happen between two leaf nodes or between a leaf node and the root node.
//    Insight2: Each non-leaf node acts as a bridge for the paths between its descendant leaf nodes.

//    distance with height; time: O(n), space: O(n)
//    The 'height' of a node is defined as the length of the longest downward path to a leaf node from that node.
//    get the longest path between two leaf nodes connected by a non leaf node;
    public  int diameter(Node root) {
        maxHeight(root);
        return diameter;
    }
    private int maxHeight(Node node) {
        if(node.children.size() == 0)
            return 0;
//        select the top two largest heights
        int maxHeight1 = 0, maxHeight2 = 0;
        for(Node child : node.children) {
            int parentHeight = maxHeight(child) + 1;
            if(parentHeight > maxHeight1) {
                maxHeight2 = maxHeight1;
                maxHeight1 = parentHeight;
            } else if(parentHeight > maxHeight2) {
                maxHeight2 = parentHeight;
            }
//            calculate the distance between the top two farthest leaf nodes
            int currPathLength = maxHeight1 + maxHeight2;
            diameter = Math.max(diameter, currPathLength);
        }
        return maxHeight1;
    }

//    distance with depth; time: O(n), space: O(n)
//    The 'depth' of a node is the length of the path to the root node.
    public  int diameter1(Node root) {
        maxDepth(root, 0);
        return diameter;
    }
    private int maxDepth(Node node, int currDepth) {
        if(node.children.size() == 0)
            return currDepth;
//        select the top two largest depths
        int maxDepth1 = 0, maxDepth2 = 0;
        for(Node child : node.children) {
            int depth = maxDepth(child, currDepth + 1);
            if(depth > maxDepth1) {
                maxDepth2 = maxDepth1;
                maxDepth1 = depth;
            } else if(depth > maxDepth2) {
                maxDepth2 = depth;
            }
//            sum of the two largest depths minus the depth of the parent node;
            int currPathLength = maxDepth1 + maxDepth2 - 2 * currDepth;
            diameter = Math.max(diameter, currPathLength);
        }
        return maxDepth1;
    }
}

/*
Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.
The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree. This path may or may not pass through the root.
(Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)
Example 1:
Input: root = [1,null,3,2,4,null,5,6]
Output: 3
Explanation: Diameter is shown in red color.
Example 2:
Input: root = [1,null,2,null,3,4,null,5,null,6]
Output: 4
Example 3:
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: 7

Constraints:
The depth of the n-ary tree is less than or equal to 1000.
The total number of nodes is between [1, 104].
 */