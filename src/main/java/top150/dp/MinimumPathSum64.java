package top150.dp;

public class MinimumPathSum64 {
    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSum1(grid));
    }

    //    bottom up 2d dp; time: O(m.n), space: O(m.n)
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i = m - 1 ; i >= 0 ; i--) {
            for(int j = n - 1 ; j >= 0 ; j--) {
                if(i == m - 1 && j != n - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else if(i != m - 1 && j == n - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else if(i != m - 1 && j != n - 1) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                } else {
                    dp[i][j] = grid[i][j];
                }
            }
        }
        return dp[0][0];
    }

//    bottom up 1d dp; time: O(m.n), space: O(n) [can also do O(1) space if input is allowed to be modified]
    public static int minPathSum1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for(int i = m - 1 ; i >= 0 ; i--) {
            for(int j = n - 1 ; j >= 0 ; j--) {
                if(i == m - 1 && j != n - 1) {
                    dp[j] = grid[i][j] + dp[j + 1];
                } else if(i != m - 1 && j == n - 1) {
                    dp[j] = grid[i][j] + dp[j];
                } else if(i != m - 1 && j != n - 1) {
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
                } else {
                    dp[j] = grid[i][j];
                }
            }
        }
        return dp[0];
    }

//    bottom up dp; time: O(m.n), space: O(1) (in place)
    public static int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int i = m - 1 ; i >= 0 ; i--) {
            for(int j = n - 1 ; j >= 0 ; j--) {
                if(i == m - 1 && j != n - 1) {
                    grid[i][j] = grid[i][j] + grid[i][j + 1];
                } else if(i != m - 1 && j == n - 1) {
                    grid[i][j] = grid[i][j] + grid[i + 1][j];
                } else if(i != m - 1 && j != n - 1) {
                    grid[i][j] = grid[i][j] + Math.min(grid[i + 1][j], grid[i][j + 1]);
                }
            }
        }
        return grid[0][0];
    }

//    [TLE]; brute force recursion; time: O(2^(m+n)), space: O(m+n)
    public static int minPathSum2(int[][] grid) {
        return calculateSum2(grid,0 ,0);
    }
    private static int calculateSum2(int[][] grid, int i, int j) {
        if(i == grid.length || j == grid[0].length) return Integer.MAX_VALUE;
        if(i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
        return Math.min(calculateSum2(grid, i + 1, j), calculateSum2(grid, i, j + 1)) + grid[i][j];
    }
}

/*
Given an m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12
Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200
 */