package linkedList.mergeKSortedLists;

import linkedList.ListNode;

import java.util.ArrayList;
import java.util.List;

public class MergeKSortedListsDivideAndConquer {
    /**
     * На вход подаётся массив связных списков, нужно их объединить и вернуть в отсортированном порядке
     *
     * Идея: Разделяй и властвуй (итеративный подход).
     * Многократно объединяем списки по парам:
     *  список 0 и список 1 - М0
     *  список 2 и список 3 - M1
     *  список 4 и список 5 - М2
     *
     * После таких объединений остаётся в два раза меньше списков
     * Попарное слияние - объединение двух отсортированных списков
     * Повторяем процесс, пока не останется один список.
     * Т.О. получаем log k по времени
     *
     * Базовый случай пустого массива - выкидываем null
     *
     * Пока длина массива > 1:
     *  Вводим объединённый массив
     *  В цикле по массиву списков с шагом два (т.к. объединяем два списка в 1):
     *      Список1 - list[i]
     *      Список2 - list[i+1] (если длина массива позволяем и мы не выходим за его пределы, иначе null)
     *      Добавляем в объединённый массив результат функции.
     *
     *      Функция (объединяет списки в один, в отсортированном порядке)
     *
     *  После цикла, в [0] позицию массива, записываем общий объединённый список, потом его возвращаем.
     *
     * Сложность:
     *  по времени: O(n*log k) проходимся k (кол-во списков в массиве) * n (кол-во узлов в списках)
     *  по памяти: O(1)
     *
     * @param lists ListNode[]
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        while (lists.length > 1) {
            List<ListNode> mergedLists = new ArrayList<>();

            for (int i = 0; i < lists.length; i+=2) {
                ListNode l1 = lists[i];
                ListNode l2 = (i+1) < lists.length ? lists[i+1] : null;
                mergedLists.add(mergeLists(l1,l2));
            }

            lists = mergedLists.toArray(new ListNode[0]);
        }
        return lists[0];
    }

    private ListNode mergeLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        if (l1 == null) tail.next = l2;
        if (l2 == null) tail.next = l1;

        return dummy.next;
    }
}
