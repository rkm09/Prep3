package leetdaily.easy;

import common.ListNode;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

public class PalindromeLL234 {
    public static void main(String[] args) {
        ListNode next3 = new ListNode(1);
        ListNode next2 = new ListNode(2, next3);
        ListNode next1 = new ListNode(1, next2);
        ListNode head = new ListNode(1, next1);
        System.out.println(isPalindrome(head));
    }

//    copy to array list; time: O(n), space: O(n)
    public static boolean isPalindrome(ListNode head) {
        List<Integer> values = new ArrayList<>();
        ListNode currentNode = head;
        while(currentNode != null) {
            values.add(currentNode.val);
            currentNode = currentNode.next;
        }
        int front = 0, back = values.size() - 1;
        while(front < back) {
//            be aware of Integer comparison with equals, since it is not int
            if(!values.get(front).equals(values.get(back)))
                return false;
            front++;
            back--;
        }
        return true;
    }
}

/*
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
Example 1:
Input: head = [1,2,2,1]
Output: true
Example 2:
Input: head = [1,2]
Output: false

Constraints:
The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9

Follow up: Could you do it in O(n) time and O(1) space?
 */