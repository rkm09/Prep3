package leetdaily.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveInterval1272 {
    public static void main(String[] args) {
        int[][] intervals = {{0,2},{3,4},{5,7}};
        int[] toBeRemoved = {1,6};
        List<List<Integer>> res = removeInterval(intervals, toBeRemoved);
        System.out.println(res);
    }

//    sweep line, one pass; time: O(n), space: O(1)
    public static List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        for(int[] interval  : intervals) {
//            if there is no overlap
            if(interval[1] < toBeRemoved[0] || interval[0] > toBeRemoved[1]) {
                res.add(List.of(interval[0], interval[1]));
            } else {
//                if there is a left interval we need to take into account
                if(interval[0] < toBeRemoved[0]) {
                    res.add(Arrays.asList(interval[0], toBeRemoved[0]));
                }
//                if there is a right interval we need to take into account
                if(interval[1] > toBeRemoved[1]) {
                    res.add(List.of(toBeRemoved[1], interval[1]));
                }
            }
        }
        return res;
    }
}

/*
A set of real numbers can be represented as the union of several disjoint intervals, where each interval is in the form [a, b). A real number x is in the set if one of its intervals [a, b) contains x (i.e. a <= x < b).
You are given a sorted list of disjoint intervals representing a set of real numbers as described above, where intervals[i] = [ai, bi] represents the interval [ai, bi). You are also given another interval toBeRemoved.
Return the set of real numbers with the interval toBeRemoved removed from intervals. In other words, return the set of real numbers such that every x in the set is in intervals but not in toBeRemoved. Your answer should be a sorted list of disjoint intervals as described above.
Example 1:
Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
Output: [[0,1],[6,7]]
Input: intervals = [[0,5]], toBeRemoved = [2,3]
Output: [[0,2],[3,5]]
Example 3:
Input: intervals = [[-5,-4],[-3,-2],[1,2],[3,5],[8,9]], toBeRemoved = [-1,4]
Output: [[-5,-4],[-3,-2],[4,5],[8,9]]
Constraints:
1 <= intervals.length <= 104
-109 <= ai < bi <= 109
 */