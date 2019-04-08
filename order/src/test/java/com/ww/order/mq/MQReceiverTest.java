package com.ww.order.mq;

import com.ww.order.OrderApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.junit.Assert.*;

@Component
@Slf4j
public class MQReceiverTest extends OrderApplicationTests{

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send() throws Exception {
        log.info("发送 MQ 消息");
        amqpTemplate.convertAndSend("myQueue", new Date());
    }

}