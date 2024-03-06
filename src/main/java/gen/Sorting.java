package gen;

import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        int[] ar = {23,22,25,21};
        bsort(ar);
        System.out.println(Arrays.toString(ar));
    }

//    [def]
    private static void bsort(int[] ar) {
        for(int i = 0 ; i < ar.length ; i++) {
            for(int j = i + 1 ; j < ar.length ; j++) {
                if(ar[i] > ar[j]) {
                    int temp = ar[i];
                    ar[i] = ar[j];
                    ar[j] = temp;
                }
            }
        }
    }
}
