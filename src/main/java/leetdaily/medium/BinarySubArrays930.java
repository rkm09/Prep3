package leetdaily.medium;

import java.util.HashMap;
import java.util.Map;

public class BinarySubArrays930 {
    public static void main(String[] args) {
        int[] nums = {1,0,1,0,1};
        System.out.println(numSubarraysWithSum(nums, 2));
    }

//    sliding window; time: O(n), space: O(1) [fastest]
//    Although there is a nested loop, each pointer starts at 0 and gets incremented at most n times, so each pointer makes just 1 pass through the array.
    public static int numSubarraysWithSum1(int[] nums, int goal) {
        return slidingWindowAtMost(nums, goal) - slidingWindowAtMost(nums, goal - 1);
    }
    private static int slidingWindowAtMost(int[] nums, int goal) {
        int start = 0, currentSum = 0, totalCount = 0;
        for(int end = 0 ; end < nums.length ; end++) {
            currentSum += nums[end];
            while(start <= end && currentSum > goal) {
                currentSum -= nums[start++];
            }
            totalCount += end - start + 1;
        }
        return totalCount;
    }

//    prefix sum with hashmap; time: O(n), space: O(n)
    public static int numSubarraysWithSum(int[] nums, int goal) {
        int totalCount = 0, currentSum = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : nums) {
            currentSum += num;
            if(currentSum == goal) totalCount++;
            if(freq.containsKey(currentSum - goal)) {
                totalCount += freq.get(currentSum - goal);
            }
            freq.put(currentSum, freq.getOrDefault(currentSum, 0) + 1);
        }
        return totalCount;
    }


//    [def]; prefix sum; time: O(n^2), space: O(1)
    public static int numSubarraysWithSum2(int[] nums, int goal) {
        int count = 0;
        for(int i = 0 ; i < nums.length ; i++) {
            int prefixSum = 0;
            for(int j = i ; j < nums.length ; j++) {
                prefixSum += nums[j];
                if(prefixSum == goal) count++;
            }
        }
        return count;
    }
}

/*
Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
A subarray is a contiguous part of the array.

Example 1:
Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
Example 2:
Input: nums = [0,0,0,0,0], goal = 0
Output: 15

Constraints:
1 <= nums.length <= 3 * 104
nums[i] is either 0 or 1.
0 <= goal <= nums.length
 */