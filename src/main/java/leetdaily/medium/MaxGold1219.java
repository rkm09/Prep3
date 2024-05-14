package leetdaily.medium;

public class MaxGold1219 {
    private static final int[] DIRECTIONS = {1,0,-1,0,1};
    public static void main(String[] args) {
        int[][] grid = {{0,6,0},{5,8,7},{0,9,0}};
        System.out.println(getMaximumGold(grid));
    }
    public static int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int maxGold = 0;
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < n ; j++) {
                maxGold = Math.max(maxGold, dfsBackTrack(grid, m, n, i, j));
            }
        }

        return maxGold;
    }

    private static int dfsBackTrack(int[][] grid, int rows, int cols, int row, int col) {
        if(row < 0 || col < 0 || row == rows || col == cols || grid[row][col] == 0)
            return 0;

        int maxGold = 0;
        int originalValue = grid[row][col];
        grid[row][col] = 0;

        for(int direction = 0 ; direction < 4 ; direction++) {
            maxGold = Math.max(maxGold, dfsBackTrack(grid, rows, cols, row + DIRECTIONS[direction],
                    col + DIRECTIONS[direction + 1]));
        }

        grid[row][col] = originalValue;

        return maxGold + originalValue;
    }
}


/*
In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
Return the maximum amount of gold you can collect under the conditions:
Every time you are located in a cell you will collect all the gold in that cell.
From your position, you can walk one step to the left, right, up, or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.

Example 1:
Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.
Example 2:
Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
Output: 28
Explanation:
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 15
0 <= grid[i][j] <= 100
There are at most 25 cells containing gold.
 */