package l1;

/**
 * @author ZhangShaowei on 2021/4/9 11:06
 */
public class Foo {

    public static void main(String[] args) {

        // jvm 将 boolean 当做数字处理，不等于 0 就是 true
        // 反编译字节码文件后就能看到
        boolean flag = true;
        if (flag) {
            System.out.println("Hello, Java!");
        }

        if (flag == true) {
            System.out.println("Hello, JVM!");
        }


    }

}
