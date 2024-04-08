package leetdaily.easy;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumStudents1500 {
    public static void main(String[] args) {
        int[] students = {0,0,0,1,1,1,1,0,0,0};
        int[] sandwiches = {1,0,1,0,0,1,1,0,0,0};
        System.out.println(countStudents(students, sandwiches));
    }

//    counting; time: O(n + m), space: O(1)
    public static int countStudents(int[] students, int[] sandwiches) {
        int circleStudentCount = 0, squareSandwichCount = 0;
        for(int student : students) {
            if(student == 0)
                circleStudentCount++;
            else
                squareSandwichCount++;
        }
        for(int sandwich : sandwiches) {
            if(sandwich == 0 && circleStudentCount == 0)
                return squareSandwichCount;
            if(sandwich == 1 && squareSandwichCount == 0)
                return circleStudentCount;
            if(sandwich == 0) {
                circleStudentCount--;
            } else {
                squareSandwichCount--;
            }
        }
        return 0;
    }

//    queue; time: O(n + m), space: O(n) [n - students, m - sandwiches]
    public static int countStudents1(int[] students, int[] sandwiches) {
        Deque<Integer> studentQueue = new ArrayDeque<>();
        int i = 0, lastServed = 0;
        for(int student : students)
            studentQueue.offer(student);
        while(!studentQueue.isEmpty() && lastServed < studentQueue.size()) {
            int student = studentQueue.poll();
            if(sandwiches[i] != student) {
                studentQueue.addLast(student);
                lastServed++;
            } else {
                i++; lastServed = 0;
            }
        }
        return studentQueue.size();
    }
}

/*
The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1 respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.
The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a stack. At each step:
If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and leave the queue.
Otherwise, they will leave it and go to the queue's end.
This continues until none of the queue students want to take the top sandwich and are thus unable to eat.
You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the ith sandwich in the stack (i = 0 is the top of the stack) and students[j] is the preference of the jth student in the initial queue (j = 0 is the front of the queue). Return the number of students that are unable to eat.
Example 1:
Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
Output: 0
Explanation:
- Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
- Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
- Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
- Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
- Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
Hence all students are able to eat.
Example 2:
Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
Output: 3

Constraints:

1 <= students.length, sandwiches.length <= 100
students.length == sandwiches.length
sandwiches[i] is 0 or 1.
students[i] is 0 or 1.
 */