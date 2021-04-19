package com.zsw.lesson.l28;

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
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

/**
 * fork 新的虚拟机排除其他影响
 * BenchmarkMode 指标
 * State 测试类的状态。Benchmark-整个虚拟机状态，Thread-线程私有，Group-线程组私有
 * Warmup 预热
 * Measurement 正式测试，参数和 Warmup 相同
 *
 * @author ZhangShaowei on 2021/4/16 15:01
 */
@Fork(5)
@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 100, timeUnit = TimeUnit.MILLISECONDS, batchSize = 10)
@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.MILLISECONDS)
public class MyBenchmark {

    public MyBenchmark() {
        System.out.println("--------------------------------------------------------");
        System.err.println(this);
    }

    @Setup(Level.Trial)
    public void init() {
        System.out.println("init ......");
    }

    @Benchmark
    public void testMethod() {
        new Exception();
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

    public void shutdown() {

    }

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
