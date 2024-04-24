package leetdaily.medium;

import java.util.*;

public class MinHeightTrees310 {
    public static void main(String[] args) {
        int[][] edges = {{1,0},{1,2},{1,3}};
        System.out.println(findMinHeightTrees(4, edges));
    }

//    topological sort; time: O(n), space: O(n)
//    intuitively, the centroid nodes are the ones that are situated in the center of a graph.
//    More precisely, the distance from the centroid to other nodes in the graph should be overall minimal,
//    which is the opposite of the extreme peripheral nodes that we defined before.
//    Indeed, if we could identify the centroid of a graph, then the distance from this centroid to any of
//    its extreme peripheral nodes would be half of the diameter of the graph.
//   There would be either one or two centroids in a tree-alike graph.
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
//        edge case
        if(n < 2) {
            List<Integer> centroids = new ArrayList<>();
            for(int i = 0 ; i < n ; i++)
                centroids.add(i);
            return centroids;
        }
//        build adjacency list
        List<Set<Integer>> graph = new ArrayList<>();
        for(int i = 0 ; i < n ; i++)
            graph.add(new HashSet<>());
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
//      initialize the first set of leaves
        List<Integer> leaves = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            if(graph.get(i).size() == 1)
                leaves.add(i);
        }
//        trim the leaves until we reach the centroids
        int remainingNodes = n;
        while(remainingNodes > 2) {
            remainingNodes -= leaves.size();
            List<Integer> nextLeaves = new ArrayList<>();
            for(Integer leaf : leaves) {
                int neighbour = graph.get(leaf).iterator().next();
                graph.get(neighbour).remove(leaf);
                if(graph.get(neighbour).size() == 1)
                    nextLeaves.add(neighbour);
            }
            leaves = nextLeaves;
        }
        return leaves;
    }

//        topological sort; time: O(n), space: O(n)
//    same thing except for the graph being hash map [slower]
    public static List<Integer> findMinHeightTrees1(int n, int[][] edges) {
//        edge cases
        if(n < 2) {
            List<Integer> centroids = new ArrayList<>();
            for(int i = 0 ; i < n ; i++)
                centroids.add(i);
            return centroids;
        }
//        build the graph with the adjacency list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

//        initialize the first layer of leaves
        List<Integer> leaves = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            if(graph.get(i).size() == 1)
                leaves.add(i);
        }
//        trim leaves till centroids alone remain
        int remainingLeaves = n;
        while(remainingLeaves > 2) {
            remainingLeaves -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for(Integer leaf : leaves) {
//                the only neighbour left on the leaf node
                int neighbour = graph.get(leaf).iterator().next();
//                remove the edge along with the leaf node [note: this should be Integer object else it
//                will throw index oob as it will take int as an index removal rather than object removal]
                graph.get(neighbour).remove(leaf);
                if(graph.get(neighbour).size() == 1)
                    newLeaves.add(neighbour);
            }
//            prepare for the next round of leaves
            leaves = newLeaves;
        }

        return leaves;
    }
}

/*
A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
Return a list of all MHTs' root labels. You can return the answer in any order.
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
Example 1:
Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
Example 2:
Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]

Constraints:
1 <= n <= 2 * 104
edges.length == n - 1
0 <= ai, bi < n
ai != bi
All the pairs (ai, bi) are distinct.
The given input is guaranteed to be a tree and there will be no repeated edges.
 */