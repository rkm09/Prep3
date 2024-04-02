package leetdaily.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings205 {
    public static void main(String[] args) {
        String s = "paper", t = "title";
        System.out.println(isIsomorphic(s, t));
    }

//    dictionary using arrays of fixed length; time: O(n), space: O(1) [faster]
    public static boolean isIsomorphic(String s, String t) {
        int[] indexSToT = new int[256];
        int[] indexTToS = new int[256];
        Arrays.fill(indexSToT, -1);
        Arrays.fill(indexTToS, -1);

        for(int i = 0 ; i < s.length() ; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
//            case 1: both not present
            if(indexSToT[c1] == -1 && indexTToS[c2] == -1) {
                indexSToT[c1] = c2;
                indexTToS[c2] = c1;
            }
//            case 2: present but unequal
            else if(!(indexSToT[c1] == c2  && indexTToS[c2] == c1))
                return false;
        }

        return true;
    }

//    hashmap; time: O(n), space: O(n)
    public static boolean isIsomorphic1(String s, String t) {
       return transformString(s).equals(transformString(t));
    }
    private static String transformString(String s) {
        Map<Character, Integer> indexMapping = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);
            if(!indexMapping.containsKey(c))
                indexMapping.put(c, i);
            sb.append(indexMapping.get(c));
//            here delimiter is essential to differentiate between index mapping of the type 1 01 vs 10 1
            sb.append(" ");
        }
        return sb.toString();
    }


}

/*
Given two strings s and t, determine if they are isomorphic.
Two strings s and t are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
Example 1:
Input: s = "egg", t = "add"
Output: true
Example 2:
Input: s = "foo", t = "bar"
Output: false
Example 3:
Input: s = "paper", t = "title"
Output: true

Constraints:
1 <= s.length <= 5 * 104
t.length == s.length
s and t consist of any valid ascii character.
 */