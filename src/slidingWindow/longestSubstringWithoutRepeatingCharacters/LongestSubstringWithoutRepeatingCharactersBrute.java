package slidingWindow.longestSubstringWithoutRepeatingCharacters;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharactersBrute {
    /**
     * На вход подаётся строка из символов. Найти самую длинную последовательность символов без повторения.
     *
     * Идея: Использовать сет, для быстрой проверки символа в текущей подстроке. Обнулять сет на каждой итерации.
     *
     * Для символа i:
     *  Создаём пустой сет, временный счётчик и итератор j.
     *  Пока j < длина массива символов и буква ещё не встречалась
     *      Временный счётчик ++, добавляем новый символ в сет, итератор j++
     *  Вышли из цикла j, находим максимум между временной переменной и результатом.
     *
     * Сложность:
     *  по времени: O(n^2) в худшем случае + O(1) проверка в сет
     *  по памяти: O(n) стек для хранения символов
     *
     * @param s String
     */
    public static int lengthOfLongestSubstring(String s) {
        int res = 0;
        if(s.isEmpty()) return 0;
        char[] c = s.toCharArray();
        for(int i = 0; i < c.length; i++) {
            Set<Character> set = new HashSet<>();
            int temp = 0;
            int j = i;
            while(j < c.length && !set.contains(c[j])) {
                set.add(c[j]);
                temp++;
                j++;
            }
            res = Math.max(res, temp);
        }
        return res;
    }
}
