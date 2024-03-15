package leetdaily.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII253 {
    public static void main(String[] args) {
        int[][] intervals = {{0,30},{5,10},{15,20}};
        System.out.println(minMeetingRooms(intervals));
    }

//    Priority Queue; time: O(nlogn), space: O(n)
    public static int minMeetingRooms(int[][] intervals) {
        if(intervals.length == 0) return 0;
        PriorityQueue<Integer> allocator = new PriorityQueue<>();
        Arrays.sort(intervals, Comparator.comparingInt(a->a[0]));
        allocator.offer(intervals[0][1]);
        for(int i = 1 ; i < intervals.length ; i++) {
            if(!allocator.isEmpty() && intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }
            allocator.offer(intervals[i][1]);
        }
        return allocator.size();
    }

//    chronological ordering; time: O(nlogn), space: O(n) [faster]
    public static int minMeetingRooms1(int[][] intervals) {
        int n = intervals.length;
        // if(n == 0) return 0;
        int[] start = new int[n];
        int[] end = new int[n];
        for(int i = 0 ; i < n ; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int usedRooms = 0, startPointer = 0, endPointer = 0;
        while(startPointer < n) {
            if(start[startPointer] >= end[endPointer]) {
                usedRooms--;
                endPointer++;
            }
            usedRooms++;
            startPointer++;
        }
        return usedRooms;
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