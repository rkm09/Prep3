package top150.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class KSmallestPairs373 {
    public static void main(String[] args) {
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        List<List<Integer>> kSmallest = kSmallestPairs(nums1, nums2, 3);
        for(List<Integer> li : kSmallest) {
            System.out.println(li);
        }
    }
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num : nums1)
            pq.add(num);
        for(int num : nums2)
            pq.add(num);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0 ; i < k ; i++) {
            List<Integer> pair = new ArrayList<>();
            int elem = pq.remove();
            result.add(pair);
        }
        return result;
    }
}

/*
You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
Define a pair (u, v) which consists of one element from the first array and one element from the second array.
Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
Example 1:
Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:
Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [[1,1],[1,1]]
Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Constraints:
1 <= nums1.length, nums2.length <= 105
-109 <= nums1[i], nums2[i] <= 109
nums1 and nums2 both are sorted in non-decreasing order.
1 <= k <= 104
k <= nums1.length * nums2.length

 */