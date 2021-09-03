package com.zsw.base.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * 权限更改时间
 *
 * @author ZhangShaowei on 2021/5/12 15:55
 */
public class PermissionsChangedEvent extends ApplicationContextEvent {
    private static final long serialVersionUID = -3695600505095704129L;

    public PermissionsChangedEvent(ApplicationContext source) {
        super(source);
    }
}
