package leetdaily.medium;

public class SortColors75 {
    public static void main(String[] args) {
        int[] nums = {2,0,1};
        sortColors(nums);
    }

//    one pass (3 pointer approach); time: O(n), space: O(1)
    public static void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1, curr = 0;
//        be mind full of <= for an input like 2,0,1
        while(curr <= right) {
            if(nums[curr] == 0) {
                swap(nums, left, curr);
                curr++; left++;
            } else if(nums[curr] == 2) {
                swap(nums, curr, right);
                right--;
            } else curr++;
        }
    }
    private static void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}

/*
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
You must solve this problem without using the library's sort function.
Example 1:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:
Input: nums = [2,0,1]
Output: [0,1,2]

Constraints:
n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.

Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */