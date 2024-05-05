package leetdaily.medium;

import java.util.HashMap;
import java.util.Map;

public class NumOfString1915 {
    public static void main(String[] args) {
        String word = "aabb";
        System.out.println(wonderfulSubstrings(word));
    }

//    count parity prefixes; time: O(NA) ~ O(N) as A is constant of 10, space: O(N) [(A < N) A is the number of distinct characters (10 a->j), N word length]
//    For any substring in the input string word, we can represent it as the difference between two prefixes of s.
//    This is because we can "subtract" the larger prefix from the smaller prefix to create this substring using the ^ (XOR) operator.
//    The XOR function is equivalent to subtraction under modulo 2.
//    Since there are only 10 distinct letters the string can consist of, we can use a bitmask of 10 bits to represent the parities of all letters in a string.
    public static long wonderfulSubstrings(String word) {
        int n = word.length(), mask = 0;
        long res = 0L;

//        create a frequency map; key -> bitmask, value -> frequency of bitmask key
        Map<Integer, Integer> freq = new HashMap<>();
//        empty prefix mask accounts for 1; (this value may be referenced for even count as well)
        freq.put(0, 1);

        for(int i = 0 ; i < n ; i++) {
            char c = word.charAt(i);
            int bit = c - 'a';
//            flip the parity of the cth bit in the running prefix mask
            mask ^= (1 << bit);
//            count smaller even prefixes that create substrings 
            res += freq.getOrDefault(mask, 0);
//            increment value associated with mask by 1
            freq.put(mask, freq.getOrDefault(mask, 0) + 1);

//            loop through every possible letter that can appear an odd number of times in a substring
            for(int oddC = 0 ; oddC < 10 ; oddC++) {
                res += freq.getOrDefault((mask ^ (1 << oddC)), 0);
            }
        }

        return res;
    }
}

/*
A wonderful string is a string where at most one letter appears an odd number of times.
For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number of wonderful non-empty substrings in word. If the same substring appears multiple times in word, then count each occurrence separately.
A substring is a contiguous sequence of characters in a string.
Example 1:
Input: word = "aba"
Output: 4
Explanation: The four wonderful substrings are underlined below:
- "aba" -> "a"
- "aba" -> "b"
- "aba" -> "a"
- "aba" -> "aba"
Example 2:
Input: word = "aabb"
Output: 9
Explanation: The nine wonderful substrings are underlined below:
- "aabb" -> "a"
- "aabb" -> "aa"
- "aabb" -> "aab"
- "aabb" -> "aabb"
- "aabb" -> "a"
- "aabb" -> "abb"
- "aabb" -> "b"
- "aabb" -> "bb"
- "aabb" -> "b"
Example 3:
Input: word = "he"
Output: 2
Explanation: The two wonderful substrings are underlined below:
- "he" -> "h"
- "he" -> "e"

Constraints:
1 <= word.length <= 105
word consists of lowercase English letters from 'a' to 'j'.

 */