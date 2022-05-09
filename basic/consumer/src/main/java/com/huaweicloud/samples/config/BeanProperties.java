package com.huaweicloud.samples.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "consumer.bean")
@Data
public class BeanProperties {

    private boolean enable;
    private Class<?> beanClass;

}
