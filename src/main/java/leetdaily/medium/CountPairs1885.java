package leetdaily.medium;

public class CountPairs1885 {
    public static void main(String[] args) {
        int[] nums1 = {2,1,2,1};
        int[] nums2 = {1,2,1,2};
        System.out.println(countPairs(nums1, nums2));
    }
    public static long countPairs(int[] nums1, int[] nums2) {

        return 0L;
    }
}

/*
Given two integer arrays nums1 and nums2 of length n, count the pairs of indices (i, j) such that i < j and nums1[i] + nums1[j] > nums2[i] + nums2[j].
Return the number of pairs satisfying the condition.
Example 1:
Input: nums1 = [2,1,2,1], nums2 = [1,2,1,2]
Output: 1
Explanation: The pairs satisfying the condition are:
- (0, 2) where 2 + 2 > 1 + 1.
Example 2:
Input: nums1 = [1,10,6,2], nums2 = [1,4,1,5]
Output: 5
Explanation: The pairs satisfying the condition are:
- (0, 1) where 1 + 10 > 1 + 4.
- (0, 2) where 1 + 6 > 1 + 1.
- (1, 2) where 10 + 6 > 4 + 1.
- (1, 3) where 10 + 2 > 4 + 5.
- (2, 3) where 6 + 2 > 1 + 5.

Constraints:
n == nums1.length == nums2.length
1 <= n <= 105
1 <= nums1[i], nums2[i] <= 105
 */