package com.kaituo.comparison.back.core.listener;

import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * Data change 事件实体 ,用于传输数据。
 *
 * eventType和groupKey都是只是分类,是自定义的。可以用在业务逻辑上
 *
 * 具体传输数据是在 父类的 source上
 *
 *
 */
public class DataChangedEvent extends ApplicationEvent {

    private String eventType;

    private String groupKey;

    public DataChangedEvent(final String groupKey, final String type, final List<?> source) {
        super(source);
        this.eventType = type;
        this.groupKey = groupKey;
    }

    String getEventType() {
        return eventType;
    }

    @Override
    public List<?> getSource() {
        return (List<?>) super.getSource();
    }

    public String getGroupKey() {
        return this.groupKey;
    }

}
