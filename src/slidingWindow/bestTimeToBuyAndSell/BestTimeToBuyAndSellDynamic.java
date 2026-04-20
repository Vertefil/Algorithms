package slidingWindow.bestTimeToBuyAndSell;

public class BestTimeToBuyAndSellDynamic {
    /**
     * На вход подаётся массив цен акции за период. Найти максимальную выгоду при покупке и продаже
     *
     * Идея: Идти по алгоритму и обновлять минимум, если встретили цену меньше предыдущего минимума.
     * Максимум, каждый раз обновляем (считаем, что в этот день мы продаём акции).
     *
     * Для каждой машины i:
     *  Если цена меньше min , то обновляем минимум
     *  Всегда находим максимум из предыдущего подсчёт и подсчёта на день i.
     *
     * Сложность:
     *  по времени: O(n) проход
     *  по памяти: O(1)
     *
     * @param prices int[]
     */
    public static int maxProfit(int[] prices) {
        int res = 0;
        int min = Integer.MAX_VALUE;
        for (int num : prices) {
            if (num <= min) min = num;
            res = Math.max(res, (num - min));
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
