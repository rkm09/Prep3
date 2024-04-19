package leetdaily.medium;

public class NumberOfIslands200 {
    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        NumberOfIslands200 n = new NumberOfIslands200();
        System.out.println(n.numIslands(grid));
    }

//    dfs; time: O(m*n), space: O(m*n)
    public int numIslands(char[][] grid) {
        
    }

//    disjoint set union; time: O(m*n), space: O(m*n) [considering dsu operations to be constant time with ackermann fn]
    public int numIslands2(char[][] grid) {
          if(grid == null || grid.length == 0) return 0;
          int nr = grid.length, nc = grid[0].length;
          UnionFind dsu = new UnionFind(grid);
          for(int r = 0 ; r < nr ; r++) {
              for(int c = 0 ; c < nc ; c++) {
                  if(grid[r][c] == '1') {
                        if(r - 1 >= 0 && grid[r - 1][c] == '1') {
                            dsu.union(r * nc + c, (r - 1) * nc + c);
                        }
                        if(r + 1 < nr && grid[r + 1][c] == '1') {
                            dsu.union(r * nc + c, (r + 1) * nc + c);
                        }
                        if(c - 1 >= 0 && grid[r][c - 1] == '1') {
                            dsu.union(r * nc + c, r * nc + (c - 1));
                        }
                        if(c + 1 < nc && grid[r][c + 1] == '1') {
                            dsu.union(r * nc + c, r * nc + (c + 1));
                        }
                  }
              }
          }
          return dsu.getCount();
    }
}

class UnionFind {
    private final int[] rank;
    private final int[] parent;
    private int count;
    UnionFind(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        parent = new int[m * n];
        rank = new int[m * n];
        for(int i = 0 ; i < m ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(grid[i][j] == '1') {
                    parent[i * n + j] = i * n + j;
                    count++;
                }
            }
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
}

/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
Example 1:
Input: grid = {
  {'1','1','1','1','0'},
  {'1','1','0','1','0'},
  {'1','1','0','0','0'},
  {'0','0','0','0','0'}
}
Output: 1
Example 2:
Input: grid = {
  {'1','1','0','0','0'},
  {'1','1','0','0','0'},
  {'0','0','1','0','0'},
  {'0','0','0','1','1'}
}
Output: 3
 
Constraints:
m == grid.length
n == grid{i}.length
1 <= m, n <= 300
grid{i}{j} is '0' or '1'.
 */