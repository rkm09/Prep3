package binarysearch.standardtraversal;

import java.util.*;

public class AllPathsSourceTarget797 {
    private  List<List<Integer>> results = new ArrayList<>();
    private int[][] graph;
    private Map<Integer, List<List<Integer>>> memo;
    private int target;
    public static void main(String[] args) {
        AllPathsSourceTarget797 all = new AllPathsSourceTarget797();
        int[][] graph = {{1,2},{3},{3},{}};
        System.out.println(all.allPathsSourceTarget1(graph));
    }

//    backtrack; time: O(n.2^n), space: O(n) [optimal & fast]
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        target = graph.length - 1;
        this.graph = graph;
        LinkedList<Integer> path = new LinkedList<>();
        path.addLast(0);
        backtrack(path, 0);
        return results;
    }
    private void backtrack(LinkedList<Integer> path, int currNode) {
        if(currNode == target) {
//            make a deep copy
            results.add(new ArrayList<>(path));
            return;
        }
        for(int nextNode : graph[currNode]) {
            path.addLast(nextNode);
            backtrack(path, nextNode);
            path.removeLast();
        }
    }

    //    top down dp; time: O(n.2^n), space: O(n)
    public List<List<Integer>> allPathsSourceTarget1(int[][] graph) {
        target = graph.length - 1;
        this.graph = graph;
        memo = new HashMap<>();
        return allPathsToTarget(0);
    }
    private List<List<Integer>> allPathsToTarget(int currNode) {
        if(memo.containsKey(currNode))
            return memo.get(currNode);
        List<List<Integer>> results = new ArrayList<>();
        if(currNode == target) {
            List<Integer> path = new ArrayList<>();
            path.add(target);
            results.add(path);
            return results;
        }
        for(int nextNode : graph[currNode]) {
            for(List<Integer> paths : allPathsToTarget(nextNode)) {
                List<Integer> newPath = new ArrayList<>();
                newPath.add(currNode);
                newPath.addAll(paths);
                results.add(newPath);
            }
        }
        memo.put(currNode, results);
        return results;
    }
}

/*
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
Constraints:
n == graph.length
2 <= n <= 15
0 <= graph[i][j] < n
graph[i][j] != i (i.e., there will be no self-loops).
All the elements of graph[i] are unique.
The input graph is guaranteed to be a DAG.
 */