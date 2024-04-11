package leetdaily.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKDigits402 {
    public static void main(String[] args) {
        String num = "112";
        System.out.println(removeKdigits(num, 1));
    }

//    given two number sequences, it is the leftmost 'distinct' digit which determines the superior of the two. 1axx, 1bxx
//    greedy; time: O(n), space: O(n)
    public static String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char c : num.toCharArray()) {
            while(!stack.isEmpty() && k > 0 && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
//        remove from the tail, the ones which did not violate the above condition (since msb has already been verified)
        while(k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        boolean isLeadingZero = true;
        while(!stack.isEmpty()) {
            char c = stack.removeLast();
            if(isLeadingZero &&  c == '0') continue;
            isLeadingZero =  false;
            sb.append(c);
        }

        if(sb.length() == 0) return "0";
        return sb.toString();
    }
}

/*
Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.
Example 1:
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:
Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

Constraints:
1 <= k <= num.length <= 105
num consists of only digits.
num does not have any leading zeros except for the zero itself.
 */