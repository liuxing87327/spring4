package com.dooioo.websocket.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能说明：TelSocketSessionUtils
 * 作者：liuxing(2014-12-26 02:32)
 */
public class SocketSessionUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketSessionUtils.class);

    private static Map<String, WebSocketSession> clients = new ConcurrentHashMap<>();

    /**
     * 保存一个连接
     * @param relationId
     * @param session
     */
    public static void add(String relationId, WebSocketSession session){
        clients.put(relationId, session);
    }

    /**
     * 获取一个连接
     * @param relationId
     * @return
     */
    public static WebSocketSession get(String relationId){
        return clients.get(relationId);
    }

    /**
     * 移除一个连接
     * @param relationId
     */
    public static void remove(String relationId) throws IOException {
        clients.remove(relationId);
    }

    /**
     * 判断是否有效连接
     * 判断是否存在
     * 判断连接是否开启
     * 无效的进行清除
     * @param relationId
     * @return
     */
    public static boolean hasConnection(String relationId) {
        if (clients.containsKey(relationId)) {
            return true;
        }

        return false;
    }

    /**
     * 获取连接数的数量
     * @return
     */
    public static int getSize() {
        return clients.size();
    }

    /**
     * 发送消息到客户端
     * @param relationId
     * @param message
     * @throws Exception
     */
    public static void sendMessage(String relationId, String message) throws Exception {
        if (!hasConnection(relationId)) {
            throw new NullPointerException(relationId + " connection does not exist");
        }

        WebSocketSession session = get(relationId);
        try {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        } catch (IOException e) {
            LOGGER.error("websocket sendMessage exception: " + relationId);
            LOGGER.error(e.getMessage(), e);
            clients.remove(relationId);
        }
    }

}
