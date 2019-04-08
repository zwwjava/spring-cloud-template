package com.ww.order.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/3/27 14:02
 */
@Slf4j
@Component
public class MQReceiver {

//    @RabbitListener(queues = "myQueue")
//    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myOrderQueue"),
            exchange = @Exchange("order"),
            key = "myOrderQueueKey"
    ))
    public void process(String message) {
        log.info("mqOrderReceiver{}:" + message);
    }

}
