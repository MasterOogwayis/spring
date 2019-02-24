package com;

/**
 * @author Shaowei Zhang on 2019/2/25 1:21
 **/
public class TestFinally {

    public static void main(String[] args) {
        System.out.println(test());
    }


    /**
     * @return
     */
    /*
      public static java.lang.String test();
        descriptor: ()Ljava/lang/String;
        flags: ACC_PUBLIC, ACC_STATIC
        Code:
          stack=1, locals=3, args_size=0
             0: ldc           #5                  // String hello
             2: astore_0
             3: aload_0
             4: astore_1
             5: ldc           #6                  // String world
             7: astore_0
             8: aload_1
             9: areturn
            10: astore_2
            11: ldc           #6                  // String world
            13: astore_0
            14: aload_2
            15: athrow
          Exception table:
             from    to  target type
                 3     5    10   any
          LineNumberTable:
            line 14: 0
            line 17: 3
            line 19: 5
            line 17: 8
            line 19: 10
            line 20: 14
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                3      13     0   str   Ljava/lang/String;
     */
    public static String test() {
        String str = "hello";
        try {
            // 逃逸了
            return str;
        } finally {
            // 栈上分配销毁了
            str = "world";
        }


    }


}
