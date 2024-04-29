package leetdaily.hard;

import java.util.Arrays;

public class FreedomTrail504 {
    public static void main(String[] args) {
        String ring = "godding", key = "godding";
//        String ring = "repetitive", key = "per";
        System.out.println(findRotateSteps(ring, key));
    }

//    bottom up DP; time: O(K.R^2), space: O(K.R) [K, R -> iterations of key & ring]
    public static int findRotateSteps(String ring, String key) {
        int ringLen = ring.length();
        int keyLen = key.length();
        int[][] bestSteps = new int[ringLen][keyLen + 1];
//        initialize values of best steps to the largest integer
        for(int[] row : bestSteps) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
//        initialize last column to zero to indicate the last column has been spelled out
        for(int i = 0 ; i < ringLen ; i++) {
            bestSteps[i][keyLen] = 0;
        }

        for(int keyIndex = keyLen - 1; keyIndex >= 0 ; keyIndex--) {
            for(int ringIndex = 0 ; ringIndex < ringLen ; ringIndex++) {
//                with each ringIndex as base (12:00)
                for(int charIndex = 0 ; charIndex < ringLen ; charIndex++) {
                    if(key.charAt(keyIndex) == ring.charAt(charIndex)) {
                        bestSteps[ringIndex][keyIndex] = Math.min(
                                bestSteps[ringIndex][keyIndex], 1 + countSteps(ringIndex, charIndex, ringLen) +
                                        bestSteps[charIndex][keyIndex + 1]
                        );
                    }
                }
            }
        }
        return bestSteps[0][0];
    }

//    find minimum steps between two indexes of the ring
    private static int countSteps(int curr, int next, int ringLength) {
        int stepsBetween = Math.abs(curr - next);
        int stepsAround = ringLength - stepsBetween;
        return Math.min(stepsAround, stepsBetween);
    }
}

/*
In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring" and use the dial to spell a specific keyword to open the door.
Given a string ring that represents the code engraved on the outer ring and another string key that represents the keyword that needs to be spelled, return the minimum number of steps to spell all the characters in the keyword.
Initially, the first character of the ring is aligned at the "12:00" direction. You should spell all the characters in key one by one by rotating ring clockwise or anticlockwise to make each character of the string key aligned at the "12:00" direction and then by pressing the center button.
At the stage of rotating the ring to spell the key character key[i]:
You can rotate the ring clockwise or anticlockwise by one place, which counts as one step. The final purpose of the rotation is to align one of ring's characters at the "12:00" direction, where this character must equal key[i].
If the character key[i] has been aligned at the "12:00" direction, press the center button to spell, which also counts as one step. After the pressing, you could begin to spell the next character in the key (next stage). Otherwise, you have finished all the spelling.
Example 1:
Input: ring = "godding", key = "gd"
Output: 4
Explanation:
For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
Also, we need 1 more step for spelling.
So the final output is 4.
Example 2:
Input: ring = "godding", key = "godding"
Output: 13

Constraints:
1 <= ring.length, key.length <= 100
ring and key consist of only lower case English letters.
It is guaranteed that key could always be spelled by rotating ring.
 */