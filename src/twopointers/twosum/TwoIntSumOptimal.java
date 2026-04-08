package twopointers.twosum;

public class TwoIntSumOptimal {
    /**
     * На вход подаётся отсортированная по возрастанию массив чисел. Найти значение позиций цифр подходящих для решения.
     * В данному случае в позиции отчёт идёт от 1 до n, а не 0...n-1, как в индексах
     *
     * Идея: вешаем два указателя на начало и конец. Если сумма чисел больше искомого, двигаем правый указатель, если
     * сумма чисел меньше, то двигаем левый.
     *
     * Сложность O(n) - в худшем случае один из указателей, придёт к другому.
     * Память O(1) используется только набор данных.
     *
     * @param numbers int[]
     * @param target int
     */
    public static int[] twoSum(int[] numbers, int target) {
        int l = 0, r =numbers.length - 1;
        /*
        t = 11
              l r
        1 2 3 4 7
         */
        while (l < r) {
            while (l < r && (target > (numbers[l] + numbers[r]))) {
                l++;
            }
            while(r > l && (target < (numbers[l] + numbers[r]))) {
                r--;
            }
            if((numbers[l] + numbers[r]) == target) return new int[]{l+1,r+1};
        }

        return new int[0];
    }
}
