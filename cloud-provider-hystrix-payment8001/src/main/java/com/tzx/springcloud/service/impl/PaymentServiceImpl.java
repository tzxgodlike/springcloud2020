package com.tzx.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tzx.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    /*
    正常访问
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK,id: "+id
                +"\t"+"hahahahaha";
    }
    /*
    出现异常
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_Timeout(Integer id){
        int timeNumber = 3;
        //int age = 10/0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_Timeout,id: "+id
                +"\t"+"hahahahaha"+"耗时"+timeNumber+"秒";
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_TimeoutHandler,id: "+id
                +"\t"+"出事了我是fallback";
    }

    /*
    服务熔断
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//短路多久之后开始尝试恢复
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id<0) {
            throw new RuntimeException("******id 不能为负数");
        }
        //hutool工具包 生成一个随机ID 不带-
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id不能为负数，请稍后再试QAQ  id: "+id;
    }
}
