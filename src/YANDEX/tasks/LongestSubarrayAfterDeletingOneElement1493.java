package YANDEX.tasks;

/**
 * https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/
 * Дан бинарный массив nums, из которого нужно удалить один элемент.
 * Верните размер самого длинного массива из 1. Верните 0, если такого подмассива нет.
 * Идея: Скользящее окно
 *
 * @param nums int[]
 */
public class LongestSubarrayAfterDeletingOneElement1493 {
    //sol1
    /*
     * Инит:
     * лев указ на 0, счётчик = 0, рез = 0
     *
     * Если прав указ на 0, то счётчик++
     * Если счётчик больше нуля и встретили 0, то сдвигаем лев указ, до след нуля, чтобы счётчик == 0
     * Считаем мак между рез и длиной окна
     *
     * Сложность:
     *  по времени: O(n)
     *  по памяти: O(1)
     */
    public int longestSubarray1(int[] nums) {
        int l = 0;
        int res = 0;
        int count = 0;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] == 0) count++;
            while (count > 1) {
                if(nums[l] == 0) count--;
                l++;
            }
            res = Math.max(res, r-l);
        }
        return res;
    }

    //sol2
    /*
     * Инит:
     * лев указ на 0, рез = 0, указ на послед ноль = -1 (чтобы в крайних случаях брать весь отрезок)
     *
     * Если прав указ на 0, то левый указ на место послед нуля +1 и послед ноль указывает на прав указ
     * Считаем максимум между рез и окном.
     *
     * Сложность:
     *  по времени: O(n)
     *  по памяти: O(1)
     */
    public int longestSubarray2(int[] nums) {
        int l = 0;
        int res = 0;
        int lz = -1;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] == 0) {
                l = lz+1;
                lz = r;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }
}
