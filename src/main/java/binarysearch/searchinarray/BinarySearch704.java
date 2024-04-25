package binarysearch.searchinarray;

public class BinarySearch704 {
    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        System.out.println(search(nums, 9));
    }

//    bin search (find the exact value); time: O(logn), space: O(1)
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target)
                return mid;
            if(nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

//    insert at upper bound; time: O(logn), space: O(1)
//    instead of looking for target in the array nums, we look for the insert position (insert into the rightmost possible position)
    public static int search1(int[] nums, int target) {
//      the maximum insert position will be nums.length
        int left = 0, right = nums.length;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] <= target)
                left = mid + 1;
            else
                right = mid;
        }
        if(left > 0 && nums[left - 1] == target)
            return left - 1;
        return -1;
    }

//    insert at lower bound; time: O(logn), space: O(1)
//    insert into the leftmost possible position
    public static int search2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }
        if(left < nums.length && nums[left] == target)
            return left;
        return -1;
    }
}

/*
Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
You must write an algorithm with O(log n) runtime complexity.
Example 1:
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
Example 2:
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1

Constraints:
1 <= nums.length <= 104
-104 < nums[i], target < 104
All the integers in nums are unique.
nums is sorted in ascending order.
 */
