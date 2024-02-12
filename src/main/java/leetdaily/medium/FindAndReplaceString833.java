package leetdaily.medium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAndReplaceString833 {
    public static void main(String[] args) {
        String s = "abcd";
        int[] indices = {0,2};
        String[] sources = {"a", "cd"};
        String[] targets = {"eee","ffff"};
        FindAndReplaceString833 find = new FindAndReplaceString833();
        System.out.println(find.findReplaceString(s, indices, sources, targets));
        Replacement replace1 = new Replacement(0, "num", "string");
        Replacement replace2 = new Replacement(4, "foo", "bar");
        String text = "num foo";
        System.out.println(find.replaceText(text, List.of(replace1, replace2)));
    }

//    hashmap (piece table); time: O(n), space: O(n)
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        Map<Integer, Integer> lookup = new HashMap<>();
        for(int i = 0 ; i < indices.length ; i++) {
            if(s.startsWith(sources[i], indices[i])) {
                lookup.put(indices[i], i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length() ; ){
            if(lookup.containsKey(i)) {
                sb.append(targets[lookup.get(i)]);
                i += sources[lookup.get(i)].length();
            } else {
                sb.append(s.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    public String findReplaceString1(String s, int[] indices, String[] sources, String[] targets) {
        return "";
    }

//    hashmap; time: O(n), space: O(n)
    public String replaceText(String text, List<Replacement> ranges) {
        Map<Integer, Integer> lookup = new HashMap<>();
        for(int i = 0 ; i < ranges.size() ; i++) {
//            assuming the string exists at the index, else use starts with
            if(text.startsWith(ranges.get(i).before, ranges.get(i).start)) {
                lookup.put(ranges.get(i).start, i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < text.length() ;) {
            if(lookup.containsKey(i)) {
                sb.append(ranges.get(lookup.get(i)).after);
                i += ranges.get(lookup.get(i)).before.length();
            } else {
                sb.append(text.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

}
class Replacement {
    int start;
    String before;
    String after;
    Replacement(int start, String before, String after) {
        this.start = start;
        this.before = before;
        this.after = after;
    }
}

/*
You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.
To complete the ith replacement operation:
Check if the substring sources[i] occurs at index indices[i] in the original string s.
If it does not occur, do nothing.
else if it does occur, replace that substring with targets[i].
For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".
All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will not overlap.
For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
Return the resulting string after performing all replacement operations on s.
A substring is a contiguous sequence of characters in a string.
Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
Output: "eeebffff"
Explanation:
"a" occurs at index 0 in s, so we replace it with "eee".
"cd" occurs at index 2 in s, so we replace it with "ffff".
Input: s = "abcd", indices = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]
Output: "eeecd"
Explanation:
"ab" occurs at index 0 in s, so we replace it with "eee".
"ec" does not occur at index 2 in s, so we do nothing.
Constraints:
1 <= s.length <= 1000
k == indices.length == sources.length == targets.length
1 <= k <= 100
0 <= indexes[i] < s.length
1 <= sources[i].length, targets[i].length <= 50
s consists of only lowercase English letters.
sources[i] and targets[i] consist of only lowercase English letters.
 */