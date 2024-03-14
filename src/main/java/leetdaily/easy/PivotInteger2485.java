package leetdaily.easy;

public class PivotInteger2485 {
    private static final int MAX = 1000;
    private static int[] precompute = new int[MAX + 1];
    public static void main(String[] args) {
        System.out.println(pivotInteger1(8));
    }

//    binary search; time: O(logn), space: O(1)
//    from derivation in math section; x^2 = n(n+1)/2 => pivot * pivot - sum = 0 for pivot integer
    public static int pivotInteger(int n) {
        int left = 1, right = n;
        int totalSum = n * (n + 1) / 2;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(mid * mid - totalSum < 0) {
                left = mid + 1;
            } else {
//                if it is equal or greater
                right = mid;
            }
        }
        if(left * left - totalSum == 0)
            return left;
        return -1;
    }

//     two pointer; time: O(n), space: O(1)
    public static int pivotInteger1(int n) {
        int leftValue = 1, rightValue = n;
        int sumLeft = leftValue, sumRight = rightValue;

//        edge case
        if(n == 1) return n;

        while(leftValue < rightValue) {
            if(sumLeft < sumRight) {
                sumLeft += ++leftValue;
            } else {
                sumRight += --rightValue;
            }
            if(sumLeft == sumRight && leftValue + 1 == rightValue - 1)
                return leftValue + 1;
        }
        return -1;
    }

//    Maths; time: O(1), space: O(1) [fastest]
//    Sum[1...x] = Sum[x...n]
//    deriving Sum[x..n] = Sum[1..n] - Sum[1..(x-1)] ps. remove 1 to (x - 1) from  1 to n [so as to preserve x inclusive]
//    when you solve => x(x + 1)/2 = n(n + 1)/2 - (x-1)x/2 => x = sqrt(n(n+1)/2);
    public static int pivotInteger2(int n) {
        int sum = n * (n + 1) / 2;
        int pivot = (int) Math.sqrt(sum);
        return pivot * pivot == sum ? pivot : -1;
    }

//    precompute & cache in a lookup table; time: O(m), space: O(m) [where m is the max precomputed value count]
//    Traditional dynamic programming involves reusing intermediate results (e.g., DP[y] based on DP[x] where x is smaller than y). However, this approach focuses on pre-computing and storing values for efficient retrieval, so it is considered pre-computation and storage in a lookup table rather than dynamic programming.
//    This approach is practical when handling multiple queries involving different n values. It's more efficient because it doesn't redo the pivot calculation every time. But there's a downside—the first setup takes some time, O(n)O(n)O(n) where n is the maximum n value. This approach is less efficient when the pivot is needed for only a few n values or when memory is limited.
    public static int pivotInteger3(int n) {
        for(int i = 1, j = 1; i <= MAX ; i++) {
            int sum = i * (i + 1) / 2;
            while(j * j < sum) j++;
            precompute[i] = j * j == sum ? j : -1;
        }
        return precompute[n];
    }

//    brute force; time: O(n^2), space: O(1)
    public static int pivotInteger4(int n) {
        for(int i = 1 ; i <= n ; i++) {
            int leftSum = 0, rightSum = 0;
            for(int j = 1 ; j <= i ; j++) {
                leftSum += j;
            }
            for(int k = i ; k <= n ; k++) {
                rightSum += k;
            }
            if(leftSum == rightSum)
                return i;
        }
        return -1;
    }
}

/*
Given a positive integer n, find the pivot integer x such that:
The sum of all elements between 1 and x inclusively equals the sum of all elements between x and n inclusively.
Return the pivot integer x. If no such integer exists, return -1. It is guaranteed that there will be at most one pivot index for the given input.
Example 1:
Input: n = 8
Output: 6
Explanation: 6 is the pivot integer since: 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21.
Example 2:
Input: n = 1
Output: 1
Explanation: 1 is the pivot integer since: 1 = 1.
Example 3:
Input: n = 4
Output: -1
Explanation: It can be proved that no such integer exist.

Constraints:
1 <= n <= 1000
 */