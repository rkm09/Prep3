package leetdaily.easy;

import common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle141 {
    public static void main(String[] args) {
        ListNode next3 = new ListNode(-4);
        ListNode next2 = new ListNode(0, next3);
        ListNode next1 = new ListNode(2, next2);
        ListNode head = new ListNode(3, next1);
        next3.next = next1;
        System.out.println(hasCycle(head));
    }

//    floyd's cycle algo; time: O(n), space: O(1)
    public static boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != slow) {
            if(fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

//    hashmap; time: O(n), space: O(n)
    public static boolean hasCycle1(ListNode head) {
        ListNode current = head;
        Set<ListNode> seen = new HashSet<>();
        while(current != null) {
            if(seen.contains(current)) return true;
            seen.add(current);
            current = current.next;
        }
        return false;
    }
}

/*
Given head, the head of a linked list, determine if the linked list has a cycle in it.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
Return true if there is a cycle in the linked list. Otherwise, return false.
Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
Example 2:
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
Example 3:
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.

Constraints:
The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.
 */