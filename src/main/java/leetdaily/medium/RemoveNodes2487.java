package leetdaily.medium;

import common.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveNodes2487 {
    public static void main(String[] args) {
        ListNode next3 = new ListNode(8);
        ListNode next2 = new ListNode(3, next3);
        ListNode next1 = new ListNode(13, next2);
        ListNode next = new ListNode(2, next1);
        ListNode head = new ListNode(5, next);
        System.out.println(removeNodes(head).val);
    }

//    reverse twice (in-place); time: O(n), auxiliary space: O(1) [fastest]
    public static ListNode removeNodes(ListNode head) {
//        reverse the original linked list
        head = reverseList(head);

        int maximum = 0;
        ListNode prev = null;
        ListNode current = head;

//        traverse the list deleting nodes
        while(current != null) {
            maximum = Math.max(maximum, current.val);
//            delete nodes that are smaller than maximum
            if(current.val < maximum) {
                prev.next = current.next;
                ListNode deleteNode = current;
                current = current.next;
                deleteNode.next = null;
            } else {
                prev = current;
                current = current.next;
            }
        }

//        reverse again and return
        return reverseList(head);
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while(current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }


//    recursion; time: O(n), space: O(n)
    public static ListNode removeNodes1(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode nextNode = removeNodes(head.next);
//        if head val is smaller, ignore current head val and pass back next instead
        if(head.val < nextNode.val)
            return nextNode;
//        keep the current head; update it's next to point to received nextNode;
        head.next = nextNode;
        return head;
    }


//    stack; time: O(n), space: O(n)
//    note: result list will always include the right most element. So safe to begin from there;
    public static ListNode removeNodes2(ListNode head) {
        ListNode current = head;
        Deque<ListNode> stack = new ArrayDeque<>();
        while(current != null) {
            stack.push(current);
            current = current.next;
        }

        current = stack.pop();
        int maximum = current.val;
        ListNode resultList = new ListNode(maximum);

        while(!stack.isEmpty()) {
            current = stack.pop();
//            current should not be added to result
            if(current.val < maximum)
                continue;
//            else add new node with current value to the front of result
            ListNode newNode = new ListNode(current.val);
            newNode.next = resultList;
            resultList = newNode;
            maximum = current.val;

        }

        return resultList;
    }

}

/*
You are given the head of a linked list.
Remove every node which has a node with a greater value anywhere to the right side of it.
Return the head of the modified linked list.
Example 1:
Input: head = [5,2,13,3,8]
Output: [13,8]
Explanation: The nodes that should be removed are 5, 2 and 3.
- Node 13 is to the right of node 5.
- Node 13 is to the right of node 2.
- Node 8 is to the right of node 3.
Example 2:
Input: head = [1,1,1,1]
Output: [1,1,1,1]
Explanation: Every node has value 1, so no nodes are removed.

Constraints:
The number of the nodes in the given list is in the range [1, 105].
1 <= Node.val <= 105
 */