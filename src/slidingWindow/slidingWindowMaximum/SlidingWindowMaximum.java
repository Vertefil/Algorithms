package slidingWindow.slidingWindowMaximum;

import java.util.*;

public class SlidingWindowMaximum {
    /**
     * На вход подаётся строка массив и длина скользящего окна
     *
     * Идея: Использовать деку, чтобы добавлять индекс максимального значения в начало, остальные в конец.
     * Далее, число в деке - число из массива nums по индексу из деки
     * Когда окно становится больше, чем k:
     *  На каждой итерации, добавляем в итоговый массив число из массива nums[] по индексу из начала деки (всегда max).
     * Крайний случай: сдвинулось окно и прошлый максимум ушёл из системы, его нужно удалить из деки.
     *
     * Создаём указатели на начало, деку и list для ответа.
     *
     * Пока r < nums.length:
     *  Пока дека не пустая и число по индексу r больше, чем по индексу из конца деки:
     *      Удаляем с конца деки.
     *
     *  Добавляем в конец деки число
     *
     *  Если индекс в начале деки меньше чем указатель l, то удаляем первый элемент из деки
     *
     *  Если указатель r+1 >= k, значит мы создали окно размером k, далее будет считать максимумы в окнах:
     *      Добавляем число по индексу из начала деки, двигаем указатель l++;
     *
     *  Двигаем указатель r++
     *
     * Переводим лист в массив (можно оптимизировать и посчитать выходной массив (nums.length - k) и без list)
     *
     * Сложность:
     *  по времени: O(n) проход по массиву
     *  по памяти: O(n) массив для ответа
     *
     * @param nums int[]
     * @param k int
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        int l = 0, r = 0;
        Deque<Integer> deq = new LinkedList<>();
        while (r < nums.length) {
            while (!deq.isEmpty() && nums[r] > nums[deq.getLast()]) {
                deq.removeLast();
            }

            deq.addLast(r);

            if (l > deq.getFirst()) deq.removeFirst();

            if ((r + 1) >= k) {
                list.add(nums[deq.getFirst()]);
                l++;
            }

            r++;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] ar1 = new int[]{1,2,1,0,4,2,6};
        int k1 = 3;
        int[] a = maxSlidingWindow(ar1, k1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
    }
}
