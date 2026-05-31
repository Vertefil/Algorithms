package Trees.invertBTree;

import Trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBTreeBFS {
    /**
     * Дано дерево, надо его инвертировать
     *
     * Идея: На каждом шаге менять левого и правого ребёнка и идти дальше
     * Проходимся в ширину, так как очередь
     *
     * Базовый случай: значение корня == null -> null
     * Заполним очередь нашим деревом
     *
     * Пока очередь не пуста:
     *  Создадим дерево из верхушки очереди.
     *  tmp для левого ребёнка
     *  Меняем левого на правого
     *  Меняем правого на tmp
     *
     *  Если левый ребёнок не пустой, то добавляем его в очередь;
     *  Если правый ребёнок не пустой, то добавляем его в очередь;
     *
     *  Возвращаем корень
     *
     * Сложность:
     *  по времени: O(n) Проходимся по всем узлам
     *  по памяти: O(n) Временная переменная
     *
     * @param root TreeNode
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }

        return root;
    }
}
