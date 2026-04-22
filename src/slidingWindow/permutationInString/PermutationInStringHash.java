package slidingWindow.permutationInString;

import java.util.HashMap;
import java.util.Map;

public class PermutationInStringHash {
    /**
     * На вход подаётся строка s1 и s2. Содержатся ли в s2, одна из перестановок s1?
     *
     * Идея: Использовать хеш мапу для проверки наличия кол-ва символа в подстроке, представляющий собой хеш-мапу.
     *
     * Создаём и заполняем мапу символов для перестановок chrs.
     *
     * Для символа l:
     *  Создаём пустую мапу sub и обнуляем счётчик.
     *  Для символа r = l и r < длина строки
     *      Увеличиваем частоту символа s2[r]++
     *      Если частота символа в chrs < частота в sub, значит такого символа нет или перебор
     *      Если частота символов в chrs == частоте в sub, счётчик++;
     *      Если счётчик == размеру массива chrs, значит мы нашли перестановку - true
     *
     * Если не нашли, выводим false
     *
     * Сложность:
     *  по времени: O(n*m) O(n) - проход по строке + O(m) - второй цикл с поиском по мапе chrs
     *  по памяти: O(1) стек для хранения 26 символов
     *
     * @param s1 String
     * @param s2 String
     */
    public static boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> chrs = new HashMap<>();
        for (char c : s1.toCharArray()) chrs.put(c, chrs.getOrDefault(c, 0) + 1);

        for (int l = 0; l < s2.length(); l++) {
            Map<Character, Integer> sub = new HashMap<>();
            int count = 0;
            for (int r = l; r < s2.length(); r++) {
                sub.put(s2.charAt(r), sub.getOrDefault(s2.charAt(r), 0) + 1);

                if (chrs.getOrDefault(s2.charAt(r),0) < sub.get(s2.charAt(r))) break;

                if (chrs.getOrDefault(s2.charAt(r),0) == sub.get(s2.charAt(r))) count++;

                if(count == chrs.size()) return true;
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
