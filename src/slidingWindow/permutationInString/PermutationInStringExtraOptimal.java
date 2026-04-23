package slidingWindow.permutationInString;

public class PermutationInStringExtraOptimal {
    /**
     * На вход подаётся строка s1 и s2. Содержатся ли в s2, одна из перестановок s1?
     *
     * Идея: Подсчёт совпадений + скользящее окно.
     *
     * Пока заполняем массив символами из ch1 заодно проходимся по массиву ch2 на кол-во символов из s1.
     * Производим сравнение массива и первичный подсчёт совпадений.
     * Назначаем указатель l на начало.
     *
     * Для символа r = s1.length():
     *  Если совпадения = 26, значит окно содержит вариацию слова s1.
     *
     *  Заполняем частоту букв в массив ch2
     *  Т.к. нет проверки на длину слова, мы делаем проверку на индексы окна справа, потом слева
     *  Двигаем указатель l++;
     *
     * Если совпадения == 26, то нашли подстроку, иначе false;
     *
     * Сложность:
     *  по времени: O(n) O(n) - проход по строке + O(26) - сравнение массивов.
     *  по памяти: O(1) стек для хранения 26*2 символов
     *
     * @param s1 String
     * @param s2 String
     */
    public static boolean checkInclusion(String s1, String s2) {
        if(s2.length() < s1.length()) return false;
        int [] ch1 = new int[26];
        int [] ch2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            ch1[s1.charAt(i) - 'a']++;
            ch2[s2.charAt(i) - 'a']++;
        }
        int match = 0;
        for (int i = 0; i < ch1.length; i++) if (ch1[i] == ch2[i]) match++;

        int l = 0;
        for (int r = s1.length(); r < s2.length(); r++) {
            if (match == 26) return true;

            int index = s2.charAt(r) - 'a';
            ch2[index]++;
            if (ch1[index] == ch2[index]){
                match++;
            } else if (ch1[index] + 1 == ch2[index]) {
                match--;
            }

            index = s2.charAt(l) - 'a';
            ch2[index]--;
            if (ch1[index] == ch2[index]){
                match++;
            } else if (ch1[index] - 1 == ch2[index]) {
                match--;
            }
            l++;
        }
        return match == 26;
    }

    public static void main(String[] args) {
        String s = "abc", a1 = "lecabee", a2 = "lecaabee";
        System.out.println(checkInclusion(s,a1)); //true
        System.out.println(checkInclusion(s,a2)); //false
    }
}
