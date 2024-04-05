package gen.easy;

public class PowerOfThree326 {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree1(27));
    }

//    iteration; time: O(log3n) [number of divisions], space: O(1)
    public static boolean isPowerOfThree(int n) {
        if(n < 1) return false;
        while(n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

//    base conversion; time: O(log3n), space: O(log3n)
//    In Base 10, all powers of 10 start with the digit 1 and then are followed only by 0 (e.g. 10, 100, 1000).
//    This is true for other bases and their respective powers. For instance in base 2, the representations of powers of 2: 10, 100, 1000..
//    Therefore, if we convert our number to base 3 and the representation is of the form 100...0, then the number is a power of 3.
    public static boolean isPowerOfThree1(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }

//    iteration [def]; time: O(log3n), space: O(1)
    public static boolean isPowerOfThree2(int n) {
        if(n < 1) return false;
        if(n == 1) return true;
        while(n > 1) {
            if(n % 3 == 0) {
                n /= 3;
            } else return false;
        }
        return true;
    }

//    recursion [def]; time: O(n), space: O(1)
    public static boolean isPowerOfThree3(int n) {
        if(n < 1) return false;
        if(n == 1) return true;
        if(n % 3 != 0) return false;
        return isPowerOfThree3(n / 3);
    }

}

/*
Given an integer n, return true if it is a power of three. Otherwise, return false.
An integer n is a power of three, if there exists an integer x such that n == 3x.
Example 1:
Input: n = 27
Output: true
Explanation: 27 = 33
Example 2:
Input: n = 0
Output: false
Explanation: There is no x where 3x = 0.
Example 3:
Input: n = -1
Output: false
Explanation: There is no x where 3x = (-1).
Constraints:
-231 <= n <= 231 - 1

Follow up: Could you solve it without loops/recursion?
 */
