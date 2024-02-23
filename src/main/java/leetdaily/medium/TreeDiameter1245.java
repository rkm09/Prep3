package leetdaily.medium;

import java.util.*;

public class TreeDiameter1245 {
    private static List<List<Integer>> graph;
    private int diameter;
    public static void main(String[] args) {
        TreeDiameter1245 tr = new TreeDiameter1245();
        int[][] edges = {{0,1},{0,2}};
        System.out.println(tr.treeDiameter(edges));
    }


//    bfs (extreme peripheral nodes); time: O(n), space: O(n)
    public  int treeDiameter(int[][] edges) {
        List<Set<Integer>> graph = new ArrayList<>();
        for(int i = 0 ; i < edges.length + 1 ; i++) {
            graph.add(new HashSet<>());
        }
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int[] nodeDistance = bfs(0, graph);
        nodeDistance = bfs(nodeDistance[0], graph);
        return nodeDistance[1];
    }
    private int[] bfs(int start, List<Set<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        visited[start] = true;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        int distance = -1, lastNode = start;
        while(!queue.isEmpty()) {
            Deque<Integer> nextQueue = new ArrayDeque<>();
            while(!queue.isEmpty()) {
                 int nextNode = queue.poll();
                 for(int neighbour : graph.get(nextNode)) {
                     if(!visited[neighbour]) {
                         visited[neighbour] = true;
                         nextQueue.offer(neighbour);
                         lastNode = neighbour;
                     }
                 }
            }
            distance++;
            queue = nextQueue;
        }
        return new int[] {lastNode, distance};
    }

//    topological sort(finding centroids); time: O(n), space: O(n) [faster]
//    ts is a graph traversal in which a node is visited only after all its dependencies have been visited (only works for DAGs)
//   similar to bfs traversal, except that here we traverse from outer layers, layer by layer till we reach the innermost layer (centroids)
    public  int treeDiameter1(int[][] edges) {
        List<Set<Integer>> graph = new ArrayList<>();
        for(int i = 0 ; i < edges.length + 1 ; i++)
            graph.add(new HashSet<>());
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

//        find leaf nodes; using linked list instead of arraydeque since we need to remove from current iteration (rather than front or back)
        Deque<Integer> leaves = new LinkedList<>();
        for(int vertex = 0 ; vertex < graph.size() ; vertex++) {
            if(graph.get(vertex).size() == 1)
                leaves.offer(vertex);
        }
//        peel the graph, layer by layer until we have the centroids left
        int layers = 0;
        int vertexLeft = edges.length + 1;
        while(vertexLeft > 2) {
            vertexLeft -= leaves.size();
            Deque<Integer> nextLeaves = new LinkedList<>();
            for(int leaf : leaves) {
//              the only neighbour left on the leaf nodes
                int neighbour = graph.get(leaf).iterator().next();
                graph.get(neighbour).remove(leaf);
                if(graph.get(neighbour).size() == 1)
                    nextLeaves.offer(neighbour);
            }
            layers++;
            leaves = nextLeaves;
        }

        if(vertexLeft == 1)
            return layers * 2;
        else
            return layers * 2 + 1;
    }

//    dfs (representing as a tree, counting distance from root to leaf); time: O(n), space: O(n) [fastest]
    public int treeDiameter2(int[][] edges) {
        graph = new ArrayList<>();
        for(int i = 0 ; i < edges.length + 1; i++)
            graph.add(new ArrayList<>());
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[graph.size()];
        dfs(0, visited);
        return diameter;
    }
//    return the max distance starting from the current node
    private int dfs(int curr, boolean[] visited) {
//        top 2 distances starting from this node
        int topDistance1 = 0, topDistance2;
        visited[curr] = true;
        for(int neighbour : graph.get(curr)) {
            int distance = 0;
            if(!visited[neighbour])
                distance = 1 + dfs(neighbour, visited);
            if(distance > topDistance1) {
                topDistance2 = topDistance1;
                topDistance1 = distance;
            } else {
                topDistance2 = distance;
            }
            diameter = Math.max(diameter, topDistance1 + topDistance2);
        }
        return topDistance1;
    }
}

/*
The diameter of a tree is the number of edges in the longest path in that tree.
There is an undirected tree of n nodes labeled from 0 to n - 1. You are given a 2D array edges where edges.length == n - 1 and edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the tree.
Return the diameter of the tree.
Input: edges = [[0,1],[0,2]]
Output: 2
Explanation: The longest path of the tree is the path 1 - 0 - 2.
Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
Output: 4
Explanation: The longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
Constraints:
n == edges.length + 1
1 <= n <= 104
0 <= ai, bi < n
ai != bi
 */