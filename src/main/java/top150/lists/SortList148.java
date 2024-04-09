package top150.lists;

import common.ListNode;

import java.util.PriorityQueue;

public class SortList148 {
    public static void main(String[] args) {
        ListNode next3 = new ListNode(3);
        ListNode next2 = new ListNode(1, next3);
        ListNode next1 = new ListNode(2, next2);
        ListNode head = new ListNode(4, next1);
        System.out.println(sortList(head).val);
    }

//    Merge sort; time: O(nlogn), space: O(logn)
    public static ListNode sortList(ListNode head) {
        return head;
    }

//    pq[def]; time: O(nlogn), space: O(n)
//    note: follow up asks for constant space; so not really applicable
    public static ListNode sortList2(ListNode head) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ListNode node = head;
        while(node != null) {
            pq.add(node.val);
            node = node.next;
        }
        ListNode result = new ListNode(0);
        ListNode temp = result;
        while(!pq.isEmpty()) {
            temp.next = new ListNode(pq.poll());
            temp = temp.next;
        }
        return result.next;
    }
}

/*
Given the head of a linked list, return the list after sorting it in ascending order.
Example 1:
Input: head = [4,2,1,3]
Output: [1,2,3,4]
Example 2:
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
Example 3:
Input: head = []
Output: []

Constraints:
The number of nodes in the list is in the range [0, 5 * 104].
-105 <= Node.val <= 105

Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */