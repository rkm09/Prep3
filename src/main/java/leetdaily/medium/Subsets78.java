package leetdaily.medium;

import java.util.ArrayList;
import java.util.List;

public class Subsets78 {
    private List<List<Integer>> output;
    private int k, n;
    public static void main(String[] args) {
        int[] nums = {4,2,3};
        Subsets78 s = new Subsets78();
        List<List<Integer>> subsets = s.subsets2(nums);
        for(List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }

//    cascading; time: O(N*2^N), space: O(N*2^N)
//    generate all subsets with each element in current and then copy them to result list
    public List<List<Integer>> subsets(int[] nums) {
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

//    backtracking; time: O(N*2^N), space: O(N)
//    generate all subsets of a particular length progressively and then backtrack
    public List<List<Integer>> subsets1(int[] nums) {
        n = nums.length;
        output = new ArrayList<>();
        for(k = 0 ; k < n + 1 ; k++) {
            backtrack(0, new ArrayList<>(), nums);
        }
        return output;
    }

    private void backtrack(int first, List<Integer> curr, int[] nums) {
        if(curr.size() == k) {
//            remember can't add curr directly
            output.add(new ArrayList<>(curr));
            return;
        }
        for(int i = first ; i < n ; i++) {
            curr.add(nums[i]);
            backtrack(i + 1, curr, nums);
            curr.remove(curr.size() - 1);
        }
    }

//    lexicographic subsets(binary sorted) subsets; time: O(N*2^N), space: O(N*2^N)
    public List<List<Integer>> subsets2(int[] nums) {
        output = new ArrayList<>();
        n = nums.length;

        for(int i = (int) Math.pow(2, n) ; i < (int) Math.pow(2, n + 1) ; i++) {
//            generate all bitmasks from 00..0 to 11..1
            String bitmask = Integer.toBinaryString(i).substring(1);

//            append subset corresponding to that bitmask
            List<Integer> curr = new ArrayList<>();
            for(int j = 0 ; j < n ; j++) {
                if(bitmask.charAt(j) == '1')
                    curr.add(nums[j]);
            }
            output.add(curr);
        }

        return output;
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