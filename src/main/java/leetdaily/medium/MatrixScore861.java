package leetdaily.medium;

public class MatrixScore861 {
    public static void main(String[] args) {
        int[][] grid = {{0,0,1,1},{1,0,1,0},{1,1,0,0}};
        System.out.println(matrixScore(grid));
    }

//    greedy(modifying the input); time: O(m.n), space: O(1)
//    Since our goal is to maximize the sum of the matrix's rows, our initial focus should be on maximizing the integer value of each row.
//    Ideally, we want all bits in the first column of the matrix (first digit) to be 1, (since, 2^0 + 2^1 + 2^2 < 2^3).
//    After optimizing rows, we optimize columns. It would be ideal for us to maximize the number of 1's in each column.
//    Finally, to calculate the score of the matrix, we need to accumulate the integer equivalent of each row.
//    To determine the contribution of a bit, we left-shift it by its position within the row, representing its place value.
    public static int matrixScore(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

//        maximize row score by setting first column to 1
        for(int i = 0 ; i < m ; i++) {
            if(grid[i][0] == 0) {
//                flip row
                for(int j = 0 ; j < n ; j++)
                    grid[i][j] = 1 - grid[i][j];
            }
        }

//        maximize column score by maximizing count of ones
        for(int j = 1 ; j < n ; j++) {
            int zerosCount = 0;
            for(int i = 0 ; i < m ; i++) {
                if(grid[i][j] == 0)
                    zerosCount++;
            }
            if(zerosCount > m - zerosCount) {
                for(int i = 0 ; i < m ; i++)
                    grid[i][j] = 1 - grid[i][j];
            }
        }

//        calculate final score by considering bit positions
        int score = 0;
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < n ; j++) {
//                left shift bit by place value of column to calculate column contribution
                int columnScore = grid[i][j] << (n - j - 1);
                score += columnScore;
            }
        }

        return score;
    }
}

/*
You are given an m x n binary matrix grid.
A move consists of choosing any row or column and toggling each value in that row or column (i.e., changing all 0's to 1's, and all 1's to 0's).
Every row of the matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
Return the highest possible score after making any number of moves (including zero moves).
Example 1:
Input: grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
Output: 39
Explanation: 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
Example 2:
Input: grid = [[0]]
Output: 1

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 20
grid[i][j] is either 0 or 1.

 */