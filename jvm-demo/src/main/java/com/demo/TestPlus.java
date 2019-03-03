package com.demo;

/**
 * i++ 和 ++i 最终编译的字节码是一样的 当然效率就一样咯
 *
 * @author Shaowei Zhang on 2019/2/25 1:11
 **/
public class TestPlus {

    /*
      public static void p1();
        descriptor: ()V
        flags: ACC_PUBLIC, ACC_STATIC
        Code:
          stack=2, locals=1, args_size=0
             0: iconst_0
             1: istore_0
             2: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
             5: iload_0
             6: iinc          0, 1
             9: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
            12: return
          LineNumberTable:
            line 12: 0
            line 14: 2
            line 16: 12
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                2      11     0     i   I
     */
    public static void p1() {
        int i = 0;

        System.out.println(i++);

    }

    /*
     public static void p2();
        descriptor: ()V
        flags: ACC_PUBLIC, ACC_STATIC
        Code:
          stack=2, locals=1, args_size=0
             0: iconst_0
             1: istore_0
             2: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
             5: iinc          0, 1
             8: iload_0
             9: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
            12: return
          LineNumberTable:
            line 22: 0
            line 24: 2
            line 26: 12
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                2      11     0     i   I
     */
    public static void p2() {
        int i = 0;

        System.out.println(++i);

    }


}
