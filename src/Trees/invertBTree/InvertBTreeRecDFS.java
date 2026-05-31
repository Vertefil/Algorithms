package Trees.invertBTree;

import Trees.TreeNode;

public class InvertBTreeRecDFS {
    /**
     * Дано дерево, надо его инвертировать
     *
     * Идея: На каждом шаге менять левого и правого ребёнка и идти дальше
     * Проходимся в глубь, так как рекурсивно идём по левому до листа и по правому.
     *
     * Рекурсивно:
     *  Базовый случай: значение корня == null -> null
     *
     *  tmp для левого ребёнка
     *  Меняем левого на правого
     *  Меняем правого на tmp
     *
     *  Повторяем рекурсивно для всего левого поддерева
     *  Повторяем рекурсивно для всего правого поддерева
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

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
