package leetdaily.medium;

import java.util.*;

public class PerfectSquares279 {
    private static Set<Integer> squareNums;
    public static void main(String[] args) {
        System.out.println(numSquares1(5));
    }

//    dp (knapsack); time: O(n.sqrt(n)), space: O(n)
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
//        bottom case
        dp[0] = 0;
//        prefill squares
        int maxSquareIndex = (int) Math.sqrt(n) + 1;
        int[] squareNums = new int[maxSquareIndex];
        for(int i = 1 ; i < maxSquareIndex ; i++) {
            squareNums[i] = i * i;
        }
        for(int i = 1 ; i <= n ; i++) {
            for(int s = 1; s < maxSquareIndex ; s++) {
                if(i < squareNums[s])
                    break;
                dp[i] = Math.min(dp[i], dp[i - squareNums[s]] + 1);
            }
        }
        return dp[n];
    }

//    greedy enumeration; time: O(n^h/2), space: O(sqrt(n)) [h-maximal number of recursion] (fastest)
    public static int numSquares1(int n) {
        squareNums = new HashSet<>();
        for(int i = 1; i * i <= n ; i++)
            squareNums.add(i*i);
        int count = 1;
        for( ; count <= n ; count++) {
            if(isDividedBy(n, count)) {
                return count;
            }
        }
        return count;
    }
    private static boolean isDividedBy(int n, int count) {
        if(count == 1)
            return squareNums.contains(n);
        for(Integer square : squareNums) {
            if(isDividedBy(n - square, count - 1))
                return true;
        }
        return false;
    }

//    bfs + greedy; time: O(n^h/2), space: O(sqrt(n)^h) [h - height of the n-ary tree]
    public static int numSquares2(int n) {
        List<Integer> squareNums = new ArrayList<>();
        for(int i = 1 ; i * i <= n ; i++)
            squareNums.add(i * i);
//        set used instead of linked list for queue in order to eliminate redundant remainders
        Set<Integer> queue = new HashSet<>();
        queue.add(n);
        int level = 0;
        while(!queue.isEmpty()) {
            level++;
            Set<Integer> nextQueue = new HashSet<>();
            for(Integer remainder : queue) {
                for(Integer square : squareNums) {
                    if(remainder.equals(square))
                        return level;
                    if(remainder < square)
                        break;
                    nextQueue.add(remainder - square);
                }
            }
            queue = nextQueue;
        }
        return level;
    }
}

/*
Given an integer n, return the least number of perfect square numbers that sum to n.
A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
Example 1:
Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.
Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
Constraints:
1 <= n <= 104
 */