package com.dooioo.websocket.handler;

import com.dooioo.websocket.utils.SocketSessionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 功能说明：WebSocket处理器
 * 可以继承 {@link TextWebSocketHandler}/{@link BinaryWebSocketHandler}，
 * 或者简单的实现{@link org.springframework.web.socket.WebSocketHandler}接口
 * 作者：liuxing(2015-01-25 03:42)
 */
public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketHandler.class);

    /**
     * 建立连接
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String relationId = MapUtils.getString(session.getAttributes(), "relationId");
        SocketSessionUtils.add(relationId, session);
        LOGGER.debug("新的连接：{}", relationId);
    }

    /**
     * 收到客户端消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String relationId = MapUtils.getString(session.getAttributes(), "relationId");
        SocketSessionUtils.sendMessage(relationId, "【来自服务器】：" + message.getPayload().toString());
    }

    /**
     * 出现异常
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String relationId = MapUtils.getString(session.getAttributes(), "relationId");

        LOGGER.error("websocket connection exception: {}", relationId);
        LOGGER.error(exception.getMessage(), exception);

        SocketSessionUtils.remove(relationId);
    }

    /**
     * 连接关闭
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String relationId = MapUtils.getString(session.getAttributes(), "relationId");
        SocketSessionUtils.remove(relationId);
        LOGGER.debug("断开连接：{}", relationId);
    }

    /**
     * 是否分段发送消息
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}