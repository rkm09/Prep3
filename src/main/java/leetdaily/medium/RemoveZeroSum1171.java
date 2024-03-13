package leetdaily.medium;

import common.ListNode;

import java.util.HashMap;
import java.util.Map;

public class RemoveZeroSum1171 {
    public static void main(String[] args) {
        ListNode next3 = new ListNode(1);
        ListNode next2 = new ListNode(3, next3);
        ListNode next1 = new ListNode(3, next2);
        ListNode next = new ListNode(-3, next1);
        ListNode head = new ListNode(1, next);
        ListNode result = removeZeroSumSublists(head);
        while(result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

//    prefix sum & hashmap; time: O(n), space: O(n)
    public static ListNode removeZeroSumSublists(ListNode head) {
        ListNode front = new ListNode(0, head);
        ListNode current = front;
        int prefixSum = 0;
        Map<Integer, ListNode> prefixSumToNode = new HashMap<>();
        prefixSumToNode.put(0, front);
//      1st pass: calculate prefix sum
        while(current != null) {
            prefixSum += current.val;;
            prefixSumToNode.put(prefixSum, current);
            current = current.next;
        }
//        reset sum & current
        prefixSum = 0;
        current = front;
//      2nd pass:  delete consecutive sequences of zero sum
        while(current != null) {
            prefixSum += current.val;
            current.next = prefixSumToNode.get(prefixSum).next;
            current = current.next;
        }
        return front.next;
    }

//    prefix sum; time: O(n^2), space: O(1)
    public static ListNode removeZeroSumSublists1(ListNode head) {
        ListNode front = new ListNode(0, head);
        ListNode start = front;
        while(start != null) {
            int prefixSum = 0;
            ListNode end = start.next;
            while(end != null) {
                prefixSum += end.val;
                if(prefixSum == 0) {
                    start.next = end.next;
                }
                end = end.next;
            }
            start = start.next;
        }
        return front.next;
    }
}

/*
Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
After doing so, return the head of the final linked list.  You may return any such answer.
(Note that in the examples below, all sequences are serializations of ListNode objects.)
Example 1:
Input: head = [1,2,-3,3,1]
Output: [3,1]
Note: The answer [1,2,1] would also be accepted.
Example 2:
Input: head = [1,2,3,-3,4]
Output: [1,2,4]
Example 3:
Input: head = [1,2,3,-3,-2]
Output: [1]

Constraints:
The given linked list will contain between 1 and 1000 nodes.
Each node in the linked list has -1000 <= node.val <= 1000.
 */