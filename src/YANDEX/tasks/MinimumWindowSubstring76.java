package YANDEX.tasks;

import java.util.HashMap;

public class MinimumWindowSubstring76 {
    /**
     * https://leetcode.com/problems/minimum-window-substring/description/
     *
     * Учитывая две строки s и t длин m и n соответственно, верните подстроку s, чтобы t был включен в окно
     * Если такой подстроки нет, верните пустую строку "".
     *
     * Входные данные: s = "ADOBECODEBANC", t = "ABC"
     * Выходные данные: "BANC"
     * Пояснение: Минимальная подстрока окна "BANC" включает в себя символы "A", "B" и "C" из строки t.
     *
     * Входные данные: s = "a", t = "a"
     * Выходные данные: "a"
     * Пояснение: Вся строка s является минимальным окном.
     *
     * Входные данные: s = "a", t = "aa"
     * Выходные данные: ""
     * Пояснение: Оба символа 'a' из t должны быть включены в окно.
     *  Поскольку в самом большом окне s только один символ 'a', верните пустую строку.
     *
     * Идея:
     * need = кол-во необходимых символов.
     * Заполняем мапу необходимыми символами и частотой: 'a' - 1. Значит необходим один символ 'a'.
     *
     * Надо, чтобы в мапе были значения 'a' = 0 - значит собрали необходимое кол-во символов
     *
     * В цикле r < s.length():
     *  Если символ содержится в мапе и нам необходимо ещё:
     *      need--
     *
     *  Кладём в мапу символ и помечаем, что он у нас есть, т.е. минусуем из мапы.
     *  (для символов которые не содержатся в строке t, значения будут (-inf; 0],
     *  так как они нам не нужны и нет в них дефицита)
     *
     *  Пока need = 0: (значит в текущем окне есть все необходимые символы)
     *      Если текущее окно наименьшее:
     *          обновляем минимум и записываем пару
     *
     *      Если символ содержится в мапе и его значение == 0 (значит он нам нужен)
     *          Выходим из цикла
     *
     *      в мапу добавляем дефицит символа левого указ.
     *      (для лишних символов значение будет 0, после проверки и удаления его из окна)
     *
     *      Если дефицит символа > 0:
     *          need++;
     *
     *      l++;
     *
     *  После всех циклов,
     *  Если минимум не поменялся, значит не нашли ни одного вхождения - return "";
     *  Возвращаем подстроку из наименьших пар. pair[0], pair[1] + 1 (так как нужно вхождение r);
     *
     * Сложность:
     *  по времени: O(n) один проход по строке s
     *  по памяти: O(1)
     *
     * @param s String
     * @param t String
     */
    public String minWindow(String s, String t) {
        if (t.isEmpty() || s.length() < t.length()) return "";
        HashMap<Character, Integer> countT = new HashMap<>();
        int need = t.length();
        for(char c: t.toCharArray()) {
            countT.put(c, countT.getOrDefault(c, 0) + 1);
        }

        int l = 0;
        int[] pair = new int[2];
        int min = Integer.MAX_VALUE;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            if (countT.containsKey(c) && countT.get(c) > 0) {
                need--;
            }
            countT.put(c, countT.getOrDefault(c, 0) - 1);

            while (need == 0) {
                if (min > r - l + 1) {
                    min = r - l + 1;
                    pair[0] = l;
                    pair[1] = r;
                }

                char cl = s.charAt(l);
                if (countT.containsKey(cl) && countT.get(cl) == 0) break;

                countT.put(cl, countT.getOrDefault(cl, 0) + 1);

                if (countT.get(cl) > 0) need++;

                l++;
            }
        }
        if (min == Integer.MAX_VALUE) return "";
        return s.substring(pair[0], pair[1] + 1);
    }
}
