package arraysAndHashes.topKFrequent;

import java.util.HashMap;
import java.util.Map;

public class TopKFrequentBrute
{
    /**
     * На вход подаётся отсортированный массив и кол-во часто встречаемых элементов
     * Решение в лоб - собрать мапу с парами (число - частота)
     * После проходим по мапе и выбираем максимумы
     *
     * Сложность O(n*k)
     *
     * @param nums
     * @param k
    */
    public static int[] topKFrequent(int[] nums, int k) {
        int[] ar = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for(int i = 0; i < k; i++) {
            int max = 0;
            int key = 0;
            for(int keys : map.keySet()) {
                int num = map.get(keys);
                if (max < num) {
                    key = keys;
                    max = num;
                }
            }
            ar[i] = key;
            map.remove(key);
        }
        return ar;
    }
}
