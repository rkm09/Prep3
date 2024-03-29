package leetdaily.medium;


import java.util.*;
import java.util.stream.Collectors;


public class CustomSorting791 {
    public static void main(String[] args) {
        String order = "bcafg", s = "aabcd";
        System.out.println(customSortString(order, s));
    }

//    hashmap; time: O(n), space: O(n); [fastest]
    public static String customSortString(String order, String s) {
        Map<Character, Integer> freq = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray())
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        for(char c : order.toCharArray()) {
            int count = freq.getOrDefault(c, 0);
            while(count-- > 0) {
                sb.append(c);
                freq.put(c, count);
            }
        }
        for(char c : freq.keySet()) {
            int count = freq.get(c);
            while(count-- > 0) sb.append(c);
        }
        return sb.toString();
    }

//    custom comparator; time: O(nlogn), space: O(n)
    public static String customSortString1(String order, String s) {
        int n = s.length();
        Character[] result = new Character[n];
        for(int i = 0 ; i < n ; i++)
            result[i] = s.charAt(i);
        Arrays.sort(result, Comparator.comparing(order::indexOf));
//        return Arrays.stream(result).map(String::valueOf).collect(Collectors.joining());
        StringBuilder sb = new StringBuilder();
        for(char c : result)
            sb.append(c);
        return sb.toString();
    }

//    [def]; hashmap; time: O(nlogn), space: O(n)
    public static String customSortString2(String order, String s) {
        Map<Integer, Character> map = new HashMap<>();
        Map<Character, Integer> count = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for(char c : s.toCharArray()) {
            if(!order.contains(String.valueOf(c)))
                sb.append(c);
            else {
                index = order.indexOf(c);
                map.put(index, c);
                count.put(c, count.getOrDefault(c, 0) + 1);
            }
            index++;
        }

        List<Integer> indexList = new ArrayList<>(map.keySet());
        Collections.sort(indexList);
        for(int idx : indexList) {
            if(map.containsKey(idx)) {
                char c = map.get(idx);
                for(int i = 0 ; i < count.get(c) ; i++)
                    sb.append(c);
            }
        }
        return sb.toString();
    }
}

/*
You are given two strings order and s. All the characters of order are unique and were sorted in some custom order previously.
Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.
Return any permutation of s that satisfies this property.
Example 1:
Input:  order = "cba", s = "abcd"
Output:  "cbad"
Explanation: "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.
Example 2:
Input:  order = "bcafg", s = "abcd"
Output:  "bcad"
Explanation: The characters "b", "c", and "a" from order dictate the order for the characters in s. The character "d" in s does not appear in order, so its position is flexible.
Following the order of appearance in order, "b", "c", and "a" from s should be arranged as "b", "c", "a". "d" can be placed at any position since it's not in order. The output "bcad" correctly follows this rule. Other arrangements like "bacd" or "bcda" would also be valid, as long as "b", "c", "a" maintain their order.

Constraints:
1 <= order.length <= 26
1 <= s.length <= 200
order and s consist of lowercase English letters.
All the characters of order are unique.
 */