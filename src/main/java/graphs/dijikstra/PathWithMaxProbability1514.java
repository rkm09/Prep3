package graphs.dijikstra;


import common.Pair;

import java.util.*;

public class PathWithMaxProbability1514 {
    public static void main(String[] args) {
//        int[][] edges = {{0,1},{1,2},{0,2}};
//        double[] succProb = {0.5,0.5,0.2};
        int[][] edges = {{0,1},{1,2},{0,2},{1,3},{2,3}};
        double[] succProb = {0.8,0.8,0.3,1,1};
        System.out.println(maxProbability(4,edges,succProb,0,2));
    }

//    bellman-ford; time: O(m.n), space: O(n) [n nodes, m edges] [faster at runtime ;), though ideally this should be slower]
    public static double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        double[] maxProb = new double[n];
        maxProb[start_node] = 1.0;
        for(int i = 0 ; i < n ; i++) {
            boolean hasUpdated = false;
            for(int j = 0 ; j < edges.length ; j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                double pathProb = succProb[j];
                if(pathProb * maxProb[u] > maxProb[v]) {
                    maxProb[v] = pathProb * maxProb[u];
                    hasUpdated = true;
                }
                if(pathProb * maxProb[v] > maxProb[u]) {
                    maxProb[u] = pathProb * maxProb[v];
                    hasUpdated = true;
                }
            }
//            break out early if no update happened within this iteration cycle
            if(!hasUpdated) break;
        }
        return maxProb[end_node];
    }

//    shortest path faster (spfa); time: O(n.m) [only in the worst case], space: O(n + m) [slowest]
    public static double maxProbability1(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        Map<Integer, List<Pair<Integer, Double>>> graph = new HashMap<>();
        for(int i = 0 ; i < edges.length ; i++) {
            int u = edges[i][0], v = edges[i][1];
            double pathProb = succProb[i];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Pair<>(v, pathProb));
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Pair<>(u, pathProb));
        }
        double[] maxProb = new double[n];
        maxProb[start_node] = 1d;

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(start_node);

        while(!queue.isEmpty()) {
            int currNode = queue.poll();
            for(Pair<Integer, Double> neighbour : graph.getOrDefault(currNode, new ArrayList<>())) {
                int nextNode = neighbour.getKey();
                double pathProb = neighbour.getValue();
                if(maxProb[currNode] * pathProb > maxProb[nextNode]) {
                    maxProb[nextNode] = maxProb[currNode] * pathProb;
                    queue.offer(nextNode);
                }
            }
        }

        return maxProb[end_node];
    }

//    dijikstra's algorithm; time: O(m + nlogn), space: O(n + m) [faster than spfa]
    public static double maxProbability2(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        Map<Integer, List<Pair<Integer, Double>>> graph = new HashMap<>();
        for(int i = 0 ; i < edges.length ; i++) {
            int u = edges[i][0], v = edges[i][1];
            double pathProb = succProb[i];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Pair<>(v, pathProb));
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Pair<>(u, pathProb));
        }
        double[] maxProb = new double[n];
        maxProb[start_node] = 1d;

//        descending priority; preference for greater probability value
        PriorityQueue<Pair<Double, Integer>> pq = new PriorityQueue<>((a,b) -> - Double.compare(a.getKey(), b.getKey()));
        pq.offer(new Pair<>(1.0, start_node));

        while(!pq.isEmpty()) {
            Pair<Double, Integer> pair = pq.poll();
            int currNode = pair.getValue();
            double currProb = pair.getKey();

            if(currNode == end_node)
                return currProb;

            for(Pair<Integer, Double> neighbour : graph.getOrDefault(currNode, new ArrayList<>())) {
                int nextNode = neighbour.getKey();
                double nextProb = neighbour.getValue();
                if(maxProb[currNode] * nextProb > maxProb[nextNode]) {
                    maxProb[nextNode] = nextProb * maxProb[currNode];
                    pq.offer(new Pair<>(maxProb[nextNode], nextNode));
                }
            }
        }
        return 0d;
    }
}

/*
You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
Example 1:
Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
Output: 0.25000
Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
Example 2:
Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
Output: 0.30000
Example 3:
Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
Output: 0.00000
Explanation: There is no path between 0 and 2.
Constraints:

2 <= n <= 10^4
0 <= start, end < n
start != end
0 <= a, b < n
a != b
0 <= succProb.length == edges.length <= 2*10^4
0 <= succProb[i] <= 1
There is at most one edge between every two nodes.
 */

