package leetdaily.medium;

import java.util.*;

public class FindAllDuplicates442 {
    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(findDuplicates1(nums));
    }
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            if(set.contains(num)) {
                res.add(num);
            }
            set.add(num);
        }
        return res;
    }
//    public static List<Integer> findDuplicates1(int[] nums) {
//        Map<Integer, Integer> map = new HashMap<>();
//        for(int num : nums) {
//            map.put(num, map.getOrDefault(num, 0) + 1);
//        }
//        List<Integer> res = map.values().stream().filter(k -> k != 2).toList();
//        return res;
//    }
}

/*
Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears once or twice, return an array of all the integers that appears twice.
You must write an algorithm that runs in O(n) time and uses only constant extra space.
Example 1:
Input: nums = [4,3,2,7,8,2,3,1]
Output: [2,3]
Example 2:
Input: nums = [1,1,2]
Output: [1]
Example 3:
Input: nums = [1]
Output: []

Constraints:
n == nums.length
1 <= n <= 105
1 <= nums[i] <= n
Each element in nums appears once or twice.
 */