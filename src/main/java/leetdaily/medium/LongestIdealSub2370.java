package leetdaily.medium;

public class LongestIdealSub2370 {
    public static void main(String[] args) {
        String s = "pvjcci";
        System.out.println(longestIdealString(s, 4));
    }

//    bottom up dp; time: O(NL) ~ O(N), space: O(L) ~ O(1) as L is constant of length 26
    public static int longestIdealString(String s, int k) {
        int n = s.length();
        int[] dp = new int[26];
        int res = 0;
        // Updating dp with the i-th character
        for (int i = 0; i < n; i++) {
            int curr = s.charAt(i) - 'a';
            int best = 0;
            for (int prev = 0; prev < 26; prev++) {
                if (Math.abs(prev - curr) <= k) {
                    best = Math.max(best, dp[prev]);
                }
            }

            // Appending s[i] to the previous longest ideal subsequence allowed
            dp[curr] = Math.max(dp[curr], best + 1);
            res = Math.max(res, dp[curr]);
        }
        return res;
    }
}

/*
You are given a string s consisting of lowercase letters and an integer k. We call a string t ideal if the following conditions are satisfied:
t is a subsequence of the string s.
The absolute difference in the alphabet order of every two adjacent letters in t is less than or equal to k.
Return the length of the longest ideal string.
A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
Note that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order of 'a' and 'z' is 25, not 1.
Example 1:
Input: s = "acfgbd", k = 2
Output: 4
Explanation: The longest ideal string is "acbd". The length of this string is 4, so 4 is returned.
Note that "acfgbd" is not ideal because 'c' and 'f' have a difference of 3 in alphabet order.
Example 2:
Input: s = "abcd", k = 3
Output: 4
Explanation: The longest ideal string is "abcd". The length of this string is 4, so 4 is returned.

Constraints:
1 <= s.length <= 105
0 <= k <= 25
s consists of lowercase English letters.
 */