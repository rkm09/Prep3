package leetdaily.medium;

import common.Pair;

import java.util.*;

public class FurthestBuilding1642 {
    public static void main(String[] args) {
        int[] heights = {4,2,7,6,9,14,12};
//        reason why greedy won't work;
//        [4,12,2,7,3,18,20,3,19], bricks: 10, ladders: 2 - testcase that works against choosing ladder first [greedy]
//        [1,5,1,2,3,4,10000], bricks: 4, ladders: 1 - testcase that works against choosing bricks first [greedy]
        System.out.println(furthestBuilding(heights, 5, 1));
    }

//  Min heap (allocate minimum diff for bricks); time: O(nlogn), space: O(n)
    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        Queue<Integer> ladderAllocations = new PriorityQueue<>();
        for(int i = 0 ; i < heights.length - 1 ; i++) {
            int climb = heights[i + 1] - heights[i];
//            jump down
            if(climb <= 0)
                continue;
//            else, allocate ladder
            ladderAllocations.add(climb);
            if(ladderAllocations.size() <= ladders) {
                continue;
            }
            bricks -= ladderAllocations.remove();
            if(bricks < 0)
                return i;
        }
        return heights.length - 1;
    }

    //  Max heap (allocate maximum diff for ladders); time: O(nlogn), space: O(n)
    public static int furthestBuilding1(int[] heights, int bricks, int ladders) {
        Queue<Integer> brickAllocations = new PriorityQueue<>((a, b) -> b - a);
        for(int i = 0 ; i < heights.length - 1 ; i++) {
            int climb = heights[i + 1] - heights[i];
//            jump down
            if(climb <= 0)
                continue;
//            else allocate bricks
            brickAllocations.add(climb);
            bricks -= climb;
//            no more bricks or ladders left
            if(bricks < 0 && ladders == 0)
                return i;
            if(bricks < 0) {
                bricks += brickAllocations.remove();
                ladders--;
            }
        }
        return heights.length - 1;
    }

    /* Binary search usage:
        Given an input, determining if it is possible to go all the way across is an easy problem;
        you could simply make a list of all the climbs, min-sort it, and then cover as many climbs as you can with bricks, and then cover the rest with ladders.
        If this covered the entire climb list, then you’d know that it was possible to get all the way across.

        On an odd-length search space, identifying the midpoint is straightforward.
        On even-length search spaces, though, we have two possible midpoints.
        The final step of the binary search algorithm design process is to decide whether it is the lower or higher midpoint that should be used.

        It's generally easiest to set lo to represent the lowest possible position of the target and hi to be the highest possible position.

        The short rule to remember is:
        if you used hi = mid - 1, then use the higher midpoint.
        If you used lo = mid + 1, then use the lower midpoint.
        If you used both of these, then you can use either midpoint. If you didn’t use either (i.e., you have lo = mid and hi = mid), then, unfortunately, your code is buggy, and you won’t be able to guarantee convergence.
     */

//    binary search for final reachable building; time: O(nlog^2n) [logn for binary search with nlogn sorting each time], space: O(n)
    public static int furthestBuilding2(int[] heights, int bricks, int ladders) {
        int low = 0;
        int high = heights.length - 1;
        while(low < high) {
            int mid = low + (high - low + 1) / 2;
            if(isReachable2(mid, heights, bricks, ladders)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
//        can return either lo or hi
        return low;
    }
    private static boolean isReachable2(int buildingIndex, int[] heights, int bricks, int ladders) {
        List<Integer> climbs = new ArrayList<>();
        for(int i = 0 ; i < buildingIndex ; i++) {
            int h1 = heights[i];
            int h2 = heights[i + 1];
            if(h1 > h2)
                continue;
            climbs.add(h2 - h1);
        }

        Collections.sort(climbs);

        for(int climb : climbs) {
            if(climb <= bricks) {
                bricks -= climb;
            } else if(ladders > 0){
                ladders--;
            } else {
                return false;
            }
        }
        return true;
    }

//    Improved binary search; time: O(nlogn), space: O(n)
    public static int furthestBuilding3(int[] heights, int bricks, int ladders) {
        List<int[]> sortedClimbs = new ArrayList<>();
        for(int i = 0 ; i < heights.length - 1; i++) {
            int climb = heights[i + 1] - heights[i];
            if(climb < 0)
                continue;
            sortedClimbs.add(new int[]{climb, i + 1});
        }
        Collections.sort(sortedClimbs, Comparator.comparingInt(a -> a[0]));

        int low = 0;
        int high = heights.length - 1;
        while(low < high) {
            int mid = low + (high - low + 1) / 2;
            if(isReachable(mid, sortedClimbs, bricks, ladders)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
    private static boolean isReachable(int buildingIndex, List<int[]> climbs, int bricks, int ladders) {
        for(int[] climbEntry : climbs) {
            int climb = climbEntry[0];
            int index = climbEntry[1];
            if(index > buildingIndex)
                continue;
            if(climb <= bricks)
                bricks -= climb;
            else if(ladders > 0)
                ladders--;
            else
                return false;
        }
        return true;
    }

}

/*
You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.

You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
While moving from building i to building i+1 (0-indexed),
If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.
Example 1:
Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
Output: 4
Explanation: Starting at building 0, you can follow these steps:
- Go to building 1 without using ladders nor bricks since 4 >= 2.
- Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
- Go to building 3 without using ladders nor bricks since 7 >= 6.
- Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
It is impossible to go beyond building 4 because you do not have any more bricks or ladders.
Example 2:
Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
Output: 7
Example 3:
Input: heights = [14,3,19,3], bricks = 17, ladders = 0
Output: 3
Constraints:
1 <= heights.length <= 105
1 <= heights[i] <= 106
0 <= bricks <= 109
0 <= ladders <= heights.length
 */
