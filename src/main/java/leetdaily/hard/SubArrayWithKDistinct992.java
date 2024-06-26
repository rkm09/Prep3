package leetdaily.hard;

import java.util.HashMap;
import java.util.Map;

public class SubArrayWithKDistinct992 {
    public static void main(String[] args) {
        int[] nums = {1,2,1,2,3};
        System.out.println(subarraysWithKDistinct1(nums, 2));
    }

//    sliding window; time: O(n), space: O(n)
    public static int subarraysWithKDistinct(int[] nums, int k) {
        return slidingWindowAtMostK(nums, k) - slidingWindowAtMostK(nums, k - 1);
    }
    private static int slidingWindowAtMostK(int[] nums, int distinctK) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int left = 0, totalCount = 0;
        for(int right = 0 ; right < nums.length ; right++) {
            freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);
            while(freqMap.size() > distinctK) {
                freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
                if(freqMap.get(nums[left]) == 0)
                    freqMap.remove(nums[left]);
                left++;
            }
            totalCount += (right - left + 1);
        }
        return totalCount;
    }

//    sliding window (one pass); time : O(n), space: O(n) [faster]
    public static int subarraysWithKDistinct1(int[] nums, int k) {
        int n = nums.length;
        int[] distinctCount = new int[n + 1];
        int totalCount = 0, left = 0, right = 0, currCount = 0;
        while(right < n) {
//            increment the count of the current element in the window; decrease k only if distinct
            if(distinctCount[nums[right++]]++ == 0)
                k--;
//            if k becomes negative, adjust the window size from the left
            if(k < 0) {
//                move left pointer until the count of distinct elements becomes valid again
                --distinctCount[nums[left++]];
                k++;
//                previous count cannot be added, reset count for new window
                currCount = 0;
            }
//            if k becomes zero, calculate the sub arrays
            if(k == 0) {
//                while the count of left remains > 1 (duplicates), keep shrinking the window from the left
                while(distinctCount[nums[left]] > 1) {
                    --distinctCount[nums[left++]];
                    currCount++;
                }
//                add 1 for the current sub array
                totalCount += (currCount + 1);
            }
        }
        return totalCount;
    }
}

/*
Given an integer array nums and an integer k, return the number of good subarrays of nums.
A good array is an array where the number of different integers in that array is exactly k.
For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.
Example 1:
Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
Example 2:
Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].

Constraints:
1 <= nums.length <= 2 * 104
1 <= nums[i], k <= nums.length
 */