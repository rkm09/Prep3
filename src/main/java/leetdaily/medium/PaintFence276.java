package leetdaily.medium;

import java.util.HashMap;
import java.util.Map;

public class PaintFence276 {
    private static Map<Integer, Integer> memo;
    public static void main(String[] args) {
        System.out.println(numWays(3,2));
    }

//    bottom up dp; time: O(n), space: O(1)
    public static int numWays(int n, int k) {
        if(n == 1) return k;
        int twoPostsBack = k;
        int onePostBack = k * k;
        for(int i = 3; i <= n ; i++) {
            int curr = (k - 1) * (onePostBack + twoPostsBack);
            twoPostsBack = onePostBack;
            onePostBack = curr;
        }
        return onePostBack;
    }

//    bottom up dp; time: O(n), space: O(n)
    public static int numWays1(int n, int k) {
        if(n == 1) return k;
        if(n == 2) return k * k;
        int[] dp = new int[n + 1];
        dp[1] = k;
        dp[2] = k * k;
        for(int i = 3; i <= n ; i++) {
            dp[i] = (k - 1) * (dp[i - 1] + dp[i - 2]);
        }
        return dp[n];
    }

//    top down dp; time: O(n), space: O(n)
    public static int numWays2(int n, int k) {
        memo = new HashMap<>();
        return totalWays(n, k);
    }
    private static int totalWays(int i, int k) {
        if(i == 1) return k;
        if(i == 2) return k * k;
        if(memo.containsKey(i))
            return memo.get(i);
        memo.put(i, (k - 1) * (totalWays(i - 1, k) + totalWays(i - 2, k)));
        return memo.get(i);
    }
}

/*
You are painting a fence of n posts with k different colors. You must paint the posts following these rules:
Every post must be painted exactly one color.
There cannot be three or more consecutive posts with the same color.
Given the two integers n and k, return the number of ways you can paint the fence.
Example 1:
Input: n = 3, k = 2
Output: 6
Explanation: All the possibilities are shown.
Note that painting all the posts red or all the posts green is invalid because there cannot be three posts in a row with the same color.
Example 2:
Input: n = 1, k = 1
Output: 1
Example 3:
Input: n = 7, k = 2
Output: 42
Constraints:
1 <= n <= 50
1 <= k <= 105
The testcases are generated such that the answer is in the range [0, 231 - 1] for the given n and k.
 */