package stack.carFleet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CarFleet {
    /**
     * На вход подаётся позиция финиша, позиция старта машины и массив их скоростей. Вывести кол-во автопарков
     * Автопарк - это машина или машины, которые прибыли к финишу вместе или по отдельности.
     * Машины не могут обгонять друг друга, поэтому если машина догнала впереди идущую, она едет прямо за ней.
     * Таким образом, машины прибывшие друг за другом считаются одним автопарком.
     * Если машина догнала другую машину в момент финиша, они считаются одним автопарком.
     *
     * Идея: отсортировать массив по возрастанию позиции и пройтись в обратную сторону length-1 -> 0.
     * Понадобится стек для хранения время прибытия машин.
     *
     * Для каждой машины i:
     *  вычисляем время прибытия на финиш, если бы не было машин спереди и добавляем в стек
     *  если размер стека >= 2 и время из начало стека меньше, чем время из стека на позиции stack.size()-2:
     *      вытаскиваем верхний элемент, таким образом как-бы удаляем машину за ним идущую, т.к. это один автопарк.
     *  повторяем, пока не закончится.
     *
     *  Размер стека и есть кол-во автопарков.
     *
     * Сложность:
     *  по времени: O(nlogn) = O(nlogn) сортировка + O(n) проход
     *  по памяти: O(n) под результат
     *
     * @param target int
     * @param position int[]
     * @param speed int[]
     */
    public static int carFleet(int target, int[] position, int[] speed) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            list.add(new int[] {position[i], speed[i]});
        }
        list.sort((a, b) -> a[0] - b[0]);
        Stack<Double> stack = new Stack<>();
        for (int i = list.size()-1; i >= 0; i--) {
            stack.push(((target - list.get(i)[0]) / (double) list.get(i)[1]));
            if (stack.size() >= 2 && (stack.peek() <= stack.get(stack.size() - 2)))
            {
                stack.pop();
            }
            System.out.println("size = " + stack.size());
        }
        return stack.size();
    }

    public static void main(String[] args) {
        int tar = 10;
        int[] pos1 = new int[] {6,8};
        int[] pos2 = new int[] {4,1,0,7};
        int[] sp1 = new int[] {3,2};
        int[] sp2 = new int[] {2,2,1,1};
        System.out.println(carFleet(tar, pos1, sp1));
        System.out.println(carFleet(tar, pos2, sp2));
    }
}
