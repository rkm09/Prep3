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

    //    1 pass (2 pointer); time: O(n), space: O(1)
    public static ListNode removeNthFromEnd(ListNode head, int n) {
//        move the current node n steps into the list
        ListNode currentNode = head;
        for(int i = 0 ; i < n ; i++)
            currentNode = currentNode.next;

//        edge case: 1st element removal
        if(currentNode == null)
            return head.next;

//        move both pointers until current node reaches the end of the list
        ListNode nodeBeforeRemoved = head;

        while(currentNode.next != null) {
            currentNode = currentNode.next;
            nodeBeforeRemoved = nodeBeforeRemoved.next;
        }
        nodeBeforeRemoved.next = nodeBeforeRemoved.next.next;

        return head;
    }


//    2 pass (1 pointer); time: O(n), space: O(1)
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode currentNode = head;
        int length = 0;
        while(currentNode != null) {
            currentNode = currentNode.next;
            length++;
        }
//      edge case: remove 1st element
        if(length == n) return head.next;

        int nodeBeforeRemovedIndex = length - n - 1;
        currentNode = head;

        for(int i = 0 ; i < nodeBeforeRemovedIndex ; i++)
            currentNode = currentNode.next;
        currentNode.next = currentNode.next.next;

        return head;
    }

    //    [def] 2 pass (1 pointer); time: O(n), space: O(1)
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
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