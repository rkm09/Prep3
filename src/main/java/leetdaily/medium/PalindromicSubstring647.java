package leetdaily.medium;

public class PalindromicSubstring647 {
    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(countSubstrings(s));
    }
    public static int countSubstrings(String s) {
        int count = 0;
        for(int i = 0 ; i < s.length() ; i++) {
            for(int j = 0 ; j <= i ; j++) {
                if(isPalindrome(s.substring(j, i + 1))) {
                    count++;
                }
            }
        }
        return count;
    }
    private static boolean isPalindrome(String s) {
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