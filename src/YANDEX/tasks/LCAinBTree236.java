package YANDEX.tasks;

import Trees.TreeNode;

public class LCAinBTree236 {
    /**
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
     *
     * Даны главное дерево и два узла.
     * Найти наименьшего (самого нижнего по уровням) общего предка
     *
     * Идея: Поиск слева и справа какого либо узла содержащего p или q.
     * Пока проходимся по левому узлу мы спрашиваем есть ли тут значения p или q.
     * Левый узел - либо нашли предка p или q, либо null
     * Потом пройдёмся по правому узлу
     * Правый узел - либо нашли предка p или q, либо null
     *
     * Так как идём рекурсивно, то если мы нашли левый и правый узлы, то текущий узел - их предок - вернём самого себя
     * Если есть только один, вернём либо правый, либо левый, так как это самый нижний общий узел.
     *
     * Сложность:
     *  по времени: O(h) h - высота дерева
     *  по памяти: O(1)
     *
     * @param root TreeNode
     * @param p TreeNode
     * @param q TreeNode
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) return root;
        TreeNode left = lowestCommonAncestor(root.left, p,q);
        TreeNode right = lowestCommonAncestor(root.right, p,q);
        if (left != null && right != null) return root;
        if (left != null) return left;
        if (right != null) return right;

        return null;
    }
}
