package leetdaily.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestSubString159 {
    public static void main(String[] args) {
        String s = "ccaabbb";
        System.out.println(lengthOfLongestSubstringTwoDistinct(s));
    }

//    sliding window; time: O(n), space: O(n)
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int start = 0, ans = 0;
        Map<Character, Integer> freqMap = new HashMap<>();
        for(int end = 0 ; end < s.length() ; end++) {
            freqMap.put(s.charAt(end), freqMap.getOrDefault(s.charAt(end), 0) + 1);
            while(freqMap.size() > 2) {
                char c = s.charAt(start++);
                freqMap.put(c, freqMap.get(c) - 1);
                if(freqMap.get(c) == 0)
                    freqMap.remove(c);
            }
            ans = Math.max(ans, end - start + 1);
        }
        return ans;
    }
}

/*
Given a string s, return the length of the longest substring that contains at most two distinct characters.
Example 1:
Input: s = "eceba"
Output: 3
Explanation: The substring is "ece" which its length is 3.
Example 2:
Input: s = "ccaabbb"
Output: 5
Explanation: The substring is "aabbb" which its length is 5.

Constraints:
1 <= s.length <= 105
s consists of English letters.
 */