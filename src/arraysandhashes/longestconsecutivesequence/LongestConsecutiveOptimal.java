package arraysandhashes.longestconsecutivesequence;

import java.util.HashSet;

public class LongestConsecutiveOptimal {
    /**
     * На вход подаётся набор чисел, нужно вывести максимальную последовательность.
     *
     * Идея: убрать дубликаты, найти начало последовательности, путём проверки set.contains(num-1). Если такого нет
     * Считаем число началом последовательности длинной изначально = 0, после проверяем set.contains(num+len) и т.д.
     *
     * Сложность O(n), запрос по set O(1);
     * Память O(n)
     *
     * @param nums int[]
     */
    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int seq = 0;
        for(int num : nums){
            set.add(num);
        }
        for(int num: set){
            if(!set.contains(num-1)) {
                int len = 0;
                while(set.contains(num+len)) {
                    len += 1;
                }
                if (len > seq) seq = len;
            }
        }
        return seq;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2,20,4,10,3,4,5};
        System.out.println(longestConsecutive(nums));
    }
}
