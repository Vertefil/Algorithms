package YANDEX.tasks;

public class PermutationInString567 {
    /**
     * https://leetcode.com/problems/permutation-in-string/description/
     * Даны две строки s1 и s2. Вернуть true, если в строке s2 содержится любая перестановка s1.
     * Перестановка - любое слово, состоящие из символов s1 (включая слово s1).
     *
     * s1 = "ab", s2 = "eidbaooo"
     * Output: true - s2 содержит одну перестановку s1 ("ba").
     *
     * Input: s1 = "ab", s2 = "eidboaoo"
     * Output: false - нет ни одной перестановки
     *
     * Идея: Скользящее окно и подсчёт совпадений - метч.
     * Если строка s1 > s2 - всегда false, не будет перестановок.
     *
     * Выделяем два массива, которые будем использовать для проверки совпадений.
     * Так как используются a-z = 26, и такого же размера массив.
     * Т.е. в first: [a=1,b=1,c=0,d=0...z=0]
     * в second [a=0,b=0...z=0]
     * В таком случае метч = 23, так как в second нет букв a,b,c в указанном кол-ве.
     * Если окно содержит перестановку s1, то first == second.
     *
     * Заполняем оба массива первыми m буквами.
     * Вводим счётчик метчей:
     * Сравниваем оба массива first и second и считаем метчи.
     *
     * Вводим два указателя:
     * l = 0, r = m;
     * В цикле, по слову s2:
     *  Если метчей == 26, то нашли перестановку.
     *
     *  idx = индекс буквы на позиции r, для сравнения в обоих массивах
     *  second[idx]++ (увеличиваем кол-во этой буквы в окне)
     *  Если Кол-во требуемых буквы == кол-ву в окне: match++
     *  Если Кол-во требуемых буквы + 1 == кол-ву в окне: match-- (значит взяли лишние буквы)
     *
     *  idx = индекс буквы на позиции l, для сравнения в обоих массивах
     *  second[idx]++ (увеличиваем кол-во этой буквы в окне)
     *  Если Кол-во требуемых буквы == кол-ву в окне: match++
     *  Если Кол-во требуемых буквы - 1 == кол-ву в окне: match-- (значит удалил нужную букву)
     *  l++ (двигаем левый указ)
     *
     * Сложность:
     *  по времени: O(n)
     *  по памяти: O(26)
     *
     * @param s1 String
     * @param s2 String
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int m = s1.length();
        int n = s2.length();
        int[] first = new int[26];
        int[] second = new int[26];

        for (int i = 0; i < m; i++) {
            first[s1.charAt(i) - 'a']++;
            second[s2.charAt(i) - 'a']++;
        }

        int match = 0;
        for (int i = 0; i < 26; i++) {
            if (first[i] == second[i]) match++;
        }

        int l = 0;
        for (int r = m; r < n; r++) {
            if (match == 26) return true;

            int idx = s2.charAt(r) - 'a';
            second[idx]++;
            if (first[idx] == second[idx]) match++;
            else if (first[idx] + 1 == second[idx]) match--;

            idx = s2.charAt(l) - 'a';
            second[idx]--;
            if (first[idx] == second[idx]) match++;
            else if (first[idx] - 1 == second[idx]) match--;
            l++;
        }
        return match == 26;
    }
}
