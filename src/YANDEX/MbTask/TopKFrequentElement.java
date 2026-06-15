package YANDEX.MbTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://neetcode.io/problems/top-k-elements-in-list/question?list=blind75
public class TopKFrequentElement {
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num ,map.getOrDefault(num, 0) + 1);

        List<int[]> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            list.add(new int[] {entry.getValue(), entry.getKey()});
        }

        list.sort((a,b) -> b[0] - a[0]);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) ans[i] = list.get(i)[1];
        return ans;
    }
}
