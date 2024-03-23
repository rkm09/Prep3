package leetdaily.medium;

import common.ListNode;

public class ReorderList143 {
    public static void main(String[] args) {
//        ListNode next5 = new ListNode(5);
        ListNode next4 = new ListNode(4);
        ListNode next3 = new ListNode(3, next4);
        ListNode next1 = new ListNode(2, next3);
        ListNode head = new ListNode(1, next1);
        ListNode res = reorderList(head);
//       1->2->3->4->5  to  1->5->2->4->3
        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

//    time: O(n), space: O(1)
    public static ListNode reorderList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode endOfFirstHalf = getEndOfFirstHalf(head);
//        first: 1->2->3->4->5, second: 5->4->3 [note we didn't start with endOfFirstHalf.next as arg to reverseList]
        ListNode first = head, second = reverseList(endOfFirstHalf);
        while(second.next != null) {
                ListNode tmp = first.next;
                first.next = second;
                first = tmp;

                tmp = second.next;
                second.next = first;
                second = tmp;
        }
        return head;
    }

//    Odd: 1->2->3->4->5 => head: 1->2->3 (return 3) || Even: 1->2->3->4 => head: 1->2->3 (return 3)
    private static ListNode getEndOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}

/*
You are given the head of a singly linked-list. The list can be represented as:
L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be in the following form:
L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.
Example 1:
Input: head = [1,2,3,4]
Output: [1,4,2,3]
Example 2:
Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]

Constraints:
The number of nodes in the list is in the range [1, 5 * 104].
1 <= Node.val <= 1000
 */