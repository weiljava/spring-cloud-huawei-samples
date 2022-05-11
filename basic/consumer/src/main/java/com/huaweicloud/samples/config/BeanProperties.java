package com.huaweicloud.samples.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "consumer.bean")
@Data
public class BeanProperties {

    private boolean enable;
    private Class<?> beanClass;

    @Value("${consumer.bean.flag.enable}")
    private boolean flag;

}
