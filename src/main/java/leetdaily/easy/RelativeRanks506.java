package leetdaily.easy;

import common.Pair;

import java.util.*;

public class RelativeRanks506 {
    public static void main(String[] args) {
        int[] score = {5,4,3,2,1};
        System.out.println(Arrays.toString(findRelativeRanks2(score)));
    }

    //    reverse sort; time: O(nlogn), space: O(n)
    public static String[] findRelativeRanks2(int[] score) {
        int n = score.length;
        int[] scoreCopy = Arrays.copyOf(score, n);
        Map<Integer, Integer> scoreToIndex = new HashMap<>();
        for(int i = 0 ; i < n ; i++) {
            scoreToIndex.put(scoreCopy[i], i);
        }
        Arrays.sort(scoreCopy);
        String[] ranks = new String[n];
        for(int i = 0 ; i < n ; i++) {
            int idx = scoreToIndex.get(scoreCopy[n - 1 - i]);
            if(i == 0)
                ranks[idx] = "Gold Medal";
            else if(i == 1)
                ranks[idx] = "Silver Medal";
            else if(i == 2)
                ranks[idx] = "Bronze Medal";
            else
                ranks[idx] = String.valueOf(i + 1);
        }

        return ranks;
    }

//  Priority queue; time: O(nlogn), space: O(n)
    public static String[] findRelativeRanks(int[] score) {
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((a,b) -> b.getKey() - a.getKey());
        for(int i = 0 ; i < score.length ; i++)
            pq.add(new Pair<>(score[i], i));
        String[] res = new String[score.length];
        int rank = 1, idx;
        while(!pq.isEmpty()) {
            idx = pq.poll().getValue();
            if(rank == 1)
                res[idx] = "Gold Medal";
            else if(rank == 2)
                res[idx] = "Silver Medal";
            else if(rank == 3)
                res[idx] = "Bronze Medal";
            else
                res[idx] = String.valueOf(rank);
            rank++;
        }

        return res;
    }



//    reverse sort; time: O(nlogn), space: O(n)
    public static String[] findRelativeRanks3(int[] score) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        int n = score.length;
        for(int i = 0 ; i < n ; i++)
            indexMap.put(i, score[i]);
        List<Map.Entry<Integer, Integer>> li = indexMap.entrySet()
                .stream().sorted(Comparator.comparingInt(a-> - a.getValue())).toList();
        String[] res = new String[n]; int count = 0;
        for(Map.Entry<Integer, Integer> entry : li) {
            int idx = entry.getKey();
            if(count == 0)
                res[idx] = "Gold Medal";
            else if(count == 1)
                res[idx] = "Silver Medal";
            else if(count == 2)
                res[idx] = "Bronze Medal";
            else
                res[idx] = String.valueOf(count + 1);
            count++;
        }

        return res;
    }

//    def; reverse sort; time: O(nlogn), space: O(n)
    public static String[] findRelativeRanks4(int[] score) {
        List<Integer> li = Arrays.stream(score).boxed().sorted(Comparator.reverseOrder()).toList();
        int count = 1, n = score.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : li) {
            map.put(num, count++);
        }
        String[] res = new String[n];
        for(int i = 0 ; i < n ; i++) {
            int pos = map.get(score[i]);
            if(pos == 1)
                res[i] = "Gold Medal";
            else if(pos == 2)
                res[i] = "Silver Medal";
            else if(pos == 3)
                res[i] = "Bronze Medal";
            else
                res[i] = String.valueOf(pos);
        }
        return res;
    }
}

/*
You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition. All the scores are guaranteed to be unique.
The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:
The 1st place athlete's rank is "Gold Medal".
The 2nd place athlete's rank is "Silver Medal".
The 3rd place athlete's rank is "Bronze Medal".
For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
Return an array answer of size n where answer[i] is the rank of the ith athlete.
Example 1:
Input: score = [5,4,3,2,1]
Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].
Example 2:
Input: score = [10,3,8,9,4]
Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].

Constraints:
n == score.length
1 <= n <= 104
0 <= score[i] <= 106
All the values in score are unique.
 */
