package leetdaily.easy;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueChar387 {
    public static void main(String[] args) {
        String s = "loveleetcode";
        System.out.println(firstUniqChar(s));
    }

//    [def]; time: O(n), space: O(1) [as their are 26 letters only]
    public static int firstUniqChar(String s) {
        Map<Character, Integer> count = new LinkedHashMap<>();
        for(char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        for(char c : count.keySet()) {
            if(count.get(c) == 1) {
                return s.indexOf(c);
            }
        }
        return -1;
    }

//    time: O(n), space: O(1)
    public static int firstUniqChar1(String s) {
        Map<Character, Integer> count = new HashMap<>();
        int n = s.length();
        for(int i = 0 ; i < n ; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        for(int i = 0 ; i < n ; i++) {
            if(count.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}

/*
Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
Example 1:
Input: s = "leetcode"
Output: 0
Example 2:
Input: s = "loveleetcode"
Output: 2
Example 3:
Input: s = "aabb"
Output: -1
Constraints:
1 <= s.length <= 105
s consists of only lowercase English letters.
 */