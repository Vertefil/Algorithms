package YANDEX.School2026;

import java.io.*;
import java.util.*;

public class Task1 {
    /**
    Вася придумал новую систему подсказок при вводе.
    В этой системе заранее подготовлен список слов, которые могут быть использованы как подсказки.
    Пользователь вводит буквы, и для каждой буквы должно выбираться слово по следующим правилам:
        из словаря выбираются слова, начинающиеся с той же буквы, которую ввел пользователь;
        из выбранных слов выбираются те, которые использовались в качестве подсказки наименьшее количество раз;
        из выбранных слов выбирается лексикографически минимальное — это слово и является подсказкой.
    Реализуйте систему, придуманную Васей.

    Формат ввода
    В первой строке содержится два числа
    n и k (1≤n,k≤100000) — размер словаря и количество введенных пользователем букв соответственно.
    В следующих n строках записаны слова словаря, по одному в строке.
    Слова состоят из строчных английских букв, их длина не превосходит 21. Все слова в словаре различны.
    В следующих k строках записаны введенные пользователем буквы, по одной в строке.
    Гарантируется, что для каждой вводимой буквы в словаре существует хотя бы одно слово, начинающееся с этой буквы.

    Формат вывода
    Для каждой введённой пользователем буквы выведите слово-подсказку.
    Пример 1
    Ввод
    4 5
    peterburg
    murmansk
    perm
    moscow
    p
    m
    m
    p
    p

    Вывод
    perm
    moscow
    murmansk
    peterburg
    perm

    Пример 2
    Ввод
    5 3
    int
    main
    void
    double
    string
    v
    m
    v

    Вывод
    void
    main
    void

    Пример 3
    Ввод
    1 3
    python
    p
    p
    p

    Вывод
    python
    python
    python
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int k = Integer.parseInt(firstLine[1]);
        String[] arr = new String[n];
        int[] count = new int[n];
        HashMap<Character, TreeSet<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) arr[i] = br.readLine().trim();

        for (int i = 0; i < n; i++) {
            char ch = arr[i].charAt(0);
            if (map.get(ch) == null) {
                map.put(ch,
                        new TreeSet<>((a, b) -> count[a] != count[b]
                                ? count[a] - count[b]
                                : arr[a].compareTo(arr[b])));
            }
            map.get(ch).add(i);
        }

        for (int i = 0; i < k; i++) {
            char ch = br.readLine().trim().charAt(0);
            int idx = map.get(ch).first();
            System.out.println(arr[idx]);
            map.get(ch).remove(idx);
            count[idx]++;
            map.get(ch).add(idx);
        }
    }
}