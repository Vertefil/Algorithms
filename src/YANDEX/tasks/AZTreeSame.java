package YANDEX.tasks;


import java.util.*;
class TreeNodeAZ {
    public Character val;
    public TreeNodeAZ left;
    public TreeNodeAZ right;

    public TreeNodeAZ() {
    }

    public TreeNodeAZ(Character val) {
        this.val = val;
    }

    public TreeNodeAZ(Character val, TreeNodeAZ left, TreeNodeAZ right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class AZTreeSame {
    /*
    // Дано бинарное дерево с выделенным корнем, в каждой вершине которого записано по одной букве A-Z.
    // Две вершины считаются эквивалентными, если поддеревья этих вершин содержат одинаковое множество
    // (т.е. без учета частот) букв.

          A
        /   \
       X+    B+
      / \   / \
      F D   F  D
     /          \
    B            X
                  \
                   D

          A
        /   \
       X     B+
      / \    / \
     F+  D  F   B
     /          \
    B            B

         X
        /
       Y+
      /
    Y+

      X+
     /
    X+

    dfs возвращает Set<Character> - множество букв в поддереве с корнем node.
    Для текущего узла создаём новый Set, добавляем букву самого узла, затем addAll множеств левого и правого поддеревьев.
    addAll сам разберётся с дубликатами - это и есть «объединение без частот».
    Кладём полученное множество в HashMap как ключ.
    Если такой ключ уже есть - значит, есть другая вершина с тем же множеством.
    computeIfAbsent добавляет текущий узел в список группы.
    В конце проходим по всем значениям мапы: если в группе 2 и более вершин — это эквивалентные.
     */
    public List<List<TreeNodeAZ>> findEquivalentSubtrees(TreeNodeAZ root) {
        Map<Set<Character>, List<TreeNodeAZ>> map = new HashMap<>();
        dfs(root, map);

        List<List<TreeNodeAZ>> res = new ArrayList<>();
        for (List<TreeNodeAZ> group : map.values()) {
            if (group.size() >= 2) res.add(group);
        }
        return res;
    }

    private Set<Character> dfs(TreeNodeAZ node, Map<Set<Character>, List<TreeNodeAZ>> map) {
        if (node == null) return new HashSet<>();

        Set<Character> set = new HashSet<>();
        set.add(node.val);
        set.addAll(dfs(node.left, map));
        set.addAll(dfs(node.right, map));

        map.computeIfAbsent(set, k -> new ArrayList<>()).add(node);
        return set;
    }
}
