package com.kaituo.comparison.back.core.config;

import com.kaituo.comparison.back.core.websocket.WebsocketCollector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * The type Data sync configuration.
 */
@Configuration
public class DataSyncConfiguration {


    /**
     * The WebsocketListener.
     */
    @Configuration
//    @ConditionalOnMissingBean(DataChangedListener.class)
    @ConditionalOnProperty(name = "soul.sync.strategy", havingValue = "websocket", matchIfMissing = true)
    static class WebsocketListener {

        /**
         * Config event listener data changed listener.
         *
         * @return the data changed listener
         */
//        @Bean
//        public DataChangedListener dataChangedListener() {
//            return new WebsocketDataChangedListener();
//        }

        /**
         * Websocket collector websocket collector.
         *
         * @return the websocket collector
         */
        @Bean
        public WebsocketCollector websocketCollector() {
            return new WebsocketCollector();
        }

        /**
         * Server endpoint exporter server endpoint exporter.
         *
         * @return the server endpoint exporter
         */
        @Bean
        public ServerEndpointExporter serverEndpointExporter() {
            return new ServerEndpointExporter();
        }
    }
}
