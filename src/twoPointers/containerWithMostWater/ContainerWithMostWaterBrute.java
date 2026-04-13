package twoPointers.containerWithMostWater;

public class ContainerWithMostWaterBrute {
    /**
     * На вход подаётся числовой массив высот. Найти максимальную площадь, которую может занять жидкость.
     *
     * Идея: Нужно найти оптимальные высоты, которые будут давать большую площадь. Вешаем указатели на начало и конец.
     * В текущей реализации, мы начинаем от левого указателя и ищем высоту больше или равную правой (можно наоборот).
     * Чтобы избежать ситуации, когда слева высота меньше правой (или наоборот в зависимости от выбора стороны),
     * в начале цикла вычисляем доступную площадь и только после проверяем.
     *
     * Сложность O(n) - в худшем случае один из указателей, придёт к другому.
     * Память O(1) используется только набор данных.
     *
     * @param heights int[]
     */
    public static int maxArea(int[] heights) {
        int l = 0; int r = heights.length - 1;
        int max = 0;
        while(l < r) {
            int minh = heights[l] > heights[r] ? heights[r] : heights[l];
            if(max < minh * (r-l)) {
                max = minh * (r-l);
            }
            if(l < r && heights[l] < heights[r]) {
                l++;
            } else if (r > l && heights[r] > heights[l]) {
                r--;
            } else {
                r--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,7,2,5,4,7,3,6};
        int[] nums2 = new int[]{0,0,0};
        int[] nums3 = new int[]{1,21};
        int[] nums4 = new int[]{4,0,1};
        //System.out.println(maxArea(nums1));
        //System.out.println(maxArea(nums2));
        System.out.println(maxArea(nums3));
        //System.out.println(maxArea(nums4));
    }
}
