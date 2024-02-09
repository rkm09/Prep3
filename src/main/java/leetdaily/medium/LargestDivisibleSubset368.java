package leetdaily.medium;

import java.util.*;

public class LargestDivisibleSubset368 {
    private static Map<Integer, List<Integer>> edsMemo;
    public static void main(String[] args) {
        int[] nums = {1,2,4,7,8};
        System.out.println(largestDivisibleSubset(nums));
    }

//    dp; time: O(n^2), space: O(n^2)
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        List<List<Integer>> eds = new ArrayList<>();
        for(int num : nums) eds.add(new ArrayList<>());
        Arrays.sort(nums);
        for(int i = 0 ; i < n ; i++) {
            List<Integer> maxSubset = new ArrayList<>();
            for(int k = 0 ; k < i ; k++) {
                if(nums[i] % nums[k] == 0 && maxSubset.size() < eds.get(k).size()) {
                    maxSubset = eds.get(k);
                }
            }
            eds.get(i).addAll(maxSubset);
            eds.get(i).add(nums[i]);
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            if(res.size() < eds.get(i).size())
                res = eds.get(i);
        }
        return res;
    }

//  recursion with memoization; time: O(n^2), space: O(n^2)
    public static List<Integer> largestDivisibleSubset1(int[] nums) {
        int n = nums.length;
        if(n == 0) return new ArrayList<>();
        edsMemo = new HashMap<>();
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            List<Integer> maxSubSet = eds(nums, i);
            if(res.size() < maxSubSet.size())
                res = maxSubSet;
        }
        return res;
    }
    private static List<Integer> eds(int[] nums, int i) {
//        memoization
        if(edsMemo.containsKey(i))
            return edsMemo.get(i);

        List<Integer> maxSubSet = new ArrayList<>();
        for(int k = 0 ; k < i ; k++) {
            if(nums[i] % nums[k] == 0) {
                List<Integer> subList = eds(nums, k);
                if(maxSubSet.size() < subList.size())
                    maxSubSet = subList;
            }
        }
        List<Integer> newEntry = new ArrayList<>();
        newEntry.addAll(maxSubSet);
        newEntry.add(nums[i]);

        edsMemo.put(i, newEntry);
        return newEntry;
    }
}

/*
Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.
Example 1:
Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
Example 2:
Input: nums = [1,2,4,8]
Output: [1,2,4,8]
Constraints:
1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 109
All the integers in nums are unique.
 */