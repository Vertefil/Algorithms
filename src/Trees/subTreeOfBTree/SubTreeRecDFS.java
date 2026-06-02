package Trees.subTreeOfBTree;

import Trees.TreeNode;

public class SubTreeRecDFS {
    /**
     * Даны два дерева, определить является ли второе дерево под деревом первого?
     *
     * Идея: проходимся по первому дереву и вызываем рекурсивную функцию проверки одинаковых деревьев.
     * Если не равны, то вызываем основную функцию к левому и потом к правому ребёнку.
     * Результатом будет наличие под дерева слева или справа.
     *
     * Рекурсивно:
     *  Если под дерево пустое, то оно есть в основном -> true
     *  Если основное дерево пустое -> false
     *  Если основное дерево и поддерево одинаковы -> true
     *
     *  Вызываем саму функцию рекурсивно к левому, потом к правому ребёнку.
     *  Если хотя бы один ребёнок равен второму дереву -> true
     *
     * Сложность:
     *  по времени: O(n*m) n кол-во узлов в root, m кол-во узлов в subRoot
     *  по памяти: O(n+m)
     *
     * @param root TreeNode
     * @param subRoot TreeNode
     */
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) return true;
        if (root == null) return false;
        if (isSameTree(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }
}
