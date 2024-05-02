package leetdaily.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LargestPositive2441 {
    public static void main(String[] args) {
        int[] nums = {-1,10,6,7,-7,1};   // -7,-1,1,6,7,10
        System.out.println(findMaxK(nums));
    }

//    def; time: O(nlogn), space: O(n)
    public static int findMaxK(int[] nums) {
        List<Integer> large = new ArrayList<>();
        List<Integer> small = new ArrayList<>();
        for(int num : nums) {
            if(num > 0) large.add(num);
            else small.add(num);
        }

        large.sort(Comparator.reverseOrder());
        small.sort(Comparator.naturalOrder());

        for(int k : large) {
            for(int j : small) {
                if(Math.abs(j) == k)
                    return k;
            }
        }
        return -1;
    }
}

/*
Given an integer array nums that does not contain any zeros, find the largest positive integer k such that -k also exists in the array.
Return the positive integer k. If there is no such integer, return -1.
Example 1:
Input: nums = [-1,2,-3,3]
Output: 3
Explanation: 3 is the only valid k we can find in the array.
Example 2:
Input: nums = [-1,10,6,7,-7,1]
Output: 7
Explanation: Both 1 and 7 have their corresponding negative values in the array. 7 has a larger value.
Example 3:
Input: nums = [-10,8,6,7,-2,-3]
Output: -1
Explanation: There is no a single valid k, we return -1.

Constraints:
1 <= nums.length <= 1000
-1000 <= nums[i] <= 1000
nums[i] != 0
 */