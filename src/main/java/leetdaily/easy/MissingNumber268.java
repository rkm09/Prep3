package leetdaily.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MissingNumber268 {
    public static void main(String[] args) {
        int[] nums  = {3,0,1};
        System.out.println(missingNumber(nums));
    }


//    bit manipulation; time: O(n), space: O(1)
    public static int missingNumber(int[] nums) {
        int missing = nums.length;
        for(int i = 0 ; i < nums.length ; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    //    gauss's formula for natural numbers; time: O(n), space: O(1)
    public static int missingNumber1(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for(int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }

//    hash set; time: O(n), space: O(n)
    public static int missingNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums) set.add(num);
        int expectedNumCount = nums.length + 1;
        for(int number = 0 ; number < expectedNumCount ; number++) {
            if(!set.contains(number))
                return number;
        }
        return -1;
    }

//    sorting; time: O(nlogn), space: O(1) [O(n) if auxiliary]
    public static int missingNumber3(int[] nums) {
        Arrays.sort(nums);
        if(nums[nums.length - 1] != nums.length)
            return nums.length;
        if(nums[0] != 0)
            return 0;
        for(int i = 1 ; i < nums.length ; i++) {
            int expectedNum = nums[i - 1] + 1;
            if(nums[i] != expectedNum)
                return expectedNum;
        }
        return -1;
    }

//    [def]; sorting
    public static int missingNumber4(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0 ; i < nums.length ; i++) {
            if(nums[i] != i)
                return i;
        }
        return nums.length;
    }
}


/*
Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
Example 1:
Input: nums = [3,0,1]
Output: 2
Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
Example 2:
Input: nums = [0,1]
Output: 2
Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
Example 3:
Input: nums = [9,6,4,2,3,5,7,0,1]
Output: 8
Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
Constraints:
n == nums.length
1 <= n <= 104
0 <= nums[i] <= n
All the numbers of nums are unique.
Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 */