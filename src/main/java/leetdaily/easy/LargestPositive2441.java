package leetdaily.easy;

import java.util.*;

public class LargestPositive2441 {
    public static void main(String[] args) {
        int[] nums = {-1,10,6,7,-7,1};   // -7,-1,1,6,7,10
        System.out.println(findMaxK(nums));
    }

//    one pass bitset; time: O(n), space: O(1)
    public static int findMaxK(int[] nums) {
        BitSet seen = new BitSet(2048);
        int ans = -1;
        for(int num : nums) {
            int absNum = Math.abs(num);
            if(absNum > ans && seen.get(-num + 1024))
                ans = absNum;
            seen.set(num + 1024);
        }
        return ans;
    }

//    one pass hashset; time: O(n), space: O(n)
    public static int findMaxK1(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        int ans = -1;
        for(int num : nums) {
            int absNum = Math.abs(num);
//            if current is greater and its negation is present in seen
            if(absNum > ans && seen.contains(-num))
                ans = absNum;
//            insert the current num into seen set
            seen.add(num);
        }
        return ans;
    }

//    two pass hashset; time: O(n), space: O(n)
    public static int findMaxK2(int[] nums) {
        Set<Integer> neg = new HashSet<>();
        for(int num : nums) {
            if(num < 0)
                neg.add(num);
        }
        int ans = -1;
        for(int num : nums) {
            if(num > ans && neg.contains(-num))
                ans = num;
        }
        return ans;
    }

//    two pointer; time: O(nlogn), space: O(1)
    public static int findMaxK3(int[] nums) {
        Arrays.sort(nums);
        int lo = 0, hi = nums.length - 1;
        while(lo < hi) {
            if(-nums[lo] == nums[hi])
                return nums[hi];
            if(-nums[lo] > nums[hi])
                lo++;
            else
                hi--;
        }
        return -1;
    }

//    def; time: O(n^2) [faster than brute force though], space: O(n)
    public static int findMaxK4(int[] nums) {
        List<Integer> large = new ArrayList<>();
        List<Integer> small = new ArrayList<>();
        for(int num : nums) {
            if(num > 0) large.add(num);
            else small.add(num);
        }

        large.sort(Comparator.reverseOrder());

        for(int k : large) {
            for(int j : small) {
                if(Math.abs(j) == k)
                    return k;
            }
        }
        return -1;
    }


//    brute force; time: O(n^2), space: O(1)
    public static int findMaxK5(int[] nums) {
        int ans = -1;
        for(int i : nums) {
            for(int j : nums) {
                if(i == -j) {
                    ans = Math.max(ans, Math.abs(i));
                }
            }
        }
        return ans;
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