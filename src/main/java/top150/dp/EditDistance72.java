package top150.dp;

public class EditDistance72 {
    private static Integer[][] memo;
    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(minDistance(word1, word2));
    }

//    top down dp with memoization; time: O(m.n), space: O(m.n)
    public static int minDistance(String word1, String word2) {
        memo = new Integer[word1.length() + 1][word2.length() + 1];
        return minDistanceRecur(word1, word2, word1.length(), word2.length());
    }
    private static int minDistanceRecur(String word1, String word2, int word1Index, int word2Index) {
        if(word1Index == 0)
            return word2Index;
        if(word2Index == 0)
            return word1Index;
        if(memo[word1Index][word2Index] != null)
            return memo[word1Index][word2Index];
        int editDistance;
        if(word1.charAt(word1Index - 1) == word2.charAt(word2Index - 1)) {
            editDistance = minDistanceRecur(word1, word2, word1Index - 1, word2Index - 1);
        } else {
            int insertOperation = minDistanceRecur(word1, word2, word1Index, word2Index - 1);
            int deleteOperation = minDistanceRecur(word1, word2, word1Index - 1, word2Index);
            int replaceOperation = minDistanceRecur(word1, word2, word1Index - 1, word2Index - 1);
            editDistance = Math.min(insertOperation, Math.min(deleteOperation, replaceOperation)) + 1;
            memo[word1Index][word2Index] = editDistance;
        }
        return editDistance;
    }

//    [TLE]; recursion; time: O(3^n), space: O(n)
    public static int minDistanceX(String word1, String word2) {
        return minDistanceRec(word1, word2, word1.length(), word2.length());
    }
    private static int minDistanceRec(String word1, String word2, int word1Index, int word2Index) {
        if(word1Index == 0)
            return word2Index;
        if(word2Index == 0)
            return word1Index;
        if(word1.charAt(word1Index - 1) == word2.charAt(word2Index - 1)) {
            return minDistanceRec(word1, word2, word1Index - 1, word2Index - 1);
        } else {
            int insertOperation = minDistanceRec(word1, word2, word1Index, word2Index - 1);
            int deleteOperation = minDistanceRec(word1, word2, word1Index - 1, word2Index);
            int replaceOperation = minDistanceRec(word1, word2, word1Index - 1, word2Index - 1);
            return Math.min(
                    insertOperation, Math.min(deleteOperation, replaceOperation)
            ) + 1;
        }
    }
}


/*
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
You have the following three operations permitted on a word:
Insert a character
Delete a character
Replace a character
Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:
Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
Constraints:
0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
 */