package leetdaily.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval57 {
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{6,9}};
        int[] newInterval = {2,5};
        int[][] res = insert(intervals, newInterval);
        System.out.println(Arrays.toString(res));
    }

    //    linear search; time: O(n), space: O(1) [faster]
    public static int[][] insert1(int[][] intervals, int[] newInterval) {
        List<int[]> res  = new ArrayList<>();
        int n = intervals.length, i = 0;
//        case 1: no overlap before merge
        while(i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i++]);
        }
//       case 2: overlap -> merge
        while(i < n && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        res.add(newInterval);

//       case 3: add the remaining
        while(i < n) {
            res.add(intervals[i++]);
        }

        return res.toArray(new int[res.size()][]);
    }

//    binary search; time: O(n + logn) ~ O(n), space: O(n)
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if(n == 0) return new int[][]{newInterval};
        int left = 0, right = n - 1, target = newInterval[0];
//        get insertion position
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(intervals[mid][0] < target)
                left = mid + 1;
             else
                right = mid - 1;
        }
//        combine all into a single list
        List<int[]> result = new ArrayList<>();
        for(int i = 0 ; i < left ; i++)
            result.add(intervals[i]);
        result.add(newInterval);
        for(int i = left ; i < n ; i++)
            result.add(intervals[i]);

//        merge without overlap
        List<int[]> merged = new ArrayList<>();
        for(int[] interval : result) {
//            if merged is empty or there is no overlap
            if(merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0])
                merged.add(interval);
            else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }


}

/*
You are given an array of non-overlapping intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
Return intervals after the insertion.
Note that you don't need to modify intervals in-place. You can make a new array and return it.
Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 105
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 105
 */