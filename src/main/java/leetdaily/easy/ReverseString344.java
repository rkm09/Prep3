package leetdaily.easy;

public class ReverseString344 {
    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};
        reverseString(s);
    }

//    two pointer; time: O(n), space: O(1)
    public static void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while(left < right) {
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }

//    recursion; time: O(n), space: O(n)
    public static void reverseString1(char[] s) {
        helper(s, 0, s.length - 1);
    }
    private static void helper(char[] s, int left, int right) {
        if(left >= right) return;
        char temp = s[left];
        s[left++] = s[right];
        s[right--] = temp;
        helper(s, left, right);
    }
}

/*
Write a function that reverses a string. The input string is given as an array of characters s.
You must do this by modifying the input array in-place with O(1) extra memory.
Example 1:
Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:
Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]

Constraints:

1 <= s.length <= 105
s[i] is a printable ascii character.
 */