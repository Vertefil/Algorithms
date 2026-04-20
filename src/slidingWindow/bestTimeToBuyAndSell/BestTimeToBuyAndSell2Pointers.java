package slidingWindow.bestTimeToBuyAndSell;

public class BestTimeToBuyAndSell2Pointers {
    /**
     * На вход подаётся массив цен акции за период. Найти максимальную выгоду при покупке и продаже
     *
     * Идея: Ставим указатели на 0 и 1.
     * Если у левого указателя число меньше, то обновляем минимум, делаем подсчёт и находим максимум.
     * Иначе левый указатель перемещаем на место правого, а правый двигается на 1 дальше.
     * Т.е. левый всегда указывает на меньшее число, а правый двигается в поиске максимального.
     *
     * Сложность:
     *  по времени: O(n) проход
     *  по памяти: O(1)
     *
     * @param prices int[]
     */
    public static int maxProfit(int[] prices) {
        int l = 0, r = 1;
        int res = 0;
        while (r < prices.length) {
            if (prices[l] < prices[r]) {
                int profit = prices[r] - prices[l];
                res = Math.max(res, profit);
            } else {
                l = r;
            }
            r++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ar1 = new int[]{10,1,5,6,7,1};
        int[] ar2 = new int[]{10,7,6,5,3};
        System.out.println(maxProfit(ar1));
        System.out.println(maxProfit(ar2));
    }
}
