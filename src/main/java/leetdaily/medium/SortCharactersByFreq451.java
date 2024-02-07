package leetdaily.medium;

import java.util.*;

public class SortCharactersByFreq451 {
    public static void main(String[] args) {
        String s = "tree";
        System.out.println(frequencySort1(s));
    }

//    hashmap & sorting [an alternative to def]; time: O(nlogn), space: O(n); fast
    public static String frequencySort1(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for(char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        List<Character> charList = new ArrayList<>(counts.keySet());
        Collections.sort(charList, (a,b)-> counts.get(b) - counts.get(a));
        StringBuilder sb = new StringBuilder();
        for(char c : charList) {
           int copies = counts.get(c);
           for(int i = 0 ; i < copies ; i++)
               sb.append(c);
        }
        return sb.toString();
    }

//    [def]; hashmap & sorting; time: O(nlogn), space: O(n)
    public static String frequencySortN(String s) {
        Map<Character, Integer> count = new HashMap<>();
        for(char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        List<Map.Entry<Character,Integer>> list = new ArrayList<>(count.entrySet());
        list.sort(Map.Entry.comparingByValue((a,b)->b-a));
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Character, Integer> entry : list) {
            int cnt = entry.getValue();
            char c = entry.getKey();
            int i = 0;
            while(i++ < cnt) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    //    bucket sort; time: O(n), space: O(n)
    public static String frequencySort(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for(char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
//        make the list of buckets and apply bucket sort
        int maxFrequency = Collections.max(counts.values());
        List<List<Character>> buckets = new ArrayList<>();
        for(int i = 0 ; i <= maxFrequency ; i++) {
            buckets.add(new ArrayList<>());
        }
        for(Character key : counts.keySet()) {
            int freq = counts.get(key);
            buckets.get(freq).add(key);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = buckets.size() - 1; i >= 1 ; i--) {
            for(Character c : buckets.get(i)) {
                for(int j = 0 ; j < i ; j++)
                    sb.append(c);
            }
        }
        return sb.toString();
    }
}

/*
Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
Return the sorted string. If there are multiple answers, return any of them.
Example 1:
Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:
Input: s = "cccaaa"
Output: "aaaccc"
Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:
Input: s = "Aabb"
Output: "bbAa"
Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
Constraints:
1 <= s.length <= 5 * 105
s consists of uppercase and lowercase English letters and digits.
 */