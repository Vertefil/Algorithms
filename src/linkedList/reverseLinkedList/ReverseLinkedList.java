package linkedList.reverseLinkedList;

import linkedList.ListNode;

public class ReverseLinkedList {
    /**
     * На вход подаётся связный список, нужно его развернуть
     *
     * Идея: нам понадобится два списка, которые будут указывать на предыдущий и текущий элемент.
     * В цикле мы будем текущий элемент ссылать на предыдущий и двигать текущий элемент дальше по списку.
     *
     * Пока текущий элемент != null:
     *  Создаём временную переменную которая будет указывать не следующий элемент.
     *  Текущий элемент ссылаем на предыдущий
     *  Предыдущий обновляем на текущий
     *  Текущий элемент обновляем на временную переменную, которая уже сдвинута дальше по списку
     *
     * Сложность:
     *  по времени: O(n) проходим по списку
     *  по памяти: O(1)
     *
     * @param head ListNode
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }
}



