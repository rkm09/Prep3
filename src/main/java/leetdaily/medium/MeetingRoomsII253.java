package leetdaily.medium;

public class MeetingRoomsII253 {
    public static void main(String[] args) {
        int[][] intervals = {{0,30},{5,10},{15,20}};
        System.out.println(minMeetingRooms(intervals));
    }
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 1) return 1;
        int rooms = 1;
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (overlap(intervals[i], intervals[i + 1])) {
                    rooms++; break;
                }
            }
        }
        return rooms;
    }
    private static boolean overlap(int[] interval1, int[] interval2) {
        return Math.min(interval1[1], interval2[1]) >
                Math.max(interval1[0], interval2[0]);
    }
}

/*
Given an array of meeting time  intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
Example 1:
Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:
Input: intervals = [[7,10],[2,4]]
Output: 1
Constraints:
1 <= intervals.length <= 104
0 <= starti < endi <= 106
 */