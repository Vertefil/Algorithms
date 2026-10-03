package YANDEX.tasks;

import com.sun.jdi.ArrayReference;

import java.util.*;

public class FindKPairsWithSmallestSums373 {
    /**
     * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/
     *
     * Даны два отсортированных массива и число K.
     * nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     * Определите пару (u, v), состоящую из одного элемента из первого массива и одного элемента из второго массива.
     * Верните те k пары (u1, v1), (u2, v2), ..., (uk, vk) с наименьшими суммами.
     *
     * Идея:
     * Использовать приоритетную очередь (сумма, индекс nums1, индекс nums2) - K log k по скорости из-за сортировки
     * И set visited, чтобы не дублировать пары.
     *
     * Добавляем в очередь 0-ую пару - (nums1[0] + nums2[0], 0, 0) и добавляем в visited
     *
     * Пока k > 0 && !heap.isEmpty():
     *  достаём из кучи верхний элемент и помещаем в массив
     *  Из массива достаём i - индекс первого, j - индекс второго
     *  Добавляем в результат.
     *
     *  Проверяем, что у первого массива можем сдвинуться на i+1
     *      Если можно, добавляем в кучу (nums1[i+1] + nums2[j], i+ 1, j)и добавляем в visited
     *  Проверяем, что у второго массива можем сдвинуться на j+1
     *      Если можно, добавляем в кучу (nums1[i] + nums2[j+1], i, j+1)и добавляем в visited
     *  k--
     *
     *  Выводим результат.
     * Сложность:
     *  по времени: O(k logk) - проходимся по куче и сортируем
     *  по памяти: O(k) - в зависимости от k
     *
     * @param nums1 int[]
     * @param nums2 int[]
     * @param k int
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        HashSet<String> visited = new HashSet<>();
        List<List<Integer>> res = new ArrayList<>();
        //Отсортированная куча, сумма, индекс1, индекс2; При poll() получаем мин сумму. Так как сортировку внутри nlogn
        heap.offer(new int[]{nums1[0]+nums2[0], 0, 0});
        visited.add("0,0");

        while(k > 0 && !heap.isEmpty()) {
            int[] top = heap.poll();
            int i = top[1];
            int j = top[2];
            res.add(Arrays.asList(nums1[i],nums2[j]));

            if (i + 1 < nums1.length && !visited.contains((i+1) + ","+j)) {
                heap.offer(new int[]{nums1[i+1] + nums2[j], i+1, j});
                visited.add((i+1) + ","+j);
            }

            if (j + 1 < nums2.length && !visited.contains(i + ","+(j+1))) {
                heap.offer(new int[]{nums1[i] + nums2[j+1],i, j+1});
                visited.add(i + ","+(j+1));
            }

            k--;
        }
        return res;
    }

    /**
     * Идея:
     * Использовать приоритетную очередь (сумма, индекс nums1, индекс nums2) - K log k по скорости из-за сортировки
     *
     * Добавляем в очередь пары с первого массива длинной min(k или nums1.length) и 0 индексом nums2
     * таким образом мы сразу можем найти k минимальных пар и в будущем будем двигаться только по второму массиву
     * Это нам сэкономит скорость и место, так как не надо держать visited
     *
     * Пока k-- > 0 && !heap.isEmpty():
     *  достаём из кучи верхний элемент и помещаем в массив
     *  Из массива достаём i - индекс первого, j - индекс второго
     *  Добавляем в результат.
     *
     *  Проверяем, что у второго массива можем сдвинуться на j+1
     *      Если можно, добавляем в кучу (nums1[i] + nums2[j+1], i, j+1)
     *
     *  В конце после цикла выполняется: k-- из условия цикла
     *
     *  Выводим результат.
     *
     * Идейное отличие в том, что не держим сет из посещений, heap у нас более стабильный,
     * так как двигаемся только по второму и имеем потенциально маленькие пары раньше, чем в первой реализации.
     *
     * Сложность:
     *  по времени: O(k log(min(k,n))) - проходимся по куче и сортируем
     *  по памяти: O(min(k,n) - в зависимости от k и n
     *
     * @param nums1 int[]
     * @param nums2 int[]
     * @param k int
     */
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return res;

        // heap entry: {sum, i, j}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            pq.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }

        while (k-- > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            int i = cur[1], j = cur[2];
            res.add(Arrays.asList(nums1[i], nums2[j]));

            if (j + 1 < nums2.length) {
                pq.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
        }
        return res;
    }
}
