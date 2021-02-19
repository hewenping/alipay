package com.java98k.alipayDemo.config;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.java98k.alipayDemo.bean.AlipayBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author 李雷（KyLin）
 * @Desc 支付宝常用类
 * @Date 2019/07/09
 */
@Component
@PropertySource("classpath:config/alipay.properties")
public class AlipayUtil {


    /**
     * 支付测试
     * @param alipayBean
     * @return
     * @throws AlipayApiException
     */
    public String payTest(AlipayBean alipayBean) throws AlipayApiException {

        //订单参数组装
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setNotifyUrl(notifyUrl);
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));

        //接口参数调用
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                appId,
                merchantPrivateKey,
                FORMAT,
                charset,
                alipayPublicKey,
                signType);

        //接口调用<原理就是返回的form表单。然后执行而已>
        return alipayClient.pageExecute(alipayRequest).getBody();

    }

    private final String FORMAT = "json";
    @Value("${app_id}")
    private String appId;
    @Value("${gatewayUrl}")
    private String gatewayUrl;
    @Value("${merchant_private_key}")
    private String merchantPrivateKey;
    @Value("${charset}")
    private String charset;
    @Value("${alipay_public_key}")
    private String alipayPublicKey;
    @Value("${sign_type}")
    private String signType;
    @Value("${return_url}")
    private String returnUrl;
    @Value("${notify_url}")
    private String notifyUrl;
}
