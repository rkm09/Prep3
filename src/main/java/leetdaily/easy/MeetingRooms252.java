package leetdaily.easy;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRooms252 {
    public static void main(String[] args) {
        int[][] intervals = {{0,30},{5,10},{15,20}};
        System.out.println(canAttendMeetings(intervals));
    }

//    [def]; sort; time: O(nlogn), space: O(1)
    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
        for(int i = 1 ; i < intervals.length ; i++) {
            if(intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }


//  brute force; time: O(n^2), space: O(1)
    public static boolean canAttendMeetings1(int[][] intervals) {
        for(int i = 0 ; i < intervals.length ; i++) {
            for(int j = i + 1 ; j < intervals.length ; j++) {
                if(isOverlapping(intervals[i], intervals[j])) {
                    return false;
                }
            }
        }
        return true;
    }
//    basic way
//    private static boolean isOverlapping(int[] interval1, int[] interval2) {
//        return ((interval1[0] >= interval2[0] && interval1[0] < interval2[1])
//        || (interval2[0] >= interval1[0] && interval2[0] < interval1[1]));
//    }

//    a more concise way
    private static boolean isOverlapping(int[] interval1, int[] interval2) {
        return Math.min(interval1[1], interval2[1]) >
                Math.max(interval1[0], interval2[0]);
    }
}
/*
Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
Example 1:
Input: intervals = [[0,30],[5,10],[15,20]]
Output: false
Example 2:
Input: intervals = [[7,10],[2,4]]
Output: true
Constraints:
0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti < endi <= 106

 */