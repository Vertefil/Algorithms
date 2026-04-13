package stack.validParentheses;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParenthesesTwo {
    /**
     * На вход подаётся строка состоящая из скобок. Нужно проверить, правильно ли закрываются скобки.
     *
     * Идея: В данной реализации используется карта в которой ключ - закрывающая скобка, значение - открывающаяся.
     * Если встречаем закрывающую скобку и стек не пуст, то берём с верхушки стека скобку и проверяем.
     * Если скобка из стека не совпадает с значением мапы по ключу закрытой скобки, значит правило нарушено - false.
     * Если это не закрывающая скобка или она идёт первой мы просто добавляем её в стек и продолжаем алгоритм.
     * Если скобки не совпали или стек не пустой, значит правило было нарушено - false. Иначе если стек пустой - true
     *
     * Сложность O(n) - используется один цикл.
     * Память O(n) - используем стек для хранения.
     *
     * @param s String
     */
    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        Map<Character, Character> map = new HashMap<>(Map.of(')', '(', ']', '[', '}', '{'));
        for(Character c : s.toCharArray()) {
            if(map.containsKey(c) && !st.empty()) {
                if(st.pop() != map.get(c)) return false;
            } else {
                st.push(c);
            }
        }
        return st.empty();
    }
}
