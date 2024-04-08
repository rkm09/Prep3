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
            while(!stack.isEmpty() && stack.peek() < num) {
//                get to the parent, no need to keep track of the processed left subTree
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

//    constant auxiliary space; time: O(n), space: O(1)
    public static boolean verifyPreorder1(int[] preorder) {
        int minLimit = Integer.MIN_VALUE;
        int i = 0;
        for(int num : preorder) {
            while(i > 0 && preorder[i - 1] < num) {
                minLimit = preorder[i - 1];
                i--;
            }

            if(num <= minLimit)
                return false;

            preorder[i] = num;
            i++;
        }
        return true;
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