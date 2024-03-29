package leetdaily.medium;


import java.util.Arrays;

public class PartitionArray1043 {
    private static Integer[] dp;
    public static void main(String[] args) {
        int[] arr = {1,15,7,9,2,5,10};
//        System.out.println(Arrays.toString(arr));
        System.out.println(maxSumAfterPartitioning1(arr, 3));
    }

//    top down dp; time: O(n.k), space: O(n)
    public static int maxSumAfterPartitioning(int[] arr, int k) {
        dp = new Integer[arr.length];
        return maxSum(arr, k, 0);
    }
    private static int maxSum(int[] arr, int k, int start) {
        int n = arr.length;
        if(start >= n) {
            return 0;
        }
        if(dp[start] != null) {
            return dp[start];
        }
        int currSum = 0, ans = 0;
        int end = Math.min(n, start + k);
        for(int i = start ; i < end ; i++) {
            currSum = Math.max(currSum, arr[i]);
            ans = Math.max(ans, currSum * (i - start + 1) + maxSum(arr, k, i + 1));
        }
        return dp[start] = ans;
    }

//    bottom up dp; time: O(n.k), space: O(n)
    public static int maxSumAfterPartitioning1(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        for(int start = n - 1 ; start >= 0 ; start--) {
            int currSum = 0, ans = 0;
            int end = Math.min(start + k, n);
            for(int i = start; i < end; i++) {
                currSum = Math.max(currSum, arr[i]);
                ans = Math.max(ans, currSum * (i - start + 1) + dp[i + 1]);
            }
            dp[start] = ans;
        }
        return dp[0];
    }

}

/*
Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.
Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.
Example 1:
Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]
Example 2:
Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83
Example 3:
Input: arr = [1], k = 1
Output: 1
Constraints:
1 <= arr.length <= 500
0 <= arr[i] <= 109
1 <= k <= arr.length
 */