package YANDEX.tasks;

import Trees.TreeNode;

public class DeleteNodeInBST450 {
    /**
     * https://leetcode.com/problems/delete-node-in-a-bst/
     *
     * Дано бинарное дерево поиска (слева меньше, справа больше)
     * Главное дерево и число, которое необходимо удалить - key
     *
     * Идея: Пройтись по дереву рекурсивно вглубь, сравнивая значения и удалить нужный узел.
     * Так как нам требуется обновить дерево, мы будет левого или правого ребёнка обновлять в зависимости от key.
     * Как итог когда мы дойдём до нужного узла будет три варианта:
     * 1 - Нет детей, мы просто возвращаем null
     * 2 - Есть один из детей, тогда мы возвращаем его
     * 3 - Есть оба ребёнка и одного\обоих есть дети
     *
     * После вернём сам корень с вставленным числом.
     *
     * Рекурсивно:
     *  Если узел пустой, возращаем root;
     *  Если key > текущ узел: переобозначаем правого ребёнка, на новый обновлённый узел после рекурсии
     *  Если key < текущ узел: переобозначаем левого ребёнка, на новый обновлённый узел после рекурсии
     *  Если key == текущ узел:
     *      1 и 2 случай - назначаем возможного ребёнка слева или справа, если они оба null, всё равно вернём null;
     *
     *      3 случай:
     *          Найти минимум справа, поставить его на текущий узел
     *          вызвать рекурсивно функцию удаления, но уже по текущему (минимальному) узлу.
     *
     *
     * возвращаем корень.
     *
     * Сложность:
     *  по времени: O(h) h - высота дерева
     *  по памяти: O(h) h - стек вызова
     *
     * @param root TreeNode
     * @param key int
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (key > root.val) root.right = deleteNode(root.right, key);
        else if (key < root.val) root.left = deleteNode(root.left, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            // find min in root.left
            TreeNode cur = root.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            root.val = cur.val;
            root.right = deleteNode(root.right, root.val);
        }

        return root;
    }
}
