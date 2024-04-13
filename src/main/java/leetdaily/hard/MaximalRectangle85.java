package leetdaily.hard;

import java.util.Arrays;

public class MaximalRectangle85 {
    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(maximalRectangle(matrix));
    }

//    dp at each point; time: O(nm), space: O(m)
    public static int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int maxArea = 0;
        int[] height = new int[n];
//        initialize left as the leftmost boundary possible
        int[] left = new int[n];
//        initialize right as the rightmost boundary possible
        int[] right = new int[n];
        Arrays.fill(right, n);

        for(int i = 0 ; i < m ; i++) {
            int currLeft = 0, currRight = n;
//            update height
            for(int j = 0 ; j < n ; j++) {
                if(matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
//            update left
            for(int j = 0 ; j < n ; j++) {
                if(matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], currLeft);
                } else {
                    left[j] = 0;
                    currLeft = j + 1;
                }
            }
//            update right
            for(int j = n - 1 ; j >= 0 ; j--) {
                if(matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], currRight);
                } else {
                    right[j] = n;
                    currRight = j;
                }
            }
//            update area
            for(int j = 0 ; j < n ; j++) {
                maxArea = Math.max(maxArea, (right[j] - left[j]) * height[j]);
            }
        }

        return maxArea;
    }
}

/*
Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
Example 1:
Input: matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
Example 2:
Input: matrix = [['0']]
Output: 0
Example 3:
Input: matrix = [['1']]
Output: 1

Constraints:
rows == matrix.length
cols == matrix[i].length
1 <= row, cols <= 200
matrix[i][j] is '0' or '1'.

 */