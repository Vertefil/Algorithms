package YANDEX.School2026;

import java.io.*;
import java.util.*;

public class Task4 {
    /**
     * Назовем шаблоном строку, состоящую из маленьких английских букв и вопросительных знаков (?).
     * Слово соответствует шаблону, если можно заменить вопросительные знаки в шаблоне на буквы таким образом, чтобы получилось слово.
     * Например, слово "baca" соответствует шаблону "bac?" и "b??a", но не соответствует шаблону "?b??".
     *
     * Вам дано n шаблонов одинаковой длины m. Необходимо определить количество пар шаблонов
     * (i,j) из этого набора, таких, что существует хотя бы одно слово, соответствующее обоим шаблонам и 1≤i<j≤n.
     *
     * Формат ввода
     * В первой строке записаны числа n (1≤n≤50000) и m (1≤m≤6) — количество шаблонов и их длина соответственно.
     *
     * В следующих n строках записаны шаблоны.
     * Каждый шаблон состоит из маленьких английских букв и вопросительных знаков и имеет длину m.
     *
     * Формат вывода
     * Выведите одно число — количество пар шаблонов, для которых найдется слово, соответствующее обоим шаблонам.
     * Пример 1
     * Ввод
     * 3 3
     * ??b
     * c??
     * c?c
     *
     * Вывод
     * 2
     *
     * Пример 2
     * Ввод
     * 4 6
     * ab??c?
     * ??kll?
     * a?k??c
     * ?bcd??
     *
     * Вывод
     * 3
     * Пример 3
     * Ввод
     * 5 2
     * ??
     * b?
     * c?
     * ?g
     * cg
     *
     * Вывод
     * 8
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().trim().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine().trim();
        }

        int[] msk = new int[n];
        int[][] ch = new int[n][m];

        for (int i = 0; i < n; i++) {
            String s = words[i];
            for (int j = 0; j < m; j++) {
                if (s.charAt(j) != '?') {
                    ch[i][j] = s.charAt(j) - 'a';
                    msk[i] |= 1 << j;
                } else {
                    ch[i][j] = -1;
                }
            }
        }
        Map<Integer, List<Integer>> grp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            grp.computeIfAbsent(msk[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> masks = new ArrayList<>(grp.keySet());
        int K = masks.size();
        long ans = 0;

        for (int a = 0; a < K; a++) {
            for (int b = a; b < K; b++) {
                int inter = masks.get(a) & masks.get(b);
                List<Integer> ga = grp.get(masks.get(a));
                List<Integer> gb = grp.get(masks.get(b));
                HashMap<Integer, Integer> freq = new HashMap<>();

                for (int p : ga) {
                    int key = proj(ch[p], inter, m);
                    freq.merge(key, 1, Integer::sum);
                }

                if (a == b) {
                    for (int cnt : freq.values()) ans += (long) cnt * (cnt - 1) / 2;
                } else {
                    for (int p : gb) {
                        int key = proj(ch[p], inter, m);
                        ans += freq.getOrDefault(key, 0);
                    }
                }
            }
        }
        System.out.println(ans);
    }

    public static int proj(int[] c, int inter, int m) {
        int key = 0;
        for (int j = 0; j < m; j++) {
            if ((inter >> j & 1) == 1) key = key * 26 + c[j];
        }
        return key;
    }
}