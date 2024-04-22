package leetdaily.medium;

import java.util.*;

public class OpenTheLock752 {
    public static void main(String[] args) {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        System.out.println(openLock(deadends, "0202"));
    }

//    bfs; time: O(4(d + 10^4)), space: O(4(d + 10^4)) [d - number of deadEnds]
//    BFS is well-suited for finding the shortest path in unweighted graphs, which makes it a good fit for this problem because BFS explores nodes level by level.
//    It starts from the source node and explores all its neighbors before moving on to the next level of neighbors.
//    Due to its level-order exploration strategy, BFS guarantees that the first time it reaches a node, it has found the shortest path to that node.
    public static int openLock(String[] deadends, String target) {
//        map the next slot digit for each current slot
        Map<Character, Character> nextSlots = Map.of(
                '0', '1',
                '1', '2',
                '2', '3',
                '3', '4',
                '4', '5',
                '5', '6',
                '6', '7',
                '7', '8',
                '8', '9',
                '9', '0'
        );
//        map the previous slot digit for each current slot
        Map<Character, Character> prevSlots = Map.of(
                '0', '9',
                '1', '0',
                '2', '1',
                '3', '2',
                '4', '3',
                '5', '4',
                '6', '5',
                '7', '6',
                '8', '7',
                '9', '8'
        );
//        set to store unique visited and dead end combinations
        Set<String> visitedCombinations = new HashSet<>(Arrays.asList(deadends));
//        queue to store combinations generated after each turn
        Deque<String> pendingCombinations = new ArrayDeque<>();
//        keep track of turns made
        int turns = 0;
//        if initial is also dead end then return -1
        if(visitedCombinations.contains("0000"))
            return -1;
//        start with the initial configuration
        visitedCombinations.add("0000");
        pendingCombinations.offer("0000");

        while(!pendingCombinations.isEmpty()) {
            int currLevelNodesCount = pendingCombinations.size();
            for(int i = 0 ; i < currLevelNodesCount ; i++) {
                String currentCombination = pendingCombinations.poll();
                if(currentCombination.equals(target))
                    return turns;
//                explore all possible combinations by turning each wheel in both directions
                for(int wheel = 0 ; wheel < 4 ; wheel++) {
//                    generate new combination by turning the wheel to the next digit
                    StringBuilder newCombination = new StringBuilder(currentCombination);
                    newCombination.setCharAt(wheel, nextSlots.get(newCombination.charAt(wheel)));
//                    if the new combination was not visited and was not a dead end, then add to queue and mark visited
                    if(!visitedCombinations.contains(newCombination.toString())) {
                        pendingCombinations.offer(newCombination.toString());
                        visitedCombinations.add(newCombination.toString());
                    }
//                    generate new combination by turning the wheel to the previous digit
                    newCombination = new StringBuilder(currentCombination);
                    newCombination.setCharAt(wheel, prevSlots.get(newCombination.charAt(wheel)));
//                    if the new combination was not visited and was not a dead end, then add to queue and mark visited
                    if(!visitedCombinations.contains(newCombination.toString())) {
                        pendingCombinations.offer(newCombination.toString());
                        visitedCombinations.add(newCombination.toString());
                    }
                }
            }
//            we will visit next level combinations
            turns++;
        }
//        never reached the target
        return -1;
    }
}

/*
You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
The lock initially starts at '0000', a string representing the state of the 4 wheels.
You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
Example 1:
Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".
Example 2:
Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".
Example 3:
Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation: We cannot reach the target without getting stuck.

Constraints:
1 <= deadends.length <= 500
deadends[i].length == 4
target.length == 4
target will not be in the list deadends.
target and deadends[i] consist of digits only.
 */