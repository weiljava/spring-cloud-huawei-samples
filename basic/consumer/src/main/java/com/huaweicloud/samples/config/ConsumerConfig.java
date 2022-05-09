package com.huaweicloud.samples.config;


import com.huaweicloud.samples.service.OtherUserServiceImpl;
import com.huaweicloud.samples.service.UserService;
import com.huaweicloud.samples.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Configuration
public class ConsumerConfig {

    @Value("${consumer.bean.enable}")
    private boolean serviceImpl;

    // 默认不注入，如果yml配置里有 logging.level.com.huaweicloud.samples.config.MyClient 才注入
    @Bean
    @ConditionalOnProperty("logging.level.com.huaweicloud.samples.config.CustomLogFeignClient")
    CustomLogFeignClient getClient() throws NoSuchAlgorithmException, KeyManagementException {
        // 忽略SSL校验
        SSLContext ctx = SSLContext.getInstance("SSL");
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        ctx.init(null, new TrustManager[]{tm}, null);
        return new CustomLogFeignClient(ctx.getSocketFactory(), (hostname, sslSession) -> true);
    }

//    /**
//     * 通过固定配置来创建实现bean
//     */
//    @Bean
//    public UserService getUserService() {
//        if (serviceImpl) {
//            return new UserServiceImpl();
//        } else {
//            return new OtherUserServiceImpl();
//        }
//    }

}
