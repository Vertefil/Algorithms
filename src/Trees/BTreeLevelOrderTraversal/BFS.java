package Trees.BTreeLevelOrderTraversal;

import Trees.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    /**
     * Дано дерево вывести в формате [[1],[2,3]...] уровни дерева
     *
     * Идея: Использовать обход в ширину
     *
     * Итеративно:
     *  Берём первого из очереди, записываем в временный массив.
     *  Добавляем его детей в очередь идём дальше
     *
     * Сложность:
     *  по времени: O(n)
     *  по памяти: O(n)
     *
     * @param root TreeNode
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<List<Integer>> res = new ArrayList<>();
        while (!q.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = q.size(); i > 0; i--) {
                TreeNode node = q.poll();
                tmp.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            res.add(tmp);
        }
        return res;
    }
}
