package com.tzx.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBanlancer {

    /*
    传进去所有服务的集合 根据规则要使用的那个服务
     */
    ServiceInstance instances (List<ServiceInstance> serviceInstances);
}
