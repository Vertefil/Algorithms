package TBank;

import java.io.*;
import java.util.StringTokenizer;

public class Task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sum = 0;
        int max = 0;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            sum += value;

            if (value > max) {
                max = value;
                maxCount = 1;
            } else if (value == max) {
                maxCount++;
            }
        }

        boolean possible = maxCount == n - 2 && sum == (n - 1) * max;
        bw.write(possible ? "YES" : "NO");
        bw.newLine();
        bw.flush();
    }
}
