package leetdaily.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorseCommit {
    private static Map<Integer, Integer> perfMap;
    private static int[] commits;
    private static int[] performance;
    private static List<Integer> result;
    public static void main(String[] args) {
        commits = new int[]{100,102, 345,1234};
        performance = new int[]{100,90,90,80};
//        int[][] arr = {{100, 100}, {102, 90}, {345, 90}, {1234, 80}};
        perfMap = new HashMap<>();
        for(int i = 0 ; i < commits.length ; i++) {
            perfMap.put(commits[i], i);
        }
        System.out.println(listCommits(100, 1234));
    }
    private static boolean worseCommit(int commit1, int commit2) {
        if(performance[perfMap.get(commit1)] - performance[perfMap.get(commit2)] == 0) {
            return false;
        }
        return true;
    }
    private static List<Integer> listCommits(int commit1, int commit2) {
        result = new ArrayList<>();
        int k = searchBadCommit(perfMap.get(commit1), perfMap.get(commit2));
        System.out.println("first bad : "+k);
        return result;
    }
    private static int searchBadCommit(int low, int high) {
        while(low < high) {
            int mid = low + (high - low) / 2;
            if(worseCommit(commits[low], commits[mid])) {
                result.add(commits[mid]);
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
