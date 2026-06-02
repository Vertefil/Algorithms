package Trees.subTreeOfBTree;

import Trees.TreeNode;

import java.util.Stack;

public class SubTreeOptimal {
    /**
     * Даны два дерева, определить является ли второе дерево под деревом первого?
     *
     * Идея: проходимся по деревьям и составляем строку из них формата "1,2,null,3";
     * Если находится подстрока, то под дерево есть, иначе нет
     *
     * Итеративно:
     *  Если под дерево пустое, то оно есть в основном -> true
     *  Если основное дерево пустое -> false
     *
     *  Пока стек не пуст:
     *      Если узел пустой, то добавляем нул и двигаемся дальше без добавление детей
     *      Если не пустой, добавляем правого, затем левого, чтобы сначала проверили всех левых, потом правых
     *
     *  Если есть вхождение строки, то нашли, иначе нет.
     *
     * Сложность:
     *  по времени: O(n+m) n кол-во узлов в root, m кол-во узлов в subRoot
     *  по памяти: O(n+m)
     *
     * @param root TreeNode
     * @param subRoot TreeNode
     */
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) return true;
        if (root == null) return false;
        String mainTree = preorderTraversal(root);
        String subTree = preorderTraversal(subRoot);
        return mainTree.contains(subTree);
    }

    public static String preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node == null) {
                sb.append("null,");
                continue;
            }
            sb.append(node.val).append(",");
            stack.push(node.right);
            stack.push(node.left);
        }
        return sb.toString();
    }
}
