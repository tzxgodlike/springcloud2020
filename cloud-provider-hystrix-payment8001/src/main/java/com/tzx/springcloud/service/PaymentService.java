package com.tzx.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {
    public String paymentInfo_OK(Integer id);
    public String paymentInfo_Timeout(Integer id);
    public String paymentInfo_TimeoutHandler(Integer id);
    public String paymentCircuitBreaker(Integer id);
    public String paymentCircuitBreaker_fallback(Integer id);
}
