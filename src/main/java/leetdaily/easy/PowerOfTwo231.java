package leetdaily.easy;

public class PowerOfTwo231 {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(16));
    }

//    bitwise operators[b & k] approach (turn off the rightmost bit); time: O(1), space: O(1)
//    A number that is not a power of 2 has more than one 1 (exception being 0);
    public static boolean isPowerOfTwo(int n) {
        if(n <= 0) return false;
        return (n & (n-1)) == 0;
    }

//    bitwise operators; get / isolate the rightmost bit
//    get the rightmost bit; in 2's complement -x is represented by x! + 1 (ie to compute minus x, revert all bits and add 1)
//    !x + 1 => reverts all bits except the rightmost 1 bit.
    public static boolean isPowerOfTwo1(int n) {
        if(n <= 0)  return false;
        return (n & (-n)) == n;
    }

//    brute force; time: O(logn), space: O(1)
    public static boolean isPowerOfTwoN(int n) {
        if(n <= 0) return false;
        while(n % 2 == 0) n /= 2;
        return n == 1;
    }
}

/*
Given an integer n, return true if it is a power of two. Otherwise, return false.
An integer n is a power of two, if there exists an integer x such that n == 2x.
Example 1:
Input: n = 1
Output: true
Explanation: 20 = 1
Example 2:
Input: n = 16
Output: true
Explanation: 24 = 16
Example 3:
Input: n = 3
Output: false
Constraints:
-231 <= n <= 231 - 1
Follow up: Could you solve it without loops/recursion?
 */