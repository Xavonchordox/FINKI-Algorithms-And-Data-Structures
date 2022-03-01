package Test6;

import java.util.Scanner;


public class LDS {


    private static int najdolgaOpagackaSekvenca(int[] a) {

        // Vasiot kod tuka
        int maxIndex = 0;
        int index = 0;
        int max = 1;
        int count = 1;
        for (int i=0; i<a.length; i++){
            if (maxIndex < a[i]) {
                maxIndex = a[i];
                index = i;
            }
        }

        for (int i=index; i<a.length; i++){
            for (int j=i+1; j<a.length; j++){
                if (maxIndex > a[j]) {
                    maxIndex = a[j];
                    count++;
                }
                else {
                    maxIndex =
                }
                if (max <= count){
                    max = count;
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}
