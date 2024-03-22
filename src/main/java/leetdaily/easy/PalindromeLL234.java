package leetdaily.easy;

import common.ListNode;

import java.util.ArrayList;
import java.util.List;

public class PalindromeLL234 {
    private static ListNode frontNode;
    public static void main(String[] args) {
        ListNode next3 = new ListNode(4);
        ListNode next2 = new ListNode(2, next3);
        ListNode next1 = new ListNode(1, next2);
        ListNode head = new ListNode(3, next1);
        System.out.println(isPalindrome(head));
    }

    //    in place for O(1) space; time: O(n), space: O(1) [fast]
    public static boolean isPalindrome(ListNode head) {
        if(head == null) return true;
//        reverse the second half with pointer to half the list
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

//        get head for first and second
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while(result && p2 != null) {
            if(p1.val != p2.val) result = false;
            p1 = p1.next;
            p2 = p2.next;
        }
//        clean up as we did in place
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
    //    returns 1st half, as fast is double the speed of slow
    private static ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

//    copy to array list; time: O(n), space: O(n) [fast]
    public static boolean isPalindrome1(ListNode head) {
        List<Integer> values = new ArrayList<>();
        ListNode currentNode = head;
        while(currentNode != null) {
            values.add(currentNode.val);
            currentNode = currentNode.next;
        }
        int front = 0, back = values.size() - 1;
        while(front < back) {
//            be aware of Integer comparison with equals, since it is not int
            if(!values.get(front).equals(values.get(back)))
                return false;
            front++;
            back--;
        }
        return true;
    }

    //    recursion; time: O(n), space: O(n)
    public static boolean isPalindrome2(ListNode head) {
        frontNode = head;
        return checkRecursively(head);
    }
    private static boolean checkRecursively(ListNode currentNode) {
        if(currentNode != null) {
            if(!checkRecursively(currentNode.next)) return false;
            if(currentNode.val != frontNode.val) return false;
            frontNode = frontNode.next;
        }
        return true;
    }

}

/*
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
Example 1:
Input: head = [1,2,2,1]
Output: true
Example 2:
Input: head = [1,2]
Output: false

Constraints:
The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9

Follow up: Could you do it in O(n) time and O(1) space?
 */