package Trees.validBST;

import Trees.TreeNode;

public class DFSValidBST {
    /**
     * Дано дерево. Является ли оно бинарным деревом поиска?
     * Корректное бинарное дерево поиска удовлетворяет следующим ограничениям:
     *
     *  левое поддерево каждого узла содержит только узлы с ключами, меньшими, чем ключ узла;
     *  правое поддерево каждого узла содержит только узлы с ключами, большими, чем ключ узла;
     *  и левое, и правое под деревья также являются бинарными деревьями поиска.
     *
     * Идея: Использовать границы для деревьев. Поиск в глубину.
     * Если под дерево не подходит под границы, значит это не двоичное дерево поиска.
     * В самом начале в функцию передаём дерево, MIN, MAX;
     *
     * Рекурсивно:
     *  Если под дерево пустое -> true
     *  Если значение узла больше максимума или меньше минимума -> false
     *  Возвращаем рекурсию для левого дерева, с обновлённым max в виде текущего узла
     *  && Возвращаем рекурсию для правого дерева, с обновлённым min в виде текущего узла
     *
     * Сложность:
     *  по времени: O(n) n кол-во узлов в root
     *  по памяти: O(n)
     *
     * @param root TreeNode
     */
    public boolean isValidBST(TreeNode root) {
        return isValidSubTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValidSubTree(TreeNode root, int min, int max) {
        if (root == null) return true;
        if (!(root.val < max && root.val > min)) return false;
        return isValidSubTree(root.left, min, root.val) && isValidSubTree(root.right, root.val, max);
    }
}
