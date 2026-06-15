package YANDEX.School2026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task3 {
    /**
     * На плоскости заданы n точек.
     * Каждую из них необходимо покрыть одним или несколькими прямоугольниками.
     * Точка считается покрытой многоугольником, если она лежит внутри него или на его границе.
     * Стороны каждого прямоугольника должны быть параллельны осям координат.
     * Центр каждого прямоугольника должен располагаться в точке (0,0).
     *
     * Необходимо выбрать набор прямоугольников, покрывающих все точки, с минимальной суммарной площадью.
     *
     * Формат ввода
     * В первой строке вводится целое число (1≤n≤5000) — количество точек.
     *
     * В следующих n строках вводятся два целых числа x и y
     * (|x|, |y| <= 5 * 10^7, x!=0, y !=0) координаты точек.
     *
     * Формат вывода
     * Выведите минимальную суммарную площадь прямоугольников, покрывающих все точки.
     *
     * Пример 1
     * Ввод
     * 2
     * 1 1
     * -1 -1
     *
     * Вывод
     * 4
     *
     * Пример 2
     * Ввод
     * 3
     * -7 19
     * 9 -30
     * 25 10
     *
     * Вывод
     * 2080
     *
     * Пример 3
     * Ввод
     * 6
     * 1 20
     * 3 17
     * 5 15
     * 8 12
     * 9 11
     * 10 10
     *
     * Вывод
     * 760
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        long[][] pts = new long[n][2];

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            long x = Long.parseLong(str[0]);
            long y = Long.parseLong(str[1]);
            pts[i][0] = Math.abs(x);
            pts[i][1] = Math.abs(y);
        }

        Arrays.sort(pts, (a, b) -> Long.compare(b[0], a[0]));
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            long maxY = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (pts[j][1] > maxY) maxY = pts[j][1];
                long w = pts[j][0];
                long h = maxY;
                long area = 4 * w * h;
                if (dp[j] != Long.MAX_VALUE && dp[j] + area < dp[i]) {
                    dp[i] = dp[j] + area;
                }
            }
        }
        System.out.println(dp[n]);
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        long[][] pts = new long[n][2];

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            long x = Long.parseLong(str[0]);
            long y = Long.parseLong(str[1]);
            pts[i][0] = Math.abs(x);
            pts[i][1] = Math.abs(y);
        }

        Arrays.sort(pts, (a, b) -> Long.compare(b[0], a[0]));
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE);

        for (int start = 0; start < n; start++) {
            long maxY = pts[start][1];
            long w = pts[start][0];
            long area = 4 * w * maxY;

            if (start == 0) {
                dp[start] = Math.min(dp[start], area);
            } else {
                dp[start] = Math.min(dp[start], dp[start - 1] + area);
            }

            for (int end = start + 1; end < n; end++) {
                if (pts[end][1] > maxY) maxY = pts[end][1];
                w = pts[start][0];
                area = 4 * w * maxY;

                if (start == 0) {
                    if (dp[end] > area) dp[end] = area;
                } else {
                    if (dp[end] > dp[start - 1] + area) dp[end] = dp[start - 1] + area;
                }
            }
        }
        long ans = dp[n - 1];
        System.out.println(ans);
    }
}
