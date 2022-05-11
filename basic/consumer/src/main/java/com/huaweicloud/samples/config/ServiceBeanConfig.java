package com.huaweicloud.samples.config;


import com.huaweicloud.samples.service.ConfigAService;
import com.huaweicloud.samples.service.ConfigBService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ServiceBeanConfig {
    @ConditionalOnExpression(value = "${config.service.a.enable:false}")
    @Bean
    public ConfigAService getAService() {
        return new ConfigAService();
    }


    @ConditionalOnExpression(value = "#{'false'.equals('${config.service.a.enable:false}')}")
    @Bean
    public ConfigBService getBService() {
        return new ConfigBService();
    }
}
