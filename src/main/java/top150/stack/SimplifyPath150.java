package top150.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;


public class SimplifyPath150 {
    public static void main(String[] args) {
        String path = "/../";
        String path1 = "/a/./b/../../c/"; // output: /c
        String path2 ="/home//foo/"; // output: /home/foo"
        String path3 = "/a//b////c/d//././/.."; // output: "/a/b/c"
        System.out.println(simplifyPath(path3));
    }

//    stack; time: O(n), space: O(n)
//    note: we can use stack instead of array deque and iterate first to last, however for ad you will have to do the following
//    for the desired order;
    public static String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] components = path.split("/");
        for(String directory : components) {
            if(directory.equals(".") || directory.isEmpty())
                continue;
            if(directory.equals("..")) {
//                pop stack's current directory
                if(!stack.isEmpty())
                    stack.pop();
            } else {
//                else it's a legitimate directory
                stack.push(directory);
            }
        }

        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()) {
            result.append("/");
            result.append(stack.pollLast());
        }
        return result.length() > 0 ? result.toString() : "/";
    }
}

/*
Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem, any other format of periods such as '...' are treated as file/directory names.
The canonical path should have the following format:
The path starts with a single slash '/'.
Any two directories are separated by a single slash '/'.
The path does not end with a trailing '/'.
The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
Return the simplified canonical path.
Example 1:
Input: path = "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.
Example 2:
Input: path = "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
Example 3:
Input: path = "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.

Constraints:
1 <= path.length <= 3000
path consists of English letters, digits, period '.', slash '/' or '_'.
path is a valid absolute Unix path.
 */