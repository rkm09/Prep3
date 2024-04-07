package leetdaily.medium;

import java.util.*;

public class ValidParentheses278 {
    public static void main(String[] args) {
        String s = "(*))";
        System.out.println(checkValidString(s));
    }

//    greedy two pointer; time: O(n), space: O(1)
    public static boolean checkValidString(String s) {
        int openBrackets = 0, closeBrackets = 0;
        int length = s.length() - 1;
        for(int i = 0 ; i <= length ; i++) {
//            count open parenthesis or '*'
            if(s.charAt(i) == '(' || s.charAt(i) == '*')
                openBrackets++;
            else
                openBrackets--;
//            count close parenthesis or '*
            if(s.charAt(length - i) == ')' || s.charAt(length- i) == '*')
                closeBrackets++;
            else
                closeBrackets--;
//            if any of them turns negative string is invalid
            if(openBrackets < 0 || closeBrackets < 0)
                return false;
        }
        return true;
    }

//    two stacks; time: O(n), space: O(n)
    public static boolean checkValidString1(String s) {
        Deque<Integer> starIndex = new ArrayDeque<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);
            if(c == '(')
                stack.push(i);
            else if(c == '*')
                starIndex.push(i);
            else {
                if(!stack.isEmpty())
                    stack.pop();
                else if(!starIndex.isEmpty())
                    starIndex.pop();
                else
                    return false;
            }
        }
        while(!stack.isEmpty() && !starIndex.isEmpty()) {
           if(stack.pop() > starIndex.pop())
               return false;
        }
        return stack.isEmpty();
    }
}

/*
Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
The following rules define a valid string:
Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
Example 1:
Input: s = "()"
Output: true
Example 2:
Input: s = "(*)"
Output: true
Example 3:
Input: s = "(*))"
Output: true

Constraints:
1 <= s.length <= 100
s[i] is '(', ')' or '*'.
 */