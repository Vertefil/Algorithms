package Trees.sameBTree;

import Trees.TreeNode;

import java.util.Stack;

public class SameBTreeIterDFS {
    /**
     * Даны два дерева, определить они одинаковые или нет?
     *
     * Идея: проходимся по деревьям в одном порядке и сравниваем значения
     * Используем стек, чтобы двигаться по деревьям.
     *
     * Итеративно:
     *  Достаём из стека два узла
     *  Если два узла пустые, то след. итерация
     *  Если один из узлов пустой или значения в узлах не одинаковы -> false
     *  Добавляем в стек левых и правых детей.
     *
     * Сложность:
     *  по времени: O(n) Проходимся по всем узлам
     *  по памяти: O(h) h - высота дерева
     *
     * @param p TreeNode
     * @param q TreeNode
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        //ver 1
//        Stack<TreeNode> one = new Stack<>();
//        Stack<TreeNode> two = new Stack<>();
//        one.push(p);
//        two.push(q);
//        while (!one.isEmpty() && !two.isEmpty()) {
//            TreeNode oneNode = one.pop();
//            TreeNode twoNode = two.pop();
//            if (oneNode == null && twoNode == null) continue;
//            if (oneNode == null
//                    || twoNode == null
//                    || oneNode.val != twoNode.val
//            ) return false;
//            one.push(oneNode.left);
//            one.push(oneNode.right);
//            two.push(twoNode.left);
//            two.push(twoNode.right);
//        }
//        return true;

        //ver 2
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{p, q});

        while (!stack.isEmpty()) {
            TreeNode[] nodes = stack.pop();
            TreeNode node1 = nodes[0], node2 = nodes[1];

            if (node1 == null && node2 == null) continue;
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            stack.push(new TreeNode[]{node1.right, node2.right});
            stack.push(new TreeNode[]{node1.left, node2.left});
        }

        return true;
    }
}
