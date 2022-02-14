package com.zsw;

import com.zsw.entries.SingletonTarget;
import com.zsw.utils.IOUtils;
import lombok.SneakyThrows;

/**
 * @author Shaowei Zhang on 2022/2/14 23:19
 */
public class SingletonTests {

    @SneakyThrows
    public static void main(String[] args) {
        SingletonTarget target = SingletonTarget.getInstance();

        System.out.println(target);

        SingletonTarget copy = IOUtils.copy(target);

        System.err.println(copy);

    }

}
