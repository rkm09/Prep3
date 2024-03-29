package graphs.graphtheory;

import common.Pair;

import java.util.HashMap;
import java.util.Map;

public class FindTheCelebrity277 extends Relation{
    private static int numberOfPeople;
    private static Map<Pair<Integer, Integer>, Boolean> cache = new HashMap<>();
    public static void main(String[] args) {
        FindTheCelebrity277 find = new FindTheCelebrity277();
        System.out.println(find.findCelebrity(2));
    }

//    logical deduction; time: O(n), space: O(n)
    public int findCelebrity(int n) {
       numberOfPeople = n;
       int celebrityCandidate = 0;
       for(int i = 1 ; i < n ; i++) {
           if(knows(celebrityCandidate, i)) {
               celebrityCandidate = i;
           }
       }
       if(isCelebrity(celebrityCandidate)) {
           return celebrityCandidate;
       }
       return -1;
    }
    private boolean isCelebrity(int i) {
        for(int j = 0 ; j < numberOfPeople ; j++) {
            if(i == j) continue;
            if(knows(i,j) || !knows(j,i)) {
                return false;
            }
        }
        return true;
    }

//    [def] brute force; time: O(n^2), space: O(1)
    public int findCelebrity2(int n) {
        int[] scores = new int[n + 1];
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                if(i == j) continue;
                if(knows(i, j)) {
                    scores[j]++;
                    scores[i]--;
                }
            }
        }
        for(int i = 0 ; i < n ; i++) {
            if(scores[i] == n - 1)
                return i;
        }
        return -1;
    }

    @Override
    public boolean knows(int a, int b) {
        if(!cache.containsKey(new Pair<>(a, b))) {
            cache.put(new Pair<>(a, b), super.knows(a, b));
        }
        return cache.get(new Pair<>(a,b));
    }
}
class Relation {
    public boolean knows(int a, int b) {
        int[][] graph = {{1,1,0},{0,1,0},{1,1,1}};
        return graph[a][b] == 1;
    }
}

/*
Suppose you are at a party with n people labeled from 0 to n - 1 and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know the celebrity, but the celebrity does not know any of them.
Now you want to find out who the celebrity is or verify that there is not one. You are only allowed to ask questions like: "Hi, A. Do you know B?" to get information about whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
You are given a helper function bool knows(a, b) that tells you whether a knows b. Implement a function int findCelebrity(n). There will be exactly one celebrity if they are at the party.
Return the celebrity's label if there is a celebrity at the party. If there is no celebrity, return -1.
Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
Output: 1
Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
Input: graph = [[1,0,1],[1,1,0],[0,1,1]]
Output: -1
Explanation: There is no celebrity.
Constraints:
n == graph.length == graph[i].length
2 <= n <= 100
graph[i][j] is 0 or 1.
graph[i][i] == 1
Follow up: If the maximum number of allowed calls to the API knows is 3 * n, could you find a solution without exceeding the maximum number of calls?
 */