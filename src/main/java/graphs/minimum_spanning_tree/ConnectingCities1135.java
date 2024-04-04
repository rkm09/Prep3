package graphs.minimum_spanning_tree;


import java.util.Arrays;
import java.util.Comparator;

public class ConnectingCities1135 {
    public static void main(String[] args) {
        int[][] connections = {{1, 2, 5}, {1, 3, 6}, {2, 3, 1}};
        ConnectingCities1135 c = new ConnectingCities1135();
        System.out.println(c.minimumCost(3, connections));
    }

//      kruskal's algo for MST; time: O(Mlog*N), space: O(n)
    public int minimumCost(int n, int[][] connections) {
//        check connectedness, avoid cycle using disjoint set data structure
        UnionFind dsu = new UnionFind(n);
//        sort connections based on cost
        Arrays.sort(connections, Comparator.comparingInt(k -> k[2]));

//        keep track of edge count and total cost;
        int totalCost = 0, edgeCount = 0;

        for (int i = 0; i < connections.length; i++) {
//         check whether already connected
            int a = connections[i][0];
            int b = connections[i][1];
            if (dsu.isConnected(a, b)) continue;
//          if not connected, merge the two
            dsu.union(a, b);

            totalCost += connections[i][2];
            edgeCount += 1;
        }

        if (edgeCount == n - 1)
            return totalCost;
        else
            return -1;
    }

//    weighted union
    class UnionFind {
        private int[] parent;
        private int[] weight;
        UnionFind(int n) {
            parent = new int[n + 1];
            weight = new int[n + 1];
            for(int i = 0 ; i <= n ; i++) {
                parent[i] = i;
                weight[i] = 1;
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
                if(weight[rootX] > weight[rootY]) {
                    parent[rootY] = rootX;
                    weight[rootX] += weight[rootY];
                }
                else {
                    parent[rootX] = rootY;
                    weight[rootY] += weight[rootX];
                }
            }
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }

    }
}

/*
There are n cities labeled from 1 to n. You are given the integer n and an array connections where connections[i] = [xi, yi, costi] indicates that the cost of connecting city xi and city yi (bidirectional connection) is costi.
Return the minimum cost to connect all the n cities such that there is at least one path between each pair of cities. If it is impossible to connect all the n cities, return -1,
The cost is the sum of the connections' costs used.
Example 1:
Input: n = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: Choosing any 2 edges will connect all cities so we choose the minimum 2.
Example 2:
Input: n = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation: There is no way to connect all cities even if all edges are used.

Constraints:
1 <= n <= 104
1 <= connections.length <= 104
connections[i].length == 3
1 <= xi, yi <= n
xi != yi
0 <= costi <= 105
 */


// classic union find by rank: (was slightly slower for this problem compared to union find by weight)
/*
class UnionFind {
    private int[] parent;
    private int[] rank;
    UnionFind(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for(int i = 0 ; i <= n ; i++)
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

 */