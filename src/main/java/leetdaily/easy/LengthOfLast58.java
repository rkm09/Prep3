package leetdaily.easy;


public class LengthOfLast58 {
    public static void main(String[] args) {
        String s = "   fly me   to   the moon  ";
        System.out.println(s.length());
        System.out.println(lengthOfLastWord1(s));
    }

//    [def]; using built in methods; time: O(n), space: O(n)
    public static int lengthOfLastWord(String s) {
        String[] words = s.split(" ");
        return words[words.length - 1].length();
    }

//    using built in methods; time: O(n), space: O(n) [since returns a copy] [fast]
    public static int lengthOfLastWord1(String s) {
        s = s.trim();
        return s.length() - s.lastIndexOf(" ") - 1;
    }

//    one loop iteration; time: O(n), space: O(1) [fast]
    public static int lengthOfLastWord2(String s) {
        int p = s.length(), length = 0;
        while(p-- > 0) {
            if(s.charAt(p) != ' ')
                length++;
            else if(length > 0)
                return length;
        }
        return length;
    }

//    string index manipulation; time: O(n), space: O(1) [fast]
    public static int lengthOfLastWord3(String s) {
        int p = s.length() - 1;
        while(p >= 0 && s.charAt(p) == ' ')
            p--;
        int length = 0;
        while(p >= 0 && s.charAt(p) != ' ') {
            p--;
            length++;
        }
        return length;
    }
}

/*
Given a string s consisting of words and spaces, return the length of the last word in the string.
A word is a maximal substring consisting of non-space characters only.
Example 1:
Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.
Example 2:
Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.
Example 3:
Input: s = "luffy is still joyboy"
Output: 6
Explanation: The last word is "joyboy" with length 6.

Constraints:
1 <= s.length <= 104
s consists of only English letters and spaces ' '.
There will be at least one word in s.
 */
