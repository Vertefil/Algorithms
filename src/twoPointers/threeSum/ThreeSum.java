package twoPointers.threeSum;

import java.util.Arrays;
import java.util.*;

public class ThreeSum {
    /**
     * На вход подаётся массив чисел. Надо найти тройки, которые в сумме дают 0, при этом они не должны повторятся.
     *
     * Идея: Сортируем массив по возрастанию и используем первый цикл, как указатель на первое слагаемое.
     * Важно учесть, что если первое число больше нуля, то смысла искать больше нет.
     * Если i > 1 и текущее число, равно предыдущему, то пропускаем эту итерацию.
     * Внутри цикла, запускаем два указателя, левый на i+1, правый на конец.
     * Если сумма больше нуля, то двигаем правый указатель, если меньше, то левый.
     * Если нашли сумму, то добавляем в массив и двигаем левый указатель.
     * Двигаем до тех пор, пока l < r и числа будут отличные друг от друга.
     *
     * Сложность O(nlogn) + O(n^2) = O(n^2)
     * Память O(1) используется только набор данных и массив для ответа.
     *
     * @param nums int[]
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
            /*
             i   l        r  r = 5
             -4 -1 -1 0 1 2
             */
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            } else if (nums[i] > 0) {
                break;
            }
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if(sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    res.add(List.of(nums[i], nums[l], nums[r]));
                    do {
                        l++;
                    } while (nums[l] == nums[l - 1] && l < r);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = new int []{-1,0,1,2,-1,-4};
        int[] nums2 = new int []{0,0,0,0};
        System.out.println(threeSum(nums1));
    }
}
