package leetdaily.medium;

import common.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;


public class DoubleANum2816 {
    public static void main(String[] args) {
        ListNode next1 = new ListNode(9);
        ListNode next = new ListNode(9, next1);
        ListNode head = new ListNode(9, next);
        ListNode res = doubleIt(head);
        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

//    [def]; reverse list; time: O(n), space: O(1)
    public static ListNode doubleIt(ListNode head) {
        if(head.val == 0) return head;

        head = reverseList(head);
        ListNode current = head;
        int carry = 0;

        ListNode res = new ListNode(0);
        ListNode temp = res;
        while(current != null) {
            int value = current.val + current.val + carry;
            carry = value / 10;
            ListNode nextNode = new ListNode(value % 10);
            temp.next = nextNode;
            temp = nextNode;
            current = current.next;
            if(current == null && carry != 0) {
                temp.next = new ListNode(carry);
                break;
            }
        }

        return reverseList(res.next);
    }

    private static ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        while(current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }

}

/*
You are given the head of a non-empty linked list representing a non-negative integer without leading zeroes.
Return the head of the linked list after doubling it.
Example 1:
Input: head = [1,8,9]
Output: [3,7,8]
Explanation: The figure above corresponds to the given linked list which represents the number 189. Hence, the returned linked list represents the number 189 * 2 = 378.
Example 2:
Input: head = [9,9,9]
Output: [1,9,9,8]
Explanation: The figure above corresponds to the given linked list which represents the number 999. Hence, the returned linked list reprersents the number 999 * 2 = 1998.

Constraints:
The number of nodes in the list is in the range [1, 104]
0 <= Node.val <= 9
The input is generated such that the list represents a number that does not have leading zeros, except the number 0 itself.
 */