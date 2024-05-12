package leetdaily.easy;

import java.util.Arrays;

public class LargestLocal2373 {
    public static void main(String[] args) {
        int[][] grid = {{9,9,8,1}, {5,6,2,6}, {8,2,6,4}, {6,2,2,2}};
        int[][] ans = largestLocal(grid);
        for(int[] arr : ans)
            System.out.println(Arrays.toString(arr));
    }

//    simulation; time: O(n^2), space: O(1)
    public static int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] maxLocal = new int[n - 2][n - 2];
        for(int i = 0 ; i < n - 2; i++) {
            for(int j = 0 ; j < n -2 ; j++) {
                maxLocal[i][j] = findMax(grid, i, j);
            }
        }
        return maxLocal;
    }

    private static int findMax(int[][] grid, int x, int y) {
        int maxElement = 0;
        for(int i = x ; i < x + 3 ; i++) {
            for(int j = y ; j < y + 3 ; j++) {
                maxElement = Math.max(maxElement, grid[i][j]);
            }
        }
        return maxElement;
    }
}


/*
You are given an n x n integer matrix grid.
Generate an integer matrix maxLocal of size (n - 2) x (n - 2) such that:
maxLocal[i][j] is equal to the largest value of the 3 x 3 matrix in grid centered around row i + 1 and column j + 1.
In other words, we want to find the largest value in every contiguous 3 x 3 matrix in grid.
Return the generated matrix.
Example 1:
Input: grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
Output: [[9,9],[8,6]]
Explanation: The diagram above shows the original matrix and the generated matrix.
Notice that each value in the generated matrix corresponds to the largest value of a contiguous 3 x 3 matrix in grid.
Example 2:
Input: grid = [[1,1,1,1,1],[1,1,1,1,1],[1,1,2,1,1],[1,1,1,1,1],[1,1,1,1,1]]
Output: [[2,2,2],[2,2,2],[2,2,2]]
Explanation: Notice that the 2 is contained within every contiguous 3 x 3 matrix in grid.

Constraints:
n == grid.length == grid[i].length
3 <= n <= 100
1 <= grid[i][j] <= 100
 */