package slidingWindow.minimumWindowSubstring;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    /**
     * На вход подаётся строка s и t. Вывести наименьшую подстроку из s содержащую вариацию t.
     *
     * Идея: Подсчёт совпадений + скользящее окно.
     * Для поиска минимума будем использовать кол-во найденных и нужных символов.
     * Пока они равны, сравниваем длину и сохраняем индексы в итоговой массив и уменьшаем окно слева.
     *
     * Создаём и заполняем мапу с нужными символами из t.
     * Вводим временные переменные для подсчёта, размера мапы cht (из слово t), массива для длины и размера длины.
     *
     * Для символа r = 0:
     *  В мапу кладём символ и увеличиваем его частоту.
     *  Если символ содержится в cht и его частота такая же, то увеличиваем совпадения.
     *  Пока совпадения == кол-ву нужных:
     *      Сравниваем длину подстроки, если меньше, то обновляем массив и минимальный размер
     *      Уменьшаем частоту и если этот символ содержался в cht, то уменьшаем кол-во совпадений
     *      Двигаем указатель l++;
     *
     * Если минимальная длина изменилась за время работы программы, выводим подстроку.
     *
     * Сложность:
     *  по времени: O(n) проход
     *  по памяти: O(n) мапа для хранения
     *
     * @param s String
     * @param t String
     */
    public static String minWindow(String s, String t) {
        if (t.isEmpty()) return "";
        Map<Character, Integer> cht = new HashMap<>();
        for (char c : t.toCharArray()) {
            cht.put(c, cht.getOrDefault(c, 0) +1);
        }

        int match = 0, need = cht.size();
        int[] res = new int[]{-1,-1};
        int l = 0, len = Integer.MAX_VALUE;
        Map<Character, Integer> chs = new HashMap<>();

        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            chs.put(c, chs.getOrDefault(c, 0) + 1);
            if (cht.containsKey(c) && chs.get(c) == cht.get(c)) match++;

            while (match == need ) {
                if ((r - l + 1) < len) {
                    res[0] = l;
                    res[1] = r;
                    len = r - l + 1;
                }
                System.out.println(l);
                char tmp = s.charAt(l);
                chs.put(tmp, chs.getOrDefault(tmp, 0) - 1);
                if (cht.containsKey(tmp) && chs.get(tmp) < cht.get(tmp)) match--;
                l++;
            }
        }
        return len != Integer.MAX_VALUE ? s.substring(res[0], res[1] + 1) : "";
    }

    public static void main(String[] args) {
        String s = "OUZODYXAZV", a1 = "XYZ", a2 = "ZE";
        System.out.println(minWindow(s,a1)); //YXAZ
        System.out.println(minWindow(s,a2)); //""
    }
}
