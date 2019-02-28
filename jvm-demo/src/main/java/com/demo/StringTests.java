package com.demo;

/**
 * @author Shaowei Zhang on 2019/2/25 1:02
 **/
public class StringTests {


    /*
      public static void s1();
        descriptor: ()V
        flags: ACC_PUBLIC, ACC_STATIC
        Code:
          stack=2, locals=2, args_size=0
             0: ldc           #2                  // String
             2: astore_0
             3: iconst_0
             4: istore_1
             5: iload_1
             6: bipush        10
             8: if_icmpge     36
            11: new           #3                  // class java/lang/StringBuilder
            14: dup
            15: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
            18: aload_0
            19: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            22: iload_1
            23: invokevirtual #6                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            26: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
            29: astore_0
            30: iinc          1, 1
            33: goto          5
            36: return
          LineNumberTable:
            line 33: 0
            line 34: 3
            line 35: 11
            line 34: 30
            line 37: 36
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
                5      31     1     i   I
                3      34     0   str   Ljava/lang/String;
     */
    public static void s1() {
        String str = "";
        for (int i = 0; i < 10; i++) {
            str = str + i;
        }
    }


    /*
     public static void s2();
        descriptor: ()V
        flags: ACC_PUBLIC, ACC_STATIC
        Code:
          stack=2, locals=2, args_size=0
             0: new           #3                  // class java/lang/StringBuilder
             3: dup
             4: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
             7: astore_0
             8: iconst_0
             9: istore_1
            10: iload_1
            11: bipush        10
            13: if_icmpge     28
            16: aload_0
            17: iload_1
            18: invokevirtual #6                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            21: pop
            22: iinc          1, 1
            25: goto          10
            28: return
          LineNumberTable:
            line 61: 0
            line 62: 8
            line 63: 16
            line 62: 22
            line 65: 28
          LocalVariableTable:
            Start  Length  Slot  Name   Signature
               10      18     1     i   I
                8      21     0    sb   Ljava/lang/StringBuilder;
     */
    public static void s2() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(i);
        }
    }



}
