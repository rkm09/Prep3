package leetdaily.hard;

import java.util.*;

public class LargestComponentSizeByFactor952 {
    public static void main(String[] args) {
        int[] nums = {4,6,15,35};
        LargestComponentSizeByFactor952 comp  = new LargestComponentSizeByFactor952();
        System.out.println(comp.largestComponentSize1(nums));
    }

//    union with factors; time: O(N.sqrt(M)logM), space: O(M+N) [M - max input value, N - nums length] [faster]
    public  int largestComponentSize(int[] nums) {
        int maxValue = Arrays.stream(nums).reduce(nums[0], Math::max);
        UnionFind dsu = new UnionFind(maxValue);
        for(int num : nums) {
            for(int factor = 2 ; factor <= (int) Math.sqrt(num) ; factor++) {
                if(num % factor == 0) {
                    dsu.union(num, factor);
//                    unite complementary pair as well
                    dsu.union(num, num / factor);
                }
            }
        }

//        count group size and find max
        int maxGroupSize = 0;
        Map<Integer, Integer> groupCount = new HashMap<>();
        for(int num : nums) {
            int groupId = dsu.find(num);
            int groupSize = groupCount.getOrDefault(groupId, 0) + 1;
            groupCount.put(groupId, groupSize);
            maxGroupSize = Math.max(maxGroupSize, groupSize);
        }

        return maxGroupSize;
    }

//    union with prime numbers (sieve method); time: O(N.(log2M.logM + sqrt(M))), space: O(M+N)
    public  int largestComponentSize1(int[] nums) {
        int maxValue = Arrays.stream(nums).reduce(nums[0], Math::max);
        UnionFind dsu = new UnionFind(maxValue);
        Map<Integer, Integer> numFactorMap = new HashMap<>();
        for(int num : nums) {
//            find all unique prime factors
            List<Integer> primeFactors = new ArrayList<>(new HashSet<>(primeDecompose(num)));
//            map a number to its first prime factor
            numFactorMap.put(num, primeFactors.get(0));
            for(int i = 0 ; i < primeFactors.size() - 1 ; i++) {
//                merge all the groups that contain prime factors
                dsu.union(primeFactors.get(i), primeFactors.get(i + 1));
            }
        }
        int maxGroupSize = 0;
        Map<Integer, Integer> groupCount = new HashMap<>();
        for(int num : nums) {
            int groupId = dsu.find(numFactorMap.get(num));
            int groupSize = groupCount.getOrDefault(groupId, 0) + 1;
            groupCount.put(groupId, groupSize);
            maxGroupSize = Math.max(maxGroupSize, groupSize);
        }
        return maxGroupSize;
    }

//    prime factor(sieve method) of a number can represent all of its factors
//    time: O(sqrt(M))
    private List<Integer> primeDecompose(int num) {
        List<Integer> primeFactors = new ArrayList<>();
        int factor = 2;
        while(num >= factor * factor) {
            if(num % factor == 0) {
                primeFactors.add(factor);
                num /= factor;
            } else {
                factor++;
            }
        }
        primeFactors.add(num);
        return primeFactors;
    }

//  time complexity of dsu: O(MlogN) where M is the number of operations either union or find and N is the number of elements, log* is the iterated logarithm
//    space complexity : O(M)
    class UnionFind {
        private int[] parent;
        private int[] rank;
        UnionFind(int size) {
            parent = new int[size + 1];
            rank = new int[size + 1];
            for(int i = 0 ; i < size + 1 ; i++) {
                parent[i] = i;
                rank[i] = i;
            }
        }
        public int find(int x) {
            if(parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if(px != py) {
                if(rank[px] > rank[py]) {
                    parent[py] = px;
                } else if(rank[px] < rank[py]){
                    parent[px] = py;
                } else {
                    parent[py] = px;
                    rank[px]++;
                }
            }
        }
    }
}

/*
You are given an integer array of unique positive integers nums. Consider the following graph:
There are nums.length nodes, labeled nums[0] to nums[nums.length - 1],
There is an undirected edge between nums[i] and nums[j] if nums[i] and nums[j] share a common factor greater than 1.
Return the size of the largest connected component in the graph.
Example 1:
Input: nums = [4,6,15,35]
Output: 4
Input: nums = [20,50,9,63]
Output: 2
Input: nums = [2,3,6,7,4,12,21,39]
Output: 8
Constraints:
1 <= nums.length <= 2 * 104
1 <= nums[i] <= 105
All the values of nums are unique.
 */