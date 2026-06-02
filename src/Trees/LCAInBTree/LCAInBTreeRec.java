package Trees.LCAInBTree;

import Trees.TreeNode;

public class LCAInBTreeRec {
    /**
     * Даны главное дерево и два узла.
     * Найти наименьшего общего предка
     *
     * Идея: найти место разделения между двумя узлами.
     * Т.к. бинарное дерево поиска, оно отсортировано и уникально.
     * Если мы будет идти по дереву и на некотором узле разделимся, значит нет смысла продолжать искать.
     * Т.к. в других под деревьях не будет одного из узлов, следовательно мы нашли минимального предка.
     * ВАЖНО: Если один из узлов корень, сразу возвращаем корень (у него нет родителя)
     *
     * Итеративно:
     *  Если оба числа больше идём вправо
     *  Если оба числа меньше идём влево
     *  Если один больше, другой меньше, или узел равен самому себе, значит нашли место разделения - ответ
     *
     * Сложность:
     *  по времени: O(h) h - высота дерева
     *  по памяти: O(h)
     *
     * @param root TreeNode
     * @param p TreeNode
     * @param q TreeNode
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;
        if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        else if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        else return root;
    }
}
