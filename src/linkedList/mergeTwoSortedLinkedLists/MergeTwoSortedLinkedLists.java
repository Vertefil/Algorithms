package linkedList.mergeTwoSortedLinkedLists;


import linkedList.ListNode;

public class MergeTwoSortedLinkedLists {
    /**
     * На вход подаётся два отсортированных массива.
     * Объединить их и вывести в порядке возрастания.
     *
     * Идея: Создаём список с 1 значением, для краевого случая (оба null) и результирующий список.
     * Сравниваем значения двух списков, добавляем в результирующий, двигаемся дальше по спискам.
     * Краевой случай: оба null, один из списков закончился, тогда мы в результирующий добавляем оставшийся список.
     *
     * Пока списки не пусты:
     *  Если значение из списка 1 < значение из списка 2:
     *      результирующий ссылается на список1
     *      список1 двигаем указатель дальше.
     *  Иначе
     *      результирующий ссылается на список2
     *      список2 двигаем указатель дальше.
     *  Двигаем результирующий дальше
     *
     * Если один из списков пустой, то добавляем в продолжение к результирующему не пустой.
     *
     * Сложность:
     *  по времени: O(n+m) проходим по списку1 + список2
     *  по памяти: O(1)
     *
     * @param list1 ListNode
     * @param list2 ListNode
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode tmp = new ListNode(0);
        ListNode res = tmp;
        while(list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                res.next = list1;
                list1 = list1.next;
            } else {
                res.next = list2;
                list2 = list2.next;
            }
            res = res.next;
        }

        if (list1 != null) res.next = list1;
        else res.next = list2;

        return tmp.next;
    }
}
