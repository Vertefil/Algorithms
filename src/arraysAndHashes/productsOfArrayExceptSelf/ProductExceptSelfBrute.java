package arraysAndHashes.productsOfArrayExceptSelf;

public class ProductExceptSelfBrute {
    /**
     * На вход подаётся набор чисел, нужно вывести массив их произведений,
     * при чём res[i] - произведение всех, кроме nums[i].
     * nums=[1,2,4,6]
     *
     * Идея: создать массив префиксов (произведения 1 -> n-1) и суффиксов (произведения n-2 -> 0). Таким образом
     * мы получаем массив префиксов в котором содержится произведения в порядке 1->n-1, а в суффиксе n-2->0.
     * Чтобы получить нужное произведение, нам нужно перемножить их одинаковые индексы, чтобы получить результат.
     *
     * Сложность O(n) память O(n), но создаём дополнительные массивы для быстроты
     *
     * @param nums int[]
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int[] suf = new int[nums.length];
        int[] pref = new int[nums.length];
        pref[0] = 1;
        suf[nums.length-1] = 1;

        for(int i = 1; i < nums.length; i++) {
            pref[i] = nums[i-1] * pref[i-1];
        }
        for(int i = nums.length-2; i >= 0; i--){
            suf[i] = nums[i+1] * suf[i+1];
        }
        for(int i = 0; i < nums.length; i++) {
            res[i] = pref[i] * suf[i];
        }
        return res;
    }
}
