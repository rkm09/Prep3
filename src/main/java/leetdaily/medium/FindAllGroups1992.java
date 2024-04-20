package leetdaily.medium;

import common.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllGroups1992 {
    public static void main(String[] args) {
        int[][] land = {{1,0,0},{0,1,1},{0,1,1}};
        FindAllGroups1992 f = new FindAllGroups1992();
        int[][] res = f.findFarmland(land);
        for (int[] re : res) {
            System.out.println(Arrays.toString(re));
        }
    }

//    greedy; time: O(m.n), space: O(1) [in place]
    public int[][] findFarmland(int[][] land) {
        List<int[]> ans = new ArrayList<>();
        int nr = land.length, nc = land.length;

        for(int row = 0 ; row < nr ; row++) {
            for(int col = 0 ; col < nc ; col++) {

                if(land[row][col] == 1) {
                    int x, y = col;
//                    find region limits
                    for(x = row ; x < nr && land[x][col] == 1 ; x++) {
                        for(y = col ; y < nc && land[x][y] == 1 ; y++) {
                            land[x][y] = 0;
                        }
                    }
                    int[] arr = new int[]{row, col, x - 1, y - 1};
                    ans.add(arr);
                }

            }
        }
        return ans.toArray(int[][] :: new);
    }

    public int[][] findFarmland1(int[][] land) {
        int nr = land.length, nc = land[0].length;
        List<int[]> res = new ArrayList<>();
        for(int r = 0 ; r < nr ; r++) {
            for(int c = 0 ; c < nc ; c++) {
                if(land[r][c] == '1') {
                    res.add(new int[]{r, c});
                    String[] s = dfs(land, r, c).split("");
                    res.add(new int[]{Integer.parseInt(s[0]), Integer.parseInt(s[1])});
                }
            }
        }
        return res.toArray(int[][]::new);
    }

    private String dfs(int[][] land, int r, int c) {
        int nr = land.length, nc = land[0].length;
        if(r < 0 || c < 0 || r >= nr || c >= nc || land[r][c] == 0)
            return "#";
//        mark visited
        land[r][c] = 0;
        dfs(land, r - 1, c);
        dfs(land, r + 1, c);
        dfs(land, r, c - 1);
        dfs(land, r, c + 1);
        return r + "" + c;
    }
}

/*
You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1 represents a hectare of farmland.
To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland. These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not four-directionally adjacent to another farmland in a different group.
land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right corner of land is (m-1, n-1). Find the coordinates of the top left and bottom right corner of each group of farmland. A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 4-length array [r1, c1, r2, c2].
Return a 2D array containing the 4-length arrays described above for each group of farmland in land. If there are no groups of farmland, return an empty array. You may return the answer in any order.
Example 1:
Input: land = [[1,0,0],[0,1,1],[0,1,1]]
Output: [[0,0,0,0],[1,1,2,2]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[0][0].
The second group has a top left corner at land[1][1] and a bottom right corner at land[2][2].
Example 2:
Input: land = [[1,1],[1,1]]
Output: [[0,0,1,1]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[1][1].
Example 3:
Input: land = [[0]]
Output: []
Explanation:
There are no groups of farmland.

Constraints:
m == land.length
n == land[i].length
1 <= m, n <= 300
land consists of only 0's and 1's.
Groups of farmland are rectangular in shape.
 */