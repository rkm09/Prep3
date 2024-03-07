package leetdaily.easy;

import common.ListNode;

public class MiddleNode876 {
    public static void main(String[] args) {
        ListNode next5 = new ListNode(5);
        ListNode next4 = new ListNode(4, next5);
        ListNode next3 = new ListNode(3, next4);
        ListNode next2 = new ListNode(2, next3);
        ListNode head = new ListNode(1, next2);
        ListNode res = middleNode(head);
        System.out.println(res.val);
    }

//    slow & fast pointer; time: O(n), space: O(1)
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

//    [def]; time: O(n), space: O(n)
    public static ListNode middleNode1(ListNode head) {
        ListNode current = head;
        int length = 0;
        while(current != null) {
            length++;
            current = current.next;
        }
        current = head; length /= 2;
        for(int i = 0 ; i < length ; i++)
            current = current.next;
        return current;
    }
}

/*
Given the head of a singly linked list, return the middle node of the linked list.
If there are two middle nodes, return the second middle node.
Example 1:
Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.
Example 2:
Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.

Constraints:
The number of nodes in the list is in the range [1, 100].
1 <= Node.val <= 100
 */