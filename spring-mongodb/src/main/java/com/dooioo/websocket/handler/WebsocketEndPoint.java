package com.dooioo.websocket.handler;

import org.joda.time.DateTime;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 功能说明：websocket处理类
 * 作者：liuxing(2014-11-14 04:20)
 */
public class WebsocketEndPoint extends TextWebSocketHandler {

    /**
     * 收到客户端消息，包含所有类型
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    /**
     * 处理文本消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        TextMessage returnMessage = new TextMessage(message.getPayload() + "来自服务器 " + DateTime.now().toString("yyyy-mm-dd HH:mm:ss.SSS"));
        session.sendMessage(returnMessage);
    }

    /**
     * 处理二进制消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        super.handleBinaryMessage(session, message);
    }

    /**
     * 处理pong消息（ping？）
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        super.handlePongMessage(session, message);
    }

    /**
     * 处理异常
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    /**
     * 建立连接
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println(session);
    }

    /**
     * 断开连接
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println(status);
    }

}