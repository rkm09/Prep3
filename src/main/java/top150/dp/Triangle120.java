package top150.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle120 {
    private static Integer[][] memo;
    public static void main(String[] args) {
        Integer[][] arr = {{2},{3,4},{6,5,7},{4,1,8,3}};
        List<List<Integer>> triangle = new ArrayList<>();
        for(Integer[] a : arr) {
            triangle.add(Arrays.asList(a));
        }
        System.out.println(minimumTotal(triangle));
    }

    //    [def]; recursion + memo(top down dp); time: O(n^2), space: O(n^2) ; fastest
    public static int minimumTotalN(List<List<Integer>> triangle) {
        memo = new Integer[triangle.size()][triangle.size()];
        return dfs(triangle, 0, 0);
    }
    private static int dfs(List<List<Integer>> triangle, int level, int index) {
        if(level >= triangle.size())
            return 0;
        if(index >= triangle.get(level).size())
            return 0;
        if(memo[level][index] != null)
            return memo[level][index];
        int ans = triangle.get(level).get(index) + Math.min(dfs(triangle, level + 1, index)
                , dfs(triangle, level + 1, index + 1));
        memo[level][index] = ans;
        return ans;
    }


//    bottom up dp; time: O(n^2), space: O(1) [overwriting input]
//    in-place (if allowed)
    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        for(int row = n - 2 ; row >= 0 ; row--) {
            for(int col = 0 ; col <= row ; col++) {
                int bestBelow = Math.min(triangle.get(row + 1).get(col), triangle.get(row + 1).get(col + 1));
                triangle.get(row).set(col, bestBelow + triangle.get(row).get(col));
            }
        }
        return triangle.get(0).get(0);
    }

//    bottom up dp with auxiliary space; time: O(n^2), space: O(n)
    public static int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        List<Integer> rowBelow = triangle.get(n - 1);
        for(int row = n - 2 ; row >= 0 ; row--) {
            List<Integer> currRow = new ArrayList<>();
            for(int col = 0 ; col <= row ; col++) {
                int bestBelow = Math.min(rowBelow.get(col), rowBelow.get(col + 1));
                currRow.add(bestBelow + triangle.get(row).get(col));
            }
            rowBelow = currRow;
        }
        return rowBelow.get(0);
    }

}

/*
Given a triangle array, return the minimum path sum from top to bottom.
For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
Example 1:
Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
Example 2:
Input: triangle = [[-10]]
Output: -10
Constraints:
1 <= triangle.length <= 200
triangle[0].length == 1
triangle[i].length == triangle[i - 1].length + 1
-104 <= triangle[i][j] <= 104
Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?

 */