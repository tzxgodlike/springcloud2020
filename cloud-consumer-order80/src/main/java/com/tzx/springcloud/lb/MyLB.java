package com.tzx.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBanlancer{

    //原子类
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            //超过最大值，为0，重新计数 2147483647 Integer.MAX_VALUE
            next = current >= 2147483647 ? 0 : current + 1;
            //自旋锁
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("*****第几次访问，次数next："+next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstance) {
        //调用一次就自增一次
        int index = getAndIncrement() % serviceInstance.size();
        return serviceInstance.get(index);
    }

}
