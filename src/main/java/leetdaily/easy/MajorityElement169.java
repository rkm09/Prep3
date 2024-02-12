package leetdaily.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement169 {
    public static void main(String[] args) {
        int[] nums = {3,2,3};
        System.out.println(majorityElement(nums));
    }

//    boyer moore voting algo, time: O(n), space: O(1) [fast];
    public static int majorityElement(int[] nums) {
        int candidate = 0, count = 0;
        for(int num : nums) {
            if(count == 0)
                candidate = num;
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    //    divide and conquer; time: O(nlogn), space: O(logn) [fast];
    public static int majorityElement1(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }
    private static int majorityElementRec(int[] nums, int lo, int hi) {
        if(lo == hi) return nums[lo];
        int mid = (lo + hi) / 2;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);
        if(left == right) return left;
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);
        return leftCount > rightCount ? left : right;
    }
    private static int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for(int i = lo ; i <= hi ; i++) {
            if(nums[i] == num)
                count++;
        }
        return count;
    }

//    bit manipulation(since the range(-10^9 to 10^9) still fits in 32 bit int); time: O(nlogC) [max absolute value 10^5 in th problem], space: O(1)
    public static int majorityElement2(int[] nums) {
        int n = nums.length;
        int majorityElement = 0;
        for(int i = 0 ; i < 32 ; i++) {
            int bit = 1 << i;
            int bitCount = 0;
            for(int num : nums) {
                if((num & bit) != 0)
                    bitCount++;
            }
            if(bitCount > n / 2)
                majorityElement |= bit;
        }
        return majorityElement;
    }

    //    [def]; time: O(nlogn), space: O(1)
    public static int majorityElement3(int[] nums) {
        Arrays.sort(nums);
        int mid = nums.length/2;
        return nums[mid];
    }

    //    hashmap; time: O(n), space: O(n)
    public static int majorityElement4(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0 ; i < n ; i++)
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        for(int num : map.keySet()) {
            if(map.get(num) > n / 2)
                return num;
        }
        return -1;
    }

}

/*
Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
Example 1:
Input: nums = [3,2,3]
Output: 3
Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2
Constraints:
n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109
Follow-up: Could you solve the problem in linear time and in O(1) space?
 */