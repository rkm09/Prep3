package leetdaily.easy;

import java.util.HashSet;
import java.util.Set;

public class MinCommon2540 {
    private int[] nums2;
    public static void main(String[] args) {
        MinCommon2540 min = new MinCommon2540();
        int[] nums1 = {1,2,3};
        int[] nums2 = {2,4};
        System.out.println(min.getCommon(nums1, nums2));
    }

//    two pointer; time: O(n + m), space: O(1) [fastest]
    public int getCommon1(int[] nums1, int[] nums2) {
        int first = 0, second = 0;
        while(first < nums1.length && second < nums2.length) {
            if(nums1[first] < nums2[second])
                first++;
            else if(nums2[second] < nums1[first])
                second++;
            else
                return nums1[first];
        }
        return -1;
    }

//    hashset [def]; time: O(n + m), space: O(n)
    public int getCommon(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums2)
            set.add(num);
        for(int num : nums1) {
            if(set.contains(num))
                return num;
        }
        return -1;
    }


    //    binary search; time: O(nlogm), space: O(1) [fast]
    public int getCommon2(int[] nums1, int[] nums2) {
        int lo = 0, hi = nums2.length - 1;
        this.nums2 = nums2;
        for(int num :  nums1) {
            if(binarySearch(lo, hi, num) == num) {
                return num;
            }
        }
        return -1;
    }
    private int binarySearch(int lo, int hi, int num) {
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if(num == nums2[mid]) return num;
            if(num < nums2[mid])
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        return nums2[lo];
    }
}

/*
Given two integer arrays nums1 and nums2, sorted in non-decreasing order, return the minimum integer common to both arrays. If there is no common integer amongst nums1 and nums2, return -1.
Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence of that integer.
Example 1:
Input: nums1 = [1,2,3], nums2 = [2,4]
Output: 2
Explanation: The smallest element common to both arrays is 2, so we return 2.
Example 2:
Input: nums1 = [1,2,3,6], nums2 = [2,3,4,5]
Output: 2
Explanation: There are two common elements in the array 2 and 3 out of which 2 is the smallest, so 2 is returned.

Constraints:
1 <= nums1.length, nums2.length <= 105
1 <= nums1[i], nums2[j] <= 109
Both nums1 and nums2 are sorted in non-decreasing order.
 */