package leetdaily.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class MinRemoval1249 {
    public static void main(String[] args) {
        String s = "lee(t(c)o)de)";
        String s1 = "())()(((";
        System.out.println(minRemoveToMakeValid(s1));
    }

//    stack[def]; time: O(n), space: O(n) [fastest]
    public static String minRemoveToMakeValid2(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder(s);
        int index = 0;
        for(char c : s.toCharArray()) {
            if(c == '(')
                stack.push(c);
            else if(c == ')') {
                if(!stack.isEmpty())
                    stack.pop();
                else
                    sb.deleteCharAt(index--);
            }
            index++;
        }
        int size = stack.size();
        while(size-- != 0) {
            sb.deleteCharAt(sb.lastIndexOf("("));
        }
        return sb.toString();
    }

//    two pass string builder; time: O(n), space: O(n)
    public static String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int openToKeep = 0, balance = 0;
//        pass 1: remove invalid ')'
        for(int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);
            if(c == '(') {
                openToKeep++;
                balance++;
            } else if(c == ')') {
                if(balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }

//        pass 2: remove invalid '('
        StringBuilder result = new StringBuilder();
        openToKeep = openToKeep - balance;
        for(int i = 0 ; i < sb.length() ; i++) {
            char c = sb.charAt(i);
            if(c == '(') {
                openToKeep--;
                if(openToKeep < 0) continue;
            }
            result.append(c);
        }
        return result.toString();
    }

//    stack; time: O(n), space: O(n)
    public static String minRemoveToMakeValid1(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);
            if(c == '(')
                stack.push(i);
            else if(c == ')') {
                if(!stack.isEmpty())
                    stack.pop();
                else
                    indexesToRemove.add(i);
            }
        }
//        add any remaining ( to set;
        while(!stack.isEmpty()) indexesToRemove.add(stack.pop());

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length() ; i++) {
            if(!indexesToRemove.contains(i))
                sb.append(s.charAt(i));
        }
        return sb.toString();
    }


}

/*
Given a string s of '(' , ')' and lowercase English characters.
Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
Formally, a parentheses string is valid if and only if:
It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Example 1:
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
Example 2:
Input: s = "a)b(c)d"
Output: "ab(c)d"
Example 3:
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.

Constraints:
1 <= s.length <= 105
s[i] is either'(' , ')', or lowercase English letter.
 */