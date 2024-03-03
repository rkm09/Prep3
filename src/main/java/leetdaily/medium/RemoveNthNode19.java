package leetdaily.medium;

import common.ListNode;


public class RemoveNthNode19 {
    public static void main(String[] args) {
        ListNode next2 = new ListNode(4);
        ListNode next1 = new ListNode(3, next2);
        ListNode next = new ListNode(2, next1);
        ListNode head = new ListNode(1, next);
        ListNode res = removeNthFromEnd(head, 2);
        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    //    1 pass (2 pointer); time : O(l), space: O(1) [l - list size]
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for(int i = 1 ; i <= n + 1 ; i++)
            first = first.next;
        while(first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    //    [def] 2 pass (1 pointer); time: O(l), space: O(1) [fast]
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode current = head;
        int length = 0;
        while(current != null) {
            current = current.next;
            length++;
        }
        int idx = 0; current = head;
        length -= n;
        while(current != null) {
            if(length == 0) return head.next;
            if(idx + 1 == length) {
                current.next = current.next.next;
                return head;
            }
            idx++;
            current = current.next;
        }
        return null;
    }


//    2 pass (1 pointer); time: O(l), space: O(1)
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        while(first != null) {
            first = first.next;
            length++;
        }
        length -= n;
        first = dummy;
        while(length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }


}

/*
Given the head of a linked list, remove the nth node from the end of the list and return its head.
Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:
Input: head = [1], n = 1
Output: []
Example 3:
Input: head = [1,2], n = 1
Output: [1]
Constraints:
The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
Follow up: Could you do this in one pass?
 */