package top150.strings_arrays;

public class TrappingRainWater42 {
    public static void main(String[] args) {
        int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap1(heights));
    }

//    dp; time: O(n), space: O(n)
    public static int trap1(int[] height) {
        int n = height.length, ans = 0;
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for(int i = 1 ; i < n ; i++) {
          leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for(int i = n - 2 ; i >= 0 ; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        for(int i = 1 ; i < n - 1 ; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    //   using two pointer; time: O(n), space: O(1)
    public static int trap(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1, result = 0;
        int leftMax = 0, rightMax = 0;
        while(left < right) {
            if(height[left] < height[right]) {
                if(height[left] < leftMax)
                    result += (leftMax - height[left]);
                else
                    leftMax = height[left];
                left++;
            } else {
                if(height[right] < rightMax)
                    result += (rightMax - height[right]);
                else
                    rightMax = height[right];
                right--;
            }

        }
        return result;
    }

    
//    brute force; time: O(n^2), space: O(1)
    public static int trap3(int[] height) {
        int n = height.length, ans = 0;
//        note the first and the last need not be considered when standing at the 1st and last respectively, as there is no wall beyond them
        for(int i = 1 ; i < n - 1 ; i++) {
            int leftMax = 0, rightMax = 0;
//            but 1st and last are valid cells to consider for any other elevation point
            for(int j = i ; j >= 0 ; j--) {
//                max height in the left half including this one
                leftMax = Math.max(leftMax, height[j]);
            }
            for(int j = i ; j < n ; j++) {
//                max height in the right half including this one
                rightMax = Math.max(rightMax, height[j]);
            }
//            finally, add to result the min of left & right minus its own height
            ans += Math.min(leftMax, rightMax) - height[i];
        }
        return ans;
    }

}

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9

Constraints:
n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
 */