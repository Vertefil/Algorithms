package Trees.constructBTreeWithOrderPreorder;

import Trees.TreeNode;

import java.util.HashMap;

public class ConstructBTreeWithOrderPreorder {
    /**
     * Даны два массива:
     * 1 - прямой проход по дереву
     * 2 - обратный проход по дереву
     * Вернуть корень дерева
     *
     * Идея: Первое значение массива 1, всегда будет корнем
     * Зная начальный корень, мы можем массив обратного порядка поделить на две части
     * Слева - левая часть дерева, Справа - правая часть.
     * Для обозначения этих границ используем левые и правые указатели.
     * Используем мапу, для быстрого нахождения значений.
     *
     * Заполняем мапу и рекурсивно вызываем функцию.
     * Рекурсивно:
     *  Если левый дошёл до правого - выходим.
     *
     *  Значение узла - глобальный индекс, потом он увеличивается на +1;
     *  Создаём узел с значением
     *  Указатель на центр - значение узла.
     *  Повторяем для левого узла, но правый указатель mid - 1;
     *  Повторяем для правого узла, но левый указатель mid + 1;
     *
     * Сложность:
     *  по времени: O(n) n кол-во узлов в root
     *  по памяти: O(n)
     *
     * @param preorder int[]
     * @param inorder int[]
     */
    int preIdx = 0;
    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
        return dfs(preorder, 0, inorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int l, int r) {
        if (l > r) return null;
        int rootVal = preorder[preIdx++];
        TreeNode root = new TreeNode(rootVal);
        int mid = map.get(rootVal);
        root.left = dfs(preorder, l, mid - 1);
        root.right = dfs(preorder, mid + 1, r);
        return root;
    }
}
