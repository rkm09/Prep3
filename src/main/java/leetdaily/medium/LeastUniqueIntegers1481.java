package leetdaily.medium;

import java.util.*;
import java.util.stream.Collectors;

public class LeastUniqueIntegers1481 {
    public static void main(String[] args) {
        int[] arr = {5,5,4};
        System.out.println(findLeastNumOfUniqueInts(arr, 1));
    }
    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        int ans = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for(int num : arr)
            count.put(num, count.getOrDefault(num, 0) + 1);
        Map<Integer, Integer> sorted = count.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2)->e1, LinkedHashMap::new));
        int sum = 0;
        for(int key : sorted.keySet()) {
            int val = sorted.get(key);
            sum += val;
            if(sum <= k) continue;
            ans++;
        }
        return ans;
    }
}

/*
Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
Example 1:
Input: arr = [5,5,4], k = 1
Output: 1
Explanation: Remove the single 4, only 5 is left.
Example 2:
Input: arr = [4,3,1,1,3,3,2], k = 3
Output: 2
Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
Constraints:
1 <= arr.length <= 10^5
1 <= arr[i] <= 10^9
0 <= k <= arr.length
 */