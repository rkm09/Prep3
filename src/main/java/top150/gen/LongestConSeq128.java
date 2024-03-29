package top150.gen;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConSeq128 {
    public static void main(String[] args) {
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(longestConsecutive(nums));
    }

    //    time: O(nlogn), space: O(1) [if in place sorting allowed, else O(logn)]
    public static int longestConsecutive1(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;

        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;
        for(int i = 1 ; i < n ; i++) {
//            duplicates ignored
            if (nums[i] != nums[i - 1]) {
                if (nums[i] == nums[i - 1]+1) {
                    currentStreak++;
                } else {
                    longestStreak = Math.max(longestStreak, currentStreak);
                    currentStreak = 1;
                }
            }
        }
        return Math.max(longestStreak, currentStreak);

    }

//    hashset; time: O(n), space: O(n) [though double loop makes it appear to have a time complexity of O(n^2), yet that
//    does not happen as we run the while loop only when there is a beginning; all numbers only scanned once]
//    ps: this approach turns out to be slower
    public static int longestConsecutive(int[] nums) {
        if(nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for(int num : nums)
            set.add(num);
        int longestStreak = 1;
        int currentStreak = 1;
        for(int num : set) {
            if(!set.contains(num - 1)) {
                int currentNum = num;
                currentStreak = 1;
                while(set.contains(currentNum + 1)) {
                     currentStreak++;
                     currentNum++;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }



}

/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
You must write an algorithm that runs in O(n) time.
Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
Constraints:
0 <= nums.length <= 105
-109 <= nums[i] <= 109
 */