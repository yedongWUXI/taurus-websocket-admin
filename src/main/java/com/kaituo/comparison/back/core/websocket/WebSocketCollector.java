/*
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.kaituo.comparison.back.core.websocket;

import com.kaituo.comparison.back.common.bean.DataEventTypeEnum;
import com.kaituo.comparison.back.common.util.spring.SpringBeanUtils;
import com.kaituo.comparison.back.core.service.SyncDataServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * The type Websocket data changed listener.
 *
 * 开启websocket服务端
 *
 * @since 2.0.0
 */
@ServerEndpoint("/websocket")
public class WebSocketCollector {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketCollector.class);

    private static final Set<Session> SESSION_SET = new CopyOnWriteArraySet<>();

    private static Session session;

    /**
     * On open.
     *
     * @param session the session
     */
    @OnOpen
    public void onOpen(final Session session) {
        LOGGER.info("websocket on open successful....");
        SESSION_SET.add(session);
    }

    /**
     * On message.
     *
     * 接收 taurus-websocket-web 的message,并记录它的session
     *
     * @param message the message       服务端给客户端发消息是通过session来完成的，想要指定用户发送消息就得拿到对方用户的session，用它来发送消息
     * @param session the session
     */
    @OnMessage
    public void onMessage(final String message, final Session session) {
        //给taurus-websocket-web发送初始化数据
        if (message.equals(DataEventTypeEnum.INIT.name())) {
            WebSocketCollector.session = session;
            SpringBeanUtils.getInstance().getBean(SyncDataServiceImpl.class).syncAll("");
        }
    }

    /**
     * On close.
     *
     * @param session the session
     */
    @OnClose
    public void onClose(final Session session) {
        SESSION_SET.remove(session);
        WebSocketCollector.session = null;
    }

    /**
     * On error.
     *
     * @param session the session
     * @param error   the error
     */
    @OnError
    public void onError(final Session session, final Throwable error) {
        SESSION_SET.remove(session);
        WebSocketCollector.session = null;
        LOGGER.error("websocket collection error:", error);
    }

    /**
     * Send.
     * 给记录的客户端发送消息
     *
     * @param message the message
     * @param type    the type
     */
    public static void send(final String message, final String type) {
        if (StringUtils.isNotBlank(message)) {
            for (Session session : SESSION_SET) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    LOGGER.error("websocket send result is exception :", e);
                }
            }
        }
    }
}
