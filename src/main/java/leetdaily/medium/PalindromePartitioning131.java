package leetdaily.medium;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning131 {
    private List<List<String>> result;
    private int n;
    public static void main(String[] args) {
        PalindromePartitioning131 p = new PalindromePartitioning131();
        String s = "aab";
        List<List<String>> res = p.partition(s);
        res.forEach(System.out::println);
    }

//    backtracking; time: O(N*2^N), space: O(N)
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