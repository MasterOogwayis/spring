package com.zsw.lesson.l28;

import lombok.SneakyThrows;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

/**
 * fork 允许开发人员指定所要 Fork 出的 Java 虚拟机的数目。新的虚拟机排除其他影响
 * BenchmarkMode 指标
 * State 允许配置测试程序的状态。
 * 测试前对程序状态的初始化以及测试后对程序状态的恢复或者校验可分别通过@Setup和@TearDown来实现。
 * Benchmark-整个虚拟机状态，Thread-线程私有，Group-线程组私有
 * Warmup 允许配置预热迭代或者测试迭代的数目，每个迭代的时间以及每个操作包含多少次对测试方法的调用。
 * Measurement 正式测试，参数和 Warmup 相同
 * CompilerControl 及时编译，控制方法内联
 *
 * @author ZhangShaowei on 2021/4/16 15:01
 */
@Fork(0)
//@Fork(jvmArgs = "-Djava.lang.invoke.stringConcat=BC_SB", value = 0)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS, batchSize = 10)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
public class MyBenchmark {


    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(123);
//        Options opt = new OptionsBuilder()
//                .include(MyBenchmark.class.getSimpleName())
////                .include(MyBenchmark.class.getSimpleName()+".*testMethod")
//                .warmupIterations(5)
//                .warmupTime(TimeValue.seconds(2))
//                .measurementIterations(5)
//                .measurementTime(TimeValue.seconds(2))
//                .forks(0)
//                .build();
//        new Runner(opt).run();
    }

    @Setup(Level.Trial)
    public void init() {
        System.out.println("----------------------------------------- init ......");
    }

    @TearDown
    public void shutdown() {
        System.err.println("----------------------------------------- shutdown ......");
    }

    @Benchmark
    public void testMethod(MyStateBenchmark stateBenchmark, Blackhole bh) {
        new Exception(stateBenchmark.message);
        bh.consume(stateBenchmark);
    }

    @State(Scope.Benchmark)
    public static class MyStateBenchmark {
        String message = "test";
    }

//    ConfigurableApplicationContext context;

//    @Setup(Level.Trial)
//    public void init() {
//        context = SpringApplication.run(TestApplication.class);
//    }
//    @Setup(Level.Trial)
//    public void init() {
//        context = SpringApplication.run(GatewayApplication.class);
//        authenticationService = context.getBean(AuthenticationService.class);
//    }

    //    public static void main(String[] args) throws RunnerException {
//        Options options = new OptionsBuilder()
//                .include(BenchmarkTests.class.getSimpleName())
////                .warmupIterations(2)
////                .measurementIterations(10)
//                .threads(1)
//                .measurementTime(TimeValue.seconds(1))
//                .build();
//        new Runner(options).run();
//    }
//
//    ConfigurableApplicationContext context;
//
//    AuthenticationService authenticationService;
//
//
//
//
//    @Param({"ROLE_SUPER_ADMIN",
//            "ROLE_ADMIN",
//            "ROLE_PC_ADMIN",
//            "ROLE_PC_OPER",
//            "ROLE_TC_ADMIN",
//            "ROLE_TC_OPER",
//            "ROLE_SC_ADMIN",
//            "ROLE_RC_ADMIN",
//            "ROLE_EPC_USER",
//            "ROLE_EPC_ADMIN",
//            "ROLE_SUB_ADMIN"})
//    private String mark;
//
//    @Benchmark
//    public void test1() {
//        this.authenticationService.authenticate("/smc/actuator/info", "POST", Collections.singletonList(this.mark));
//    }


}
