package leetdaily.medium;

public class PalindromicSubstring647 {
    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(countSubstrings(s));
    }

    // expand around possible centres ; time: O(n^2), space: O(n^2)
    public static int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        for(int i = 0 ; i < n ; i++) {
//        count even length palindromes
            ans += countPalindromesAroundCenter(s, i, i) ;
//        count odd length palindromes
            ans += countPalindromesAroundCenter(s, i, i + 1);
        }
        return ans;
    }
    private static int countPalindromesAroundCenter(String s, int l, int r) {
        int count = 0;
        while(l >= 0 && r < s.length()) {
            if(s.charAt(l) != s.charAt(r))
                break;
            l--; r++;
            count++;
        }
        return count;
    }

//  dp; time: O(n^2), space: O(n^2)
    public static int countSubstrings1(String s) {
        int n = s.length(), count = n;
        boolean[][] dp = new boolean[n][n];
//      base case : length 1
        for(int i = 0 ; i < n ; i++)
            dp[i][i] = true;
//      base case : length 2
        for(int i = 0 ; i < n - 1; i++) {
            dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
            count += dp[i][i + 1] ? 1 : 0;
        }

        for(int len = 3 ; len <= n ; len++) {
            for(int i = 0, j = i + len - 1 ; j < n ; i++, j++) {
                dp[i][j] = dp[i+1][j-1] && (s.charAt(i) == s.charAt(j));
                count += dp[i][j] ? 1 : 0;
            }
        }
        return count;
    }

    //    [def]; brute force (starts with); time: O(N^3), space: O(1)
    public static int countSubstrings2(String s) {
        int count = 0;
        for(int start = 0 ; start < s.length() ; start++) {
            for(int end = start ; end < s.length() ; end++) {
                if(isPalindrome(s, start, end))
                    count++;
            }
        }
        return count;
    }
    private static boolean isPalindrome(String s, int start, int end) {
        while(start < end) {
            if(s.charAt(start) != s.charAt(end))
                return false;
            start++; end--;
        }
        return true;
    }

//    [def]; brute force(ends with); time: O(N^3), space: O(1)
    public static int countSubstringsN(String s) {
        int count = 0;
        for(int i = 0 ; i < s.length() ; i++) {
            for(int j = 0 ; j <= i ; j++) {
                if(isPalindromeN(s.substring(j, i + 1))) {
                    count++;
                }
            }
        }
        return count;
    }
    private static boolean isPalindromeN(String s) {
        int l = 0, r = s.length() - 1;
        while(l < r) {
            if(s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++; r--;
        }
        return true;
    }
}

/*
Given a string s, return the number of palindromic substrings in it.
A string is a palindrome when it reads the same backward as forward.
A substring is a contiguous sequence of characters within the string.
Example 1:
Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Constraints:
1 <= s.length <= 1000
s consists of lowercase English letters.
 */