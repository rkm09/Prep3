package leetdaily.easy;

import java.util.Arrays;


public class MaxOddBinaryNumber2864 {
    public static void main(String[] args) {
        String s = "010";
        System.out.println(maximumOddBinaryNumber(s));
    }

//    greedy bit manipulation; (1 pass) 2 pointers; time: O(n), space: O(n) [fastest]
    public static String maximumOddBinaryNumber(String s) {
        char[] arr = s.toCharArray();
        int n = s.length();
        int left = 0, right = n - 1;
        while(left <= right) {
            if(arr[left] == '1')
                left++;
            if(arr[right] == '0')
                right--;
            if(left < right && arr[left] == '0' && arr[right] == '1') {
                arr[left] = '1';
                arr[right] = '0';
            }
        }
//        swap rightmost one bit to the end
        arr[left - 1] = '0';
        arr[n - 1] = '1';
        return new String(arr);
    }

//    greedy + ones count; time: O(n), space: O(n)
    public static String maximumOddBinaryNumber1(String s) {
        int n = s.length();
        int onesCnt = 0;
        for(int i = 0 ; i < n ; i++)
            onesCnt += s.charAt(i) - '0';
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < onesCnt - 1 ; i++)
            sb.append("1");
        for(int i = 0 ; i < n - onesCnt ; i++)
            sb.append("0");
        sb.append("1");
        return sb.toString();
    }

//    greedy bit manipulation; sorting & swapping; time: O(nlogn), space: O(n)
    public static String maximumOddBinaryNumber2(String s) {
        char[] arr = s.toCharArray();
        int n = s.length();
        Arrays.sort(arr);
        int secondLast = n - 2;
        for(int i = 0 ; i < n / 2 ; i++) {
            char temp = arr[i];
            arr[i] = arr[secondLast - i];
            arr[secondLast - i] = temp;
        }
        return new String(arr);
    }

}

/*
You are given a binary string s that contains at least one '1'.
You have to rearrange the bits in such a way that the resulting binary number is the maximum odd binary number that can be created from this combination.
Return a string representing the maximum odd binary number that can be created from the given combination.
Note that the resulting string can have leading zeros.
Example 1:
Input: s = "010"
Output: "001"
Explanation: Because there is just one '1', it must be in the last position. So the answer is "001".
Example 2:
Input: s = "0101"
Output: "1001"
Explanation: One of the '1's must be in the last position. The maximum number that can be made with the remaining digits is "100". So the answer is "1001".
Constraints:
1 <= s.length <= 100
s consists only of '0' and '1'.
s contains at least one '1'.
 */