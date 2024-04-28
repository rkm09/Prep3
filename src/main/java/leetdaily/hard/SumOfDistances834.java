package leetdaily.hard;

import java.util.*;

public class SumOfDistances834 {
    private int[] ans, count;
    private int N;
    private List<Set<Integer>> graph;
    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{2,3},{2,4},{2,5}};
//        int[][] edges = {{}};
        SumOfDistances834 s = new SumOfDistances834();
//        System.out.println(Arrays.toString(s.sumOfDistancesInTree(1, edges)));
        System.out.println(Arrays.toString(s.sumOfDistancesInTree(6, edges)));
    }

//    time: O(n), space: O(n)
//    ans[x] = subtreeSum(x) + subtreeSum(y) + count[y]
//    ans[y] = subtreeSum(x) + subtreeSum(y) + count[x]
//    => ans[x] - ans[y] = count[y] - count[x] => ans[x] = ans[y] - count[x] + (N - count[x])
//    ps: using map for graph leads to MLE;
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        graph = new ArrayList<>();
        this.N = n;
        ans = new int[n];
        count = new int[n];
        for(int i = 0 ; i < N ; i++)
            graph.add(new HashSet<>());
//        initialize count
        Arrays.fill(count, 1);
//        edge case
        if(n == 1) return ans;

        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

//        calculate individual subtree sum (root the tree)
        dfsPostOrder(0, -1);
//        calculate the overall relative sum wrt the whole tree
        dfsPreOrder(0, -1);
        return ans;
    }

    private void dfsPostOrder(int node, int parent) {
        for(int child : graph.get(node)) {
            if(child != parent) {
                dfsPostOrder(child, node);
                count[node] += count[child];
                ans[node] += ans[child] + count[child];
            }
        }
    }

    private void dfsPreOrder(int node, int parent) {
        for(int child : graph.get(node)) {
            if(child != parent) {
                ans[child] = ans[node] - count[child] + (N - count[child]);
                dfsPreOrder(child, node);
            }
        }
    }
}

/*
There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.
Example 1:
Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation: The tree is shown above.
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.
Hence, answer[0] = 8, and so on.
Example 2:
Input: n = 1, edges = []
Output: [0]
Example 3:
Input: n = 2, edges = [[1,0]]
Output: [1,1]

Constraints:
1 <= n <= 3 * 104
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
ai != bi
The given input represents a valid tree.
 */