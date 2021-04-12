package com.zsw.lesson.l2;

/**
 * @author ZhangShaowei on 2021/4/9 13:52
 */
public class L2 {

    public static void main(String[] args) {
        // 直接编译会报错
//        boolean 吃过饭没 = 2;
//        if (吃过饭没) {
//            System.out.println("吃了");
//        }
//        if (true == 吃过饭没) {
//            System.out.println("真吃了");
//        }

        float f = Float.intBitsToFloat(0X80000000);
        System.out.println(f);
        System.err.println(5.5f / f);

        f = -0.0f;
        System.out.println(f);
        f = +0.0f;
        System.out.println(f);

        float 正无穷 = Float.intBitsToFloat(0x7F800000);
        float 负无穷 = Float.intBitsToFloat(0xFF800000);

        System.out.println("正无穷：" + 正无穷);
        System.out.println("负无穷：" + 负无穷);
        int max = 0x7F800000 + 1;
        System.out.println("max: " + max);

        System.out.println("正无穷 + 1 = 0x7F800001 是这样：" + 0x7F800001);

        System.out.println(正无穷 != 0x7F800000);
        System.out.println(正无穷 == 0x7F800000);

        System.out.println(Float.NaN);
        System.out.println(Double.NaN);

    }

}
