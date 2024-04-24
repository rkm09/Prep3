package leetdaily.easy;

import java.util.Arrays;

public class NthTribonacci1137 {
    private static int[] cache;
    public static void main(String[] args) {
        System.out.println(tribonacci(4));
    }

//    better bottom up dp; time: O(n), space: O(1)
    public static int tribonacci2(int n) {
        if(n < 3)
            return n > 0 ? 1 : 0;
        int a = 0, b = 1, c = 1;
        for(int i = 3 ; i <= n ; i++) {
            int tmp = a + b + c;
            a = b;
            b = c;
            c = tmp;
        }
        return c;
    }

//    top down recursive dp[def]; time: O(n), space: O(n)
    public static int tribonacci(int n) {
        if(n < 2) return n;
        cache = new int[n + 1];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        cache[2] = 1;
        return helper(n);
    }
    private static int helper(int n) {
        if(cache[n] != -1) return cache[n];
        cache[n] = helper(n - 1) + helper(n - 2) + helper(n - 3);
        return cache[n];
    }

//    bottom up (iterative) dp[def]; time: O(n), space: O(n)
    public static int tribonacci1(int n) {
        if(n < 2) return n;
        cache = new int[n + 1];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        cache[2] = 1;
        for(int i = 3 ; i <= n ; i++) {
            cache[i] = cache[i - 1] + cache[i - 2] + cache[i - 3];
        }
        return cache[n];
    }

}

/*
The Tribonacci sequence Tn is defined as follows:
T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
Given n, return the value of Tn.
Example 1:
Input: n = 4
Output: 4
Explanation:
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
Example 2:
Input: n = 25
Output: 1389537

Constraints:
0 <= n <= 37
The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 */
