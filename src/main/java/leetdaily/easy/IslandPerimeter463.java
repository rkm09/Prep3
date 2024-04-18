package leetdaily.easy;

public class IslandPerimeter463 {
    public static void main(String[] args) {
        int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        System.out.println(islandPerimeter(grid));
    }

//    better counting; time: O(mn), space: O(1)
    public static int islandPerimeter(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int result = 0;
        for(int row = 0 ; row < m ; row++) {
            for(int col = 0 ; col < n ; col++) {
                if(grid[row][col] == 1) {
                    result += 4;
                    if(row > 0 && grid[row - 1][col] == 1)
                        result -= 2;
                    if(col > 0 && grid[row][col - 1] == 1)
                        result -= 2;
                }
            }
        }
        return result;
    }

//    basic counting; time: O(mn), space: O(1)
    public static int islandPerimeter1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int left, right, up, down, result = 0;
        for(int row = 0 ; row < m ; row++) {
            for(int col = 0 ; col < n ; col++) {
                if(grid[row][col] == 1) {
                    if(row == 0) up = 0;
                    else up = grid[row - 1][col];

                    if(row == m - 1) down = 0;
                    else down = grid[row + 1][col];

                    if(col == 0) left = 0;
                    else left = grid[row][col - 1];

                    if(col == n - 1) right = 0;
                    else right = grid[row][col + 1];

                    result += 4 - (up + down + left + right);
                }
            }
        }
        return result;
    }

}

/*
You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
Example 1:
Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
Output: 16
Explanation: The perimeter is the 16 yellow stripes in the image above.
Example 2:
Input: grid = [[1]]
Output: 4
Example 3:
Input: grid = [[1,0]]
Output: 4

Constraints:

row == grid.length
col == grid[i].length
1 <= row, col <= 100
grid[i][j] is 0 or 1.
There is exactly one island in grid.
 */