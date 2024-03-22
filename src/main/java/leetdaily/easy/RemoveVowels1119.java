package leetdaily.easy;

public class RemoveVowels1119 {
    public static void main(String[] args) {
        String s = "leetcodeisacommunityforcoders";
        System.out.println(removeVowels(s));
    }

//    string builder [def]; time: O(n), space: O(n)
    public static String removeVowels(String s) {
        StringBuilder sb = new StringBuilder();
        String vowels = "aeiou";
        for(int i = 0 ; i < s.length() ; i++) {
            String letter = String.valueOf(s.charAt(i));
            if(!vowels.contains(letter))
                sb.append(letter);
        }
        return sb.toString();
    }
}

/*
Given a string s, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.
Example 1:
Input: s = "leetcodeisacommunityforcoders"
Output: "ltcdscmmntyfrcdrs"
Example 2:
Input: s = "aeiou"
Output: ""

Constraints:
1 <= s.length <= 1000
s consists of only lowercase English letters.
 */