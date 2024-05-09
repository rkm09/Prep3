package leetdaily.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumHappiness3076 {
    public static void main(String[] args) {
        int[] happiness = {12,1,42};
        System.out.println(maximumHappinessSum(happiness, 3));
    }

//    def; sort; time: O(nlogn), space: O(logn) [fast]
    public static long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        int n = happiness.length;
        long sum = 0; int i = 0, j = 0;
        while(k-- > 0) {
            int val = Math.max(happiness[n - 1 - i] - j, 0);
            sum += val;
            i++; j++;
        }
        return sum;
    }

//    sort + greedy; time: O(nlogn), space: O(logn) [fast] [same as def; instead using for]
    public static long maximumHappinessSum1(int[] happiness, int k) {
        Arrays.sort(happiness);
        int n = happiness.length;
        long sum = 0; int j = 0;
        for(int i = n - 1 ; i >= n - k ; i--) {
            int val = Math.max(happiness[i] - j++, 0);
            sum += val;
        }
        return sum;
    }

//    priority queue & greedy; time: O(nlogn + klogn), space: O(n)
//    time : inserting into pq -> nlogn + iterating through first k elements in pq [klogn] + deleting (pop) the top element [logn]
    public static long maximumHappinessSum2(int[] happiness, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int h : happiness)
            pq.add(h);
        long totalHappinessSum = 0; int turns = 0;
        while(!pq.isEmpty() && turns < k) {
            totalHappinessSum += Math.max(pq.poll() - turns++, 0);
        }

        return totalHappinessSum;
    }
}

/*
You are given an array happiness of length n, and a positive integer k.
There are n children standing in a queue, where the ith child has happiness value happiness[i]. You want to select k children from these n children in k turns.
In each turn, when you select a child, the happiness value of all the children that have not been selected till now decreases by 1. Note that the happiness value cannot become negative and gets decremented only if it is positive.
Return the maximum sum of the happiness values of the selected children you can achieve by selecting k children.
Example 1:
Input: happiness = [1,2,3], k = 2
Output: 4
Explanation: We can pick 2 children in the following way:
- Pick the child with the happiness value == 3. The happiness value of the remaining children becomes [0,1].
- Pick the child with the happiness value == 1. The happiness value of the remaining child becomes [0]. Note that the happiness value cannot become less than 0.
The sum of the happiness values of the selected children is 3 + 1 = 4.
Example 2:
Input: happiness = [1,1,1,1], k = 2
Output: 1
Explanation: We can pick 2 children in the following way:
- Pick any child with the happiness value == 1. The happiness value of the remaining children becomes [0,0,0].
- Pick the child with the happiness value == 0. The happiness value of the remaining child becomes [0,0].
The sum of the happiness values of the selected children is 1 + 0 = 1.
Example 3:
Input: happiness = [2,3,4,5], k = 1
Output: 5
Explanation: We can pick 1 child in the following way:
- Pick the child with the happiness value == 5. The happiness value of the remaining children becomes [1,2,3].
The sum of the happiness values of the selected children is 5.

Constraints:
1 <= n == happiness.length <= 2 * 105
1 <= happiness[i] <= 108
1 <= k <= n
 */