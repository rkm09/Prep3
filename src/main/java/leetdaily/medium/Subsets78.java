package leetdaily.medium;

import java.util.ArrayList;
import java.util.List;

public class Subsets78 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> subsets = subsets(nums);
        for(List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }

//    cascading; time: O(N*2^N), space: O(N*2^N)  [generate all subsets and then copy them to result list]
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList<>();
            for (List<Integer> curr : res) {
                newSubsets.add(new ArrayList<>(curr){{add(num);}});
            }

            res.addAll(newSubsets);
        }

        return res;
    }
}

/*
Given an integer array nums of unique elements, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.
Example 1:
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:
Input: nums = [0]
Output: [[],[0]]

Constraints:
1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
 */