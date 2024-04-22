package leetdaily.hard;

import java.util.ArrayList;
import java.util.List;

public class NumOfIslandsII305 {
    public static void main(String[] args) {
//        int[][] positions = {{0,0},{0,1},{1,2},{2,1}};
        int[][] positions = {{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}};
        NumOfIslandsII305 n = new NumOfIslandsII305();
        System.out.println(n.numIslands2(3,3,positions));
    }


//    union find; time: O(m.n + l), space: O(m.n) [l positions]
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        UnionFind dsu = new UnionFind(m * n);
        int[] x = {-1, 1, 0, 0};
        int[] y = {0, 0, -1, 1};
        for(int[] position : positions) {
            int landPosition = position[0] * n + position[1];
            dsu.addLand(landPosition);
            for(int i = 0 ; i < 4 ; i++) {
                int neighbourX = position[0] + x[i];
                int neighbourY = position[1] + y[i];
                int neighbourPosition = neighbourX * n + neighbourY;
                if(neighbourX >= 0 && neighbourX < m && neighbourY >= 0
                        && neighbourY < n
                       && dsu.isLand(neighbourPosition))
                    dsu.union(landPosition, neighbourPosition);
            }
            res.add(dsu.getCount());
        }
        return res;
    }

    static class UnionFind {
        private final int[] parent;
        private final int[] rank;
        private int count;
        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for(int i = 0 ; i < size ; i++) {
                parent[i] = -1;
            }
        }
        public int find(int i) {
            if(parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY) {
                if(rank[rootX] > rank[rootY])
                    parent[rootY] = rootX;
                else if(rank[rootX] < rank[rootY])
                    parent[rootX] = rootY;
                else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                count--;
            }
        }
        public int getCount() {
            return count;
        }
        public boolean isLand(int x) {
            return parent[x] >= 0;
        }
        public void addLand(int x) {
            if(parent[x] >= 0)
                return;
            parent[x] = x;
            count++;
        }
    }
}



/*
You are given an empty 2D binary grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. Initially, all the cells of grid are water cells (i.e., all the cells are 0's).
We may perform an add land operation which turns the water at position into a land. You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.
Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
Example 1:
Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
Output: [1,1,2,3]
Explanation:
Initially, the 2d grid is filled with water.
- Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
- Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
- Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
- Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
Example 2:
Input: m = 1, n = 1, positions = [[0,0]]
Output: [1]

Constraints:
1 <= m, n, positions.length <= 104
1 <= m * n <= 104
positions[i].length == 2
0 <= ri < m
0 <= ci < n

 */