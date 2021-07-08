package com.kaituo.comparison.back.core.listener;

import com.kaituo.comparison.back.core.websocket.WebSocketCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * 实现ApplicationListener,自定义处理事件的能力
 *
 */
@Component
@Slf4j
public class DataChangedEventListener implements ApplicationListener<DataChangedEvent> {


    //暂时只用到 groupKey
    @Override
    public void onApplicationEvent(final DataChangedEvent event) {
        log.info("监听事件开始");

        switch (event.getGroupKey()) {
            case "delete":
                log.info("delete事件 发送websocket,数据：{}",event.getSource().toString());
                WebSocketCollector.send(event.getSource().toString(), event.getEventType());
                break;
            case "add":
                log.info("add事件 发送websocket,数据：{}",event.getSource().toString());
                WebSocketCollector.send(event.getSource().toString(), event.getEventType());
                break;
            case "update":
                log.info("update事件 发送websocket,数据：{}",event.getSource().toString());
                WebSocketCollector.send(event.getSource().toString(), event.getEventType());
                break;
            case "init":
                log.info("init事件 发送websocket,数据：{}",event.getSource().toString());
                WebSocketCollector.send(event.getSource().toString(), event.getEventType());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + event.getGroupKey());
        }


    }


}
