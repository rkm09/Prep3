package leetdaily.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring340 {
    public static void main(String[] args) {
        String s = "eceba";
        System.out.println(lengthOfLongestSubstringKDistinct(s, 2));
    }

//    sliding window; time: O(n), space: O(k) [fastest]
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


//    sliding window II; time: O(n), space: O(k) [slower]
//    we don't need to decrease the size of the window. If we have already found a window of length max_size, then what we need to do next
//    is to search for a larger valid window, for example, a window with length max_size + 1.
//    Therefore, in the following sliding window process, even if the current window with size max_size is not valid, there is no problem, because we have already found a window of length max_size before, so we may as well continue looking for a larger window.
    public static int lengthOfLongestSubstringKDistinct1(String s, int k) {
        Map<Character, Integer> counter = new HashMap<>();
        int maxSize = 0;
        for(int right = 0 ; right < s.length() ; right++) {
            counter.put(s.charAt(right), counter.getOrDefault(s.charAt(right), 0) + 1);
            if(counter.size() <= k)
                maxSize++;
            else {
                char c = s.charAt(right - maxSize);
                counter.put(c, counter.get(c) - 1);
                if(counter.get(c) == 0)
                    counter.remove(c);
            }
        }
        return maxSize;
    }


//    binary search & fixed size sliding window; time: O(nlogn), space: O(n) [slowest]
    public static int lengthOfLongestSubstringKDistinct2(String s, int k) {
        int n = s.length();
        if(k >= n) return n;
        int left = k, right = n;
        while(left < right) {
            int mid = (left + right + 1) / 2;
//            check whether this value of mid is a good fit for window size
            if(isValid(s, mid, k)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    private static boolean isValid(String s, int windowSize, int k) {
        int n = s.length();
        Map<Character, Integer> counter = new HashMap<>();
        for(int i = 0 ; i < windowSize ; i++) {
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }
        if(counter.size() <= k)
            return true;
        for(int i = windowSize ; i < n ; i++) {
            char c1 = s.charAt(i);
            counter.put(c1, counter.getOrDefault(c1, 0) + 1);
            char c2 = s.charAt(i - windowSize);
            counter.put(c2, counter.getOrDefault(c2, 0) - 1);
            if(counter.get(c2) == 0)
                counter.remove(c2);
            if(counter.size() <= k)
                return true;
        }
        return false;
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