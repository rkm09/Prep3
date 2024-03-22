package leetdaily.easy;

public class RemoveVowels1119 {
    public static void main(String[] args) {
        String s = "leetcodeisacommunityforcoders";
        System.out.println(removeVowels(s));
        System.out.println(s.replaceAll("[aeiou]", ""));
    }

//    good to know: can be solved easily by replaceAll(). but we are not going to use built in

//    string builder [def]; time: O(n), space: O(1) [return space not counted]
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

//    time: O(n), space: O(1)
    public static String removeVowels1(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(!isVowel(c)) sb.append(c);
        }
        return sb.toString();
    }
    private static Boolean isVowel(Character c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
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