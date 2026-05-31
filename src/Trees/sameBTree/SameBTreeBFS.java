package Trees.sameBTree;

import Trees.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SameBTreeBFS {
    /**
     * Даны два дерева, определить они одинаковые или нет?
     *
     * Идея: проходимся по деревьям в ширину и сравниваем значения
     * Добавляем в две очереди корни деревьев
     *
     * Рекурсивно:
     *  Пока очереди не пустые:
     *      Достаём из очередей деревья
     *      Проходимся в ширину по размеру очереди:
     *          Если два узла пустые, то они равны -> дальше
     *          Если два узла не пустые и числа  не равны -> false
     *          Добавляем значения левых и правых детей
     *
     * Сложность:
     *  по времени: O(n) Проходимся по всем узлам, O(logn) лучший случай
     *  по памяти: O(h) h - ширина дерева
     *
     * @param p TreeNode
     * @param q TreeNode
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(p);
        q2.offer(q);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            for (int i = q1.size(); i > 0; i--) {
                TreeNode oneNode = q1.poll();
                TreeNode twoNode = q2.poll();
                if (oneNode == null && twoNode == null) continue;
                if (oneNode == null || twoNode == null || (oneNode.val != twoNode.val)) return  false;

                q1.offer(oneNode.left);
                q1.offer(oneNode.right);
                q2.offer(twoNode.left);
                q2.offer(twoNode.right);
            }
        }
        return true;
    }
}
