package stack.minStack;

import java.util.Stack;

public class MinStackOptimal {
    /**
     * Будет набор команд, которые должны исполниться за O(1).
     *
     * Идея: используем структуру Stack и выделение двух стеков, основного и минимального.
     * Для реализации поиска минимального числа, создаётся дополнительный стек с минимальными числами.
     * При добавлении числа, сравниваем с минимальным стеком и если оно меньше или стек пуст, то добавляем в оба стека.
     *
     * Сложность O(1)- Выделение памяти и копирование аналогично структуре Stack.
     * Память O(n) - используем массив для хранения набора минимумов.
     *
     * Команды: ["MinStack", "push", 1, "push", 2, "push", 0, "getMin", "pop", "top", "getMin"]
     */
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStackOptimal() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;
        int top = stack.pop();
        if (top == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
