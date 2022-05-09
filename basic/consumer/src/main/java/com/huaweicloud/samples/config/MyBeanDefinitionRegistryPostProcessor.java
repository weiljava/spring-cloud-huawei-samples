package com.huaweicloud.samples.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Map;

@Component
@Slf4j
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    private BeanProperties beanProperties;


    // 根据配置信息进行逻辑控制 动态注册BeanDefintion从而创建Bean
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        GenericBeanDefinition beandefinition = new GenericBeanDefinition();
        beandefinition.setBeanClass(beanProperties.getBeanClass());
        //如果实例化对象有其他参数可以进行配置
//        Map<String, Object> properties = beanProperties.getProperties();
//        for (String propertyName : properties.keySet()) {
//
//            beandefinition.getPropertyValues().add(propertyName, properties.get(propertyName));
//            beandefinition.getPropertyValues().add(propertyName, properties.get(propertyName));
//
//        }
        registry.registerBeanDefinition(beandefinition.getBeanClass().getName(), beandefinition);
    }

    // 绑定配置信息
    @Override
    public void setEnvironment(Environment environment) {
        BindResult<BeanProperties> bindResult = Binder.get(environment).bind("consumer.bean", BeanProperties.class);
        beanProperties = bindResult.get();
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        Iterator<String> it= beanFactory.getBeanNamesIterator();
//        while(it.hasNext()){
//            log.info(it.next());
//        }
    }

}