package arraysAndHashes.productsOfArrayExceptSelf;

public class ProductExceptSelfOptimal {
    /**
     * На вход подаётся набор чисел, нужно вывести массив их произведений,
     * при чём res[i] - произведение всех, кроме nums[i].
     * nums=[1,2,4,6]
     *
     * Идея: вместо массивов префиксов (произведения 1 -> n-1) и суффиксов (произведения n-2 -> 0).
     * использовать изначальный массив и хранить результаты в нём.Таким образом мы получаем массив, в котором
     * содержится произведения в порядке 1->n-1, а после проходимся в обратном порядке n-2->0.
     *
     * Сложность O(n) память O(1) + O(n) - нужен только массив для ответа.
     *
     * @param nums int[]
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int pre = 1;
        for(int i = 0; i < nums.length; i++) {
            res[i] = pre;
            pre *= nums[i];
        }

        int post = 1;
        for(int i = nums.length-1; i >= 0; i--){
            res[i] *= post;
            post *= nums[i];
        }
        return res;
    }
}
