package leetdaily.easy;

import java.util.ArrayDeque;
import java.util.Deque;

public class MakeGood1554 {
    public static void main(String[] args) {
        String s = "leEeetcode";
        System.out.println(makeGood(s));
    }

//    using stack; time: O(n), space: O(n)
    public static String makeGood(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(Character c : s.toCharArray()) {
            if(!stack.isEmpty() && Math.abs(stack.peek() - c) == 32)
                stack.pop();
            else
                stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }

//    iteration; time: O(n^2), space: O(n)
    public static String makeGood2(String s) {
        StringBuilder newStr = new StringBuilder(s);
        while(newStr.length() > 1) {
            boolean found = false;
            for(int i = 0 ; i < newStr.length() - 1 ; i++) {
//                everything wrt the builder
                char currChar = newStr.charAt(i);
                char nextChar = newStr.charAt(i + 1);
                if(Math.abs(currChar - nextChar) == 32) {
                    newStr.deleteCharAt(i);
                    newStr.deleteCharAt(i);
                    found = true;
//                  important step, since reshuffling has happened
                    break;
                }
            }
//            break the loop if no more changes required
            if(!found)
                break;
        }
        return newStr.toString();
    }

//    recursion; time: O(n^2), space: O(n^2) [space is proportional to the maximum depth of the recursion tree (upto n/2 pairs -> O(n) plus each function takes O(n) space)]
    public static String makeGood3(String s) {
//        if we find a pair in s, remove this pair and solve the remaining recursively
        for(int i = 0 ; i < s.length() - 1 ; i++) {
            if(Math.abs(s.charAt(i) - s.charAt(i + 1)) == 32)
                return makeGood3(s.substring(0, i) + s.substring(i + 2));
        }
//        base case: if we can't find a pair just return s;
        return s;
    }

    //    [def]; using stack; time: O(n), space: O(n) [slowest]
    public static String makeGood1(String s) {
        Deque<String> stack = new ArrayDeque<>();
        String[] arr = s.split("");
        for(String str : arr) {
            if(!stack.isEmpty() && stack.peek().equalsIgnoreCase(str)) {
                if(!stack.peek().equals(str)) {
                    stack.pop();
                    continue;
                }
            }
            stack.push(str);
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }
}

/*
Given a string s of lower and upper case English letters.
A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
0 <= i <= s.length - 2
s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep doing this until the string becomes good.
Return the string after making it good. The answer is guaranteed to be unique under the given constraints.
Notice that an empty string is also good.
Example 1:
Input: s = "leEeetcode"
Output: "leetcode"
Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".
Example 2:
Input: s = "abBAcC"
Output: ""
Explanation: We have many possible scenarios, and all lead to the same answer. For example:
"abBAcC" --> "aAcC" --> "cC" --> ""
"abBAcC" --> "abBA" --> "aA" --> ""
Example 3:
Input: s = "s"
Output: "s"

Constraints:
1 <= s.length <= 100
s contains only lower and upper case English letters.
 */