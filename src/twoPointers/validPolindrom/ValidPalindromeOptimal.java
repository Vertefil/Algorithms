package twoPointers.validPolindrom;

public class ValidPalindromeOptimal {
    /**
     * На вход подаётся строка, нужно узнать является ли она палиндромом?
     *
     * Идея: вешаем два указателя на начало и конец, двигаем указатели, пока символ не является буквой или цифрой.
     * Если символ по указателям буква или цифра, приводим к нижнему регистру и сравниваем.
     *
     * Сложность O(n) память O(1) используется только набор данных.
     *
     * @param s String
     */
    public static boolean isPalindrome(String s) {
        if(s.length() == 1) return true;
        int l = 0, r = s.length()-1;
        while(l < r) {
            while(l < r && !Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            }
            while(r > l && !Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            }
            if(Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) return false;
            l++;
            r--;
        }
        return true;
    }
}
