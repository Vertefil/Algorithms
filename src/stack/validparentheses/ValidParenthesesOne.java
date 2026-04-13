package stack.validparentheses;

import java.util.Stack;

public class ValidParenthesesOne {
    /**
     * На вход подаётся строка состоящая из скобок. Нужно проверить, правильно ли закрываются скобки.
     *
     * Идея: В данной реализации, когда мы встречаем открывающуюся скобку, мы кладём в стек закрывающую.
     * Таким образом мы сохраняем порядок закрытия скобок в строке.
     * Когда встречаем закрывающую скобку, мы берём верхнюю скобку с стека и сравниваем.
     * Если скобки не совпали, то нарушилось правило закрытие скобок.
     * Если после всех проверок у нас что-то осталось в стеке, значит скобки не закрылись.
     *
     * Сложность O(n) - используется один цикл.
     * Память O(n) - используем стек для хранения.
     *
     * @param s String
     */
    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(Character c : s.toCharArray()) {
            if(c == '(') st.push(')');
            else if (c == '[') st.push(']');
            else if (c == '{') st.push('}');
            else {
                if(st.empty() || st.pop() != c) return false;
            }
        }
        return st.empty();
    }
}
