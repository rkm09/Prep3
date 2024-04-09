package top150.strings_arrays;

import java.util.ArrayDeque;
import java.util.Deque;

public class RotateArray189 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6};
        rotate1(nums, 2);
    }

//    reverse; time: O(n), space: O(1) [fastest]
    public static void rotate(int[] nums, int k) {
        int n = nums.length - 1;
//        rotations follow cyclical order; so ensure you take the mod;
        k %= nums.length;
        reverse(nums, 0, n);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n);
    }

    private static void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++; end--;
        }
    }

//    cyclic replacement; time: O(n), space: O(1) // an improvement in space with logic being similar to the next
//    when we hit the original number's index again, we start the same process with the number following it.
//    When we reach back the original index, we have placed n/k elements at their correct position;
//    k * n/k = n happens after k cycles whereby i % k == 0;
    public static void rotate1(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        int count = 0;
        for(int start = 0 ; count < n ; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while(start != current);
        }
    }

//    using aux array; place elements at the right index; time: O(n), space: O(n)
    public static void rotate2(int[] nums, int k) {
        int n = nums.length;
        int[] aux = new int[n];
        for(int i = 0 ; i < n ; i++) {
            aux[(i + k) % n] = nums[i];
        }
        for(int i = 0 ; i < n ; i++) {
            nums[i] = aux[i];
        }
    }

//    [def]; time:O(n), space: O(n) [not valid for follow-up O(1) space]
    public static void rotate3(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        for(int num : nums)
            queue.offer(num);
        k %= nums.length;
        while(k-- > 0) {
              queue.addFirst(queue.removeLast());
        }
        int i = 0;
        while(!queue.isEmpty()) {
            nums[i++] = queue.poll();
        }
    }
}

/*
Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
Example 1:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

Constraints:
1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
0 <= k <= 105


Follow up:

Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
Could you do it in-place with O(1) extra space?
 */