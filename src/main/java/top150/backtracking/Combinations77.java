package top150.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations77 {
    private static List<List<Integer>> results;
    public static void main(String[] args) {
        List<List<Integer>> li = combine(4,2);
        for(List<Integer> l : li) {
            System.out.println(l);
        }
    }

    //   [def]; backtracking with optimization; time: O(n!/(n-k)!(k-1)!), space: O(n); faster [k leaves]
    public static List<List<Integer>> combine(int n, int k) {
        results = new ArrayList<>();
        backtrack(n, k, new ArrayList<>(), 1);
        return results;
    }
    private static void backtrack(int n, int k, List<Integer> curr, int start) {
        if(curr.size() == k) {
            results.add(new ArrayList<>(curr));
            return;
        }
        int need = k - curr.size();
        int remain = n - start + 1;
        int available = remain - need;
        for(int i = start; i <= start + available ; i++) {
            curr.add(i);
            backtrack(n, k, curr, i + 1);
            curr.remove(curr.size() - 1);
        }
    }

//   [def]; backtracking; time: O(n!/(n-k)!k!), space: O(n)
    public static List<List<Integer>> combine1(int n, int k) {
        results = new ArrayList<>();
        backtrack1(n, k, new ArrayList<>(), 1);
        return results;
    }
    private static void backtrack1(int n, int k, List<Integer> curr, int start) {
        if(curr.size() == k) {
            results.add(new ArrayList<>(curr));
            return;
        }
        for(int i = start; i <= n ; i++) {
            curr.add(i);
            backtrack1(n, k, curr, i + 1);
            curr.remove(curr.size() - 1);
        }
    }


}
/*
Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
You may return the answer in any order.
Example 1:
Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
Example 2:
Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.
Constraints:
1 <= n <= 20
1 <= k <= n
 */
