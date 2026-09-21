package YANDEX.tasks;

public class OneEditDistance640 {
    /**
     * https://www.lintcode.com/problem/640/description
     *
     * Даны две строки S и T.
     * ab abc - true
     * abc bc - true
     * abc abc - false
     * abcde abce - true
     * aDb adb - true
     * abcd ab = false
     * "" "" = false
     *
     * Можем ли мы используя одну операцию, переделать строку S -> T
     * Операции
     *  Удаление символа из любого места строки S
     *  Добавление символа в любое место строки S
     *  Замена любого символа в строке S
     *
     * Идея:
     * В самом начале проверяем, что строки не одинаковые или не длинее друг друга на 1 символ.
     *
     * 1 случай - строки одинаковые, проверяем замену:
     *  Инит счётчик различий
     *  В цикле по строке S:
     *      Если символы не совпали, счётчик++
     *      Если счётчик > 1 -> false;
     * 2 случай - строки отличаются на 1:
     *  В данном случае мы будем, либо удалять, либо добавлять букву.
     *  Инит длинную и коротку строку.
     *  Инит i = 0 (счётчик для короткой строки)
     *  Инит index = 0 (счётчик для длинной строки)
     *  Инит diff = 0 (счётчик различий)
     *
     *  Пока i < short.length && index < long.length:
     *      Если символы одинаковы, увеличиваем оба счётчика.
     *      Иначе увеличиваем счётчик длинной подстроки (значит надо удалить символ) и diff++
     *      Если diff > 1 значит надо удалить больше 1 символа -> false
     *
     * Если раньше не вышли, значит diff <= 1 -> одно удаление/вставка -> true;
     *
     * Сложность:
     *  по времени: O(n) проход по самой длиной строке
     *  по памяти: O(n)
     *
     * @param s String
     * @param t String
     */
    public boolean isOneEditDistance(String s, String t) {
        if (s.equals(t) || Math.abs(s.length() - t.length()) > 1) return false;
        if (s.length() == t.length()) {
            int diff = 0;
            for (int i = 0; i < s.length(); i++) {
                if (diff > 1) return false;
                if (s.charAt(i) != t.charAt(i)) diff++;
            }
        } else {
            String shortStr = s.length() < t.length() ? s: t;
            String longStr = t.length() > s.length() ? t: s;
            int i = 0;
            int index = 0;
            int diff = 0;
            while (i < shortStr.length() && index < longStr.length()) {
                if (shortStr.charAt(i) == longStr.charAt(index)) {
                    i++;
                    index++;
                } else {
                    diff++;
                    index++;
                    if (diff > 1) return false;
                }
            }
        }

        return true;
    }
}
