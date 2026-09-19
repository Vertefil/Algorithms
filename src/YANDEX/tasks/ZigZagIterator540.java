package YANDEX.tasks;

import java.util.List;

public class ZigZagIterator540 {
    /**
     * https://www.lintcode.com/problem/540/
     * https://leetcode.com/problems/zigzag-iterator/description/
     *
     * Даны два списка v1,v2. Нужно пройтись по ним зигзагом. (v1.get, v2.get, v1.get, ,v2.get)
     *
     * Идея:
     * Использовать два указателя и флаг для перемены порядка подбора.
     *
     * Сложность:
     *  по времени: O(n+m) n1 - len v1, m - len v2
     *  по памяти: O(1)
     *
     * @param root TreeNode
     * @param p TreeNode
     * @param q TreeNode
     */
    public final List<Integer> v1;
    public final List<Integer> v2;
    public boolean flag = true;
    public int firstPointer = 0;
    public int secondPointer = 0;

    /*
     * просто меняем исходные ссылки, без копирования
     */
    public ZigZagIterator540(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    /*
     * Пока один из указателей не дошёл до конца, мы используем флаг.
     * Если один из указателей дошёл до конца, то мы заполняем остатками другого.
     */
    public int next() {
        if(firstPointer < v1.size() && secondPointer < v2.size()) {
            if (this.flag) {
                this.flag = false;
                return v1.get(firstPointer++);
            } else {
                this.flag = true;
                return v2.get(secondPointer++);
            }
        } else if (firstPointer == v1.size() && secondPointer < v2.size()) {
            return v2.get(secondPointer++);
        }
        return v1.get(firstPointer++);
    }

    /*
     * Возвращаем true, если один из указателей не дошёл до конца.
     */
    public boolean hasNext() {
        // write your code here
        return firstPointer != v1.size() || secondPointer != v2.size();
    }
}
