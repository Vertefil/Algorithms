package twopointers.containerwithmostwater;

public class ContainerWithMostWaterOptimal {
    /**
     * На вход подаётся числовой массив высот. Найти максимальную площадь, которую может занять жидкость.
     *
     * Идея: Нужно найти оптимальные высоты, которые будут давать большую площадь. Вешаем указатели на начало и конец.
     * В текущей реализации, вычисляем площадь которую имеем и двигаем указатель с той стороны, где меньше высота.
     *
     * Сложность O(n) - в худшем случае один из указателей, придёт к другому.
     * Память O(1) используется только набор данных.
     *
     * @param heights int[]
     */
    public static int maxArea(int[] heights) {
        int l = 0;
        int r = heights.length - 1;
        int max = 0;

        while (l < r) {
            int minHeight = Math.min(heights[l], heights[r]);
            int area = minHeight * (r - l);
            max = Math.max(max, area);

            if (heights[l] < heights[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }
}
