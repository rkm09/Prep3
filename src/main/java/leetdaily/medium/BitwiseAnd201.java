package leetdaily.medium;

public class BitwiseAnd201 {
    public static void main(String[] args) {
        System.out.println(rangeBitwiseAnd(5,7));
    }

//    given two integer numbers, we are asked to find the common prefix of their binary strings
//    bit manipulation (bk) [turn off rightmost one]; time: O(1), space: O(1); faster as there are fewer iterations (skips 0 bits)
    public static int rangeBitwiseAnd(int left, int right) {
        while(left < right) {
            right = right & (right - 1);
        }
        return left & right;
    }

//    bit manipulation (shift till you end up with the common prefix); time: O(1), space: O(1)
    public static int rangeBitwiseAnd1(int left, int right) {
        int shift = 0;
        while(left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        return left << shift;
    }
}

/*
Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.
Example 1:
Input: left = 5, right = 7
Output: 4
Example 2:
Input: left = 0, right = 0
Output: 0
Example 3:
Input: left = 1, right = 2147483647
Output: 0
Constraints:
0 <= left <= right <= 231 - 1
 */