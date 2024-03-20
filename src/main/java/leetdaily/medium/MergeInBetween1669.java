package leetdaily.medium;

import common.ListNode;

public class MergeInBetween1669 {
    public static void main(String[] args) {
        ListNode next3 = new ListNode(3);
        ListNode next2 = new ListNode(2, next3);
        ListNode next1 = new ListNode(1, next2);
        ListNode list = new ListNode(0, next1);
        ListNode next11 = new ListNode(5);
        ListNode list1 = new ListNode(4, next11);
        ListNode res = mergeInBetween(list, 1, 2, list1);
        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

//    two pointer; time: O(n + m), space: O(1)
    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode start = null;
        ListNode end = list1;

        for(int i = 0 ; i < b ; i++) {
            if(i == a - 1) {
                start = end;
            }
            end = end.next;
        }
        start.next = list2;

        while(list2.next != null) {
            list2 = list2.next;
        }

        list2.next = end.next;
//        this step of end.next = null is not necessary, but is a good practise to ensure unpredictable behaviour from the removed nodes
        end.next = null;

        return list1;
    }
}

/*
You are given two linked lists: list1 and list2 of sizes n and m respectively.
Remove list1's nodes from the ath node to the bth node, and put list2 in their place.
The blue edges and nodes in the following figure indicate the result:
Build the result list and return its head.
Example 1:
Input: list1 = [10,1,13,6,9,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
Output: [10,1,13,1000000,1000001,1000002,5]
Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place. The blue edges and nodes in the above figure indicate the result.
Example 2:
Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
Explanation: The blue edges and nodes in the above figure indicate the result.

Constraints:
3 <= list1.length <= 104
1 <= a <= b < list1.length - 1
1 <= list2.length <= 104
 */
