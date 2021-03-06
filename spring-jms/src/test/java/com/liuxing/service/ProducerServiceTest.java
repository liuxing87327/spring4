package com.liuxing.service;

import com.liuxing.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.jms.Destination;
import java.util.HashSet;
import java.util.Set;

/**
 * @title：ProducerServiceTest
 * @author：liuxing
 * @date：2015-07-03 01:06
 */
public class ProducerServiceTest extends BaseTest {

    @Autowired
    private ProducerService producerService;
    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;

    @Test
    public void testSend() {
        Set<Integer> list = new HashSet<>();
        for (int i = 1; i < 1000000; i++) {
            list.add(i);
        }

        list.parallelStream().forEach(i -> {
            producerService.sendMessage(destination, "你好，生产者！这是消息：" + i);
        });
    }

}