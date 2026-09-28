package YANDEX.tasks;

import java.util.PriorityQueue;

public class KthSmallestElementinaSortedMatrix378 {
    /**
     * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
     *
     * Для n x n matrix где каждая строка и столбец отсортированы в порядке возрастания,
     * вернуть самый kth наименьший элемент в матрице.
     * Обратите внимание, что это kth наименьший элемент в отсортированном порядке, а не kth уникальный элемент.
     *
     * Необходимо найти решение с временной сложностью не более O(n2).
     *
     * Идея:
     * Использовать PriorityQueue<int[]>, с компаратором ((a, b) -> a[0] - b[0]);
     * Внутри будем держать [val, row, col], чтобы знать какое число смотрим и куда двигаться.
     *
     * 1. Добавляем первый столбец.
     * 2. Так как у нас отсортированная очередь, то первый элемент всегда будет наименьший - бин поиск - log n
     * В цикле идём до k - 1:
     *  достаём из очереди первый массив (наименьшее число в очереди), таким образом он удаляется из очереди
     *  добавляем следующее число в строке, откуда пришло текущее наименьшее число.
     *  Так как у нас приоритетная очередю, то у нас всегда на первом месте будет наименьшее число.
     *
     *  После того, как сделали k-1 итерацию:
     *  Достаём из очеред k-ый наименьший элемент из матрицы и возращаем его значение
     *
     *
     *
     * Сложность:
     *  по времени: O(k log n), если k > n, иначе O(n log n)
     *  по памяти: O(n) - память на очередь
     *
     * @param matrix int[][]
     * @param k int
     */
    public static int kthSmallest(int[][] matrix, int k) {
        int res = Integer.MAX_VALUE;
        //PriorityQueue - при poll всегда возращает наименьший элеемент.
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for(int i = 0; i < matrix.length; i++) {
            heap.offer(new int[] {matrix[i][0], i, 0});
        }
        // заполнили heap [val, row, col]
        for (int i = 0; i < k - 1; i++) {
            int[] top = heap.poll();
            int row = top[1];
            int col = top[2];
            if (col + 1 < matrix.length) {
                heap.offer(new int[]{matrix[row][col+1], row, col + 1});
            }
        }
        return heap.poll()[0];
    }

    public static void main(String[] args) {
        int[][] matrix1 = new int[][] {{1,5,9},{10,11,13},{12,13,15}};
        int[][] matrix2 = new int[][] {{1,100},{2,101},{3,103}};
        System.out.println(kthSmallest(matrix1, 3));
        System.out.println(kthSmallest(matrix2, 4));
    }
}
