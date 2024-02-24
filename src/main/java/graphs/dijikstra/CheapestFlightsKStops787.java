package graphs.dijikstra;

import java.util.*;

public class CheapestFlightsKStops787 {
    public static void main(String[] args) {
        int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
        System.out.println(findCheapestPrice2(3, flights, 0, 2, 1));
    }

//    bfs; time: O(N + E.K), space: O(N + E.K) [E: number of flights, N: number of cities]
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> adjMap = new HashMap<>();
        for(int[] flight : flights)
            adjMap.computeIfAbsent(flight[0], value -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});

        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);

        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{src, 0});
        int stops = 0;

        while(stops <= k && !queue.isEmpty()) {
            int size = queue.size();
//            Iterate on current level
            while(size-- > 0) {
                int[] nodeInfo = queue.poll();
                int node = nodeInfo[0];
                int nodePrice = nodeInfo[1];
                if(!adjMap.containsKey(node))
                    continue;
//                loop over neighbours of popped node
                for(int[] neighbourInfo : adjMap.get(node)) {
                    int neighbour = neighbourInfo[0];
                    int neighbourPrice = neighbourInfo[1];
                    if(nodePrice + neighbourPrice >= distances[neighbour])
                        continue;
                    distances[neighbour] = neighbourPrice + nodePrice;
                    queue.offer(new int[]{neighbour, distances[neighbour]});
                }
            }
            stops++;
        }

        return distances[dst] == Integer.MAX_VALUE ? -1 : distances[dst];
    }

//    bellman ford; time: O((N+E).K), space: O(N) [fastest]
    public static int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;

        for(int i = 0 ; i <= k ; i++) {
            int[] temp = Arrays.copyOf(distances, n);
            for(int[] flight : flights) {
                if(distances[flight[0]] != Integer.MAX_VALUE) {
                    temp[flight[1]] = Math.min(temp[flight[1]], distances[flight[0]] + flight[2]);
                }
            }
            distances = temp;
        }

        return distances[dst] == Integer.MAX_VALUE ? -1 : distances[dst];
    }

//    dijikstra's algo; time: O(N + E.KlogE.K), space: O(N + E.K)
    public static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> adjMap = new HashMap<>();
        for(int[] flight : flights)
            adjMap.computeIfAbsent(flight[0], value -> new ArrayList<>()).add(new int[] {flight[1], flight[2]});

        int[] stops = new int[n];
        Arrays.fill(stops, Integer.MAX_VALUE);

//        {distanceFromSource, node, numberOfStopsFromSource}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[] {0, src, 0});

        while(!pq.isEmpty()) {
            int[] temp = pq.poll();
            int distance = temp[0];
            int node = temp[1];
            int steps = temp[2];
//          we have already encountered a path with lower cost & fewer steps or the number of stops exceeds the limit
            if(steps > stops[node] || steps > k + 1)
                continue;
            stops[node] = steps;
            if(node == dst)
                return distance;
            if(!adjMap.containsKey(node))
                continue;
            for(int[] nei : adjMap.get(node)) {
                pq.offer(new int[]{nei[1] + distance, nei[0], steps + 1});
            }
        }
        return -1;
    }
}

/*
There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
Output: 500
Explanation:
The graph is shown above.
The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
Constraints:
1 <= n <= 100
0 <= flights.length <= (n * (n - 1) / 2)
flights[i].length == 3
0 <= fromi, toi < n
fromi != toi
1 <= pricei <= 104
There will not be any multiple flights between two cities.
0 <= src, dst, k < n
src != dst

 */