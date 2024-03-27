package leetdaily.medium;

public class SubArrayProduct713 {
    public static void main(String[] args) {
        int[] nums = {1,5,2,6};
        System.out.println(numSubarrayProductLessThanK1(nums, 100));
    }

//    sliding window; time: O(n), space: O(1)
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
//        edge case (strictly less than k for k = 0 or 1)
        if(k <= 1) return 0;
        int count = 0, product = 1;
        for(int left = 0, right = 0 ; right < nums.length ; right++) {
            product *= nums[right];
//            shrink the window, if required
            while(product >= k) {
                product /= nums[left++];
            }
//         (current window size) take the count of all sub arrays that end in right (that includes right + all those that can extend from it to the left);
            count += right - left + 1;
        }
        return count;
    }

//    binary search; time: O(nlogn), space: O(1)
    public static int numSubarrayProductLessThanK1(int[] nums, int k) {
        if(k == 0) return 0;
        double logK = Math.log(k);
        int count = 0, m = nums.length + 1;
        double[] logsPrefixSum = new double[m];
//      calculate the prefix sum of logarithm of elements
        for(int i = 0 ; i < nums.length ; i++) {
            logsPrefixSum[i + 1] = logsPrefixSum[i] + Math.log(nums[i]);
        }
//      calculate the sub array count with product less than or equal to k
        for(int idx = 0 ; idx < m ; idx++) {
            int low = idx + 1, high = m;
            while(low < high) {
                int mid = low + (high - low) / 2;
                if(logsPrefixSum[mid] < logsPrefixSum[idx] + logK - 1e-9) {
                    low = mid + 1;
                } else
                    high = mid;
            }
            count += low - idx - 1;
        }
        return count;
    }

//    brute force [def]; time: O(n^2), space: O(1)
    public static int numSubarrayProductLessThanK2(int[] nums, int k) {
        int count = 0;
        for(int i = 0 ; i < nums.length ; i++) {
            int product = 1;
            for(int j = i ; j < nums.length ; j++) {
                product = nums[j] * product;
                if(product < k) count++;
                else break;
            }
        }
        return count;
    }
}

/*
Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.
Example 1:
Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Example 2:
Input: nums = [1,2,3], k = 0
Output: 0

Constraints:
1 <= nums.length <= 3 * 104
1 <= nums[i] <= 1000
0 <= k <= 106
 */