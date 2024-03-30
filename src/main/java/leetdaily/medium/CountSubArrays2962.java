package leetdaily.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountSubArrays2962 {
    public static void main(String[] args) {
        int[] nums = {1,3,2,3,3};
        System.out.println(countSubarrays(nums, 2));
    }

//    sliding window; time: O(n), space: O(1)
    public static long countSubarrays(int[] nums, int k) {
        int maxNum = Arrays.stream(nums).max().orElse(0);
        int maxNumCount = 0, start = 0;
        long ans = 0;
        for(int end = 0 ; end < nums.length ; end++) {
            if(nums[end] == maxNum)
                maxNumCount++;
            while(maxNumCount == k) {
                if(nums[start] == maxNum)
                    maxNumCount--;
                start++;
            }
            ans += start;
        }
        return ans;
    }

//    track indexes of max element; time: O(n), space: O(n)
    public static long countSubarrays1(int[] nums, int k) {
        int maxElement = Arrays.stream(nums).max().orElse(0);
        List<Integer> indexesOfMaxElement = new ArrayList<>();
        long ans = 0;
        for(int i = 0 ; i < nums.length ; i++) {
            if(nums[i] == maxElement)
                indexesOfMaxElement.add(i);
            int freq = indexesOfMaxElement.size();
            if(freq >= k) {
                ans += indexesOfMaxElement.get(freq - k) + 1;
            }
        }
        return ans;
    }
}

/*
You are given an integer array nums and a positive integer k.
Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.
A subarray is a contiguous sequence of elements within an array.
Example 1:
Input: nums = [1,3,2,3,3], k = 2
Output: 6
Explanation: The subarrays that contain the element 3 at least 2 times are: [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].
Example 2:
Input: nums = [1,4,2,1], k = 3
Output: 0
Explanation: No subarray contains the element 4 at least 3 times.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 106
1 <= k <= 105
 */
