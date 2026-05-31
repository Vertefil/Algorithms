package Trees.maxDeptOfBTree;

import Trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;


public class MaxDepthOfBTreeBFS {
    /**
     * Дано дерево, надо его макс глубину
     *
     * Идея: проходимся в ширину и увеличиваем уровень.
     * В конце вернём уровень, так как дошли до самого глубокого листа
     *
     * Создаём очередь и если дерево не пустое, добавляем в очередь
     * Заводим счётчик глубины
     *
     * Итеративно:
     *  Пока очередь не пуста:
     *      В цикле по кол-ву доступных узлов:
     *          Если есть ребёнок слева или справа добавляем его
     *      После того как добавили в очередь детей, увеличиваем уровень глубины
     *
     *  Возвращаем глубины - максимум.
     *
     * Сложность:
     *  по времени: O(n) Проходимся по всем узлам
     *  по памяти: O(n) временная переменная
     *
     * @param root TreeNode
     */
    public static int maxDepth(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) q.offer(root);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
            level++;
        }
        return level;
    }
}
