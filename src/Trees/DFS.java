package Trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DFS {
    /**
     * Дано дерево вывести в формате [1,2,3...] обход дерева в глубину
     *
     * Идея: Использовать обход в глубину
     * Рекурсивно preorder (сначала корень, лево, право)
     * Итеративно preorder
     *
     * Сложность:
     *  по времени: O(n)
     *  по памяти: O(n)
     *
     * @param root TreeNode
     */
    public static void RECpreorderDFS(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        RECpreorderDFS(root.left, res);
        RECpreorderDFS(root.right, res);
    }

    public static List<Integer> ITERpreorderDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> deq = new ArrayDeque<>();
        deq.push(root);
        while(!deq.isEmpty()) {
            TreeNode node = deq.pop();
            res.add(node.val);
            if (node.right != null) deq.push(node.right);
            if (node.left != null) deq.push(node.left);
        }
        return res;
    }

    public static void RECinorderDFS(TreeNode root, List<Integer> res) {
        if (root == null) return;
        RECinorderDFS(root.left, res);
        res.add(root.val);
        RECinorderDFS(root.right, res);
    }

    public static List<Integer> ITERinorderDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> deq = new ArrayDeque<>();
        TreeNode curr = root;
        while(curr != null || !deq.isEmpty()) {
            while (curr != null) {
                deq.push(curr);
                curr = curr.left;
            }
            curr = deq.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    public static void RECpostorderDFS(TreeNode root, List<Integer> res) {
        if (root == null) return;
        RECinorderDFS(root.left, res);
        RECinorderDFS(root.right, res);
        res.add(root.val);
    }

    public static void main(String[] args) {

    }
}
