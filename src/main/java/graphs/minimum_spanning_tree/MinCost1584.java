package graphs.minimum_spanning_tree;

import java.util.*;

public class MinCost1584 {
    public static void main(String[] args) {
        int[][] points = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        MinCost1584 m = new MinCost1584();
        System.out.println(m.minCostConnectPoints(points));
    }

//    kruskal's; time: O(n^2logn), space:O(n)
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<int[]> edges = new ArrayList<>();
        for(int i = 0 ; i < n ; i++) {
            for(int j = i + 1 ; j < n ; j++) {
                int[] currEdge = {distance(points[i], points[j]), i, j};
                edges.add(currEdge);
            }
        }

        edges.sort(Comparator.comparingInt(a -> a[0]));

        UnionFind dsu = new UnionFind(n);
        int mstCost = 0, edgesUsed = 0;
        for(int[] edge : edges) {
            if(edgesUsed < n) {
                if(!dsu.isConnected(edge[1], edge[2])) {
                    dsu.union(edge[1], edge[2]);
                    mstCost += edge[0];
                    edgesUsed++;
                }
            } else break;
        }
        return mstCost;
    }
    private int distance(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }
    
}

class UnionFind {
    private final int[] parent;
    private final int[] rank;
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for(int i = 0 ; i < n ; i++)
            parent[i] = i;
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
            else if(rank[rootX] < rank[rootY])
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

/*
You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
Example 1:
Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation:
We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.
Example 2:
Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18

Constraints:
1 <= points.length <= 1000
-106 <= xi, yi <= 106
All pairs (xi, yi) are distinct.
 */