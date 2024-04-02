package top150.strings_arrays;

import java.util.*;

public class ReverseWords151 {
    public static void main(String[] args) {
        String s = "  the    sky is blue  ";
        System.out.println(reverseWords2(s));
    }

//    [def]; using built-in split & trim; time: O(n), space: O(n) [fast]
    public static String reverseWords1(String s) {
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(int i = words.length - 1 ; i >= 0 ; i--) {
            sb.append(words[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

//    built-in reverse and join; time: O(n), space: O(n)
    public static String reverseWords(String s) {
        s = s.trim();
        List<String> words = Arrays.asList(s.split("\\s+"));
        Collections.reverse(words);
        return String.join(" ", words);
    }


//    using deque of words; time: O(n), space: O(n)
    public static String reverseWords2(String s) {
        int left = 0, right = s.length() - 1;
//        remove leading spaces
        while(left <= right && s.charAt(left) == ' ') ++left;
//        remove trailing spaces
        while(left <= right && s.charAt(right) == ' ') --right;

        Deque<String> stack = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        while(left <= right) {
            char c = s.charAt(left);
//            push word by word in front of the deque
            if((word.length() != 0) && (c == ' ')) {
                stack.push(word.toString());
                word.setLength(0);
            } else if(c != ' ') {
                word.append(c);
            }
            left++;
        }
        stack.push(word.toString());

        return String.join(" ", stack);
    }

//    without using built-in; string manipulation; time: O(n), space: O(n)
    public static String reverseWords3(String s) {
//        trim spaces
        StringBuilder sb = trimSpaces(s);
//        reverse the whole string
        reverse(sb, 0, sb.length() - 1);
//        reverse each word
        reverseEachWord(sb);

        return sb.toString();
    }

    private static void reverse(StringBuilder sb, int left, int right) {
        while(left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    private static void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;
        while(start < n) {
//            get to the end of the word
            while(end < n && sb.charAt(end) != ' ') end++;
//           reverse the word
            reverse(sb, start, end - 1);
//           set the start pointer to point to next word
            start = end + 1;
//            move end pointer to also point to next word
            end++;
        }
    }

    private static StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
//        remove leading spaces
        while(left <= right && s.charAt(left) == ' ') ++left;
//        remove trailing spaces
        while(left <= right && s.charAt(right) == ' ') --right;

        StringBuilder sb = new StringBuilder();
//        remove multiple spaces between words to a single space
        while(left <= right) {
            char c = s.charAt(left);
            if(c != ' ')
                sb.append(c);
            else if(sb.charAt(sb.length() - 1) != ' ')
                sb.append(c);
            left++;
        }
        return sb;
    }

}

/*
Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.
Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
Example 1:
Input: s = "the sky is blue"
Output: "blue is sky the"
Example 2:
Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:
Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

Constraints:
1 <= s.length <= 104
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.

Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 */