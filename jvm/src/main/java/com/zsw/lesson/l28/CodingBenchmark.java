package com.zsw.lesson.l28;

import lombok.SneakyThrows;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

/**
 * @author ZhangShaowei on 2021/6/1 13:51
 */
public class CodingBenchmark {

    @SneakyThrows
    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
//                .include(MyBenchmark.class.getSimpleName()+".*testMethod")
                .warmupIterations(5)
                .warmupTime(TimeValue.seconds(2))
                .measurementIterations(5)
                .measurementTime(TimeValue.seconds(2))
                .forks(0)
                .build();
        new Runner(opt).run();
    }

}
