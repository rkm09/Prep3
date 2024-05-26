package leetdaily.hard;

import java.util.Arrays;

public class AttendanceII552 {
    private final int MOD = (int) 1e9 + 7;
    private int[][][] memo;
    public static void main(String[] args) {
        AttendanceII552 a = new AttendanceII552();
        System.out.println(a.checkRecord(2));
    }

//    top down dp(recursive); time: O(n), space: O(n) [O(n * 2 * 3) => O(6.n) => O(n)]
    public int checkRecord(int n) {
        memo = new int[n + 1][2][3];
        for(int[][] array2D : memo)
            for(int[] array1D : array2D)
                Arrays.fill(array1D, - 1);

        return eligibleCombinations(n, 0, 0);
    }

    private int eligibleCombinations(int n, int totalAbsence, int consecutiveLate) {
        if(totalAbsence >= 2 || consecutiveLate >= 3)
            return 0;
        if(n == 0)
            return 1;
        if(memo[n][totalAbsence][consecutiveLate] != -1)
            return memo[n][totalAbsence][consecutiveLate];

        int count;
//        count 'P'
        count = eligibleCombinations(n - 1, totalAbsence, 0) % MOD;
//        count 'A'
        count = (count + eligibleCombinations(n - 1, totalAbsence + 1, 0)) % MOD;
//        count 'L'
        count = (count + eligibleCombinations(n - 1, totalAbsence, consecutiveLate + 1)) % MOD;

        return memo[n][totalAbsence][consecutiveLate] = count;

    }
}

/*
An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:
'A': Absent.
'L': Late.
'P': Present.
Any student is eligible for an attendance award if they meet both of the following criteria:
The student was absent ('A') for strictly fewer than 2 days total.
The student was never late ('L') for 3 or more consecutive days.
Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.
Example 1:
Input: n = 2
Output: 8
Explanation: There are 8 records with length 2 that are eligible for an award:
"PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
Example 2:
Input: n = 1
Output: 3
Example 3:
Input: n = 10101
Output: 183236316

Constraints:
1 <= n <= 105
 */