package Trees.sameBTree;

import Trees.TreeNode;

public class SameBTreeRecDFS {
    /**
     * Даны два дерева, определить они одинаковые или нет?
     *
     * Идея: проходимся по деревьям в одном порядке и сравниваем значения
     *
     * Рекурсивно:
     *  Если два узла пустые, то они равны -> true
     *  Если два узла не пустые и числа равны -> проходимся дальше по левым и правым под деревьям.
     *  Результатом будет true, если они равны, но если где-то не равны, то получим false;
     *
     * Сложность:
     *  по времени: O(n) Проходимся по всем узлам, O(logn) лучший случай
     *  по памяти: O(h) h - высота дерева
     *
     * @param p TreeNode
     * @param q TreeNode
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }
}
