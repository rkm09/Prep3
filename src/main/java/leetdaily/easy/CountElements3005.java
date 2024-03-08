package leetdaily.easy;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CountElements3005 {
    public static void main(String[] args) {
        int[] nums = {1,2,2,3,1,4};
        System.out.println(maxFrequencyElements(nums));
    }

//    hashmap [def]; time: O(n), space: O(n)
    public static int maxFrequencyElements(int[] nums) {
        Map<Integer,Integer> counts = new HashMap();
        for(int num : nums)
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        int max = Collections.max(counts.values());
        int count = 0;
        for(int value : counts.values()) {
            if(value == max) {
//                or count++, followed by count * maxfreqValue
                count += value;
            }
        }
        return count;
    }

//    [def]; counting sort; time: O(n), space: O(n)
    public static int maxFrequencyElements1(int[] nums) {
        int[] freq = new int[101];
        for(int num : nums)
            freq[num]++;
        int max = Arrays.stream(freq).max().getAsInt();
        int count = 0;
        for(int fr : freq) {
            if(fr == max) count += fr;
        }
        return count;
    }

    //   sort & count; time: O(nlogn), space: O(n) [fast]
    public static int maxFrequencyElements2(int[] nums) {
        int[] freq = new int[100];
        for(int num : nums)
            freq[num - 1]++;
        Arrays.sort(freq);
        int maxFreqIndex = freq.length - 1;
        int totalFrequencies = freq[maxFreqIndex];
        while(maxFreqIndex > 0 && freq[maxFreqIndex] == freq[maxFreqIndex - 1]) {
            totalFrequencies += freq[maxFreqIndex--];
        }
        return totalFrequencies;
    }

//    one pass; time: O(n), space: O(n) [fast]
    public static int maxFrequencyElements3(int[] nums) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        int totalFrequency = 0, maxFrequency = 0;
        for(int num : nums) {
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
            int frequency = frequencies.get(num);
            if(frequency > maxFrequency) {
                maxFrequency = frequency;
                totalFrequency = frequency;
            } else if(frequency == maxFrequency) {
                totalFrequency += frequency;
            }
        }
        return totalFrequency;
    }
}

/*
You are given an array nums consisting of positive integers.
Return the total frequencies of elements in nums such that those elements all have the maximum frequency.
The frequency of an element is the number of occurrences of that element in the array.
Example 1:
Input: nums = [1,2,2,3,1,4]
Output: 4
Explanation: The elements 1 and 2 have a frequency of 2 which is the maximum frequency in the array.
So the number of elements in the array with maximum frequency is 4.
Example 2:
Input: nums = [1,2,3,4,5]
Output: 5
Explanation: All elements of the array have a frequency of 1 which is the maximum.
So the number of elements in the array with maximum frequency is 5.

Constraints:
1 <= nums.length <= 100
1 <= nums[i] <= 100
 */