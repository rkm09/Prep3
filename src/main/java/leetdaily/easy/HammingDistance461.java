package leetdaily.easy;

public class HammingDistance461 {
    public static void main(String[] args) {
        System.out.println(hammingDistance(1,4));
    }

//    xor operation returns 1 only when bits are different, and thus can be used to calculate hamming distance;
//    bit manipulation (bk); time: O(1), space: O(1)
    public static int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while(xor != 0) {
            distance++;
            xor = xor & (xor - 1);
        }
        return distance;
    }

//   bit manipulation (bit shift & xor); time: O(1), space: O(1)
    public static int hammingDistance1(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while(xor != 0) {
//            or (xor % 2 == 1)
            if((xor & 1) == 1) {
                distance++;
            }
            xor >>= 1;
        }
        return distance;
    }

//    bit manipulation (in built way); time: O(1), space: O(1)
    public static int hammingDistance2(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}

/*
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
Given two integers x and y, return the Hamming distance between them.
Example 1:
Input: x = 1, y = 4
Output: 2
Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
The above arrows point to positions where the corresponding bits are different.
Example 2:
Input: x = 3, y = 1
Output: 1
Constraints:
0 <= x, y <= 231 - 1
 */