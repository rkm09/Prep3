package graphs.traversal;

public class NumOfProvinces547 {
    public static void main(String[] args) {
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(findCircleNum(isConnected));
    }

//    dfs; time: n^2, space: O(n)
    public static int findCircleNum(int[][] isConnected) {
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
    private static void dfs(int node, int[][] isConnected, boolean[] visited) {
        visited[node] = true;
        for(int i = 0 ; i < isConnected.length ; i++) {
            if(isConnected[node][i] != 0 && !visited[i]) {
                dfs(i, isConnected, visited);
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