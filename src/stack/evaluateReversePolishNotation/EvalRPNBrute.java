package stack.evaluateReversePolishNotation;

import java.util.Set;
import java.util.Stack;

public class EvalRPNBrute {
    /**
     * На вход подаётся массив токенов по правилам Обратной Польской Нотации
     *
     * Идея: использовать стек для хранения чисел и результатов операции.
     * Если стек пустой и это не операция, то это число и мы его помещаем в стек.
     * При работе с операциями, важно учитывать, что результат операций всегда находится в конце стека.
     * При выполнении операций верхнее число из стека, сохраняем в tmp и при операции, ставим его в конец (num - tmp).
     *
     * Сложность O(n) - проходимся по массиву + O(1) запрос в Set
     * Память O(n) - стек для чисел + O(1) стек для операций (всегда знаем сколько)
     *
     * @param tokens String[]
     */
    public static int evalRPN(String[] tokens) {
        Stack<Integer> nums = new Stack<>();
        Set<String> operators = Set.of("+", "-", "*", "/");
        for (String token : tokens) {
            if (nums.isEmpty() || !operators.contains(token)) {
                nums.push(Integer.parseInt(token));
            } else if (token.equals("+")) {
                int c = nums.pop();
                nums.push(nums.pop() + c);
            } else if (token.equals("-")) {
                int c = nums.pop();
                nums.push(nums.pop() - c);
            } else if (token.equals("/")) {
                int c = nums.pop();
                nums.push(nums.pop() / c);
            } else if (token.equals("*")) {
                int c = nums.pop();
                nums.push(nums.pop() * c);
            }
        }
        return nums.pop();
    }

    public static void main(String[] args) {
        String[] tokens = new String[]{"1","2","+","3","*","4","-"};
        System.out.println(evalRPN(tokens));
    }
}