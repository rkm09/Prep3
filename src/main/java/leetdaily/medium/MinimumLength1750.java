package leetdaily.medium;

public class MinimumLength1750 {
    public static void main(String[] args) {
        String s = "cabaabac";
        System.out.println(minimumLength(s));
    }

//    two pointer; time: O(n), space: O(1)
    public static int minimumLength(String s) {
        int left = 0, right = s.length() - 1;
        while(left < right && s.charAt(left) == s.charAt(right)) {
            char c = s.charAt(left);
//            delete consecutive occurrences of c from prefix
            while((left <= right) && s.charAt(left) == c)
                left++;
//            delete consecutive occurrences of c from suffix
            while((right > left) && s.charAt(right) == c)
                right--;
        }
//        return remaining
        return right - left + 1;
    }

//    tail recursion; time: O(n), space: O(n)
    public static int minimumLength1(String s) {
        return deleteSimilarEnds(s, 0, s.length() - 1);
    }
    private static int deleteSimilarEnds(String s, int begin, int end) {
//        end differs or meets in the middle
        if((begin >= end) || s.charAt(begin) != s.charAt(end))
            return end - begin + 1;
        else {
            char c = s.charAt(begin);
//            delete consecutive occurrences of c from prefix
            while((begin <= end) && s.charAt(begin) == c)
                begin++;
//            delete consecutive occurrences of c from suffix
            while((end > begin) && s.charAt(end) == c)
                end--;
        }
        return deleteSimilarEnds(s, begin, end);
    }
}


/*
Given a string s consisting only of characters 'a', 'b', and 'c'. You are asked to apply the following algorithm on the string any number of times:
Pick a non-empty prefix from the string s where all the characters in the prefix are equal.
Pick a non-empty suffix from the string s where all the characters in this suffix are equal.
The prefix and the suffix should not intersect at any index.
The characters from the prefix and suffix must be the same.
Delete both the prefix and the suffix.
Return the minimum length of s after performing the above operation any number of times (possibly zero times).
Example 1:
Input: s = "ca"
Output: 2
Explanation: You can't remove any characters, so the string stays as is.
Example 2:
Input: s = "cabaabac"
Output: 0
Explanation: An optimal sequence of operations is:
- Take prefix = "c" and suffix = "c" and remove them, s = "abaaba".
- Take prefix = "a" and suffix = "a" and remove them, s = "baab".
- Take prefix = "b" and suffix = "b" and remove them, s = "aa".
- Take prefix = "a" and suffix = "a" and remove them, s = "".
Example 3:
Input: s = "aabccabba"
Output: 3
Explanation: An optimal sequence of operations is:
- Take prefix = "aa" and suffix = "a" and remove them, s = "bccabb".
- Take prefix = "b" and suffix = "bb" and remove them, s = "cca".

Constraints:
1 <= s.length <= 105
s only consists of characters 'a', 'b', and 'c'.
 */