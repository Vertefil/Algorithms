package TBank;

import java.io.*;
import java.util.*;

public class Task2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        Map<String, Integer> counts = new HashMap<>();
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            int count = counts.merge(s, 1, Integer::sum);
            maxCount = Math.max(maxCount, count);
        }

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == maxCount) {
                result.add(entry.getKey());
            }
        }

        Collections.sort(result);

        for (String s : result) {
            bw.write(s);
            bw.newLine();
        }
        bw.flush();
    }
}
