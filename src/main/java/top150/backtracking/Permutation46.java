package top150.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutation46 {
    private static List<List<Integer>> results;
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(permute(nums));
    }

//    time: O(n.n!), space: O(n)
    public static List<List<Integer>> permute(int[] nums) {
        results = new ArrayList<>();
        backtrack(nums, new ArrayList<>());
        return results;
    }
    private static void backtrack(int[] nums, List<Integer> curr) {
        if(curr.size() == nums.length) {
            results.add(new ArrayList<>(curr));
            return;
        }
        for(int num : nums) {
            if(!curr.contains(num)) {
                curr.add(num);
                backtrack(nums, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }

}

/*
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:
Input: nums = [1]
Output: [[1]]
Constraints:
1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
 */