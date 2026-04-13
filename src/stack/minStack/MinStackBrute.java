package stack.minStack;

import java.util.Arrays;

public class MinStackBrute {
    /**
     * Будет набор команд, которые должны исполниться за O(1).
     *
     * Идея: реализуем стек через массив, стандартные команды и выделение памяти при переполнении.
     * Для реализации поиска минимального числа, создаётся дополнительный массив с минимальными числами.
     * Первое число попавшее в стек, отправляется и в минимальный стек, с которым в будущем будет сравнение.
     * Если берётся число с верхушки стека и оно минимальное, то уменьшается размер минимального стек (удаляем число).
     *
     * Сложность O(1)- Выделение памяти и копирование аналогично структуре Stack.
     * Память O(n) - используем массив для хранения набора минимумов.
     *
     * Команды: ["MinStack", "push", 1, "push", 2, "push", 0, "getMin", "pop", "top", "getMin"]
     */
    private int[] stack;
    private int[] minStack;
    private int minSize = 0;
    private int size = 0;

    public MinStackBrute() {
        stack = new int[16];
        minStack = new int[16];
    }

    public void push(int val) {
        ensureCapacity();
        stack[size] = val;
        size++;
        if (minSize == 0 || val <= minStack[minSize - 1]) {
            minStack[minSize] = val;
            minSize++;
        }
    }

    public void pop() {
        if(stack[size-1] == minStack[minSize-1]) minSize--;
        size--;
    }

    public int top() {
        return stack[size-1];
    }

    public int getMin() {
        return minStack[minSize-1];
    }

    private void ensureCapacity() {
        if (size >= stack.length) {
            int newSize = stack.length * 2;
            stack = Arrays.copyOf(stack, newSize);
            minStack = Arrays.copyOf(minStack, newSize);
        }
    }
}