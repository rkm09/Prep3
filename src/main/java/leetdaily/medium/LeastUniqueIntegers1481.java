package leetdaily.medium;

import java.util.*;
import java.util.stream.Collectors;

public class LeastUniqueIntegers1481 {
    public static void main(String[] args) {
        int[] arr = {5,5,4};
        System.out.println(findLeastNumOfUniqueInts(arr, 1));
    }

//    counting sort; time: O(n), space: O(n)
    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> countMap  = new HashMap<>();
        for(int num : arr)
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        int n = arr.length;
        int[] freq = new int[n + 1];
        for(int count : countMap.values()) {
            freq[count]++;
        }
        int remainingUniqueElements = countMap.size();
        for(int i = 1 ; i <= n ; i++) {
//            k/i represents the maximum possible elements that can be removed
            int numElementsToRemove = Math.min(k / i, freq[i]);
            k -= (i * numElementsToRemove);
            remainingUniqueElements -= numElementsToRemove;
            if(k < i) {
                return remainingUniqueElements;
            }
        }
        return 0;
    }

    //    hashmap; time: O(nlogn), space: O(n)
    public static int findLeastNumOfUniqueInts2(int[] arr, int k) {
        Map<Integer, Integer> count  = new HashMap<>();
        for(int num : arr)
            count.put(num, count.getOrDefault(num, 0) + 1);
        List<Integer> frequencies = new ArrayList<>(count.values());
        Collections.sort(frequencies);
        int elementsRemoved = 0;
        for(int i = 0 ; i < frequencies.size() ; i++) {
            elementsRemoved += frequencies.get(i);
            if(elementsRemoved > k)
                return frequencies.size() - i;
        }
//        exhausted all
        return 0;
    }

    //     min heap; time: O(n), space: O(n)
    public static int findLeastNumOfUniqueInts1(int[] arr, int k) {
        Map<Integer, Integer> count  = new HashMap<>();
        for(int num : arr)
            count.put(num, count.getOrDefault(num, 0) + 1);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(count.values());
        int elementsRemoved = 0;
        while(!minHeap.isEmpty()) {
            elementsRemoved += minHeap.peek();
            if(elementsRemoved > k)
                return minHeap.size();
            minHeap.poll();
        }
        return 0;
    }



//    [def]; hashmap; time: O(nlogn), space: O(n)
    public static int findLeastNumOfUniqueInts3(int[] arr, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for(int num : arr)
            count.put(num, count.getOrDefault(num, 0) + 1);
        Map<Integer, Integer> sorted = count.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2)->e1, LinkedHashMap::new));
        int ans = 0, sum = 0, exclude = 0;
        for(int key : sorted.keySet()) {
            int val = sorted.get(key);
            sum += val;
            if(sum <= k) {
                exclude++; continue;
            }
            return sorted.size() - exclude;
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