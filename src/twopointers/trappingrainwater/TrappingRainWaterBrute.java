package twopointers.trappingrainwater;

public class TrappingRainWaterBrute {
    /**
     * На вход подаётся числовой массив высот. Найти максимальную площадь, которую может занять дождевая вода.
     *
     * Идея: Чтобы узнать сколько воды может поместиться в столбце с левым или правым указателем,
     * нужно знать максимальную высоту указателя на данном этапе.
     * Зная максимальную высоту слева или справа, мы вычитаем высоту на которой стоит указатель
     * с максимумом у этого указателя и добавляем в сумму.
     * В нашей реализации не надо делать проверки на 0 или отрицание, так как мы сначала обновляем max и потом считаем.
     *
     * Сложность O(n) - в худшем случае один из указателей, придёт к другому.
     * Память O(1) используется только набор данных.
     *
     * @param height int[]
     */
    public static int trap(int[] height) {
        if (height.length == 0) return 0;
        int l = 0, r = height.length-1;
        int sum = 0;
        int ml = height[l], mr = height[r];
        while(l < r) {
            if(ml < mr) {
                l++;
                ml = Math.max(height[l], ml);
                sum += ml - height[l];
            } else {
                r--;
                mr = Math.max(height[r], mr);
                sum += mr - height[r];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] {0,2,0,3,1,0,1,3,2,1};
        System.out.println(trap(nums1));
    }
}
