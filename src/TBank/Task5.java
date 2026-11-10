package TBank;

import java.io.*;
import java.util.*;

public class Task5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long[] a = new long[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            while (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
            a[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(a);

        int negativeCount = 0;
        while (negativeCount < n && a[negativeCount] < 0) {
            negativeCount++;
        }

        int positiveStart = negativeCount;
        while (positiveStart < n && a[positiveStart] == 0) {
            positiveStart++;
        }

        int zeroCount = positiveStart - negativeCount;

        long first = a[0] * a[1];
        long last = a[n - 2] * a[n - 1];
        long mixed = a[0] * a[n - 1];

        long low = Math.min(mixed, Math.min(first, last));
        long high = Math.max(mixed, Math.max(first, last));

        while (low < high) {
            long mid = (low & high) + ((low ^ high) >> 1);
            long count = 0;

            if (mid < 0) {
                int j = positiveStart;

                for (int i = 0; i < negativeCount; i++) {
                    while (j < n && a[i] * a[j] > mid) {
                        j++;
                    }
                    count += n - j;
                }
            } else {
                count += (long) negativeCount * (n - positiveStart);
                count += (long) zeroCount * (n - zeroCount);
                count += (long) zeroCount * (zeroCount - 1) / 2;

                int left = 0;
                int right = negativeCount - 1;

                while (left < right) {
                    if (a[left] * a[right] <= mid) {
                        count += right - left;
                        right--;
                    } else {
                        left++;
                    }
                }

                left = positiveStart;
                right = n - 1;

                while (left < right) {
                    if (a[left] * a[right] <= mid) {
                        count += right - left;
                        left++;
                    } else {
                        right--;
                    }
                }
            }

            if (count >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        bw.write(Long.toString(low));
        bw.flush();
    }
}
