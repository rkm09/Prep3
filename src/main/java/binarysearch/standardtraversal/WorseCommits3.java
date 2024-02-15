package binarysearch.standardtraversal;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorseCommits3 {
    private static int[] commits = {100, 102, 345, 1234};
    private static int[] performance = {100, 90, 90, 80};
    private static Map<Integer, Integer> perfMap = new HashMap<>();
    public static void main(String[] args) {
        for(int i = 0 ; i < commits.length ; i++)
            perfMap.put(commits[i], i);
        System.out.println(listCommits(100, 1234));
    }
    private static boolean worseCommit(int commit1, int commit2) {
        return performance[perfMap.get(commit1)] - performance[perfMap.get(commit2)] != 0;
    }

//    binary search; time: O(klogn) where k is the number of times bin search is called for a length of n. if k ~ n, it becomes nlogn...[logn is just for bin search itself(divide into half search space each time)];
    private static List<Integer> listCommits(int commit1, int commit2) {
        List<Integer> result = new ArrayList<>();
        int start = 0, end = commits.length - 1;
        while(start < end) {
            int badCommitIdx = firstBadCommit(start, end);
            if(badCommitIdx != -1) {
                result.add(commits[badCommitIdx]);
                start = badCommitIdx;
            } else {
                break;
            }
        }
        return result;
    }
    private static int firstBadCommit(int low, int high) {
        int badCommitIdx = -1;
        while(low < high) {
//            to account for length 2 where you want mid to be right instead of left;
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

/*
TestCases:
if bin is called too many times.
Basic bruter force linear search O(n). With bin: O(klogn)

commits: 100, 200, 300
perf: 100,90,80
 */