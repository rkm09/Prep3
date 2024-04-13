package leetdaily.hard;

public class LargestRectangle84 {
    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
    }


    public static int largestRectangleArea(int[] heights) {
       return 0;
    }

//    [TLE] brute force; time: O(n^2), space: O(1)
    public static int largestRectangleArea2(int[] heights) {
        int n = heights.length, maxArea = 0;
        for(int i = 0 ; i < n ; i++) {
            int minHeight = Integer.MAX_VALUE;
            for(int j = i ; j < n ; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }
}


/*
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
Example 1:
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:
Input: heights = [2,4]
Output: 4

Constraints:

1 <= heights.length <= 105
0 <= heights[i] <= 104
 */