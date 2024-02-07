package graphs.topologicalsort;

import java.util.*;

public class CourseScheduleII210 {
    private static boolean[] inStack;
    private static boolean[] visited;
    public static void main(String[] args) {
        int[][] prerequisites = {{1,0}};
        System.out.println(Arrays.toString(findOrder(2, prerequisites)));
    }

    //   dfs; time: O(v+e), space: O(v+e) ..[v-vertices, e-edges] ; fast
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        inStack = new boolean[numCourses];
        visited = new boolean[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < numCourses ; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] prerequisite : prerequisites) {
            adj.get(prerequisite[1]).add(prerequisite[0]);
        }
        List<Integer> topologicalOrder = new ArrayList<>();
        int[] order = new int[numCourses];
        for(int i = 0 ; i < numCourses ; i++) {
            if(dfs(i, adj, topologicalOrder)){
                return new int[0];
            }
        }

        for(int i = 0 ; i < numCourses ; i++) {
            order[i] = topologicalOrder.get(numCourses - i - 1);
        }
        return order;
    }
    private static boolean dfs(int node, List<List<Integer>> adj, List<Integer> topologicalOrder) {
        if(inStack[node])
            return true;
        if(visited[node])
            return false;
        inStack[node] = true;
        visited[node] = true;
        for(int neighbour : adj.get(node)) {
            if(dfs(neighbour, adj, topologicalOrder)) {
                return true;
            }
        }
        inStack[node] = false;
        topologicalOrder.add(node);
        return false;
    }

//   topological sort (kahn's algo); time: O(v+e), space: O(v+e) ..[v-vertices, e-edges]
    public static int[] findOrder1(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < numCourses ; i++) {
            adj.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for(int[] prerequisite : prerequisites) {
            adj.get(prerequisite[1]).add(prerequisite[0]);
            inDegree[prerequisite[0]]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0 ; i < numCourses ; i++) {
            if(inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        int nodesVisited = 0;
        int[] topologicalOrder = new int[numCourses];
        int i = 0;
        while(!queue.isEmpty()) {
            int node = queue.poll();
            topologicalOrder[i++] = node;
            nodesVisited++;
            for(int neighbour :adj.get(node)) {
                inDegree[neighbour]--;
                if(inDegree[neighbour] == 0) {
                    queue.offer(neighbour);
                }
            }
        }
        return nodesVisited == numCourses ? topologicalOrder : new int[0];
    }
}

/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:
Input: numCourses = 1, prerequisites = []
Output: [0]
Constraints:
1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
 */