package com.java98k.alipayDemo.controller;

import com.alipay.api.AlipayApiException;
import com.java98k.alipayDemo.bean.AlipayBean;
import com.java98k.alipayDemo.service.PayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/* 订单接口 */
@RestController()
@RequestMapping("order")
public class OrderController {
    @Resource
    private PayService payService;//调用支付服务

    /*阿里支付*/
    @PostMapping(value = "alipay")
    public String alipay(String outTradeNo,String subject,StringBuffer totalAmount,String body) throws AlipayApiException {

        return payService.aliPay(AlipayBean.builder()
                .body(body)
                .out_trade_no(outTradeNo)
                .total_amount(totalAmount)
                .subject(subject).build());
    }
}
