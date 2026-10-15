package YANDEX.tasks;

import java.util.ArrayList;
import java.util.List;

public class DvaStypenchGraphis {
    public class Point {
        public final long time;
        public final long value;
        public Point(long time, long value) {
            this.time = time;
            this.value = value;
        }
    }

    /*
    // Есть два ступенчатых графика некоторых наблюдаемых величин,
    // заданных отсортированными списками координат начала ступенек
    // (<время измерения>, <значение>) {time, value}.
    Снизу time, свеху value
    // Требуется построить новый график, который будет являться суммой двух представленных

    // a = {{2, 4}, {3, 6}, {9,7}}
    2 -> 5
    3 -> 6
    9 -> 7
    1 -> 2
    // b = {{1, 2}, {2, 1}, {5, 1}}

    // res = {{1, 2}, {2, 5}, {3, 7}, {9, 8}}
    Input:
    ↑
    │
    9
    8
    7                         ┌╌╌╌
    6       ┌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌╌┘
    5       ┆
    4     ╌╌┘
    3
    2  ────────────┐
    1              └───────────────
    ┼──1──2──3──4──5──6──7──8──9──→

    Output:
    ↑
    │
    9
    8       ╔══════╗           ╔═══
    7       ║      ╚═══════════╝
    6    ╔══╝
    5    ║
    4    ║
    3    ║
    2  ══╝
    1
    ┼──1──2──3──4──5──6──7──8──9──→

    // a {{1, 2}}
    // b {{2, 3}}
    // res = {{1, 2}, {2, 5}}

    Два указателя.
    на каждом шаге обрабатываем наименьшее время из двух списков и обновляем соответствующие значения.

    Сложность
    Время: O(n + m). Каждый указатель двигается не более n и m раз соответственно.
    Память: O(n + m) на результат.
     */
    public int[][] addStepFunctions(int[][] a, int[][] b) {
        List<int[]> res = new ArrayList<>();
        //Два указателя по a \ b
        int i = 0, j = 0;
        // Текущее значение функции
        int valA = 0, valB = 0;
        //Первая точка
        int lastSum = Integer.MIN_VALUE;

        while (i < a.length || j < b.length) {
            // Следующее время в каждом списке, если список пуст, то MAX_VALUE
            long tA = Long.MAX_VALUE;
            long tB = Long.MAX_VALUE;
            if (i < a.length) tA = a[i][0];
            if (j < b.length) tB = b[j][0];
            // Ищем минимальное время.
            long t = Math.min(tA, tB);

            //Обновляем значения на момент t, которые совпали
            if (tA == t) {
                valA = a[i][1];
                i++;
            }
            if (tB == t) {
                valB = b[j][1];
                j++;
            }
            int sum = valA + valB;
            // Добавляем новую точку в результат, если время изменилось
            // Если не изменилось, то конечная точка возрастает
            if (sum != lastSum) {
                res.add(new int[]{(int) t, sum});
                lastSum = sum;
            }
        }

        int[][] ans = new int[res.size()][2];
        for (int k = 0; k < res.size(); k++) {
            ans[k] = res.get(k);
        }
        return ans;
    }
}
