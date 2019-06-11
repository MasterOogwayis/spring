package com.zsw.metrics.micrometer.config;

/**
 * spring boot 1.x
 *
 * 停机配置，shutdown 后立即向 eureka 注销服务，然后等待所有请求已处理再关机，超出等待时间则强行关机
 * <p>
 * endpoints.shutdown.enabled=true
 * <p>
 * 停机方法:
 * 1. 请求 /shutdown 接口，可能涉及到权限问题
 * 2. 使用 JMX 调用 ShutdownEndpoint.shutdown() 方法  （推荐）
 * 3. spring.application.admin.enabled=true SpringApplicationAdminMXBean 提供的 shutdown，这是个阻塞方法，会导致最终强行关机
 *
 * @author zsw on 2019/4/18 11:24
 **/
//@Slf4j
//@Configuration
//public class ShutdownConfiguration {
//
//    @Bean
//    public EmbeddedServletContainerCustomizer tomcatCustomizer() {
//        return container -> {
//            if (container instanceof TomcatEmbeddedServletContainerFactory) {
//                ((TomcatEmbeddedServletContainerFactory) container).addConnectorCustomizers(gracefulShutdown());
//            }
//
//        };
//    }
//
//    @Bean
//    public GracefulShutdown gracefulShutdown() {
//        return new GracefulShutdown();
//    }
//
//
//    public static class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {
//
//        private static final String shutdownTimeoutKey = "endpoints.shutdown.waitedTime";
//
//        private volatile long shutdownTimeout = 30L;
//
//        private volatile Connector connector;
//
//        @Override
//        public void customize(Connector connector) {
//            this.connector = connector;
//        }
//
//        @Override
//        public void onApplicationEvent(ContextClosedEvent event) {
//            String propertyTime = event.getApplicationContext().getEnvironment().getProperty(shutdownTimeoutKey);
//            if (StringUtils.hasText(propertyTime)) {
//                try {
//                    this.shutdownTimeout = Long.parseLong(propertyTime);
//                } catch (Exception e) {
//                    // doNothing...
//                }
//            }
//            this.connector.pause();
//            Executor executor = this.connector.getProtocolHandler().getExecutor();
//            if (executor instanceof ThreadPoolExecutor) {
//                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
//                try {
//                    threadPoolExecutor.shutdown();
//                    boolean waiting = false;
//                    for (int i = 0; i < shutdownTimeout; i++) {
//                        // 等待所有请求处理完
//                        if (threadPoolExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
//                            log.info("All requests have been processed, shutdown gracefully...");
//                            waiting = true;
//                            break;
//                        }
//                        log.warn("Waiting for all requests to be processed, forceful shutdown in {}s", this.shutdownTimeout - i);
//                    }
//                    if (!waiting) {
//                        log.warn("Tomcat thread pool did not shut down gracefully within {} seconds. Proceeding with forceful shutdown", this.shutdownTimeout);
//                        threadPoolExecutor.shutdownNow();
//                    }
//                } catch (InterruptedException ex) {
//                    Thread.currentThread().interrupt();
//                    threadPoolExecutor.shutdownNow();
//                }
//            }
//        }
//    }
//
//
//}
