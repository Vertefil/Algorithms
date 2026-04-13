package twoPointers.validPolindrom;

public class ValidPalindromeBrute {
    /**
     * На вход подаётся строка, нужно узнать является ли она палиндромом?
     *
     * Идея: так как строка может быть любая, но нам интересны, только буквы и цифры, первой задачей
     * будет приведение строки к нижнему регистру и формату: буква или цифра.
     * После запускаем два указателя на начало и конец и сравниваем посимвольно доходя до середины слова.
     *
     * Сложность O(n) память O(n).
     *
     * @param s String
     */
    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(Character.isLetterOrDigit(c)) sb.append(Character.toLowerCase(c));
        }
        if(sb.isEmpty()) return true;
        int l = 0, r = sb.length()-1;
        // 6 n = 3 1..2..3   6..5..4
        for(int i = 0; i < (sb.length() / 2) + 1;i++) {
            if(sb.charAt(l) != sb.charAt(r)){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
