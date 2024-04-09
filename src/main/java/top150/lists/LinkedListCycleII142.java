package top150.lists;

import common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycleII142 {
    public static void main(String[] args) {
        ListNode next2 = new ListNode(-4);
        ListNode next1 = new ListNode(0, next2);
        ListNode next = new ListNode(2, next1);
        ListNode head = new ListNode(3, next);
        next2.next = next;
        ListNode res = detectCycle(head);
        System.out.println(res.val);
    }

//    hashset; time: O(n), space: O(n)
    public static ListNode detectCycle(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        ListNode node = head;
        while(node != null) {
            if(seen.contains(node)) {
                return node;
            }
            seen.add(node);
            node = node.next;
        }
        return null;
    }

//    floyd & warshall's cycle detection; time: O(n), space: O(1) [fastest]
//    for logic can check findDuplicate287
    public static ListNode detectCycle1(ListNode head) {
        ListNode hare = head;
        ListNode tortoise = head;
//        get the intersection, with hare speed twice that of tortoise
        while(hare != null && hare.next != null) {
            hare = hare.next.next;
            tortoise = tortoise.next;
            if(hare == tortoise) break;
        }
//        check if there is no cycle
        if(hare == null || hare.next == null) return null;

//        reset either of hare or tortoise pointer
        tortoise = head;
//        move both one step at a time
        while(hare != tortoise) {
            hare = hare.next;
            tortoise = tortoise.next;
        }
//        return where the cycle begins
        return tortoise;
    }
}

/*
Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
Do not modify the linked list.
Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
Example 2:
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
Example 3:
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.

Constraints:
The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.

Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */