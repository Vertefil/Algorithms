package YANDEX.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals56 {
    /**
     * https://leetcode.com/problems/merge-intervals/
     *
     * Даны интервалы в случайном порядке: [[1,3],[8,10],[2,6],[15,18]]
     *
     * Идея:
     * Отсортировать интервалы в порядке возрастания.
     * Идти по интервалам и сравнивать конец меньшего интервала с началом следующего.
     * Если он меньше или равен, то включаем в интервал. Иначе добавляем начало меньшего отрезка и начало большего.
     *
     * Инит i = 0
     * Пока i < длина массивов интервалов:
     *  инит start и end массива текущим интервалом.
     *  Пока i + 1 < длина интервалов и начало следующего интервала <= end:
     *      i++
     *      max(end, конец текущего интервала)
     *
     *  Добавляем в список массив [start, end]
     *  i++
     *
     *
     * Сложность:
     *  по времени: O(nlogn) сортировка nlogn
     *  по памяти: O(n)
     *
     * @param intervals int[][]
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> list = new ArrayList<>();
        int i = 0;
        while (i < intervals.length) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            while (i + 1 < intervals.length && intervals[i+1][0] <= end) {
                i++;
                end = Math.max(intervals[i][1], end);
            }
            list.add(new int[]{start,end});
            i++;
        }
        int[][] res = new int[list.size()][2];
        for(i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }
}
