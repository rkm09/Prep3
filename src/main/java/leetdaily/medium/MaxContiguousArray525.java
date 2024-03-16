package leetdaily.medium;

import java.util.HashMap;
import java.util.Map;

public class MaxContiguousArray525 {
    public static void main(String[] args) {
        int[] nums = {0,1,0};
        System.out.println(findMaxLength(nums));
    }

//  hashmap; time: O(n), space: O(n)
    public static int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxLength = 0, count = 0;
        map.put(0, -1);
        for(int i = 0 ; i < nums.length ; i++) {
            count += (nums[i] == 1 ? 1 : -1);
            if(map.containsKey(count)) {
                maxLength =  Math.max(maxLength, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxLength;
    }

//    brute force; TLE; time: O(n^2), space: O(1)
    public static int findMaxLength2(int[] nums) {
        int n = nums.length;
        int maxLength = 0;
        int zeroes = 0, ones = 0;
        for(int start = 0 ; start < n ; start++) {
            for(int end = start ; end < n ; end++) {
                if(nums[end] == 0) {
                    zeroes++;
                } else ones++;
                if(zeroes == ones)
                    maxLength = Math.max(maxLength, end - start + 1);
            }
        }
        return maxLength;
    }
}

/*
Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
Example 1:
Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
Example 2:
Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Constraints:
1 <= nums.length <= 105
nums[i] is either 0 or 1.
 */