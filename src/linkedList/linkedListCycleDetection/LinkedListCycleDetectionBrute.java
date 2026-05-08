package linkedList.linkedListCycleDetection;

import linkedList.ListNode;

import java.util.HashSet;

public class LinkedListCycleDetectionBrute {
    /**
     * На вход подаётся список в котором может быть цикл. Вернуть true если цикл есть и false если нет.
     *
     * Идея: Использовать Set, чтобы хранить пройденные точки и если наткнёмся на такую же, то вернуть true
     * Т.к. храним объекты, а не цифры, циклы с повторными числами не проблема.
     *
     * Пока список не пуст:
     *  Если значение содержится в set - true
     *  Добавляем в set объект
     *  Двигаемся дальше по списку
     *
     * Если цикл завершился, значит в списке нет циклов
     *
     * Сложность:
     *  по времени: O(n) проходим по списку
     *  по памяти: O(n) память под дубликаты
     *
     * @param head ListNode
     */
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while(head != null) {
            if (set.contains(head)) return true;
            set.add(head);
            head = head.next;
        }
        return false;
    }
}
