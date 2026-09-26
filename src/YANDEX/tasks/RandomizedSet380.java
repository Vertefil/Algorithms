package YANDEX.tasks;

import java.util.*;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 *
 * Задача: Создать класс, который будет содержать уникальные значения, удалять, добавлять и отдавать случайное значение.
 * Любая из этих 3-ёх операции должна быть за O(1)
 *
 * Идея: Использовать HashMap, чтобы хранить пары число и его индекс (понадобится при удалении числа, т.к. map.get(n) = O(1) )
 * Использовать ArrayList, так как при вставке по индексу и удаления с конца - происходит за O(1)
 *
 * Вставка:
 *  Если список пустой - добавляем в мапу val и индекс 0, добавляем в list -> true;
 *  Иначе если такого значения нет в мапе: добавляем в мапу val и индекс list.size() и потом добавляем в list -> true;
 *  (таким образом мы сохраняем индекс. Если сначала добавить в list, а потом в мапу - порядок будет не верный)
 *  Иначе false;
 *
 * getRandom:
 *  Используя Random. задаём диапазон [0, list.size() )
 *
 * Удаление:
 *  Integer num = val (перестраховка на случай null - в текущей версии можно убрать)
 *  Если такое число есть в нашей мапе:
 *      Если число последнее в списке:
 *          удаляем с конца list - O(1)
 *          удаляем из мапы - О(1)
 *      Иначе:
 *          index = значение по ключу num
 *          last = последнее число из list
 *          Устанавливаем в list.set по индексу index, значение last
 *          удаляем последнее число изи list
 *          Кладём в мапу по ключу last, значение index
 *          Удаляем из мапы num
 *      return true;
 *
 *  Иначе return false
 *
 * Сложность:
 *  по времени: O(1)
 *  по памяти: O(n)
 */
public class RandomizedSet380 {
    private Map<Integer, Integer> set;
    private Random r;
    private List<Integer> list;

    public RandomizedSet380() {
        this.set = new HashMap<>();
        this.list = new ArrayList<>();
        this.r = new Random();
    }

    public boolean insert(int val) {
        if (list.isEmpty()) {
            set.put(val, 0);
            list.add(val);
            return true;
        } else if (!set.containsKey(val)) {
            set.put(val, list.size());
            list.add(val);
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        Integer num = val;
        if (set.containsKey(num)) {
            if (val == list.getLast()) {
                list.removeLast();
                set.remove(num);
            } else {
                int index = set.get(num);
                int last = list.getLast();
                list.set(index, last);
                list.removeLast();
                set.put(last, index);
                set.remove(num);
            }
            return true;
        }
        return false;
    }

    public int getRandom() {
        return list.get(r.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */