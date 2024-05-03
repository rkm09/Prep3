package leetdaily.medium;

import java.util.Arrays;

public class CountPairs1885 {
    public static void main(String[] args) {
        int[] nums1 = {2,1,2,1};
        int[] nums2 = {1,2,1,2};
        System.out.println(countPairs(nums1, nums2));
    }

//    sort and two pointer; time: O(nlogn), space: O(n)
//    nums1[i] + nums1[j] > nums2[i] + nums2[j] => nums1[i] + nums1[j] - nums2[i] - nums2[j] > 0
//    => diff(nums[i]) + diff(nums[j]) > 0 => if so, then every pair between the two diffs(sorted) is also valid wrt 'j';
//    eg. sorted diff: [-7,-3,0,5,6]
    public static long countPairs(int[] nums1, int[] nums2) {
        int n = nums1.length;
        long[] difference = new long[n];
//        store the differences
        for(int i = 0 ; i < n ; i++) {
            difference[i] = nums1[i] - nums2[i];
        }
//        imp: sort the differences
        Arrays.sort(difference);
//        count the pairs
        int left = 0, right = n - 1;
        long result = 0L;
        while(left < right) {
            if(difference[left] + difference[right] > 0) {
//                left makes a valid pair with right; right also makes a valid pair with indices in between
                result += right - left;
                right--;
            } else {
                left++;
            }
        }
        return result;
    }


//    sort & binary search; time: O(nlogn), space: O(n)
    public static long countPairs1(int[] nums1, int[] nums2) {
        int n = nums1.length;
        long[] difference = new long[n];
//        calculate differences
        for(int i = 0 ; i < n ; i++) {
            difference[i] = nums1[i] - nums2[i];
        }
//        sort the differences
        Arrays.sort(difference);
        long result = 0L;
//       count valid pairs
        for(int i = 0 ; i < n ; i++) {
//            all indices following i form valid pairs
            if(difference[i] > 0)
                result += n - i - 1;
            else {
//            binary search to find the first index 'j' that forms a valid pair with i
                int left = i + 1, right = n - 1;
                while(left <= right) {
                    int mid = left + (right - left) / 2;
//                    if the diff makes a valid pair search in the left half, else search in the right half
                    if(difference[i] + difference[mid] > 0)
                        right = mid - 1;
                    else
                        left = mid + 1;
                }
//                after the search left points to the first index 'j' that forms a valid pair with i; so we count all the ones following that
                result += n - left;
//                or result += n - right - 1;
            }
        }
        return result;
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