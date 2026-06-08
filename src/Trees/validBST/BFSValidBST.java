package Trees.validBST;

import Trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BFSValidBST {
    /**
     * Дано дерево. Является ли оно бинарным деревом поиска?
     * Корректное бинарное дерево поиска удовлетворяет следующим ограничениям:
     *
     *  левое поддерево каждого узла содержит только узлы с ключами, меньшими, чем ключ узла;
     *  правое поддерево каждого узла содержит только узлы с ключами, большими, чем ключ узла;
     *  и левое, и правое под деревья также являются бинарными деревьями поиска.
     *
     * Идея: Использовать границы для деревьев. Поиск в ширину.
     * Если под дерево пустое -> true
     * Если под дерево не подходит под границы, значит это не двоичное дерево поиска.
     * В самом начале в очередь передаём объект (дерево, MIN, MAX);
     *
     * Итеративно:
     *  Пока очередь не пуста:
     *      Достаём объект из очереди
     *      Назначаем переменные
     *      Если значение узла больше максимума или меньше минимума -> false
     *      Добавляем в очередь левый узел, с обновлённым max в виде текущего узла
     *      Добавляем в очередь правый узел, с обновлённым min в виде текущего узла
     *
     * Сложность:
     *  по времени: O(n) n кол-во узлов в root
     *  по памяти: O(n)
     *
     * @param root TreeNode
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null)  return true;
        Queue<Object[]> queue = new LinkedList<>();
        queue.offer(new Object[]{root, Integer.MIN_VALUE, Integer.MAX_VALUE});
        while (!queue.isEmpty()) {
            Object[] current = queue.poll();
            TreeNode node = (TreeNode) current[0];
            int left = (int) current[1];
            int right = (int) current[2];
            if (!(left < node.val && node.val < right)) return false;
            if (node.left != null) queue.offer(new Object[]{node.left, left, node.val});
            if (node.right != null) queue.offer(new Object[]{node.right, node.val, right});
        }
        return true;
    }
}
