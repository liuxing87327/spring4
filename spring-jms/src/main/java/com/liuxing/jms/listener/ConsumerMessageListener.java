package com.liuxing.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @title：ConsumerMessageListener
 * @author：liuxing
 * @date：2015-07-03 00:56
 */
public class ConsumerMessageListener implements MessageListener {

    public void onMessage(Message message) {
        //这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换
        TextMessage textMsg = (TextMessage) message;
        System.out.println("接收到一个纯文本消息。");
        try {
            System.out.println("消息内容是：" + textMsg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
