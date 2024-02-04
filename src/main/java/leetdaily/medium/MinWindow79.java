package leetdaily.medium;

import common.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinWindow79 {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));
    }

//    optimized sliding window; time: O(|s| + |t|), space: O(|s| + |t|)
//  helpful when the length of t << length of s; only store the relevant chars of s
    public static String minWindow(String s, String t) {
        if(s.isEmpty() || t.isEmpty()) return "";
        Map<Character, Integer> targetCount = new HashMap<>();
        for(char c : t.toCharArray()) {
            targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
        }
        int required = targetCount.size();
        List<Pair<Character, Integer>> filteredS = new ArrayList<>();
        for(int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);
            if(targetCount.containsKey(c)) {
                filteredS.add(new Pair<>(c, i));
            }
        }
        Map<Character, Integer> windowCount = new HashMap<>();
        int l = 0, r = 0, formed = 0;
        int[] ans = {-1, 0, 0};
        while(r < filteredS.size()) {
            char c = filteredS.get(r).getKey();
            windowCount.put(c, windowCount.getOrDefault(c, 0) + 1);
            if(targetCount.containsKey(c) && windowCount.get(c).intValue() == targetCount.get(c).intValue()) {
                formed++;
            }
//            keep reducing window size and update ans, as long as it is valid
            while(l <= r && formed == required) {
                char c1 = filteredS.get(l).getKey();
                int start = filteredS.get(l).getValue();
                int end = filteredS.get(r).getValue();
//                save the smallest window so far
                if(ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }
                windowCount.put(c1, windowCount.get(c1) - 1);
                if(windowCount.get(c1) < targetCount.get(c1)) {
                   formed--;
                }
                l++;
            }
            r++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

//    sliding window; time: O(|s| + |t|), space: O(|s| + |t|)
    public static String minWindow1(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";
        Map<Character, Integer> targetCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
        }
        int required = targetCount.size();
        int l = 0, r = 0;
        Map<Character, Integer> windowCount = new HashMap<>();
        int formed = 0;
        int[] ans = {-1, 0, 0};
        while (r < s.length()) {
            char c = s.charAt(r);
            windowCount.put(c, windowCount.getOrDefault(c, 0) + 1);
            if (targetCount.containsKey(c) && windowCount.get(c).intValue() == targetCount.get(c).intValue()) {
                formed++;
            }
            while (l <= r && formed == required) {
                char c1 = s.charAt(l);
                if (ans[0] == -1 || ans[0] > r - l + 1) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }
                windowCount.put(c1, windowCount.get(c1) - 1);
                if (targetCount.containsKey(c1) && windowCount.get(c1) < targetCount.get(c1)) {
                    formed--;
                }
                l++;
            }
            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}

/*
Given two strings s and t of lengths m and n respectively, return the minimum window
substring
 of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
The testcases will be generated such that the answer is unique.
Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
Constraints:
m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.
Follow up: Could you find an algorithm that runs in O(m + n) time?
 */