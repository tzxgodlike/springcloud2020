package com.tzx.springcloud.controller;


import com.tzx.springcloud.service.IMessageProvider;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.naming.ldap.PagedResultsControl;

@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }
}
