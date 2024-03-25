package leetdaily.medium;

import java.util.*;

public class FindDuplicate287 {
    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        System.out.println(findDuplicate2(nums));
    }

//    [def]; sorting; time: O(nlogn), space: O(1)
    public static int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1 ; i < nums.length ; i++) {
            if(nums[i] == nums[i-1]) return nums[i];
        }
        return -1;
    }

//    set; time: O(n), space: O(n)
    public static int findDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num: nums) {
            if(set.contains(num))
                return num;
            set.add(num);
        }
        return -1;
    }

//    [def]; map
    public static int findDuplicate1(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for(int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
            if(counts.get(num) > 1)
                return num;
        }
        return -1;
    }

    public static int findDuplicate3(int[] nums) {
        int sum = 0, cnt = 0;
        for(int num : nums) {
            sum += num;
            cnt ^= num;
        }
        return (sum - cnt) / 2;
    }
}

/*
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
There is only one repeated number in nums, return this repeated number.
You must solve the problem without modifying the array nums and uses only constant extra space.
Example 1:
Input: nums = [1,3,4,2,2]
Output: 2
Example 2:
Input: nums = [3,1,3,4,2]
Output: 3
Example 3:
Input: nums = [3,3,3,3,3]
Output: 3
Constraints:
1 <= n <= 105
nums.length == n + 1
1 <= nums[i] <= n
All the integers in nums appear only once except for precisely one integer which appears two or more times.

Follow up:
How can we prove that at least one duplicate number must exist in nums?
Can you solve the problem in linear runtime complexity?
 */