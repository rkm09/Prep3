package leetdaily.easy;

public class Fibonacci509 {
    public static void main(String[] args) {
        System.out.println(fib(4));
    }

    //    [def] dp; iterative; fast
    public static int fib1(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //    [def]; iterative; constant space; time: O(n), space: O(1)
    public static int fib2(int n) {
        if (n <= 1) return n;
        int prev1 = 0, prev2 = 1;
        int current = 0;
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev1 = prev2;
            prev2 = current;
        }
        return current;
    }

    //    [def]; recursion + memo
    public static int fib(int n) {
        Integer[] memo = new Integer[n + 1];
        return fib(n, memo);
    }

    private static int fib(int n, Integer[] memo) {
        if (n <= 1) return n;
        if (memo[n] != null) return memo[n];
        memo[n] = fib(n - 1) + fib(n - 2);
        return memo[n];
    }

}

/*
The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
Given n, calculate F(n).
Example 1:
Input: n = 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
Example 2:
Input: n = 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
Example 3:
Input: n = 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
Constraints:
0 <= n <= 30

 */
