package graphs.traversal;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumOfProvinces547 {
    public static void main(String[] args) {
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        NumOfProvinces547 province = new NumOfProvinces547();
        System.out.println(province.findCircleNum(isConnected));
    }

//    dfs; time: n^2, space: O(n)
    public int findCircleNum(int[][] isConnected) {
        int numOfComponents = 0, n = isConnected.length;
        boolean[] visited = new boolean[n];
        for(int i = 0 ; i < n ; i++) {
            if(!visited[i]) {
                ++numOfComponents;
                dfs(i, isConnected, visited);
            }
        }
        return numOfComponents;
    }
    private void dfs(int node, int[][] isConnected, boolean[] visited) {
        visited[node] = true;
        for(int i = 0 ; i < isConnected.length ; i++) {
            if(isConnected[node][i] != 0 && !visited[i]) {
                dfs(i, isConnected, visited);
            }
        }
    }

//    bfs; time: O(n^2), space: O(n)
    public  int findCircleNum1(int[][] isConnected) {
        int numOfComponents = 0, n = isConnected.length;
        boolean[] visited = new boolean[n];
        for(int i = 0 ; i < n ; i++) {
            if(!visited[i]) {
                ++numOfComponents;
                bfs(i, isConnected, visited);
            }
        }
        return numOfComponents;
    }
    private  void bfs(int node, int[][] isConnected, boolean[] visited) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(node);
        visited[node] = true;
        while(!queue.isEmpty()) {
            node = queue.poll();
            for(int i = 0 ; i < isConnected.length ; i++) {
                if(isConnected[node][i] != 0 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }

//    union find; time: O(n^2), space: O(n)
    public  int findCircleNum3(int[][] isConnected) {
        int n = isConnected.length;
        int numOfComponents = n;
        UnionFind dsu = new UnionFind(n);
        for(int i = 0 ; i < n ; i++) {
            for(int j = i + 1 ; j < n ; j++) {
                if(isConnected[i][j] != 0 && dsu.find(i) != dsu.find(j)) {
                    numOfComponents--;
                    dsu.union(i, j);
                }
            }
        }
        return numOfComponents;
    }
    class UnionFind {
        int[] parent;
        int[] rank;
        UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for(int i = 0 ; i < size ; i++)
                parent[i] = i;
        }
//        path compression
        public int find(int i) {
            if(parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY) {
                return;
            } else if(rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if(rank[rootY] > rank[rootX]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}

/*
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
A province is a group of directly or indirectly connected cities and no other cities outside of the group.
You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
Return the total number of provinces.
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3
Constraints:
1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
 */