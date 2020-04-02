package com.tzx.springcloud.service.impl;

import com.tzx.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

//这不是传统的service,这是和rabbitmq打交道的，不需要加注解@Service
//这里不掉dao，掉消息中间件的service
//信道channel和exchange绑定在一起
@EnableBinding(Source.class)    //定义消息的推送管道
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;  //消息发送管道
    @Override
    public String send() {
        String serial  = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("===================serial:"+serial);
        return null;
    }
}
