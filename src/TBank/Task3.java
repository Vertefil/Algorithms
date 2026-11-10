package TBank;

import java.io.*;
import java.util.*;

public class Task3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[] rows = new int[n];
        int[] cols = new int[n];
        int mainDiagonal = 0;
        int antiDiagonal = 0;
        int answer = -1;

        st = new StringTokenizer(br.readLine());

        for (int round = 1; round <= t; round++) {
            while (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }

            int cell = Integer.parseInt(st.nextToken()) - 1;
            int row = cell / n;
            int col = cell % n;

            rows[row]++;
            cols[col]++;

            if (row == col) {
                mainDiagonal++;
            }
            if (row + col == n - 1) {
                antiDiagonal++;
            }

            if (rows[row] == n || cols[col] == n
                    || mainDiagonal == n || antiDiagonal == n) {
                answer = round;
                break;
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
}
