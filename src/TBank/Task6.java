package TBank;

import java.io.*;
import java.util.*;

public class Task6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        int players = 1 << n;

        int[][] prize = new int[players][n + 1];

        for (int i = 0; i < players; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int wins = 1; wins <= n; wins++) {
                prize[i][wins] = Integer.parseInt(st.nextToken());
            }
        }

        long[] dp = new long[players];

        for (int round = 1; round <= n; round++) {
            int size = 1 << round;
            int half = size / 2;

            for (int start = 0; start < players; start += size) {
                int middle = start + half;
                int end = start + size;

                long bestLeft = 0;
                long bestRight = 0;

                for (int i = start; i < middle; i++) {
                    bestLeft = Math.max(
                            bestLeft, dp[i] + prize[i][round - 1]);
                }

                for (int i = middle; i < end; i++) {
                    bestRight = Math.max(
                            bestRight, dp[i] + prize[i][round - 1]);
                }

                for (int i = start; i < middle; i++) {
                    dp[i] += bestRight;
                }

                for (int i = middle; i < end; i++) {
                    dp[i] += bestLeft;
                }
            }
        }

        long answer = 0;

        for (int i = 0; i < players; i++) {
            answer = Math.max(answer, dp[i] + prize[i][n]);
        }

        bw.write(Long.toString(answer));
        bw.flush();
    }
}
