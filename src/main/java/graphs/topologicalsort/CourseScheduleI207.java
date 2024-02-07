package graphs.topologicalsort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CourseScheduleI207 {
    public static void main(String[] args) {
        int[][] prerequisites = {{1,0}};
        System.out.println(canFinish1(2, prerequisites));
    }

//    topological sort (kahn's algo); time: O(m+n), space: O(m+n) ..[n - numOfCourses, m - size of prerequisites]
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
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
        while(!queue.isEmpty()) {
            int node = queue.poll();
            nodesVisited++;
            for(int neighbour : adj.get(node)) {
                inDegree[neighbour]--;
                if(inDegree[neighbour] == 0) {
                    queue.offer(neighbour);
                }
            }
        }
        return numCourses == nodesVisited;
    }

    //    dfs; time: O(m+n), space: O(m+n); faster
    public static boolean canFinish1(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < numCourses ; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] prerequisite : prerequisites) {
            adj.get(prerequisite[1]).add(prerequisite[0]);
        }
        boolean[] visited = new boolean[numCourses];
        boolean[] inStack = new boolean[numCourses];
        for(int i = 0 ; i < numCourses ; i++) {
            if(dfs(i, adj, visited, inStack)) {
                return false;
            }
        }
        return true;
    }
    private static boolean dfs(int node, List<List<Integer>> adj, boolean[] visited, boolean[] inStack) {
//        cycle detected via current recursion stack
        if(inStack[node])
            return true;
        if(visited[node])
            return false;
        inStack[node] = true;
        visited[node] = true;
        for(int neighbour : adj.get(node)) {
            if(dfs(neighbour, adj, visited, inStack))
                return true;
        }
//        remove from stack
        inStack[node] = false;
        return false;
    }
}

/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.
Example 1:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
Example 2:
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
Constraints:
1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
 */
