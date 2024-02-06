package leetdaily.medium;

import java.util.*;

public class GroupAnagrams49 {
    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
    }

//    sorting (faster); time: O(nklogk), space: O(nk) where n is the length of strs and k is the max length of a string
    public static List<List<String>> groupAnagrams(String[] strs) {
        if(strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> ans = new HashMap<>();
        for(String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            ans.computeIfAbsent(key, k -> new ArrayList<>());
            ans.get(key).add(s);
        }
        return new ArrayList<>(ans.values());
    }

    //    counting sort; time: O(nk), space: O(nk)
    public static List<List<String>> groupAnagrams1(String[] strs) {
        if(strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> ans = new HashMap<>();
        int[] count = new int[26];
        for(String s : strs) {
//            erase each time
            Arrays.fill(count, 0);
            for(char c : s.toCharArray())
                count[c - 'a']++;
            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < 26 ; i++) {
                sb.append("#");
                sb.append(count[i]);
            }
            String key = sb.toString();
            ans.computeIfAbsent(key, k -> new ArrayList<>());
            ans.get(key).add(s);
        }

        return new ArrayList<>(ans.values());
    }
}

/*
Given an array of strings strs, group the anagrams together. You can return the answer in any order.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Example 2:
Input: strs = [""]
Output: [[""]]
Example 3:
Input: strs = ["a"]
Output: [["a"]]
Constraints:
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
 */