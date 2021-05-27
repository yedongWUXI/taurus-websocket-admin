package com.kaituo.comparison.back.core.listener;

import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * Data change event.
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

    /**
     * Gets event type.
     *
     * @return the event type
     */
    String getEventType() {
        return eventType;
    }

    @Override
    public List<?> getSource() {
        return (List<?>) super.getSource();
    }

    /**
     * Gets group key.
     *
     * @return the group key
     */
    public String getGroupKey() {
        return this.groupKey;
    }

}
