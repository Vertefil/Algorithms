package YANDEX;

import java.io.*;
import java.util.*;

public class Task5 {
    /**
     * На центральном проспекте города было решено построить новый дом из блоков.
     * Каждый блок представляет собой клетку 1×1.
     * В соответствии с архитектурным планом дом должен был быть переменной высоты:
     * его можно представить в виде последовательности столбцов различной высоты и шириной в один блок.
     * Столбцы занумерованы числами от 1 до n.
     *
     * Однако из-за обильных снегопадов в последние зимы в городе было введено новое правило строительства домов.
     * Теперь ровно один из столбцов в доме должен быть самым высоким, а остальные столбцы должны образовывать скат,
     * чтобы снег не накапливался на крыше. Если самым высоким столбцом является столбец i и его высота равна hi,
     * то высота столбца j должна быть равна hi - |i - j|. То есть, соседние с i столбцы должны быть на 1 ниже i-го,
     * столбцы с номерами i−2 и i+2 на 2 ниже и т.д.
     *
     * За одну операцию можно добавить или удалить один блок в любой из столбцов.
     * Необходимо изменить план дома, чтобы он соответствовал новому правилу, используя минимальное число операций.
     * После изменения высоты всех столбцов должны быть строго положительными
     * (если самым высоким является столбец i, то hi >= 1 + max (i-1, n - i))
     *
     * Формат ввода
     * В первой строке вводится число n (1≤n≤100000) — количество столбцов в описании дома.
     *
     * Во второй строке вводится n целых чисел hi (1 <= hi <= 10^9) — высоты столбцов в изначальном архитектурном плане.
     *
     * Формат вывода
     * Выведите минимальное количество операций, необходимых для изменения плана дома в соответствии с новым правилом.
     *
     * Пример 1
     * Ввод
     * 4
     * 1 1 2 3
     *
     * Вывод
     * 3
     *
     * Пример 2
     * Ввод
     * 5
     * 4 5 7 2 2
     * Вывод
     * 4
     *
     * Пример 3
     * Ввод
     * 6
     * 4 5 6 5 4 3
     *
     * Вывод
     * 0
     */
    static long[] comp;
    static int csz, FN;
    static int[] fc1, fc2;
    static long[] fs1, fs2;

    static void add(int[] fc, long[] fs, int i, int d, long v) {
        for (; i <= FN; i += i & -i) { fc[i] += d; fs[i] += v; }
    }

    static int getCnt(int[] fc, int i) {
        int r = 0; for (; i > 0; i -= i & -i) r += fc[i]; return r;
    }

    static long getSum(long[] fs, int i) {
        long r = 0; for (; i > 0; i -= i & -i) r += fs[i]; return r;
    }

    static int ub(long v) {
        int lo = 0, hi = csz;
        while (lo < hi) { int m = (lo + hi) >>> 1; if (comp[m] <= v) lo = m + 1; else hi = m; }
        return lo;
    }

    static int lb(long v) {
        int lo = 0, hi = csz;
        while (lo < hi) { int m = (lo + hi) >>> 1; if (comp[m] < v) lo = m + 1; else hi = m; }
        return lo;
    }

    static int cntLE(int[] fc, long v) {
        int p = ub(v); return p > 0 ? getCnt(fc, p) : 0;
    }

    static long sumLE(long[] fs, long v) {
        int p = ub(v); return p > 0 ? getSum(fs, p) : 0;
    }

    static long absCost(int[] fc, long[] fs, int tot, long totS, long center) {
        int cl = cntLE(fc, center);
        long sl = sumLE(fs, center);
        return center * (long) cl - sl + (totS - sl) - center * (long) (tot - cl);
    }

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedInputStream(System.in));
        in.nextToken(); int n = (int) in.nval;
        long[] h = new long[n + 1];
        for (int i = 1; i <= n; i++) { in.nextToken(); h[i] = (long) in.nval; }

        long[] L = new long[n + 1], R = new long[n + 1];
        for (int j = 1; j <= n; j++) { L[j] = h[j] - j; R[j] = h[j] + j; }

        // координатное сжатие
        long[] tmp = new long[2 * n];
        for (int j = 1; j <= n; j++) { tmp[j - 1] = L[j]; tmp[n + j - 1] = R[j]; }
        Arrays.sort(tmp);
        comp = new long[2 * n]; csz = 0;
        for (int i = 0; i < 2 * n; i++)
            if (i == 0 || tmp[i] != tmp[i - 1]) comp[csz++] = tmp[i];

        FN = csz + 1;
        fc1 = new int[FN + 1]; fs1 = new long[FN + 1];
        fc2 = new int[FN + 1]; fs2 = new long[FN + 1];

        int totL = 0, totR = n;
        long sumLt = 0, sumRt = 0;

        // изначально все в правой группе
        for (int j = 1; j <= n; j++) {
            add(fc2, fs2, lb(R[j]) + 1, 1, R[j]);
            sumRt += R[j];
        }

        long ans = Long.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            // переносим i из правой в левую
            add(fc2, fs2, lb(R[i]) + 1, -1, -R[i]);
            totR--; sumRt -= R[i];
            add(fc1, fs1, lb(L[i]) + 1, 1, L[i]);
            totL++; sumLt += L[i];

            // бинпоиск медианы: count(a<=V) = cntLE(L, V-i) + cntLE(R, V+i)
            int need = (n + 1) / 2;
            long lo = 1, hi = 1_200_000_000L;
            while (lo < hi) {
                long mid = (lo + hi) >>> 1;
                if (cntLE(fc1, mid - i) + cntLE(fc2, mid + i) >= need) hi = mid;
                else lo = mid + 1;
            }

            long med = lo;
            long minH = 1L + Math.max(i - 1, n - i);

            // функция выпуклая => оптимум в медиане или на границе minH
            long H1 = Math.max(med, minH);
            long H2 = Math.max(med - 1, minH);
            long c1 = absCost(fc1, fs1, totL, sumLt, H1 - i) + absCost(fc2, fs2, totR, sumRt, H1 + i);
            long c2 = absCost(fc1, fs1, totL, sumLt, H2 - i) + absCost(fc2, fs2, totR, sumRt, H2 + i);
            ans = Math.min(ans, Math.min(c1, c2));
        }

        System.out.println(ans);
    }
}
