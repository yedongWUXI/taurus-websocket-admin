package com.kaituo.comparison.back.core.config;

import com.kaituo.comparison.back.core.websocket.WebSocketCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * webSocket配置类
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public WebSocketCollector websocketCollector() {
        return new WebSocketCollector();
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
