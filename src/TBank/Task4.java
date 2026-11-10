package TBank;

import java.io.*;
import java.util.*;

public class Task4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        int left = 1;
        int right = n;

        for (int i = 0; i < n; i++) {
            int value;

            if (k >= right - left) {
                k -= right - left;
                value = right--;
            } else {
                value = left++;
            }

            if (i > 0) {
                bw.write(' ');
            }
            bw.write(Integer.toString(value));
        }

        bw.flush();
    }
}
