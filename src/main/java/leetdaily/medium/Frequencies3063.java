package leetdaily.medium;

import common.ListNode;

import java.util.HashMap;
import java.util.Map;


public class Frequencies3063 {
    public static void main(String[] args) {
        ListNode next5 = new ListNode(3);
        ListNode next4 = new ListNode(2, next5);
        ListNode next3 = new ListNode(1, next4);
        ListNode next2 = new ListNode(2, next3);
        ListNode next1 = new ListNode(1, next2);
        ListNode head = new ListNode(1, next1);
        ListNode res = frequenciesOfElements(head);
        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

//    hashmap; time: O(n), space: O(n) [fast]
    public static ListNode frequenciesOfElements(ListNode head) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        ListNode current = head;
        while(current != null) {
            frequencies.put(current.val, frequencies.getOrDefault(current.val, 0) + 1);
            current = current.next;
        }
        ListNode freqHead = new ListNode(0);
        current = freqHead;
        for(int frequency : frequencies.values()) {
            current.next = new ListNode(frequency);
            current = current.next;
        }
        return freqHead.next;
    }

//    counting sort; time : O(m + n), space: O(n) [n - length of linked list, m - max value in LL] [very slow]
    public static ListNode frequenciesOfElements1(ListNode head) {
        int maxValue = 100000;
        int[] frequencies = new int[maxValue];
        ListNode current = head;
        while(current != null) {
            frequencies[current.val - 1]++;
            current  = current.next;
        }
        ListNode freqHead = new ListNode(0);
        current = freqHead;
        for(int i = 0 ; i < maxValue ; i++) {
            if(frequencies[i] > 0) {
                current.next = new ListNode(frequencies[i]);
                current = current.next;
            }
        }
        return freqHead.next;
    }

}

/*
Given the head of a linked list containing k distinct elements, return the head to a linked list of length k containing the
frequency
 of each distinct element in the given linked list in any order.
Example 1:
Input:  head = [1,1,2,1,2,3]
Output:  [3,2,1]
Explanation: There are 3 distinct elements in the list. The frequency of 1 is 3, the frequency of 2 is 2 and the frequency of 3 is 1. Hence, we return 3 -> 2 -> 1.
Note that 1 -> 2 -> 3, 1 -> 3 -> 2, 2 -> 1 -> 3, 2 -> 3 -> 1, and 3 -> 1 -> 2 are also valid answers.
Example 2:
Input:  head = [1,1,2,2,2]
Output:  [2,3]
Explanation: There are 2 distinct elements in the list. The frequency of 1 is 2 and the frequency of 2 is 3. Hence, we return 2 -> 3.
Example 3:
Input:  head = [6,5,4,3,2,1]
Output:  [1,1,1,1,1,1]
Explanation: There are 6 distinct elements in the list. The frequency of each of them is 1. Hence, we return 1 -> 1 -> 1 -> 1 -> 1 -> 1.

Constraints:
The number of nodes in the list is in the range [1, 105].
1 <= Node.val <= 105
 */