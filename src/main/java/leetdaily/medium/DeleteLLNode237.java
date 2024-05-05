package leetdaily.medium;

import common.ListNode;

public class DeleteLLNode237 {
    public static void main(String[] args) {
        ListNode next2 = new ListNode(4);
        ListNode next1 = new ListNode(2, next2);
        ListNode head = new ListNode(3, next1);
        deleteNode(next1);
        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

//    data overwriting; time: O(1), space: O(1)
//    if head node would've been given, the straightforward way would have been making changes from the previous node's next;
//    this method only works as the node to be deleted is not the last node (as there is no successor to it)
    public static void deleteNode(ListNode node) {
//        overwrite data of next node on current node
       node.val = node.next.val;
//       make current node point to next of next
       node.next = node.next.next;
    }
}

/*
There is a singly-linked list head and we want to delete a node node in it.
You are given the node to be deleted node. You will not be given access to the first node of head.
All the values of the linked list are unique, and it is guaranteed that the given node node is not the last node in the linked list.
Delete the given node. Note that by deleting the node, we do not mean removing it from memory. We mean:
The value of the given node should not exist in the linked list.
The number of nodes in the linked list should decrease by one.
All the values before node should be in the same order.
All the values after node should be in the same order.
Custom testing:
For the input, you should provide the entire linked list head and the node to be given node. node should not be the last node of the list and should be an actual node in the list.
We will build the linked list and pass the node to your function.
The output will be the entire list after calling your function.
Example 1:
Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
Example 2:
Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.

Constraints:
The number of the nodes in the given list is in the range [2, 1000].
-1000 <= Node.val <= 1000
The value of each node in the list is unique.
The node to be deleted is in the list and is not a tail node.
 */