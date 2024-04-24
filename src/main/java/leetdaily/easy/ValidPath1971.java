package leetdaily.easy;


import java.util.*;

public class ValidPath1971 {
    public static void main(String[] args) {
//        int[][] edges = {{0,1},{0,2},{3,5},{5,4},{4,3}};
        int[][] edges = {{0,1},{1,2},{2,0}};
//        int[][] edges = {{}};
        ValidPath1971 v = new ValidPath1971();
        System.out.println(v.validPath(3, edges, 0, 2));
    }

//    dsu; if there exists a path connecting source and destination, these two nodes must be in the same group.
//    time: O(m.alpha(n)), space: O(n) [fastest] alpha(n) -> inverse ackermann fn
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if(edges.length == 0) return true;
        UnionFind dsu = new UnionFind(n);
        for(int edge[] : edges) {
            dsu.union(edge[0], edge[1]);
        }
        return dsu.isConnected(source, destination);
    }

    static class UnionFind {
        private final int[] parent;
        private final int[] rank;
        UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for(int i = 0 ; i < size ; i++) {
                parent[i] = i;
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
                else if(rank[rootY] > rank[rootX])
                    parent[rootX] = rootY;
                else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }

//    iterative bfs; time: O(n + m), space: O(m + n)
    public boolean validPath1(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        boolean[] seen = new boolean[n];
        seen[source] = true;

        while(!queue.isEmpty()) {
            int currNode = queue.poll();
            if(currNode == destination) return true;
            for(int nextNode : graph.get(currNode)) {
                if(!seen[nextNode]) {
                    seen[nextNode] = true;
                    queue.offer(nextNode);
                }
            }
        }

        return false;
     }

//    iterative dfs; time: O(n + m), space: O(m + n) n -> nodes, m -> edges
    public boolean validPath2(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge: edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(source);
        boolean[] seen = new boolean[n];
        seen[source] = true;
        while(!stack.isEmpty()) {
            int currNode = stack.pop();
            if(currNode == destination)
                return true;
            for(int nextNode : graph.get(currNode)) {
                if(!seen[nextNode]) {
                    seen[nextNode] = true;
                    stack.push(nextNode);
                }
            }
        }
        return false;
    }

//    recursive dfs; time: O(m + n), space: O(m + n)
    public boolean validPath3(int n, int[][] edges, int source, int destination) {
        if(edges.length == 0) return true;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
        boolean[] seen = new boolean[n];
        return dfs(graph, source, destination, seen);
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, int currNode, int target, boolean[] seen) {
        if(currNode == target)
            return true;
        if(!seen[currNode]) {
            seen[currNode] = true;
            for(int nextNode : graph.get(currNode)) {
                if(dfs(graph, nextNode, target, seen))
                    return true;
            }
        }
        return false;
    }


}

/*
There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
You want to determine if there is a valid path that exists from vertex source to vertex destination.
Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
Example 1:
Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2
Example 2:
Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
Output: false
Explanation: There is no path from vertex 0 to vertex 5.

Constraints:

1 <= n <= 2 * 105
0 <= edges.length <= 2 * 105
edges[i].length == 2
0 <= ui, vi <= n - 1
ui != vi
0 <= source, destination <= n - 1
There are no duplicate edges.
There are no self edges.
 */