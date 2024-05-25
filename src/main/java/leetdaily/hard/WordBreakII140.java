package leetdaily.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakII140 {
    private Set<String> wordSet;
    private List<String> results;
    private int n;
    public static void main(String[] args) {
        WordBreakII140 b = new WordBreakII140();
        String s = "catsanddog";
        List<String> wordDict = List.of("cat","cats","and","sand","dog");
        System.out.println(b.wordBreak(s, wordDict));
    }

//    backtracking; time: O(2^N), space: O(2^N)
    public List<String> wordBreak(String s, List<String> wordDict) {
//        convert wordDict to a set for O(1) lookups
        wordSet = new HashSet<>(wordDict);
        results = new ArrayList<>();
        n = s.length();
        backtrack(s, new StringBuilder(), 0);
        return results;
    }

    private void backtrack(String s, StringBuilder currentSentence, int startIndex) {
//        if we've reached the end, add the current sentence to results
        if(startIndex == n) {
            results.add(currentSentence.toString().trim());
            return;
        }
//        iterate over all possible end indices
        for(int endIndex = startIndex + 1 ; endIndex <= n ; endIndex++) {
            String word = s.substring(startIndex, endIndex);
//            if the word is in the set proceed with backtracking
            if(wordSet.contains(word)) {
                int currentLength = currentSentence.length();
                currentSentence.append(word).append(" ");
//                recursively call backtrack with the new end index
                backtrack(s, currentSentence, endIndex);
//                reset current sentence to its original length
                currentSentence.setLength(currentLength);
            }
        }
    }
}

/*
Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.
Note that the same word in the dictionary may be reused multiple times in the segmentation.
Example 1:
Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:
Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []

Constraints:
1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
Input is generated in a way that the length of the answer doesn't exceed 105.

 */