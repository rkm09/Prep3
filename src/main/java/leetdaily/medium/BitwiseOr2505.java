package leetdaily.medium;

import java.util.ArrayList;
import java.util.List;

public class BitwiseOr2505 {
    public static void main(String[] args) {
        int[] nums = {2,1,0,3};
        System.out.println(subsequenceSumOr(nums));
    }

//    prefix sum; time: O(n), space: O(1)
    public static long subsequenceSumOr(int[] nums) {
        long prefixSum = 0, result = 0;
        for(int num : nums) {
//            update the cumulative sum by adding the current element
            prefixSum += num;
//          update the result by performing the bitwise OR with the current element and cumulative sum
            result |= num | prefixSum;
        }
        return result;
    }
}

/*
Given an integer array nums, return the value of the bitwise OR of the sum of all possible subsequences in the array.
A subsequence is a sequence that can be derived from another sequence by removing zero or more elements without changing the order of the remaining elements.
Example 1:
Input: nums = [2,1,0,3]
Output: 7
Explanation: All possible subsequence sums that we can have are: 0, 1, 2, 3, 4, 5, 6.
And we have 0 OR 1 OR 2 OR 3 OR 4 OR 5 OR 6 = 7, so we return 7.
Example 2:
Input: nums = [0,0,0]
Output: 0
Explanation: 0 is the only possible subsequence sum we can have, so we return 0.

Constraints:
1 <= nums.length <= 105
0 <= nums[i] <= 109
 */