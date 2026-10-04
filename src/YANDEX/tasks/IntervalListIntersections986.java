package YANDEX.tasks;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections986 {
    /**
     * https://leetcode.com/problems/interval-list-intersections/
     *
     * Даны два интервала, каждый список интервалов попарно непересекающиеся и упорядочены.
     * Найти все возможные пересечения (случай на границах тоже учитывается, это будет отдельный интервал)
     *
     * firstList = [[0,2],[5,10],[13,23],[24,25]],
     * secondList = [[1,5],[8,12],[15,24],[25,26]]
     * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     *
     * firstList = [[1,3],[5,9]],
     * secondList = []
     * Output: []
     *
     * firstList = [[10,12],[18,19]]
     * secondList = [[1,6],[8,11],[13,17],[19,20]]
     * Output
     * [[10,11],[19,19]]
     *
     * Идея:
     * Использовать два указателя f - указ на первый и s - указывает на второй
     * Пока f < first.length && s < second.length:
     *  Если начало first <= конец second И конец first >= начало second:
     *      Найти max от обоих начал
     *      Найти min от обоих концов
     *      Добавить его в список
     *
     *  Как мы двигаем указатели?
     *  Если конец first > конец second, двигаем s
     *  Иначе двигаем f.
     *
     * Вернуть получившийся массив интервалов
     *
     * Сложность:
     *  по времени: O(n)
     *  по памяти: O(1)
     *
     * @param firstList int[][]
     * @param secondList int[][]
     */
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new ArrayList<>();
        int f = 0;
        int s = 0;
        while (f < firstList.length && s < secondList.length) {
            if (firstList[f][0] <= secondList[s][1] && firstList[f][1] >= secondList[s][0]) {
                int start = Math.max(firstList[f][0], secondList[s][0]);
                int end = Math.min(firstList[f][1], secondList[s][1]);
                res.add(new int[]{start, end});
            }
            if (firstList[f][1] > secondList[s][1]) s++;
            else f++;
        }
        int[][] ans = new int[res.size()][2];
        for (int k = 0; k < res.size(); k++) {
            ans[k] = res.get(k);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] first = {
                {0, 2},
                {5, 10},
                {13, 23},
                {24, 25}
        };

        int[][] second = {
                {1, 5},
                {8, 12},
                {15, 24},
                {25, 26}
        };
        int[][] res = intervalIntersection(first, second);
        for (int[] re : res) {
            System.out.println(re[0] + ", " + re[1]);
        }
    }
}
