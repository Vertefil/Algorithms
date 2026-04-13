package arraysAndHashes.encodeAndDecode;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecodeString {
    /**
     * На вход подаётся массив строк, которые нужно закодировать в строку и декодировать в массив строк.
     * Идея кодировки: создаём массив размеров, заполняем его длинами слов из массива
     * Записываем в закодированную строку размеры и ставим разделитель '#', после записываем все словам из массива.
     *
     * Идея декодировки: создаём массив размеров, считываем строку до '#'. Заводим счётчик символов i
     * после каждого вхождения ',', записываем в массив размеров, длину слова.
     * После проходимся по массиву размеров и вычленяем кол-во символов после '#' (на это указывает i)
     *
     * Сложность O(m) для кодирования и декодирования
     *
     * @param strs List<String>
     */

    public static String encode(List<String> strs) {
        if (strs.isEmpty()) return "";
        StringBuilder res = new StringBuilder();
        List<Integer> sizes = new ArrayList<>();
        for (String str : strs) {
            sizes.add(str.length());
        }
        for (int size : sizes) {
            res.append(size).append(',');
        }
        res.append('#');
        for (String str : strs) {
            res.append(str);
        }
        return res.toString();
    }

    public static List<String> decode(String str) {
        if (str.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        List<Integer> sizes = new ArrayList<>();
        int i = 0;
        while (str.charAt(i) != '#') {
            StringBuilder cur = new StringBuilder();
            while (str.charAt(i) != ',') {
                cur.append(str.charAt(i));
                i++;
            }
            sizes.add(Integer.parseInt(cur.toString()));
            i++;
        }
        i++;
        for (int sz : sizes) {
            res.add(str.substring(i, i + sz));
            i += sz;
        }
        return res;
    }
}
