package Trees.subTreeOfBTree;

import Trees.TreeNode;

import java.util.Stack;

public class SubTreeIterDFS {
    /**
     * Даны два дерева, определить является ли второе дерево под деревом первого?
     *
     * Идея: проходимся по первому дереву и вызываем рекурсивную функцию проверки одинаковых деревьев.
     * Если не равны, то вызываем основную функцию к левому и потом к правому ребёнку.
     * Результатом будет наличие под дерева слева или справа.
     *
     * Итеративно:
     *  Если под дерево пустое, то оно есть в основном -> true
     *  Если основное дерево пустое -> false
     *  Если основное дерево и поддерево одинаковы -> true
     *
     *  Пока стек не пуст:
     *      Если хотя бы один ребёнок равен второму дереву -> true
     *      Добавляем левого и правого, если не пустые
     *  Если не нашли -> false
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
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isSameTree(node, subRoot)) return true;
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return false;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{p,q});
        while (!stack.isEmpty()) {
            TreeNode[] nodes = stack.pop();
            TreeNode nodeOne = nodes[0], nodeTwo = nodes[1];
            if (nodeOne == null && nodeTwo == null) continue;
            if (nodeOne == null || nodeTwo == null || nodeOne.val != nodeTwo.val) {
                return false;
            }
            stack.push(new TreeNode[] {nodeOne.left, nodeTwo.left});
            stack.push(new TreeNode[] {nodeOne.right, nodeTwo.right});
        }
        return true;
    }
}
