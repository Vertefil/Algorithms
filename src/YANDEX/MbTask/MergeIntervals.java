package YANDEX.MbTask;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://neetcode.io/problems/merge-intervals/question?list=allNC
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0],b[0]));
        List<int[]> out = new ArrayList<>();
        out.add(intervals[0]);

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            int last = out.get(out.size() - 1)[1];
            if (start <= last) {
                out.get(out.size() -1)[1] = Math.max(last, end);
            }
            else {
                out.add(new int[]{start, end});
            }
        }
        return out.toArray(new int[out.size()][]);
    }
}
