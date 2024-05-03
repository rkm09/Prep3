package leetdaily.medium;

import common.Pair;

public class CompareVersion165 {
    public static void main(String[] args) {
        String version1 = "1.01";
        String version2 = "1.001";
        System.out.println(compareVersion(version1, version2));
    }

//    split + parse; two pass; time: O(m + n + max(m,n)), space: O(max(m,n))
    public static int compareVersion(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int n1 = nums1.length, n2 = nums2.length;

//        compare versions
        for(int i = 0 ; i < Math.max(n1, n2) ; i++) {
            int i1 = i < n1 ? Integer.parseInt(nums1[i]) : 0;
            int i2 = i < n2 ? Integer.parseInt(nums2[i]) : 0;
            if(i1 != i2) {
                return i1 > i2 ? 1 : -1;
            }
        }
//      equal
        return 0;
    }

//    one pass; time: O(max(m,n)), space: O(max(m,n))
    public static int compareVersion1(String version1, String version2) {
        int p1 = 0, p2 = 0;
        int n1 = version1.length(), n2 = version2.length();
        Pair<Integer, Integer> pair;

        int i1, i2;
        while(p1 < n1 || p2 < n2) {
            pair = getNextChunk(version1, n1, p1);
            i1 = pair.getKey();
            p1 = pair.getValue();

            pair = getNextChunk(version2, n2, p2);
            i2 = pair.getKey();
            p2 = pair.getValue();

            if(i1 != i2)
                return i1 > i2 ? 1 : -1;
        }
//        versions are equal
        return 0;
    }

    private static Pair<Integer, Integer> getNextChunk(String version, int n, int p) {
//        if the pointer is at the end of the string, return 0
        if(p > n - 1)
            return new Pair<>(0, p);
//        find the end of the next chunk
        int i, pEnd = p;
        while(pEnd < n && version.charAt(pEnd) != '.') {
            pEnd++;
        }
//        retrieve the next chunk
        if(pEnd < n)
            i = Integer.parseInt(version.substring(p, pEnd));
        else
            i = Integer.parseInt(version.substring(p, n));
//       update pointer's position to the next chunk
        p = pEnd + 1;

        return new Pair<>(i, p);
    }
}

/*
Given two version numbers, version1 and version2, compare them.

Version numbers consist of one or more revisions joined by a dot '.'. Each revision consists of digits and may contain leading zeros. Every revision contains at least one character. Revisions are 0-indexed from left to right, with the leftmost revision being revision 0, the next revision being revision 1, and so on. For example 2.5.33 and 0.1 are valid version numbers.
To compare version numbers, compare their revisions in left-to-right order. Revisions are compared using their integer value ignoring any leading zeros. This means that revisions 1 and 001 are considered equal. If a version number does not specify a revision at an index, then treat the revision as 0. For example, version 1.0 is less than version 1.1 because their revision 0s are the same, but their revision 1s are 0 and 1 respectively, and 0 < 1.
Return the following:
If version1 < version2, return -1.
If version1 > version2, return 1.
Otherwise, return 0.
Example 1:
Input: version1 = "1.01", version2 = "1.001"
Output: 0
Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".
Example 2:
Input: version1 = "1.0", version2 = "1.0.0"
Output: 0
Explanation: version1 does not specify revision 2, which means it is treated as "0".
Example 3:
Input: version1 = "0.1", version2 = "1.1"
Output: -1
Explanation: version1's revision 0 is "0", while version2's revision 0 is "1". 0 < 1, so version1 < version2.

Constraints:
1 <= version1.length, version2.length <= 500
version1 and version2 only contain digits and '.'.
version1 and version2 are valid version numbers.
All the given revisions in version1 and version2 can be stored in a 32-bit integer.
 */