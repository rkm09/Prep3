package leetdaily.medium;

import java.util.*;

public class FindAllDuplicates442 {
    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        System.out.println(findDuplicates3(nums));
    }

//    one pass; mark visited elements[in-place]; time: O(n), space: O(n); [fast]
//    ps: this approach works since we have a constraint: 1 <= nums[i] <= n (every num value fits in within array length)
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int num : nums) {
            if(nums[Math.abs(num) - 1] < 0) {
//                add the abs value
                res.add(Math.abs(num));
            }
//            mark visited by multiplying by -1
            nums[Math.abs(num) - 1] *= -1;
        }
        return res;
    }

//    multiple pass; mark visited elements[in-place]; time: O(n), space: O(n); [fast]
    public static List<Integer> findDuplicates2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int num : nums) {
            nums[Math.abs(num) - 1] *= -1;
        }
        for(int num : nums) {
            if(nums[Math.abs(num) - 1] > 0) {
                res.add(Math.abs(num));
                nums[Math.abs(num) - 1] *= -1;
            }
        }
        return res;
    }

//    cycle sort; time: O(n), space:O(n) [fast]
//    The values in nums are in the range 1 to n according to the problem constraints, so we can utilize cycle sort.
//    Cycle sort is a sorting algorithm that can sort a given sequence in a range from a to n by putting each element at the index that corresponds to its value.
//    nums is a zero-indexed array, so an element with the value x will be located at index x - 1. cycle sort is often used to handle duplicates.
//    we use a simplified version of cycle sort since there is no problem if the duplicate of a value is not in the correct position. Duplicates will reside at indexes that do not have a corresponding value in nums.
    public static List<Integer> findDuplicates3(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length, i = 0;
        while(i < n) {
            int rightIdx = nums[i] - 1;
            if(nums[i] != nums[rightIdx]) {
                swap(nums, i, rightIdx);
            } else i++;
        }
        for(int j = 0 ; j < n ; j++) {
            if(nums[j] != j + 1) {
                res.add(nums[j]);
            }
        }
        return res;
    }
    private static void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

//    [def]; hashset; time: O(n), space: O(n)
    public static List<Integer> findDuplicate4(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for(int num : nums) {
            if(seen.contains(num)) {
                res.add(num);
            }
            else seen.add(num);
        }
        return res;
    }

//    sort and compare; time: O(nlogn), space: O(logn)
    public static List<Integer> findDuplicates5(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 1 ; i < nums.length ; i++) {
            if(nums[i] == nums[i - 1]) {
                res.add(nums[i]);
//                skip the next;
                i++;
            }
        }
        return res;
    }

//    brute force; time: O(n^2), space: O(1)
    public static List<Integer> findDuplicates6(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0 ; i < nums.length ; i++) {
            for(int j = i + 1 ; j < nums.length ; j++) {
                if(nums[i] == nums[j]) {
                    res.add(nums[i]);
                    break;
                }
            }
        }
        return res;
    }

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