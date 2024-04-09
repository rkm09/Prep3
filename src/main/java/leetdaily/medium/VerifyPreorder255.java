package leetdaily.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class VerifyPreorder255 {
    public static void main(String[] args) {
        int[] preorder = {5,2,1,3,6};
        System.out.println(verifyPreorder(preorder));
    }

//    monotonic stack(descending); time: O(n), space: O(n)
//    Because a node is handled before its children(preorder), we can essentially iterate over the sequence preorder
//    and decide what subtree each subsequent value should be in. The BST rule is that all nodes in the left subtree of 2 must be less than 2.
//    When we perform a standard DFS on a binary tree with recursion, we traverse as far down the left branch as possible.
//    Once we reach a leaf node, we backtrack up the tree by returning from the DFS function.
//    Under the hood, the computer implements this by using a call stack. The issue arises when we find a larger value,
//    because it must be a right child, and we need to identify which node should be its parent. we can't just immediately "walk" right as we might violate the
//    BST property due to an ancestor that's higher up the tree. We need to find the ancestor with the greatest value that is less than x. The value needs to be less than x because x is going to be its right child.
//    We need to find the greatest value, otherwise, there would be some other node that would break the BST property.
//    In a preorder traversal, a node's left subtree is visited completely before any node in the right subtree is visited. Because we are now in the right subtree, we can completely forget about the left subtree.
    public static boolean verifyPreorder(int[] preorder) {
        int minLimit = Integer.MIN_VALUE;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int num : preorder) {
            while(!stack.isEmpty() && num > stack.peek()) {
//                get to the parent(backtrack), no need to keep track of the processed left subTree
                minLimit = stack.pop();
            }
//            less than parent after traversing entire left subTree => invalid
            if(num <= minLimit)
                return false;
//            otherwise, keep walking left
            stack.push(num);
        }
        return true;
    }

//    constant auxiliary space; time: O(n), space: O(1) auxiliary space
//    in general, in-place modification to be avoided, included here only because of the follow-up question
    public static boolean verifyPreorder1(int[] preorder) {
        int minLimit = Integer.MIN_VALUE;
        int i = 0;
        for(int num : preorder) {
//            preorder[i - 1] represents current stack top;
            while(i > 0 && num > preorder[i - 1]) {
                minLimit = preorder[i - 1];
//                to remove an element we simply decrement i;
                i--;
            }

            if(num <= minLimit)
                return false;

//            push num onto the stack by setting and incrementing
            preorder[i] = num;
            i++;
        }
        return true;
    }

//    recursion; time: O(n), space: O(n)
//    The thing that makes this solution very unintuitive is that i is shared between all function calls - i.e., it acts as a global variable. This is inconsistent with recursive solutions where each function call stores its own state.
//    The reason for this is that we need to process the nodes of preorder in the same order that they appear in the input.
//    If the sequence is invalid, all calls will eventually end up failing the BST condition check.
    public static boolean verifyPreorder2(int[] preorder) {
        return helper(preorder, new int[]{0}, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private static boolean helper(int[] preorder, int[] i, int minLimit, int maxLimit) {
        if(i[0] == preorder.length)
            return true;
//        validation check
        int root = preorder[i[0]];
        if(root <= minLimit || root >= maxLimit)
            return false;
        i[0]++;
        boolean left = helper(preorder, i, minLimit, root);
        boolean right = helper(preorder, i, root, maxLimit);

        return left || right;
    }
}

/*
Given an array of unique integers preorder, return true if it is the correct preorder traversal sequence of a binary search tree.
Example 1:
Input: preorder = [5,2,1,3,6]
Output: true
Example 2:
Input: preorder = [5,2,6,1,3]
Output: false
Constraints:
1 <= preorder.length <= 104
1 <= preorder[i] <= 104
All the elements of preorder are unique.

Follow up: Could you do it using only constant space complexity?
 */