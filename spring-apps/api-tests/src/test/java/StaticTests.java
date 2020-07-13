import java.util.Arrays;

/**
 * @author ZhangShaowei on 2020/7/9 10:39
 */
public class StaticTests {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] copy = new int[arr.length + 1];
        System.arraycopy(arr, 0, copy, 1, copy.length - 1);
        copy[0] = 10;
        System.out.println(Arrays.toString(copy));
    }

}
