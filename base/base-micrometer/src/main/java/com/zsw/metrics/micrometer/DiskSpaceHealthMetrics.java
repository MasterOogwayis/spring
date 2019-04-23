package com.zsw.metrics.micrometer;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.boot.actuate.autoconfigure.system.DiskSpaceHealthIndicatorProperties;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 磁盘信息 metrics 格式化到 prometheus
 * <p>
 * DiskSpaceHealthMetrics
 *
 * @author ZhangShaowei on 2018/4/10 17:15
 **/
public class DiskSpaceHealthMetrics {

    private Map<String, Supplier<Long>> functions;

    private MeterRegistry registry;


    public DiskSpaceHealthMetrics(MeterRegistry registry, DiskSpaceHealthIndicatorProperties properties) {
        this.registry = registry;
        this.functions = new HashMap<>();
        // 磁盘 总容量 byte
        this.functions.put("total", properties.getPath()::getTotalSpace);
        // 磁盘 可用容量 byte
        this.functions.put("free", properties.getPath()::getUsableSpace);
        // 最小剩余,注意单位是 mb
        this.functions.put("threshold", new Supplier<Long>() {
            @Override
            public Long get() {
                return properties.getThreshold().toMegabytes();
            }
        });

    }

    @PostConstruct
    public void config() {
        this.functions.forEach((key, value) -> {
            this.registry.gauge(
                    "system_disk",
                    Collections.singletonList(Tag.of("name", key)),
                    value,
                    Supplier::get);
        });
    }

}
