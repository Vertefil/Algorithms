package Trees.invertBTree;

import Trees.TreeNode;

import java.util.Stack;

public class InvertBTreeIterDFS {
    /**
     * Дано дерево, надо его инвертировать
     *
     * Идея: На каждом шаге менять левого и правого ребёнка и идти дальше
     * Проходимся в глубь, так как стек
     *
     * Базовый случай: значение корня == null -> null
     * Заполним стек изначальным деревом
     *
     * Пока стек не пустой:
     *
     *  tmp для левого ребёнка
     *  Меняем левого на правого
     *  Меняем правого на tmp
     *
     *  Если левый ребёнок не пустой, то добавляем его в стек;
     *  Если правый ребёнок не пустой, то добавляем его в стек;
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
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return root;
    }
}
