package TBank;

import java.io.*;
import java.util.*;

public class Task7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[][] parent = new int[9][n + 1];

        for (int[] p : parent) {
            Arrays.fill(p, -1);
        }

        int answer = 10 * (n - 1);

        for (int i = 0; i < n - 1 + q; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            for (int level = w - 1; level < 9; level++) {
                int[] p = parent[level];

                int a = find(p, u);
                int b = find(p, v);

                if (a == b) {
                    break;
                }

                if (p[a] > p[b]) {
                    int temp = a;
                    a = b;
                    b = temp;
                }

                p[a] += p[b];
                p[b] = a;
                answer--;
            }

            if (i >= n - 1) {
                bw.write(Integer.toString(answer));
                bw.newLine();
            }
        }

        bw.flush();
    }

    private static int find(int[] parent, int v) {
        int root = v;

        while (parent[root] >= 0) {
            root = parent[root];
        }

        while (v != root) {
            int next = parent[v];
            parent[v] = root;
            v = next;
        }

        return root;
    }
}
