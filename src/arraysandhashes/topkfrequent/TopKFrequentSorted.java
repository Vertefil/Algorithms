package arraysandhashes.topkfrequent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentSorted {
    /**
     * На вход подаётся отсортированный массив и кол-во часто встречаемых элементов
     * Основная идея создать мапу с парой (частота - число) и далее отсортировать по возрастанию
     *
     * Сложность O(nlogn)
     *
     * @param nums
     * @param k
     */
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<int[]> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(new int[]{entry.getValue(), entry.getKey()});
        }

        //Основная идея тут  - сортируем по возрастанию
        list.sort((a, b) -> b[0] - a[0]);

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) ans[i] = list.get(i)[1];

        return ans;
    }
}
