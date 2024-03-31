package leetdaily.hard;

import java.util.ArrayList;
import java.util.List;

public class CountSubArrays2444 {
    public static void main(String[] args) {
        int[] nums = {1,3,5,2,7,5};
        System.out.println(countSubarrays(nums, 1,5));
    }

//    two pointers; time: O(n), space: O(1)
    public static long countSubarrays(int[] nums, int minK, int maxK) {
        long answer = 0;
//        minPosition -> most RECENT minK position, maxPosition -> most RECENT maxK position
//        leftBound -> the most RECENT value outside the range [minK, maxK]
        int minPosition = -1, maxPosition = -1, leftBound = -1;
//        iterate to find sub arrays ending at i;
        for(int i = 0 ; i < nums.length ; i++) {
//            update leftBound with the most RECENT, if found outside the range
            if(nums[i] < minK || nums[i] > maxK)
                leftBound = i;
//            mark the most RECENT indices (smaller of the two (close to left)) of minK & maxK;
            if(nums[i] == minK)
                minPosition = i;
            if(nums[i] == maxK)
                maxPosition = i;
//          minimum of the two to cover at least one pair of min and max position, minimum actually covers more ground at the index level;
//          negative value of min(minPos, maxPos) - leftBound indicates that there is no valid subArray ending at i;
            answer += Math.max(0, Math.min(minPosition, maxPosition) - leftBound);
        }
        return answer;
    }
}

/*
You are given an integer array nums and two integers minK and maxK.
A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
The minimum value in the subarray is equal to minK.
The maximum value in the subarray is equal to maxK.
Return the number of fixed-bound subarrays.
A subarray is a contiguous part of an array.
Example 1:
Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
Output: 2
Explanation: The fixed-bound subarrays are [1,3,5] and [1,3,5,2].
Example 2:
Input: nums = [1,1,1,1], minK = 1, maxK = 1
Output: 10
Explanation: Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.

Constraints:
2 <= nums.length <= 105
1 <= nums[i], minK, maxK <= 106
 */