package leetdaily.medium;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongest2958 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1,2,3,2};
        System.out.println(maxSubarrayLength(nums, 2));
    }

//    sliding window; time: O(n), space: O(n)
    public static int maxSubarrayLength(int[] nums, int k) {
        int start = -1, res = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for(int end = 0 ; end < nums.length ; end++) {
            freq.put(nums[end], freq.getOrDefault(nums[end], 0) + 1);
//            shrink window
            while(freq.get(nums[end]) > k) {
                start++;
                freq.put(nums[start], freq.get(nums[start]) - 1);
            }
            res = Math.max(res, end - start);
        }
        return res;
    }

//    sliding window with single loop; time: O(n), space: O(n)
    public static int maxSubarrayLength1(int[] nums, int k) {
        int start = 0, freqOverK = 0, n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for(int end = 0 ; end < nums.length ; end++) {
            freq.put(nums[end], freq.getOrDefault(nums[end], 0) + 1);
//            shrink window
            if(freq.get(nums[end]) == k + 1)
                freqOverK++;
            if(freqOverK > 0) {
                freq.put(nums[start], freq.get(nums[start]) - 1);
                if(freq.get(nums[start]) == k)
                    freqOverK--;
                start++;
            }
        }
        return n - start;
    }
}

/*
You are given an integer array nums and an integer k.
The frequency of an element x is the number of times it occurs in an array.
An array is called good if the frequency of each element in this array is less than or equal to k.
Return the length of the longest good subarray of nums.
A subarray is a contiguous non-empty sequence of elements within an array.
Example 1:
Input: nums = [1,2,3,1,2,3,1,2], k = 2
Output: 6
Explanation: The longest possible good subarray is [1,2,3,1,2,3] since the values 1, 2, and 3 occur at most twice in this subarray. Note that the subarrays [2,3,1,2,3,1] and [3,1,2,3,1,2] are also good.
It can be shown that there are no good subarrays with length more than 6.
Example 2:
Input: nums = [1,2,1,2,1,2,1,2], k = 1
Output: 2
Explanation: The longest possible good subarray is [1,2] since the values 1 and 2 occur at most once in this subarray. Note that the subarray [2,1] is also good.
It can be shown that there are no good subarrays with length more than 2.
Example 3:
Input: nums = [5,5,5,5,5,5,5], k = 4
Output: 4
Explanation: The longest possible good subarray is [5,5,5,5] since the value 5 occurs 4 times in this subarray.
It can be shown that there are no good subarrays with length more than 4.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= k <= nums.length
 */
