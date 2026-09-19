package YANDEX.MbTask;

import java.util.HashSet;

/** Дан массив точек с целочисленными координатами (x,y).
 * Определить существует ли вертикальная прямая, делящая все точки,
 * не лежащей на ней на 2 симметричных относительно этой прямой набора точек.
 * Наборы симметричны, когда каждая точка исходного массива имеет пару из другого набор.
 * примеры:
 * 0,0 0,1 1,1 2,2 3,1 4,1 4,0  = true
 * 0,0 0,0 1,1 2,2 3,1 4,1 4,0  = true
 * 0,0 0,0 1,1 2,2 3,1 4,0 = false
 * () = true
 * 0,0 = true
 * 0,0 10,0 = true
 * 0,0 11,1 = false
 * 0,0 1,0 3,0 = false
 *
 * Идея - такая линия проходит через середину, между самой левой и самой правой точкой.
 * Самая левая точка должна отразиться в самую правую, самая правая — в самую левую
 * Не смотрим на координаты целиком.
 * 1. Найти границы.
 * Пройди по всем точкам и найди minX и maxX.
 * 2. Вычислить ось.
 * Это линия x = (minX + maxX) / 2.
 * Чтобы избежать проблем с делением (и получить целое число),
 * удобнее работать с удвоенной осью: sumX = minX + maxX. Тогда отражение точки (x, y) — это точка (sumX - x, y).
 * 3. Проверить все точки.
 * Для каждой точки (x, y) из исходного массива проверь, есть ли в множестве её отражение - (sumX - x, y).
 * Если хотя бы для одной точки отражения нет ответ false.
*/
// https://www.lintcode.com/problem/908/
public class LineReflection {
    public static boolean isReflected(int[][] points) {
        if (points.length <= 1) return true;
        HashSet<String> set = new HashSet<>();
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        //Step 1
        for (int i = 0; i < points.length; i++) {
            set.add(points[i][0] + "," + points[i][1]);
            minX = Math.min(minX, points[i][0]);
            maxX = Math.max(maxX, points[i][0]);
        }
        //Step 2
        int sumX = minX + maxX;
        //Step 3
        for (int i = 0; i < points.length; i++) {
            int reflectedX = sumX - points[i][0];
            String ref = reflectedX + "," + points[i][1];

            if(!set.contains(ref)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] t1 = {{0,0},{0,1},{1,1},{2,2},{3,1},{4,1},{4,0}};
        System.out.println("Test 1 (true):  " + isReflected(t1));

        // Тест 2: то же + дубликат (0,0) → true
        int[][] t2 = {{0,0},{0,1},{0,1},{1,1},{2,2},{3,1},{4,1},{4,0}};
        System.out.println("Test 2 (true):  " + isReflected(t2));

        // Тест 3: не хватает (4,1) → true
        int[][] t3 = {{0,0},{0,0},{1,1},{2,2},{3,1},{4,0}};
        System.out.println("Test 3 (true):  " + isReflected(t3));

        // Тест 4: пустой массив → true
        int[][] t4 = {};
        System.out.println("Test 4 (true):  " + isReflected(t4));

        // Тест 5: одна точка → true
        int[][] t5 = {{0,0}};
        System.out.println("Test 5 (true):  " + isReflected(t5));

        // Тест 6: две точки на одной горизонтали → true (ось x = 5)
        int[][] t6 = {{0,0},{10,0}};
        System.out.println("Test 6 (true):  " + isReflected(t6));

        // Тест 7: разные y → false
        int[][] t7 = {{0,0},{11,1}};
        System.out.println("Test 7 (false): " + isReflected(t7));

        // Тест 8: (1,0) не имеет отражения → false
        int[][] t8 = {{0,0},{1,0},{3,0}};
        System.out.println("Test 8 (false): " + isReflected(t8));
    }
}
