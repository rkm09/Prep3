package leetdaily.medium;

import common.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;


public class DoubleANum2816 {
    public static void main(String[] args) {
        ListNode next1 = new ListNode(9);
        ListNode next = new ListNode(9, next1);
        ListNode head = new ListNode(9, next);
        ListNode res = doubleIt1(head);
        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

//    one pointer(in-place); time: O(n), space: O(1) [fastest]
    public static ListNode doubleIt(ListNode head) {
        if(head.val > 4)
            head = new ListNode(0, head);
        ListNode node = head;

        while(node != null) {
            node.val = (node.val * 2) % 10;
            if(node.next != null && node.next.val > 4)
                node.val++;
            node = node.next;
        }

        return head;
    }


//    two pointer (in-place); time: O(n), space: O(1) [fastest]
    public static ListNode doubleIt1(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;

        while(curr != null) {
            int twiceOfVal = curr.val * 2;
            if(twiceOfVal < 10)
                curr.val = twiceOfVal;
            else {
                curr.val = twiceOfVal % 10;
                if(prev != null)
                    prev.val++;
                else {
                    head = new ListNode(1, curr);
                }
            }
            prev = curr;
            curr = curr.next;
        }

        return head;
    }

//    recursion; time: O(n), space: O(n)
    public static ListNode doubleIt2(ListNode head) {
        int carry = helper(head);
        if(carry != 0)
            head = new ListNode(carry, head);
        return head;
    }

    private static int helper(ListNode head) {
        if(head == null) return 0;
        int doubledValue = head.val * 2 + helper(head.next);
        head.val = doubledValue % 10;
        return doubledValue / 10;
    }

//    stack; time: O(n), space: O(n)
//    this approach ensures that we handle integer overflow concerns efficiently while also reducing the number of passes 
//    through the linked list. (unlike the next approach)
    public static ListNode doubleIt3(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<>();
        ListNode current = head;

        while(current != null) {
            stack.push(current.val);
            current = current.next;
        }

        ListNode newTail = null;
        int value = 0;

        while(!stack.isEmpty() || value != 0) {
            newTail = new ListNode(0, newTail);
            if(!stack.isEmpty()) {
                value += stack.pop() * 2;
            }
            newTail.val = value % 10;
            value = value / 10;
        }

        return newTail;
    }

//    reverse list; time: O(n), space: O(1)
    public static ListNode doubleIt4(ListNode head) {
        ListNode resList = reverseList(head);
        ListNode current = resList, previous = null;
        int carry = 0;
        while(current != null) {
            int newValue = current.val * 2 + carry;
            current.val = newValue % 10;
            carry = newValue > 9 ? 1 : 0;
            previous = current;
            current = current.next;
        }
        if(carry != 0) {
            previous.next = new ListNode(carry);
        }
        return reverseList(resList);
    }

//    [def]; reverse list; time: O(n), space: O(1) [fast]
    public static ListNode doubleIt5(ListNode head) {
        if(head.val == 0) return head;

        head = reverseList(head);
        ListNode current = head;
        int carry = 0;

        ListNode res = new ListNode(0);
        ListNode temp = res;
        while(current != null) {
            int value = current.val + current.val + carry;
            carry = value / 10;
            ListNode nextNode = new ListNode(value % 10);
            temp.next = nextNode;
            temp = nextNode;
            current = current.next;
            if(current == null && carry != 0) {
                temp.next = new ListNode(carry);
                break;
            }
        }

        return reverseList(res.next);
    }

    private static ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        while(current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }

}

/*
You are given the head of a non-empty linked list representing a non-negative integer without leading zeroes.
Return the head of the linked list after doubling it.
Example 1:
Input: head = [1,8,9]
Output: [3,7,8]
Explanation: The figure above corresponds to the given linked list which represents the number 189. Hence, the returned linked list represents the number 189 * 2 = 378.
Example 2:
Input: head = [9,9,9]
Output: [1,9,9,8]
Explanation: The figure above corresponds to the given linked list which represents the number 999. Hence, the returned linked list reprersents the number 999 * 2 = 1998.

Constraints:
The number of nodes in the list is in the range [1, 104]
0 <= Node.val <= 9
The input is generated such that the list represents a number that does not have leading zeros, except the number 0 itself.
 */