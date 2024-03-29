package leetdaily.medium;

public class FirstMissingPositive41 {
    public static void main(String[] args) {
        int[] nums = {1,2,0};
        System.out.println(firstMissingPositive(nums));
    }

//    array as lookup; time: O(n), space: O(n)
//    negative numbers, zeros, and numbers larger than n are not relevant.
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean[] seen = new boolean[n + 1];
        for(int num : nums) {
            if(num > 0 && num <= n)
                seen[num] = true;
        }
        for(int i = 1 ; i <= n ; i++) {
            if(!seen[i]) return i;
        }
//        else return the next positive after array length
        return n + 1;
    }

//    index as a hash key; time: O(n), space: O(n)[modifies input], auxiliary space: O(1)
    public static int firstMissingPositive1(int[] nums) {
        int n = nums.length;
        boolean contains1 = false;
//        (data clean up) mark all nums that are negative, zero or greater than n as 1;
        for(int i = 0 ; i < n ; i++) {
            if(nums[i] == 1)
                contains1 = true;
            if(nums[i] < 1 || nums[i] > n)
                nums[i] = 1;
        }

//        1 itself is missing
        if(!contains1) return 1;

//        mark whether integers 1 to n are present in nums
//        use index as a hash key and negative sign as a presence indicator
//        note we use absolute value to ensure duplicates don't affect sign; since index n not present we store that in the unused index 0;
        for(int i = 0 ; i < n ; i++) {
            int value = Math.abs(nums[i]);
            if(value == n)
                nums[0] = - Math.abs(nums[0]);
            else
                nums[value] = - Math.abs(nums[value]);
        }
//        first positive in nums is the smallest missing positive integer (start from 1)
        for(int i = 1 ; i < n ; i++) {
            if(nums[i] > 0)
                return i;
        }
//        nums[0] stores whether n is in nums
        if(nums[0] > 0)
            return n;
//        if nums contains all elements from 1 to n, the smallest positive integer is n + 1;
        return n + 1;
    }

//    cycle sort;
    public static int firstMissingPositive2(int[] nums) {
        int n = nums.length;
//        use cycle sort to place elements at the correct index
        for(int i = 0 ; i < n ; ) {
            int correctIdx = nums[i] - 1;
            if(nums[i] > 0 && nums[i] <= n && nums[i] != nums[correctIdx]) {
                swap(nums, i, correctIdx);
            } else {
                i++;
            }
        }
//        return the smallest missing positive integer
        for(int i = 0 ; i < n ; i++) {
            if(nums[i] != i + 1)
                return i + 1;
        }
//        else return n + 1;
        return n + 1;
    }
    private static void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}

/*
Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
Example 1:
Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.
Example 2:
Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.
Example 3:
Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.

Constraints:
1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
 */