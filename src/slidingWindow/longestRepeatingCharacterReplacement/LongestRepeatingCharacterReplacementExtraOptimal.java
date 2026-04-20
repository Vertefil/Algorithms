package slidingWindow.longestRepeatingCharacterReplacement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestRepeatingCharacterReplacementExtraOptimal {
    /**
     * На вход подаётся строка из заглавных символов.
     * Найти самую длинную последовательность одинаковых символов с возможностью заменой K раз.
     *
     * Идея: Используем мапу для хранения символом и их кол-во в подстроке и скользящее окно.
     * Основная идея в переменной maxf, которая показывает максимальную доступную частоту символа.
     * Вместо того, чтобы проходится по алфавиту и высчитывать максимум для каждого и потом общий максимум.
     * Мы идём по строке и двигаем окно до тех пор, пока не найдём символ, который встречается больше maxf.
     *
     * Для символа r:
     *  Заполянем карту или увеличиваем частоту символа r
     *  Обновляем maxf, если символ r встречался чаще, остальных
     *
     *  Пока длина подстроки (r - l + 1) - maxf больше, чем кол-во замен:
     *      Уменьшаем частоту символа на позиции l
     *      Двигаем указатель l++
     *
     *  Вычисляем максимальную подстроку
     *
     * Сложность:
     *  по времени: O(n) проход по строке
     *  по памяти: O(n) мапа для хранения
     *
     * @param s String
     * @param k int
     */
    public static int characterReplacement(String s, int k) {
        Map<Character, Integer> count = new HashMap<>();
        int res = 0, maxf = 0, l = 0;
        for (int r = 0; r < s.length(); r++) {
            count.put(s.charAt(r), count.getOrDefault(s.charAt(r), 0) + 1);
            maxf = Math.max(maxf, count.get(s.charAt(r)));
            while ((r - l + 1) - maxf > k) {
                count.put(s.charAt(l), count.get(s.charAt(l)) - 1);
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

}
