package leetdaily.easy;

import java.util.*;

public class Intersection349 {
    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        System.out.println(Arrays.toString(intersection(nums1, nums2)));
//        can use retainAll in java too(inbuilt intersection set)
    }

//    hashset [def]; time: O(n+m), space: O(n)
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> resSet = new HashSet<>();
        for(int num : nums1)
            set.add(num);
        for(int num : nums2) {
            if(set.contains(num)) {
                resSet.add(num);
            }
        }

        int[] res = new int[resSet.size()];
        int i = 0;
        for(int num : resSet)
            res[i++] = num;
        return res;
    }

//    hashmap;
    public static int[] intersection1(int[] nums1, int[] nums2) {
        Map<Integer, Integer> seen = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for(int x : nums1)
            seen.put(x, 1);
        for(int x : nums2) {
            if(seen.containsKey(x) && seen.get(x) == 1) {
                result.add(x);
                seen.put(x, 0);
            }
        }
        return result.stream().mapToInt(x->x).toArray();
    }
}

/*
Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.
Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.

Constraints:
1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000

 */