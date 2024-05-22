package leetdaily.medium;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning131 {
    private List<List<String>> result;
    private int n;
    private boolean[][] dp;
    public static void main(String[] args) {
        PalindromePartitioning131 p = new PalindromePartitioning131();
        String s = "aaa";
        List<List<String>> res = p.partition(s);
        res.forEach(System.out::println);
    }

//    backtracking; time: O(N*2^N), space: O(N) [N is the length of the string s, 2^N is the possible number of substrings]
    public List<List<String>> partition(String s) {
        result = new ArrayList<>();
        n = s.length();
        backtrack(0, new ArrayList<>(), s);
        return result;
    }

    private void backtrack(int start, List<String> curr, String s) {
        if(start == n) {
           result.add(new ArrayList<>(curr));
           return;
        }

        for(int end = start ; end < n ; end++) {
              if(isPalindrome(s, start, end)) {
                  curr.add(s.substring(start, end + 1));
                  backtrack(end + 1, curr, s);
                  curr.remove(curr.size() - 1);
              }
        }
    }

    private boolean isPalindrome(String s, int low, int high) {
        while(low < high) {
            if(s.charAt(low++) != s.charAt(high--))
                return false;
        }
        return true;
    }

//    dp; time: O(N*2^N), space: O(N*N)
//    the previous approach performs one extra iteration to determine if a given substring is a palindrome or not.
//    we are repeatedly iterating over the same substring multiple times and the result is always the same.
//    there are overlapping sub problems which could further be optimized with dp;
    public List<List<String>> partition1(String s) {
        n = s.length();
        result = new ArrayList<>();
        dp = new boolean[n][n];
        backtrackDp(0, new ArrayList<>(), s);
        return result;
    }

    private void backtrackDp(int start, List<String> curr, String s) {
        if(start == n) {
            result.add(new ArrayList<>(curr));
            return;
        }
        for(int end = start ; end < n ; end++) {
            if(s.charAt(start) == s.charAt(end) &&
                    (end - start <= 2 || dp[start + 1][end - 1])
            ) {
                dp[start][end] = true;
                curr.add(s.substring(start, end + 1));
                backtrackDp(end + 1, curr, s);
                curr.remove(curr.size() - 1);
            }
        }
    }
}

/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.
Example 1:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:
Input: s = "a"
Output: [["a"]]

Constraints:
1 <= s.length <= 16
s contains only lowercase English letters.

 */