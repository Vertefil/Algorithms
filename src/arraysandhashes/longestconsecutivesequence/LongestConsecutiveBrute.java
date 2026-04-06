package arraysandhashes.longestconsecutivesequence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LongestConsecutiveBrute {
    /**
     * На вход подаётся набор чисел, нужно вывести максимальную последовательность.
     *
     * Идея: убрать дубликаты и отсортировать. После пройтись по отсортированному списку и проверять содержание num+1;
     *
     * Сложность O(nlogn) - все проходы O(n) + сортировка O(nlogn) из-за set.contains получаем O(1)
     * Память O(n)
     *
     * @param nums int[]
     */
    public static int longestConsecutive(int[] nums) {
        if(nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return 1;
        }
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        List<Integer> sorted = new ArrayList<>(set);
        sorted.sort(null);
        int max = 1;
        int tmp = 1;
        for (int num : sorted) {
            System.out.println(num);
            if (set.contains(num + 1)) {
                tmp++;
                if (tmp > max) max = tmp;
            } else tmp = 1;
        }
        return max;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2,20,4,10,3,4,5};
        System.out.println(longestConsecutive(nums));
    }
}
