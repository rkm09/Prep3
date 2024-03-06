package gen;

import java.util.List;

public class Solution1 {
    public static void main(String[] args) {
        String s = "abc";
        System.out.println(rollingString(s, List.of("0 0 L", "2 2 R"))); // zbd
    }
    public static String rollingString(String s, List<String> operations) {
        StringBuilder sb = new StringBuilder(s);
        for(String operation : operations) {
            String[] ar = operation.split(" ");
            int i = Integer.parseInt(ar[0]);
            int j = Integer.parseInt(ar[1]);
            String direction = ar[2];
            while(i <= j) {
                if(direction.equals("L")) {
                    int val = s.charAt(i) - 1;
                    if(val == 96) val = 122;
                    sb.replace(i, i + 1,  String.valueOf((char)val));
                }
                else {
                    int val = s.charAt(i) + 1;
                    if(val == 123) val = 97;
                    sb.replace(i, i + 1, String.valueOf((char)val));
                }
                i++;
            }
        }
        return sb.toString();
    }
}
