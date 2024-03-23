package leetdaily.easy;

import common.ListNode;

public class MergeTwoSortedLists21 {
    public static void main(String[] args) {
        ListNode next2 = new ListNode(3);
        ListNode next1 = new ListNode(2, next2);
        ListNode head1 = new ListNode(1, next1);

        ListNode next3 = new ListNode(5);
        ListNode head2 = new ListNode(4, next3);
        ListNode res = mergeTwoLists1(head1, head2);
        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
//    recursion; time: O(n+m), space: O(n+m)
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        if(list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

//    iterative; time: O(n+m), space: O(1)
    public static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode preHead = new ListNode(-1);
        ListNode curr = preHead;
        while(list1 != null && list2 != null) {
            if(list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        curr.next = list1 == null ? list2 : list1;
        return preHead.next;
    }
}
