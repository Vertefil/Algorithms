package slidingWindow.longestRepeatingCharacterReplacement;

import java.util.HashSet;
import java.util.Set;

public class LongestRepeatingCharacterReplacementOptimal {
    /**
     * На вход подаётся строка из заглавных символов.
     * Найти самую длинную последовательность одинаковых символов с возможностью заменой K раз.
     *
     * Идея: Используем сет для итерации и метод скользящего окна для поиска максимума.
     * Ставим два указателя l = 0, его мы двигаем в ручную, а r будет двигаться по циклу.
     *
     * Проходимся по всем символам из сет:
     *  Для символа r:
     *      Если символ на позиции r - это символ из сета, то счётчик ++
     *
     *      Пока длина подстроки (r-l+1) - счётчик (count), больше чем кол-во замен:
     *          Если символ на позиции l - символ из сета, то счётчик --
     *          Двигаем указатель слева.
     *
     *      Находим максимум из вычислений
     *
     * Сложность:
     *  по времени: O(n) = O(26*n) проход по сету и по строке
     *  по памяти: O(n) стек для хранения символов
     *
     * @param s String
     * @param k int
     */
    public static int characterReplacement(String s, int k) {
        Set<Character> set = new HashSet<>();
        int res = 0;

        for (char c : s.toCharArray()){
            set.add(c);
        }

        for (char c : set) {
            int count = 0, l = 0;
            for (int r = 0; r < s.length(); r++) {
                if(s.charAt(r) == c) count++;
                while ((r - l + 1) - count > k) {
                    if (s.charAt(l) == c) count--;
                    l++;
                }
                res = Math.max(res, r - l + 1);
            }
        }
        return res;
    }
}
