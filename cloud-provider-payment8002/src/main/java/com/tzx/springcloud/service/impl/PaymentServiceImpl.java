package com.tzx.springcloud.service.impl;

import com.tzx.springcloud.dao.PaymentDao;
import com.tzx.springcloud.entities.Payment;
import com.tzx.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(@Param("id")Long id) {
        return paymentDao.getPaymentById(id);
    }
}
