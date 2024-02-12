package leetdaily.medium;

import java.util.ArrayList;
import java.util.List;

public class WorkCommit {
    private static List<Integer> performance;
    private static List<Integer> commits;
    public static void main(String[] args) {
        int[][] arr = {{100, 100}, {102, 90}, {345, 90}, {1234, 80}};
        for(int[] ar : arr) {
            performance.add(ar[1]);
            commits.add(ar[0]);
        }
        workCommit(performance.get(0), commits.get(0));
    }
    private static boolean workCommit(int commit1, int commit2) {
        return false;
    }
    private static List<Integer> listCommits(int commit1, int commit2) {
        List<Integer> list = new ArrayList<>();
        return list;
    }
}
