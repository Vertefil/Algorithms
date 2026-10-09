package YANDEX.tasks;

import java.util.Arrays;
import java.util.HashMap;

public class CustomSortString791 {
    /**
     * https://leetcode.com/problems/custom-sort-string/description/
     * Вам даны две строки order и s. Все символы order уникальны и ранее были отсортированы в произвольном порядке.
     * Переставьте символы s так, чтобы они соответствовали порядку сортировки order.
     * В частности, если символ x встречается в y раньше символа order,
     * то в переставленной строке x должен встречаться раньше y.
     * Вернуть любую перестановку s удовлетворяющую этому свойству.
     *
     * order = "cba", s = "abcd"
     * Output: "cbad"
     * Пояснение: "a", "b", "c" идут в правильном порядке, поэтому порядок "a", "b", "c" должен быть "c", "b" и "a".
     * Поскольку "d" не встречается в order, она может находиться в любом месте возвращаемой строки.
     * Валидные строки: "dcba", "cdba", "cbda" являются допустимыми вариантами.
     *
     * order = "bcafg", s = "abcd"
     * Output: "bcad"
     * Пояснение: Символы "b", "c" и "a" из order определяют порядок следования символов в s.
     * Символ "d" в s не встречается в order, поэтому его позиция может быть любой.
     * В соответствии с порядком следования в order, "b", "c" и "a" из s должны быть расположены в следующем порядке:
     * "b", "c", "a". "d" может стоять в любом месте, так как его порядок не важен.
     * Результат "bcad" соответствует этому правилу.
     * Другие варианты, такие как "dbca" или "bcda" также допустимы, если "b", "c", "a" сохраняют свой порядок.
     *
     * Идея: Мапа частот и проход по циклу
     * Вводим мапу частот, так как у нас англ строчные символы - это int[26];
     *
     * StringBuilder sb - для посимвольного построения строки.
     * Пройдёмся по строке s и запишем кол-во вхождений каждого символа в строке.
     *
     * Так как нам нужен порядок, мы будет проходится по строке order,
     * таким образом мы будем идти в отсортированном порядке строки order
     *
     * Проходимся посимвольно по строке order:
     *  Если в мапе частот есть символ из строки order и его кол-во больше 0:
     *  Входим в цикл, добавляем символ и уменьшаем кол-во в мапе
     *
     * После прохождения у нас получится отсортированная по order строка
     * Проходимся по мапе частот и добавляем в конец строки, оставшиеся символы из мапы
     *
     * Выводим sb.toString() -> String
     *
     * Сложность:
     *  по времени: O(n)
     *  по памяти: O(1)
     *
     * @param s String
     */
    public static String customSortString(String order, String s) {
        int[] freq = new int[26];
        for (char c: s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (char c: order.toCharArray()) {
            while (freq[c - 'a'] > 0) {
                sb.append(c);
                freq[c - 'a']--;
            }
        }
        for (int i = 0; i < 26; i++) {
            while (freq[i] > 0) {
                sb.append((char)('a' + i));
                freq[i]--;
            }
        }
        return sb.toString();
    }

    //BruteForce O(m+n log n) and O(N) space
    public static String customSortString1(String order, String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
            System.out.println(order.charAt(i) + " " + i);
        }
        int[] numeric = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                numeric[i] = map.get(s.charAt(i));
            } else {
                numeric[i] = 999 + i;
            }
        }
        Arrays.sort(numeric);
        StringBuilder sb = new StringBuilder();
        for (int num: numeric) {
            System.out.println("Flag = " + (num < 999));
            if (num < 999) {
                sb.append(order.charAt(num));
                System.out.println(num + " " + order.charAt(num));
            } else {
                sb.append(s.charAt(num - 999));
                System.out.println(num + " " + s.charAt(num - 999));
            }

        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String or1 = "abcd";
        String s1 = "cba";
        System.out.println(customSortString(or1, s1));
        System.out.println(customSortString1(or1, s1));
    }
}
