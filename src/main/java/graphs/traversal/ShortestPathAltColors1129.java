package graphs.traversal;

import common.Pair;

import java.util.*;

public class ShortestPathAltColors1129 {
    public static void main(String[] args) {
        int[][] redEdges = {{0,1},{1,2}};
        int[][] blueEdges = {};
        System.out.println(Arrays.toString(shortestAlternatingPaths(3, redEdges, blueEdges)));
    }

//    bfs; time: O(n+e), space: O(n+e)
    public static int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();
        for(int[] redEdge : redEdges) {
            adj.computeIfAbsent(redEdge[0], k -> new ArrayList<>()).add(new Pair<>(redEdge[1], 0));
        }
        for(int[] blueEdge : blueEdges) {
            adj.computeIfAbsent(blueEdge[0], k -> new ArrayList<>()).add(new Pair<>(blueEdge[1], 1));
        }

        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][2];

//      start with node 0, steps as 0 to this node and an undefined color value;
        queue.offer(new int[] {0, 0, -1});
        answer[0] = 0;
        visited[0][0] = true; visited[0][1] = true;

        while(!queue.isEmpty()) {
            int[] element = queue.poll();
            int node = element[0], steps = element[1], prevColor = element[2];
            if(!adj.containsKey(node))
                continue;
            for(Pair<Integer, Integer> nei : adj.get(node)) {
                int neighbour = nei.getKey();
                int color = nei.getValue();
                if(!visited[neighbour][color] && color != prevColor) {
                    if(answer[neighbour] == -1) {
                        answer[neighbour] = steps + 1;
                    }
                    visited[neighbour][color] = true;
                    queue.offer(new int[] {neighbour, steps + 1, color});
                }
            }
        }
        return answer;
    }
}

/*
You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.
You are given two arrays redEdges and blueEdges where:
redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.
Example 1:
Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
Output: [0,1,-1]
Example 2:
Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
Output: [0,1,-1]
Constraints:
1 <= n <= 100
0 <= redEdges.length, blueEdges.length <= 400
redEdges[i].length == blueEdges[j].length == 2
0 <= ai, bi, uj, vj < n
 */