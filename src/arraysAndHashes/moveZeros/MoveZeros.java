package arraysAndHashes.moveZeros;

public class MoveZeros {
    /**
     * На вход подаётся массив состоящий из цифр и нулей.
     * Исправить массив, чтобы нули были в конце,а цифры были в том же порядке в начале.
     *
     * Идея: два указателя + замена.
     * Указатель слева находится на начале и будет указывать куда мы будет ставить не нули.
     * Правый указатель будет двигаться по всему массиву и если встретит != 0, то цифру переносим на левый указатель.
     *
     * r = 0; r->nums.length:
     *  Если число != 0:
     *      Если левый, не равен правому: (не меняем само себя)
     *          в tmp сохраняет число по левому
     *          Число по левому меняем на число по правому
     *          Число по правому меняем на tmp
     *  двигаем левый указатель
     *
     * Начинаем с места где остановился левый указатель, заполняем нулями
     *
     * Сложность:
     *  по времени: O(n)
     *  по памяти: O(1)
     *
     * @param nums ListNode
     */
    public static void moveZeros(int[] nums) {
        int left = 0;
        for(int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                if (left != right) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                }
                left++;
            }
        }
    }
    public static void main(String[] args) {
        int[] ar = {0,1,2,3,0,5,6,0};
        int[] ar2 = {7,8,0,9,0,10};
        moveZeros(ar);
        for (int c : ar) System.out.println(c);
        moveZeros(ar2);
        for (int c : ar2) System.out.println(c);
    }
}
