package stack.largestRectangleInHistogram;

import java.util.Stack;

public class LargestRectangleArea {
    /**
     * На вход подаются высоты гистограммы. Найти, максимальную площадь, которую можно составить из этих высот.
     * Ширина столбца = 1.
     *
     * Идея: Стек с парами - индекс, высота. Первый элемент всегда будет просто добавлен в стек.
     * Start - место откуда можем строить текущий прямоугольник. Нужен в цикле, иначе можем построить h * 1;
     *
     * Для каждой машины i:
     *  Если стек не пуст и высота i меньше, верхней высоты в стеке
     *      Извлекаем верхний элемент из стека, считаем площадь и выбираем максимум, обновляем старт для вычислений.
     *      Повторяем пока не наткнёмся на высоту ниже или стек не пуст.
     *  Иначе добавляем в стек пару index, height
     *
     * После первого цикла, запускаем второй цикл по стеку, где считаем оставшиеся площади и выбираем максимальную.
     *
     * Сложность:
     *  по времени: O(n) = O(n) проход + O(n) проход по остатку стека
     *  по памяти: O(n) = стек под высоты и их индексы.
     *
     * @param heights int[]
     */
    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<int[]> stack = new Stack<>(); //{idx, height}
        for (int i = 0; i < heights.length; i++) {
            int start = i;
            while(!stack.isEmpty() && stack.peek()[1] > heights[i]) {
                int[] ar = stack.pop();
                maxArea = Math.max(maxArea, ar[1] * (i - ar[0]));
                start = ar[0];
            }
            stack.push(new int[] {start, heights[i]});
        }

        for (int[] pair : stack) {
            int index = pair[0];
            int height = pair[1];
            maxArea = Math.max(maxArea, height * (heights.length - index));
        }

        return maxArea;
    }

    public static void main(String[] args) {

    }
}
