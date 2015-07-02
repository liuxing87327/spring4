package com.liuxing.service;

import javax.jms.Destination;

/**
 * @title：ProducerService
 * @author：liuxing
 * @date：2015-07-03 00:53
 */
public interface ProducerService {

    /**
     * 发送普通的纯文本消息
     *
     * @param destination
     * @param message
     */
    void sendMessage(Destination destination, String message);

}
