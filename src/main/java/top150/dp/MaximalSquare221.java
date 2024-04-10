package top150.dp;

public class MaximalSquare221 {
    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(maximalSquare(matrix));
    }

//  1d dp; time: O(mn), space: O(n) [faster and less space]
    public static int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int maxSqLength = 0, prev = 0;
        int[] dp = new int[cols + 1];
        for(int i = 1 ; i <= rows ; i++) {
            for(int j = 1 ; j <= cols ; j++) {
                int temp = dp[j];
                if(matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxSqLength = Math.max(maxSqLength, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxSqLength * maxSqLength;
    }

//   2d dp; time: O(mn), space: O(mn)
//   dp(i,j) represents the side length of the maximum square whose bottom right corner is the cell with index (i,j) in the original matrix.
    public static int maximalSquare1(char[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        // for convenience, we add an extra all zero column and row to simplify the transition
        int[][] dp = new int[rows + 1][cols + 1];
        int maxSqLength = 0;
        for(int i = 1 ; i <= rows ; i++) {
            for(int j = 1 ; j <= cols ; j++) {
                if(matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][ j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                    maxSqLength = Math.max(maxSqLength, dp[i][j]);
                }
            }
        }
        return maxSqLength * maxSqLength;
    }
}

/*
Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
Example 1:
Input: matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
Output: 4
Example 2:
Input: matrix = [['0','1'],['1','0']]
Output: 1
Example 3:
Input: matrix = [['0']]
Output: 0

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.
 */