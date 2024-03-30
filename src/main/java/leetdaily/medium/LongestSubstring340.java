package leetdaily.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring340 {
    public static void main(String[] args) {
        String s = "eceba";
        System.out.println(lengthOfLongestSubstringKDistinct(s, 2));
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> freqMap = new HashMap<>();
        int maxLength = 0, left = 0;
        for(int right = 0 ; right < s.length() ; right++) {
            char curr = s.charAt(right);
            freqMap.put(curr, freqMap.getOrDefault(curr, 0 ) + 1);
            while(freqMap.size() > k) {
                char c = s.charAt(left);
                freqMap.put(c, freqMap.get(c) - 1);
                if(freqMap.get(c) == 0)
                    freqMap.remove(c);
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}

/*
Given a string s and an integer k, return the length of the longest
substring of s that contains at most k distinct characters.
Example 1:
Input: s = "eceba", k = 2
Output: 3
Explanation: The substring is "ece" with length 3.
Example 2:
Input: s = "aa", k = 1
Output: 2
Explanation: The substring is "aa" with length 2.

Constraints:
1 <= s.length <= 5 * 104
0 <= k <= 50
 */