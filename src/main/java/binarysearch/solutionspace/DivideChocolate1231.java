package binarysearch.solutionspace;

import java.util.Arrays;

public class DivideChocolate1231 {
    public static void main(String[] args) {
        int[] sweetness = {1,2,3,4,5,6,7,8,9};
        System.out.println(maximizeSweetness(sweetness, 5));
    }

//    binary search + greedy; time: O(nlog(S/k)), space: O(1) [S - total sweetness, n - total chunks]
    public static int maximizeSweetness(int[] sweetness, int k) {
        int numberOfPeople = k + 1;
        int left = Arrays.stream(sweetness).min().getAsInt();
        int right = Arrays.stream(sweetness).sum() / numberOfPeople;
        while(left < right) {
//            special case
            int mid = (left + right + 1) / 2;
            int currSweetness = 0;
            int peopleWithChocolate = 0;
            for(int s : sweetness) {
                currSweetness += s;
                if(currSweetness >= mid) {
                    peopleWithChocolate++;
                    currSweetness = 0;
                }
            }
            if(peopleWithChocolate >= numberOfPeople) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}

/*
You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
You want to share the chocolate with your k friends, so you start cutting the chocolate bar into k + 1 pieces using k cuts, each piece consists of some consecutive chunks.
Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
Example 1:
Input: sweetness = [1,2,3,4,5,6,7,8,9], k = 5
Output: 6
Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
Example 2:
Input: sweetness = [5,6,7,8,9,1,2,3,4], k = 8
Output: 1
Explanation: There is only one way to cut the bar into 9 pieces.
Example 3:
Input: sweetness = [1,2,2,1,2,2,1,2,2], k = 2
Output: 5
Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
Constraints:
0 <= k < sweetness.length <= 104
1 <= sweetness[i] <= 105
 */