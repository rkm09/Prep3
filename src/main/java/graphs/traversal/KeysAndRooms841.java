package graphs.traversal;

import java.util.*;
import java.util.stream.Collectors;


public class KeysAndRooms841 {
    public static void main(String[] args) {
//        rooms.add(List.of(1,3));
        Integer[][] arr = new Integer[][] {{1,3},{3,0,1},{2},{0}};
        List<List<Integer>> rooms = Arrays.stream(arr)
                .map(Arrays::asList)
                .collect(Collectors.toList());
        System.out.println(canVisitAllRooms(rooms));
    }

//   [def]; dfs (recursive); time: O(n+e), space: O(n)
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        dfs(rooms, visited, 0);
        for(int i = 0 ; i < n ; i++) {
            if(!visited[i])
                return false;
        }
        return true;
    }
    private static void dfs(List<List<Integer>> rooms, boolean[] visited, int node) {
        visited[node] = true;
        for(int neighbour : rooms.get(node)) {
            if(!visited[neighbour])
                dfs(rooms, visited, neighbour);
        }
    }

//    dfs (iterative) ; time: O(n+e), space: O(n)
    public static boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        int n = rooms.size();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        boolean[] visited = new boolean[n];
        while(!stack.isEmpty()) {
            int node = stack.pop();
            visited[node] = true;
            for(int neighbour : rooms.get(node)) {
                if(!visited[neighbour])
                    stack.push(neighbour);
            }
        }
        for(int i = 0 ; i < n ; i++) {
            if(!visited[i])
                return false;
        }
        return true;
    }
}

/*
There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.
When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.
Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.
Example 1:
Input: rooms = [[1],[2],[3],[]]
Output: true
Explanation:
We visit room 0 and pick up key 1.
We then visit room 1 and pick up key 2.
We then visit room 2 and pick up key 3.
We then visit room 3.
Since we were able to visit every room, we return true.
Example 2:
Input: rooms = [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.
Constraints:
n == rooms.length
2 <= n <= 1000
0 <= rooms[i].length <= 1000
1 <= sum(rooms[i].length) <= 3000
0 <= rooms[i][j] < n
All the values of rooms[i] are unique.
 */