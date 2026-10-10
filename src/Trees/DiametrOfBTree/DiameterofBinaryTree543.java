package Trees.DiametrOfBTree;

import Trees.TreeNode;

public class DiameterofBinaryTree543 {
    /**
     * https://leetcode.com/problems/diameter-of-binary-tree/
     *
     * Дано бинарное дерево root. Вернуть его его диаметр.
     * Диметр - максимальная длина дерева (важно учесть, диаметр может считаться не только от корня:
     * Например с крайнего левого листа -> корень -> до правого нижнего листа)
     * Оговорка, дерево [1, null, 2, 3, 4] - тут есть два ответа: 1->2->4 (длинна 3) 3->2->4 (длина 3)
     *
     * Идея:
     * Используем глобальную переменную, для поиска максимальной длины диаметра
     * Используем DFS, так как необходимо пройтись в глубь и собирать результат.
     *
     * Рекурсивно:
     *  Если корень == нул - вернём 0
     *
     *  //Высота - кол-во подряд идущих рёбер с низу вверх. Диаметр, мы можем идти: с лева низ -> корень -> правый низ.
     *  высота левого = dfs(root.left)
     *  высота правого = dfs(root.right)
     *
     *  Глобальный res = макс(res, сумма высот)
     *
     *  Вернём 1 + макс(высота левого, высота правого)
     *
     *
     * Применяем DFS к корню и возвращаем глобальный res.
     *
     *
     * Сложность:
     *  по времени: O(n) проходимся по всему дереву только 1 раз
     *  по памяти: O(h) максимальный стек - высота дерева
     *
     * @param word1 String
     * @param word2 String
     */
    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return this.res;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        this.res = Math.max(this.res, left + right);
        return 1 + Math.max(left, right);
    }

}
