package binarysearch.standardtraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllPathsSourceTarget797 {
    private  List<List<Integer>> results = new ArrayList<>();
    private int target;
    public  void main(String[] args) {
        AllPathsSourceTarget797 all = new AllPathsSourceTarget797();
        int[][] graph = {{1,2},{3},{3},{}};
        System.out.println(all.allPathsSourceTarget(graph));
    }

//    backtrack; time: O(n.2^n), space: O(n)
    public  List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        target = graph.length - 1;
        LinkedList<Integer> path = new LinkedList<>();
        path.addLast(0);
        backtrack(graph, path, 0);
        return results;
    }
    private  void backtrack(int[][] graph, LinkedList<Integer> path, int currNode) {
        if(currNode == target) {
            results.add(new ArrayList<>(path));
            return;
        }
        for(int nextNode : graph[currNode]) {
            path.addLast(nextNode);
            backtrack(graph, path, nextNode);
            path.removeLast();
        }
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