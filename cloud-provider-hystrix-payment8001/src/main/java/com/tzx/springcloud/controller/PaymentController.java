package com.tzx.springcloud.controller;

import com.tzx.springcloud.service.PaymentService;
import com.tzx.springcloud.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /*
    正常访问
     */
    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String reslut = paymentService.paymentInfo_OK(id);
        log.info("==========result:"+reslut);
        return reslut;
    }
    /*
    出现异常
     */
    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        String reslut = paymentService.paymentInfo_Timeout(id);
        log.info("==========result:"+reslut);
        return reslut;
    }

    /*
    服务熔断
     */
    @GetMapping(value = "/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("-------result"+result);
        return result;
    }
}
