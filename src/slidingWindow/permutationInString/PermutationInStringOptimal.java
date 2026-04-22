package slidingWindow.permutationInString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PermutationInStringOptimal {
    /**
     * На вход подаётся строка s1 и s2. Содержатся ли в s2, одна из перестановок s1?
     *
     * Идея: Символьный подсчёт + скользящее окно.
     *
     * Создаём и заполняем массив ch1 для s1, создаём пустой массив для s2. Назначаем левый указатель на начало.
     *
     * Для символа r:
     *  Заполняем частоту букв в массив ch2;
     *  Если длина подстроки равна размеру s1:
     *      Сравниваем массивы O(26) - по сути константа, если равны, то есть перестановка в слове s2 = true
     *  Иначе:
     *      Уменьшаем частоту символа на позиции l, и двигаем указатель l++
     *
     * Если не нашли, выводим false
     *
     * Сложность:
     *  по времени: O(n) O(n) - проход по строке + O(26) - сравнение массивов.
     *  по памяти: O(1) стек для хранения 26*2 символов
     *
     * @param s1 String
     * @param s2 String
     */
    public static boolean checkInclusion(String s1, String s2) {
        int [] ch1 = new int[26];
        int [] ch2 = new int[26];
        for (char c : s1.toCharArray()) {
            ch1[c - 'a']++;
        }
        int l = 0;
        for (int r = 0; r < s2.length(); r++) {
            ch2[s2.charAt(r) - 'a']++;
            if (r-l+1 == s1.length()) {
                if (Arrays.equals(ch1, ch2)) return true;
                else {
                    ch2[s2.charAt(l) - 'a']--;
                    l++;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "abc", a1 = "lecabee", a2 = "lecaabee";
        System.out.println(checkInclusion(s,a1)); //true
        System.out.println(checkInclusion(s,a2)); //false
    }
}
