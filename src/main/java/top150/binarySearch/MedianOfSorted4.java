package top150.binarySearch;

import java.util.ArrayList;
import java.util.List;

public class MedianOfSorted4 {
    private int[] nums1;
    private int[] nums2;
    private List<Integer> result;
    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        MedianOfSorted4 med = new MedianOfSorted4();
        System.out.println(med.findMedianSortedArrays(nums1, nums2));
    }

//    binary search; time: O(log(m + n)), space: O(n)
    public  double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0.0;
    }

//    merge sort; time: O(m + n), space: O(1)
    public  double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        return 0.0;
    }

//    linear merge (recursion) [def]; time: O(m + n), space: O(n)
    public  double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        result = new ArrayList<>();
        merge(0, 0);
        int mid = result.size() / 2;
        double median;
        if(result.size() % 2 != 0) {
            median = result.get(mid);
        } else
            median = (double) (result.get(mid - 1) + result.get(mid)) / 2;
        return median;
    }
    private void merge(int idx1, int idx2) {
        if(idx1 == nums1.length || idx2 == nums2.length) {
            if(idx1 < nums1.length)  {
                while(idx1 < nums1.length) {
                    result.add(nums1[idx1++]);
                }
            } else {
                while(idx2 < nums2.length) {
                    result.add(nums2[idx2++]);
                }
            }
            return;
        }
        if(nums1[idx1] <= nums2[idx2]) {
            result.add(nums1[idx1]);
            merge(idx1 + 1, idx2);
        } else {
            result.add(nums2[idx2]);
            merge(idx1, idx2 + 1);
        }
    }
}


/*
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).
Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

Constraints:
nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
 */