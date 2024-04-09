package gen.easy;

public class Grid101 {
    public static void main(String[] args) {
        int[][] grid = {{0,0,0,0,5},{0,1,1,1,0},{2,0,0,0,0}};
        System.out.println(pickMaxPoints(grid));
    }
    private static int pickMaxPoints(int[][] grid) {
        int m = grid.length, n = grid[0].length;
//        int[][] dp = new int[m + 1][n + 1];
        for(int row = n - 2 ; row >= 0 ; row--) {
            for(int col = 1 ; col < m ; col++) {
//                dp[row][col] = Math.max(dp[row + 1][col], dp[row][col - 1]) + grid[row + 1][col - 1];
                grid[row][col] = Math.max(grid[row + 1][col], grid[row][col - 1]) + grid[row + 1][col - 1];
            }
        }
//        ??
        return grid[0][m - 1];
    }
}
