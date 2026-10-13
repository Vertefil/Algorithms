package YANDEX.tasks;

import Trees.TreeNode;

public class InsertIntoBST701 {
    /**
     * https://leetcode.com/problems/insert-into-a-binary-search-tree/
     *
     * Дано бинарное дерево поиска (слева меньше, справа больше)
     * Главное дерево и число, которое необходимо вставить - val
     *
     * Идея: Пройтись по дереву рекурсивно вглубь, сравнивая значения и вставить узел на нужную позицию.
     * Так как нам требуется обновить дерево, мы будет левого или правого ребёнка обновлять в зависимости от val.
     * Как итог когда мы дойдём до нижнего листа, мы его поставим на позицию null слева или справа.
     *
     * После вернём сам корень с вставленным числом.
     *
     * Рекурсивно:
     *  Если узел пустой, создаём новый узел с val и возращаем его
     *  Если val > текущ узел: переобозначаем правого ребёнка, на новый обновлённый узел после рекурсии
     *  Если val < текущ узел: переобозначаем левого ребёнка, на новый обновлённый узел после рекурсии
     *
     * возвращаем корень.
     *
     * Сложность:
     *  по времени: O(h) h - высота дерева
     *  по памяти: O(h)
     *
     * @param root TreeNode
     * @param val int
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val > root.val) root.right = insertIntoBST(root.right, val);
        if (val < root.val) root.left = insertIntoBST(root.left, val);
        return root;
    }

    //Аналогичная идея, но итеративно
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        TreeNode node = new TreeNode(val, null, null);
        if (root == null) return node;
        TreeNode curr = root;
        while(true) {
            if (val > curr.val) {
                if (curr.right == null) {
                    curr.right = node;
                    return root;
                }
                curr = curr.right;
            } else {
                if (curr.left == null) {
                    curr.left = node;
                    return root;
                }
                curr = curr.left;
            }
        }
    }
}
