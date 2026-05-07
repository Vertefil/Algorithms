package YANDEX;

import java.util.*;
import java.io.*;

public class Task2 {
    /**
     * Даны две последовательности, состоящие из n элементов.
     * Каждый элемент может быть числом или названием переменной.
     * Определите, существует ли способ заменить каждую переменную на какое-либо число так,
     * чтобы последовательности совпадали.
     *
     * Формат ввода
     * В первой строке вводится число n (1≤n≤50000) — длина последовательностей.
     *
     * В следующих двух строках вводятся последовательности.
     * Каждая последовательность состоит из n элементов, разделенных одним пробелом.
     * Каждый элемент может быть целым числом от 1 до 1000 или названием переменной,
     * состоящим только из английских букв, при этом длина названия не превосходит 10.
     *
     * Формат вывода
     * Выведите "YES", если существует способ замены переменных на числа так,
     * чтобы последовательности совпадали, и "NO" в противном случае.
     *
     * Пример 1
     * Ввод
     * 3
     * 3 1 2
     * 3 1 3
     *
     * Вывод
     * NO
     *
     * Пример 2
     * Ввод
     * 4
     * 4 5 igrek igrek
     * 4 iks 3 iks
     * Вывод
     * NO
     *
     * Пример 3
     * Ввод
     * 5
     * x 3 x y 3
     * x y 2 z 3
     * Вывод
     * YES
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String[] l = br.readLine().trim().split(" ");
        String[] r = br.readLine().trim().split(" ");
        Map<String, String> prnt = new HashMap<>();
        Map<String, String> rt = new HashMap<>();
        boolean res = true;

        for (int i = 0; i < n; i++) {
            String a = l[i];
            String b = r[i];
            boolean aDig = Character.isDigit(a.charAt(0));
            boolean bDig = Character.isDigit(b.charAt(0));

            if (!aDig && !bDig) {
                String ra = find(prnt, a);
                String rb = find(prnt, b);
                if (!ra.equals(rb)) prnt.put(ra, rb);

            }
        }

        for (int i = 0; i < n; i++) {
            String a = l[i];
            String b = r[i];
            boolean aDig = Character.isDigit(a.charAt(0));
            boolean bDig = Character.isDigit(b.charAt(0));

            if (aDig && bDig) {
                if (!a.equals(b)) {
                    res = false;
                    break;
                }
            } else if (!aDig && !bDig) {
                String ra = find(prnt, a);
                String rb = find(prnt, b);
                String numA = rt.get(ra);
                String numB = rt.get(rb);

                if (numA != null && numB != null && !numA.equals(numB)) {
                    res = false;
                    break;
                }
                else if (numA != null) rt.put(rb, numA);
                else if (numB != null) rt.put(ra, numB);
            } else {
                String var = aDig ? b : a;
                String num = aDig ? a : b;
                String fnd = find(prnt, var);
                String ex = rt.get(fnd);

                if (ex == null) rt.put(fnd, num);
                else if (!ex.equals(num)) {
                    res = false;
                    break;
                }
            }
        }
        System.out.println(res ? "YES" : "NO");
    }

    public static String find(Map<String, String> prnt, String x) {
        if (!prnt.containsKey(x)) return x;
        return find(prnt, prnt.get(x));
    }
}