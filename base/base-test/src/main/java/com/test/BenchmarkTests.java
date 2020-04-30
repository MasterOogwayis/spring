package com.test;

import com.TestApplication;
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
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2020/4/8 14:17
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(2)
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 10, time = 5)
public class BenchmarkTests {

    ConfigurableApplicationContext context;

    @Setup(Level.Trial)
    public void init() {
        context = SpringApplication.run(TestApplication.class);
    }

    public void shutdown() {

    }

    @Benchmark
    public void test1() {

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
//    @Setup(Level.Trial)
//    public void init() {
//        context = SpringApplication.run(GatewayApplication.class);
//        authenticationService = context.getBean(AuthenticationService.class);
//    }
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
