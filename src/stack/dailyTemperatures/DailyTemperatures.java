package stack.dailyTemperatures;

import java.util.Stack;

public class DailyTemperatures {
    /**
     * На вход подаётся массив температур.
     * Вывести массив, который содержит кол-во дней от i-го до следующего, где температура будет выше.
     * вход - [30,35,31,36], вывод - [1,2,1,0].
     * для 30 - это 1 день (35), для 35 - это 2 дня (36), для 31 - 1 день (36), для 36 - 0, т.к. дальше нет повышения.
     *
     * Идея: Используем стек для хранения пары [температура, индекс].
     * Пока стек не пуст и температура этого дня больше, чем из верхушки стека, находим разницу между индексами
     * и добавляем в результат, до тех пор, пока стек не опустеет, или не встретим более высокую температуру.
     * В любом случае добавляем новую температуру и индекс в стек.
     *
     * Сложность O(n) - проходимся по массиву
     * Память O(n) - стек для чисел
     *
     * @param temperatures int[]
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<int[]> stack = new Stack<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > stack.peek()[0]) {
                res[stack.peek()[1]] = i - stack.peek()[1];
                stack.pop();
            }
            stack.push(new int[] {temperatures[i], i});
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ar1 = new int[] {30,38,30,36,35,40,28};
        //exp [1,4,1,2,1,0,0]
        int[] ar2 = new int[] {22,21,20};
        //exp [0,0,0]
        for(int t : dailyTemperatures(ar1)) System.out.println(t);
    }
}
