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

//    Merge sort (top down recursion)[fast]; time: O(nlogn), space: O(logn) [log2n is the height of the tree(split function is in a way a binary tree)]
//    follows divide & conquer; split + merge
    public static ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode mid = getMid(head);
//        after the getMid function call, head is now modified to end before the mid;
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return merge(left, right);
    }

    private static ListNode merge(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        if(list1.val <= list2.val) {
            list1.next = merge(list1.next, list2);
            return list1;
        } else {
            list2.next = merge(list1, list2.next);
            return list2;
        }
    }

    private static ListNode getMid(ListNode head) {
        ListNode midPrev = null;
        while(head != null && head.next != null) {
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;
        }
        ListNode mid = midPrev.next;
        midPrev.next = null;
        return mid;
    }

//    pq[def]; time: O(nlogn), space: O(n)
//    note: follow-up asks for constant space; so not really applicable
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