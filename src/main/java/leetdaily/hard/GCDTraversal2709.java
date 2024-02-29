package leetdaily.hard;

import java.util.*;

public class GCDTraversal2709 {
    public static void main(String[] args) {
        int[] nums = {2,3,6};
        System.out.println(canTraverseAllPairs(nums));
    }

    public static boolean canTraverseAllPairs(int[] nums) {
        if(nums.length == 1) return true;
        Set<Integer> set = new HashSet<>();
        Arrays.stream(nums).forEach(set::add);
        if(set.contains(1)) return false;
        Map<Integer, List<Integer>> primeGroups = new HashMap<>();
        List<Integer> uniqueElements = new ArrayList<>(set);
        for(int num : uniqueElements) {
            for(int prime : primeFactors(num)) {
                primeGroups.compute(prime, (k, group) -> {
                    List<Integer> li = new ArrayList<>(group != null ? group : List.of());
                    li.add(num);
                    return li;
                });
//          primeGroups.computeIfAbsent(num, ArrayList::new);
//          primeGroups.get(num).add(num);
            }
        }
//        int maxValue = Arrays.stream(nums).reduce(nums[0], Math::max);
        UnionFind dsu = new UnionFind(uniqueElements.size());
        for(Map.Entry<Integer, List<Integer>> group : primeGroups.entrySet()) {
            int groupLeader = group.getValue().get(0);
            for(int i = 1; i < group.getValue().size() ; i++)
                dsu.union(groupLeader, group.getValue().get(i));
        }
        System.out.println(dsu.getNumGroups());
        return dsu.getNumGroups() == 1;
    }

    private static Set<Integer> primeFactors(int num) {
        Set<Integer> factors = new HashSet<>();
        for(int prime = 2 ; prime <= num / prime ; prime++) {
            while(num % prime == 0) {
                factors.add(prime);
                num /= prime;
            }
        }
        if(num > 1) factors.add(num);
        return factors;
    }
}
    class UnionFind {
        private int[] parent;
        private int[] rank;
        private int groupCnt;
        UnionFind(int size) {
            parent = new int[size + 1];
            rank = new int[size + 1];
            for(int i = 0 ; i <= size ; i++) {
                parent[i] = i;
            }
            groupCnt = size;
        }
        public int find(int x) {
            if(parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY) {
                if(rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if(rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                groupCnt--;
            }
        }
        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
        public int getNumGroups() {
            return groupCnt;
        }
    }


/*
You are given a 0-indexed integer array nums, and you are allowed to traverse between its indices. You can traverse between index i and index j, i != j, if and only if gcd(nums[i], nums[j]) > 1, where gcd is the greatest common divisor.
Your task is to determine if for every pair of indices i and j in nums, where i < j, there exists a sequence of traversals that can take us from i to j.
Return true if it is possible to traverse between all such pairs of indices, or false otherwise.
Example 1:
Input: nums = [2,3,6]
Output: true
Explanation: In this example, there are 3 possible pairs of indices: (0, 1), (0, 2), and (1, 2).
To go from index 0 to index 1, we can use the sequence of traversals 0 -> 2 -> 1, where we move from index 0 to index 2 because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1, and then move from index 2 to index 1 because gcd(nums[2], nums[1]) = gcd(6, 3) = 3 > 1.
To go from index 0 to index 2, we can just go directly because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1. Likewise, to go from index 1 to index 2, we can just go directly because gcd(nums[1], nums[2]) = gcd(3, 6) = 3 > 1.
Example 2:
Input: nums = [3,9,5]
Output: false
Explanation: No sequence of traversals can take us from index 0 to index 2 in this example. So, we return false.
Example 3:
Input: nums = [4,3,12,8]
Output: true
Explanation: There are 6 possible pairs of indices to traverse between: (0, 1), (0, 2), (0, 3), (1, 2), (1, 3), and (2, 3). A valid sequence of traversals exists for each pair, so we return true.
Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 105
 */


/*
//    public static boolean canTraverseAllPairs1(int[] nums) {
//        int MAX = 100000;
//        int N = nums.length;
//        boolean[] has = new boolean[MAX + 1];
//        for (int x: nums) {
//            has[x] = true;
//        }
//
//        // edge cases
//        if (N == 1) {
//            return true;
//        }
//        if (has[1]) {
//            return false;
//        }
//
//        // the general solution
//        int[] sieve = new int[MAX + 1];
//        for (int d = 2; d <= MAX; d++) {
//            if (sieve[d] == 0) {
//                for (int v = d; v <= MAX; v += d) {
//                    sieve[v] = d;
//                }
//            }
//        }
//
//        DSU union = new DSU(2 * MAX + 1);
//        for (int x: nums) {
//            int val = x;
//            while (val > 1) {
//                int prime = sieve[val];
//                int root = prime+MAX;
//                if (union.find(root) != union.find(x)) {
//                    union.merge(root, x);
//                }
//                while (val % prime == 0) {
//                    val /= prime;
//                }
//            }
//        }
//
//        int cnt = 0;
//        for (int i=2; i <= MAX; i++) {
//            if (has[i] && union.find(i) == i) {
//                cnt++;
//            }
//        }
//        return cnt == 1;
//    }
 */