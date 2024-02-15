package leetdaily.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DirectedGraph {
    public static void main(String[] args) {
        int[][] edges = {{1,3},{2,1},{2,0},{3,1}};
        System.out.println(reciprocalEdges(edges));
    }
    private static int reciprocalEdges(int[][] edges) {
        int count = 0;
        Map<Integer, Set<Integer>> adjMap = new HashMap<>();
        for(int[] edge : edges) {
            int first = edge[0];
            int second = edge[1];
            adjMap.computeIfAbsent(first, k -> new HashSet<>());
            Set<Integer> set = adjMap.get(first);
            set.add(second);
            adjMap.put(first, set);
        }

        return count;
    }
}
