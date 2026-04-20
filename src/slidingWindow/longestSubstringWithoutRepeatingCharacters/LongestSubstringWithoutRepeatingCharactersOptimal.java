package slidingWindow.longestSubstringWithoutRepeatingCharacters;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharactersOptimal {
    /**
     * На вход подаётся строка из символов. Найти самую длинную последовательность символов без повторения.
     *
     * Идея: Используем метод скользящего окна и сет для проверки символов в окне.
     * Ставим два указателя l = 0, его мы двигаем в ручную, а r будет двигаться по циклу.
     *
     * Для символа r:
     *  Пока в окне находится дубликат буквы:
     *      Удаляем из множества самый левый символ из окна и двигаем левый указатель.
     *      т.о. получаем окно, в котором нет дубликатов, и сохраняем информацию о последовательности.
     *  Вышли из цикла while, значит буква новая, добавляем в сет и находим максимум между вычислениями.
     *
     * Сложность:
     *  по времени: O(n) проход + O(1) проверка в сет
     *  по памяти: O(n) стек для хранения символов
     *
     * @param s String
     */
    public static int lengthOfLongestSubstring(String s) {
        int l = 0, res = 0;
        Set<Character> set = new HashSet<>();
        char[] c = s.toCharArray();
        for(int r = 0; r < c.length; r++) {
            while (set.contains(c[r])) {
                set.remove(c[l]);
                l+=1;
            }
            set.add(c[r]);
            res = Math.max(res, r - l +1);
        }
        return res;
    }
}
