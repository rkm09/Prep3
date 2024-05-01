package leetdaily.easy;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReversePrefix500 {
    public static void main(String[] args) {
        String word = "abcdefd";
        System.out.println(reversePrefix(word, 'd'));
    }

//    sb[def]; time: O(n), space: O(n)
    public static String reversePrefix(String word, char ch) {
        int idx = word.indexOf(ch);
        if(idx == -1) return word;
        StringBuilder sb = new StringBuilder();
        String prefix = word.substring(0, idx + 1);
        String suffix = word.substring(idx + 1);
        sb.append(prefix);
        sb.reverse().append(suffix);

        return sb.toString();
    }

//    find idx and fill res; time: O(n), space: O(n)
    public static String reversePrefix1(String word, char ch) {
        int idx = word.indexOf(ch);
        if(idx == -1) return word;
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < word.length() ; i++) {
            if(i <= idx) {
                result.append(word.charAt(idx - i));
            } else
                result.append(word.charAt(i));
        }
        return result.toString();
    }

//    two pointer; time: O(n), space: O(n)
    public static String reversePrefix2(String word, char ch) {
        char[] arr = word.toCharArray();
        int left = 0;
        for(int right = 0 ; right < word.length() ; right++) {
            if(word.charAt(right) == ch) {
                while(left < right) {
                    swap(arr, left, right);
                    left++;
                    right--;
                }
                return new String(arr);
            }
        }
        return word;
    }

    private static void swap(char[] arr, int idx1, int idx2) {
        char temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

//    stack; time: O(n), space: O(n) [slowest]
    public static String reversePrefix3(String word, char ch) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder res = new StringBuilder();
        int index = 0, n = word.length();
        while(index < n) {
            char c = word.charAt(index);
            stack.push(c);
            if(c == ch) {
                while(!stack.isEmpty())
                    res.append(stack.pop());
                index++;
                while(index < n) {
                    res.append(word.charAt(index));
                    index++;
                }
                return res.toString();
            }
            index++;
        }
        return word;
    }
}

/*
Given a 0-indexed string word and a character ch, reverse the segment of word that starts at index 0 and ends at the index of the first occurrence of ch (inclusive). If the character ch does not exist in word, do nothing.
For example, if word = "abcdefd" and ch = "d", then you should reverse the segment that starts at 0 and ends at 3 (inclusive). The resulting string will be "dcbaefd".
Return the resulting string.
Example 1:
Input: word = "abcdefd", ch = "d"
Output: "dcbaefd"
Explanation: The first occurrence of "d" is at index 3.
Reverse the part of word from 0 to 3 (inclusive), the resulting string is "dcbaefd".
Example 2:
Input: word = "xyxzxe", ch = "z"
Output: "zxyxxe"
Explanation: The first and only occurrence of "z" is at index 3.
Reverse the part of word from 0 to 3 (inclusive), the resulting string is "zxyxxe".
Example 3:
Input: word = "abcd", ch = "z"
Output: "abcd"
Explanation: "z" does not exist in word.
You should not do any reverse operation, the resulting string is "abcd".

Constraints:
1 <= word.length <= 250
word consists of lowercase English letters.
ch is a lowercase English letter.
 */