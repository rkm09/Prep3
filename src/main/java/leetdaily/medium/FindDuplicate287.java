package leetdaily.medium;

import java.util.*;

public class FindDuplicate287 {
    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        System.out.println(findDuplicate2(nums));
    }

//    floyd's tortoise and hare; cycle detection; time: O(n), space: O(1) [fast]
//    x, nums[x], nums[nums[x]], nums[nums[nums[x]]], ....
//    Each new element in the sequence is an element in nums at the index of the previous element.
//    If one starts from x = nums[0], such a sequence will produce a linked list with a cycle.
//    The cycle appears because nums contains duplicates. The duplicate node is a cycle entrance.
//    a-> non cyclic path, b-> intersection, if t does -> (a+b), then h does -> 2(a+b); [k is number of times hare does the cycle]
//    at intersection (h): a + b + k.c = 2(a+b) => k.c = a + b => a = k.c - b (1)
//    reaching cycle entrance(t): a (2); for h starting at intersection to cycle entrance(with same speed this time):
//    from (1) & (2): (h)-> (k.c - b) + b = k.c => a = k.c
    public static int findDuplicate7(int[] nums) {
        int tortoise = nums[0], hare = nums[0];
//        find the 'intersection' of the two runners
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while(tortoise != hare);
//        find the 'entrance' to the cycle
        tortoise = nums[0];
        while(tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }
        return hare;
    }
    

//    set; time: O(n), space: O(n)
    public static int findDuplicate1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num: nums) {
            if(set.contains(num))
                return num;
            set.add(num);
        }
        return -1;
    }

//    mark as visited; time: O(n), space: O(1)
    public static int findDuplicate2(int[] nums) {
        int duplicate = -1;
        for(int i = 0 ; i < nums.length ; i++) {
            int curr = Math.abs(nums[i]);
            if(nums[curr] < 0) {
                duplicate = curr;
                break;
            }
            nums[curr] *= -1;
        }
//      restore to original
        for(int num : nums)
            num = Math.abs(num);
        return duplicate;
    }

//    array as hashmap (iterative); time: O(n), space: O(1) [fast]
//    cyclic sort; we start with index 0. Since all numbers are in the range [1, n], they will be mapped to indices 1 through n inclusive, and hence no number will be mapped to index 0.
//    The key idea is to always map the number at index 0 to its equivalent index. While in the recursive approach, we directly jump to the next index, in this approach, we will bring the number from the next index to index 0 and continue from there (effectively performing a swap).
    public static int findDuplicate4(int[] nums) {
        while(nums[0] != nums[nums[0]]) {
            int next = nums[nums[0]];
            nums[nums[0]] = nums[0];
            nums[0] = next;
        }
        return nums[0];
    }

//    array as hashmap (recursion); time: O(n), space: O(n)
//    this & the next, modify individual elements, and hence do not meet the problem constraints. However, they utilize an insightful concept that can help with similar problems.
//    Since there are (n + 1) positions/indexes in the input array, and the numbers range from 1 to n, at least one index will have more than one number (due to the pigeonhole principle).
//    all numbers are in the range [1,n], no number will be mapped to index 0 (so we start from number at index 0).
//    the duplicate number will, on its first instance, be mapped/stored correctly at its equivalent index, and then on its second occurrence, we will attempt to store it there again.
    public static int findDuplicate3(int[] nums) {
        return store(nums, 0);
    }
    private static int store(int[] nums, int curr) {
//        if not true, implies number is out of place
        if(nums[curr] == curr) return curr;
        int next = nums[curr];
        nums[curr] = curr;
        return store(nums, next);
    }

// ---------------slow-----------------//

//    binary search; time: O(nlogn), space: O(1)
//    the duplicate number will have a count of numbers less than or equal to itself, that is greater than itself; Hence, the smallest number that satisfies this property is the duplicate number.
//    A linear scan based approach would require an overall O(n^2). Fortunately, count is monotonic (it's values are always in non-decreasing order), and hence it is an excellent candidate for binary search.
//    Since count(N)>=count(N−1), count must be monotonically increasing.
    public static int findDuplicate5(int[] nums) {
        int low = 1, high = nums.length - 1, duplicate = -1;
        while(low <= high) {
            int curr = low + (high - low) / 2;
//            count the number of nums that are less than or equal to num;
            int count = 0;
            for(int num : nums) {
                if(num <= curr) count++;
            }
            if(count > curr) {
                duplicate = curr;
                high = curr - 1;
            } else {
                low = curr + 1;
            }
        }
        return duplicate;
    }

    //    [def]; sorting; time: O(nlogn), space: O(1)
    public static int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1 ; i < nums.length ; i++) {
            if(nums[i] == nums[i-1]) return nums[i];
        }
        return -1;
    }

//    sum of set bits; time: O(nlogm), space: O(1) [n elements, m bits per element]
//    A key observation is that if the array had n elements instead of n + 1 (where each element is in the range [1,n]), this solution would not work.
//    The n + 1 number ensures that number from 1 to n appear exactly once and plus one extra number.
    public static int findDuplicate6(int[] nums) {
        int duplicate = 0;
        int n = nums.length;
        int maxNum = findMaxNum(nums);
        int maxBits = calculateMaxBit(maxNum);
        for(int bit = 0 ; bit < maxBits ; bit++) {
            int mask = (1 << bit);
            int baseCnt = 0, numsCnt = 0;
            for(int i = 0 ; i < n ; i++) {
//                if the bit is set in number (i) then add to baseCnt
                if((i & mask) > 0) baseCnt++;
//                if the bit is set in base then add to baseCnt
                if((nums[i] & mask) > 0) numsCnt++;
            }
//            if the current bit is more frequently set in nums than in base count [1,2..n], then it must also be set in duplicate
            if(numsCnt > baseCnt)
                duplicate |= mask;
        }

        return duplicate;
    }
//    find the position of the most significant bit in num
    private static int calculateMaxBit(int num) {
        int bits = 0;
        while(num > 0) {
            num /= 2;
            bits++;
        }
        return bits;
    }
    private static int findMaxNum(int[] nums) {
        int maxNum = 0;
        for(int i = 0 ; i < nums.length ; i++) {
            maxNum = Math.max(maxNum, nums[i]);
        }
        return maxNum;
    }

}

/*
Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
There is only one repeated number in nums, return this repeated number.
You must solve the problem without modifying the array nums and uses only constant extra space.
Example 1:
Input: nums = [1,3,4,2,2]
Output: 2
Example 2:
Input: nums = [3,1,3,4,2]
Output: 3
Example 3:
Input: nums = [3,3,3,3,3]
Output: 3
Constraints:
1 <= n <= 105
nums.length == n + 1
1 <= nums[i] <= n
All the integers in nums appear only once except for precisely one integer which appears two or more times.

Follow up:
How can we prove that at least one duplicate number must exist in nums?
Can you solve the problem in linear runtime complexity?
 */