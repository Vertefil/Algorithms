package Trees.BTreeLevelOrderTraversal;

import Trees.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DFSByLevel {
    /**
     * Дано дерево вывести в формате [[1],[2,3]...] уровни дерева
     *
     * Идея: Использовать обход в глубину с запоминанием уровня на котором находимся.
     * Выносим результирующий список вне метода.
     *
     * Рекурсивно:
     *  Начинаем с нулевой глубины.
     *  Создаём под массив, если размер массива равен глубине на которой мы находимся.
     *
     *  Запрашиваем массив по индексу глубину и вставляем туда числа
     *  Т.о. мы проходимся сначала слева, создавая массивы на всех уровнях и внося туда самые левые числа
     *  Потом уже запрашиваем созданные массивы и в зависимости от глубины добавляем в нужный массив числа.
     *
     * Сложность:
     *  по времени: O(n)
     *  по памяти: O(n)
     *
     * @param root TreeNode
     */
    public List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    public void dfs(TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        if (res.size() == depth) {
            res.add(new ArrayList<>());
        }

        res.get(depth).add(node.val);
        dfs(node.left, depth + 1);
        dfs(node.right, depth + 1);
    }
}
