package leetdaily.easy;

public class PivotInteger2485 {
    public static void main(String[] args) {
        System.out.println(pivotInteger1(8));
    }

//    binary search; time: O(logn), space: O(1)
    public static int pivotInteger(int n) {
//      to-do
        return -1;
    }

//     two pointer; time: O(n), space: O(1)
    public static int pivotInteger1(int n) {
        int leftValue = 1, rightValue = n;
        int sumLeft = leftValue, sumRight = rightValue;

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

//    Maths; time: O(1), space: O(1)
    public static int pivotInteger2(int n) {
        int sum = n * (n + 1) / 2;
        int pivot = (int) Math.sqrt(sum);
        return pivot * pivot == sum ? pivot : -1;
    }

//    brute force; time: O(n^2), space: O(1)
    public static int pivotInteger3(int n) {
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