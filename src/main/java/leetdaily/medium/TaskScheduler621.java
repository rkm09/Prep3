package leetdaily.medium;


import java.util.*;

public class TaskScheduler621 {
    public static void main(String[] args) {
        char[] tasks = {'A','A','B','C'};
        System.out.println(leastInterval(tasks, 2));
    }

//    all approaches use greedy => decisions are made step by step based on what seems best at the moment to reach an overall optimal

//    filling the slots and sorting; time: O(n), space:O(n) [fastest]
    public static int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char c : tasks)
            freq[c - 'A']++;
//        sort on increasing count
        Arrays.sort(freq);

//       max interval length
        int maxIdleFreq = freq[25] - 1;
        int idleSlots = maxIdleFreq * n;
        for(int i = 24 ; i >= 0 && freq[i] > 0; i--) {
            idleSlots -= Math.min(maxIdleFreq, freq[i]);
        }
        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;
    }

//    choosing tasks with max frequency minimizes overall idle time
//    priority queue; time: O(n), space: O(1) [pq operations take O(logk), so overall O(nlogk); but since k = 26 (constant); hence O(n)]
    public static int leastInterval1(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char c : tasks)
            freq[c - 'A']++;
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0 ; i < 26 ; i++) {
            if(freq[i] > 0) pq.offer(freq[i]);
        }
        int time = 0;
//        process tasks until heap is empty
        while(!pq.isEmpty()) {
            int cycle = n + 1, taskCount = 0;
            List<Integer> store = new ArrayList<>();
//            execute tasks in each cycle
            while(cycle-- > 0 && !pq.isEmpty()) {
                 int currentFreq = pq.poll();
                 if(currentFreq > 1)
                     store.add(currentFreq - 1);
                 taskCount++;
            }
//            restore updated frequencies to the heap
            store.forEach(pq::offer);
//            add time for completed cycle
            time += (pq.isEmpty() ? taskCount : n + 1);
        }
        return time;
    }
}

/*
You are given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n. Each cycle or interval allows the completion of one task. Tasks can be completed in any order, but there's a constraint: identical tasks must be separated by at least n intervals due to cooling time.
Return the minimum number of intervals required to complete all tasks.

Example 1:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
After completing task A, you must wait two cycles before doing A again. The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th cycle, you can do A again as 2 intervals have passed.
Example 2:
Input: tasks = ["A","C","A","B","D","B"], n = 1
Output: 6
Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
With a cooling interval of 1, you can repeat a task after just one other task.
Example 3:
Input: tasks = ["A","A","A", "B","B","B"], n = 3
Output: 10
Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice between repetitions of these tasks.

Constraints:
1 <= tasks.length <= 104
tasks[i] is an uppercase English letter.
0 <= n <= 100
 */