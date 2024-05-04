package leetdaily.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

public class BoatsToSave881 {
    public static void main(String[] args) {
        int[] people = {5,1,4,2};
        System.out.println(numRescueBoats(people, 6));
    }

//    greedy(two pointer); time: O(nlogn), space: O(n)
    public static int numRescueBoats(int[] people, int limit) {
//        sort the elements lightest to the heaviest
        Arrays.sort(people);
        int boats = 0;
        int left = 0, right = people.length - 1;
        while(left <= right) {
            boats++;
//            processed the lighter; move to the next
            if(people[left] + people[right] <= limit)
                left++;
//            processed the heavier; move to the next
            right--;
        }
        return boats;
    }
}


/*
You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.
Return the minimum number of boats to carry every given person.
Example 1:
Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)
Example 2:
Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)
Example 3:
Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)

Constraints:
1 <= people.length <= 5 * 104
1 <= people[i] <= limit <= 3 * 104
 */