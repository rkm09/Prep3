package leetdaily.easy;

import common.ListNode;

public class ReverseLL206 {
    public static void main(String[] args) {
        ListNode next1 = new ListNode(3);
        ListNode next = new ListNode(2, next1);
        ListNode head = new ListNode(1, next);
        ListNode result = reverseList(head);
        while(result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

//    iterative; time: O(n), space: O(1)
    public static ListNode reverseList(ListNode head) {
//        keep track of previous and next;
        ListNode prevNode = null;
        ListNode currNode = head;
        while(currNode != null) {
            ListNode nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        return prevNode;
    }

//    recursive; time: O(n), space: O(n)
    public static ListNode reverseList1(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode reversedSubList = reverseList1(head.next);
//        append the tail of sublist and mark the end node's next as null (to ensure absence of cycle)
        head.next.next = head;
        head.next = null;
        return reversedSubList;
    }
}

/*
Given the head of a singly linked list, reverse the list, and return the reversed list.
Example 1:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
Example 2:
Input: head = [1,2]
Output: [2,1]
Example 3:
Input: head = []
Output: []

Constraints:
The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000

Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */