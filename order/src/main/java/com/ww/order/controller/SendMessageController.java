/*
package com.ww.order.controller;

import com.ww.order.mq.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

*/
/**
 * @Description -
 * @Author 查旺旺
 * @Date 2019/3/29 14:10
 *//*

@RestController
public class SendMessageController {

    @Autowired
    StreamClient streamClient;

    @GetMapping("sendMessager")
    public void sendMessager(){
        streamClient.output().send(MessageBuilder.withPayload("this is message").build());
    }

}
*/
