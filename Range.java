package Test4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Range {

    static long s(long x) {
        long sum = 0;
        while (x != 0){
            sum += x%10;
            x /= 10;
        }

        return sum;
    }

    static long proveri(long N, long A, long B) {
        // Vasiot kod tuka
        long x = A;
        while(x < B){
            x = B - A;
            if((x*x + s(x) + (200*x)) != N)
                B /= 2;
            else
                return x;
        }

            return -1;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long res = proveri(N, A, B);
        System.out.println(res);

        br.close();

    }

}
