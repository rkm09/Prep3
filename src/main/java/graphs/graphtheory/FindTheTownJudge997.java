package graphs.graphtheory;

public class FindTheTownJudge997 {
    public static void main(String[] args) {
        int[][] trust = {{1,3},{2,3},{3,1}};
        System.out.println(findJudge(3, trust));
    }

//    using a single array; time: O(e), space: O(n)
    public static int findJudge(int n, int[][] trust) {
        if(trust.length < n - 1)
            return -1;
        int[] trustScores = new int[n + 1];
        for(int[] relation: trust) {
            trustScores[relation[0]]--;
            trustScores[relation[1]]++;
        }
        for(int i = 1 ; i <= n ; i++) {
            if(trustScores[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }

//    using two arrays; time: O(E), space: O(n)
    public static int findJudge1(int n, int[][] trust) {
        if(trust.length < n - 1) return -1;

        int[] inDegree = new int[n + 1];
        int[] outDegree = new int[n + 1];

        for(int[] relation : trust) {
            outDegree[relation[0]]++;
            inDegree[relation[1]]++;
        }

        for(int i = 1 ; i <= n ; i++) {
            if(inDegree[i] == n - 1 && outDegree[i] == 0)
                return i;
        }

        return -1;
    }
}
