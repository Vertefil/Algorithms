package YANDEX.tasks;

import java.util.Arrays;

public class SmallestDifferenceLcci16o6 {
    /**
     * https://leetcode.cn/problems/smallest-difference-lcci/description/
     *
     * Минимальная разница
     * Даны массивы a и b. Найдите пару, которая даёт наименьшее абсолютную разницу и верните эту разницу.
     * пример：
     * ввод: {1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
     * Вывод: 3, то есть пара значений (11, 8)
     * подсказка：
     * 1 <= a.length, b.length <= 100000
     * -2147483648 <= a[i], b[i] <= 2147483647
     * Правильный результат находится в пределах [0, 2147483647]
     *
     * Идея:
     * 1 идея - использовать сортировку (только что она даст, если у нас например будет где-то в середине?)
     * 2 2 указателя, но смысла как будто бы нет
     * 3 Подсказка - сортировать и сделать обход через два указателя. (иногда надо использовать два подхода)
     *
     * Сложность:
     *  по времени: O(n log n + m log m) Сортировка + два указателя O(n+m)
     *  по памяти: O(1)
     *
     * @param a int[]
     * @param b int[]
     */
    public static int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a); //8  19  23  127  235 n = 5
        Arrays.sort(b); //1  2   3   11   13  m = 5
        int i = 0;
        int j = 0;
        long res = Long.MAX_VALUE;
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) return 0;
            long  diff = (long) a[i] - b[j];
            res = Math.min(Math.abs(diff), res);
            if (diff > 0) j++;
            else i++;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 15, 11, Integer.MAX_VALUE};
        int[] b = new int[]{23, 127, 235, 19, Integer.MIN_VALUE};
        int[] c = new int[]{1, Integer.MAX_VALUE};
        int[] d = new int[]{Integer.MIN_VALUE, 2};
        System.out.println(smallestDifference(c, d));
    }
}
