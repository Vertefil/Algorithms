package YANDEX.tasks;

import java.util.HashMap;

public class NumberofSubstringsContainingAllThreeCharacters1358 {
    /**
     * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
     * Дана строка s состоящих из букв a,b,c. Вывести кол-во подстрок  из s, в которых содержатся все 3-и буквы: a,b,c.
     *
     * Input: s = "abcabc"
     * Output: 10
     * Explanation: The substrings containing at least one occurrence of the characters a, b and c
     * are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
     *
     * Input: s = "aaacb"
     * Output: 3
     * Explanation: The substrings containing at least one occurrence of the characters a, b and c are
     * "aaacb", "aacb" and "acb".
     *
     * Input: s = "abc"
     * Output: 1
     *
     * Идея: Два указателя и проверка на условие и HashMap.
     * Вводим мапу частот map<Character, Integer>
     * res = 0 (сумма всех строк)
     * Вводим два указателя:
     * l = 0, r = 0
     * В цикле, пока r < nums.length:
     *  Помещаем символ на прав указ в мапу (если такой уже есть, увеличиваем его счётчик)
     *
     *  Пока размер мапы == 3:
     *      Считаем результат: Длинна s - индекс прав указ.
     *      Так как мы собрали подстроку с всеми 3-я символам, которые встречаются хотя бы 1 раз.
     *      Можно сказать, что остаток слова от прав указ до s.length() - 1, подходящие подстроки.
     *      Так как нужно посчитать текущую подстроку + остаток до конца строки мы добавляем в сумму:
     *      res += s.length() - r
     *
     *      Далее нам необходимо подвинуть левый указ, чтобы найти новые валидные подстроки:
     *      уменьшаем кол-во символов по лев указ из мапы.
     *      Если кол-во символов после уменьшения == 0, мы его удаляем из мапы и после сдвига лев указ - выйдем из цикла
     *      Двигаем лев указ.
     *
     * Выводим res.
     *
     * Сложность:
     *  по времени: O(n)
     *  по памяти: O(1)
     *
     * @param s String
     */
    public int numberOfSubstrings(String s) {
        int l = 0;
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int r = 0; r < s.length(); r++) {
            map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) + 1);
            while (map.size() == 3) {
                res += (s.length() - r);
                map.put(s.charAt(l), map.get(s.charAt(l)) - 1);
                if (map.get(s.charAt(l)) == 0) map.remove(s.charAt(l));
                l++;
            }
        }
        return res;
    }
}
