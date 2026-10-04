package YANDEX.tasks;

public class EditDistance72 {
    /**
     * https://leetcode.com/problems/edit-distance/
     *
     * Даны две строки word1 и word2. Вернуть минимальное кол-во операций, для превращений word1 -> word2.
     * Операции:
     *  Вставить символ
     *  Удалить символ
     *  Поменять 1 символ на любой другой, например: enention -> exention (поменять 'n' with 'x')
     *
     * Input: word1 = "horse", word2 = "ros"
     * Output: 3
     * horse -> rorse (поменять 'h' с 'r')
     * rorse -> rose (удалить 'r')
     * rose -> ros (удалить 'e')
     *
     * Input: word1 = "intention", word2 = "execution"
     * Output: 5
     * intention -> inention (удалить 't')
     * inention -> enention (поменять 'i' with 'e')
     * enention -> exention (поменять 'n' with 'x')
     * exention -> exection (поменять 'n' with 'c')
     * exection -> execution (вставить 'u')
     *
     * Идея: строим матрицу dp размерами word1 + 1 = m, word2 + 1 = n;
     * где dp[i][j] обозначает минимальное количество операций,
     * необходимых для преобразования подстроки word1[0...i-1] в подстроку word2[0...j-1].
     *
     * Как строим матрицу:
     *
     * Используем первый ряд и первый столбец как базовые случае:
     * dp[i][0] = i: для преобразования word1[0...i-1] в пустую строку требуется i удалений.
     * dp[0][j] = j: для преобразования пустой строки в word2[0...j-1] требуется j вставок.
     *
     * Строка - word1, столбец - word2 (для удобства можно расположить буквы за матрицой начиная с индекса 1)
     * т.е. представим, что было бы, если word1 = "", word2 = "abc"
     * В таком случае нам нужно было бы сделать n удалений, значит первый столбец будет 0, 1, 2, 3
     * кол-во операций чтобы превратить строку word1 -> word2 = 3. Так как на каждом символе мы удаляли
     * Если  word1 = "abcd", word2 = "" - значит надо сделать m удалений, первая строка будет 0, 1, 2, 3, 4
     * кол-во операций чтобы превратить строку word1 -> word2 = 4. Так как на каждом символе мы удаляли
     *
     * Как строим матрицу:
     *  Если word1[i-1] == word2[j-1], то dp[i][j] = dp[i-1][j-1] - операция не требуется,
     *  потому что символы в позициях i-1 и j-1 равны.
     *  Иначе в dp[i][j] — помещаем минимум из :
     *      dp[i-1][j-1] + 1: заменить символ в позиции i-1 в word1 на символ в позиции j-1 в word2.
     *      dp[i-1][j] + 1: удалить символ в позиции i-1 в word1.
     *      dp[i][j-1] + 1: вставить символ в позиции j-1 в word2 в word1 в позиции i.
     *
     * Возвращаем dp[m][n] - будет содержать минимальное кол-во операций.
     *
     * Сложность:
     *  по времени: O(mn) проходимся по матрице длинной m, размером n
     *  по памяти: O(mn) матрица длинной m, размером n
     *
     * @param word1 String
     * @param word2 String
     */
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) return 0;
        if (word1.isEmpty() || word2.isEmpty()) return Math.max(word1.length(), word2.length());

        final int m = word1.length();
        final int n = word2.length();
        int dp[][] = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
