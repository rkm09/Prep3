package leetdaily.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorseCommits2 {
    private static  Map<Integer, Integer> perfMap = new HashMap<>();
    private static int[] commits = new int[] {100, 102, 345, 1234};
    private static int[] performance = new int[] {100,90,90,80};
    public static void main(String[] args) {
        for(int i = 0; i < commits.length ; i++)
            perfMap.put(commits[i], i);
        System.out.println(listCommits(100, 1234));
    }
    private static boolean worseCommit(int commit1, int commit2) {
        return performance[perfMap.get(commit1)] - performance[perfMap.get(commit2)] != 0;
    }
    private static List<Integer> listCommits(int commit1, int commit2) {
        List<Integer> badCommitList = new ArrayList<>();
        int start = perfMap.get(commit1);
        int end = perfMap.get(commit2);
        while(start < end) {
            int badCommitIdx = firstBadCommit(start, end);
            if(badCommitIdx != -1) {
                badCommitList.add(commits[badCommitIdx]);
                start = badCommitIdx;
            } else {
                break;
            }
        }
        return badCommitList;
    }
    private static int firstBadCommit(int low, int high) {
        int badCommitIdx = -1;
        while(low < high) {
            int mid = (low + high + 1) / 2;
            if(worseCommit(commits[low], commits[mid])) {
                badCommitIdx = mid;
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return badCommitIdx;
    }

}
