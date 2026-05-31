package Trees.maxDeptOfBTree;

import Trees.TreeNode;

public class MaxDepthOfBTreeRecDFS {
    /**
     * Дано дерево, надо его макс глубину
     *
     * Идея: вычислить максимум у левого поддерева и его детей, аналогично для правого и вернуть значение + 1;
     *
     * Рекурсивно:
     *  Базовый случай: значение корня == null -> 0
     *
     *  Вычисляем рекурсивно максимум слева
     *  Вычисляем рекурсивно максимум справа
     *
     *  Возвращаем max + 1 (начальный корень)
     *
     * Сложность:
     *  по времени: O(n) Проходимся по всем узлам
     *  по памяти: O(h) h - высота дерева
     *
     * @param root TreeNode
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left),  maxDepth(root.right));
    }
}
