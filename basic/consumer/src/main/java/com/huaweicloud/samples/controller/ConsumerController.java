package com.huaweicloud.samples.controller;

import com.huaweicloud.samples.client.FeignConsumerService;
import com.huaweicloud.samples.config.BeanProperties;
import com.huaweicloud.samples.domain.User;
import com.huaweicloud.samples.service.UserService;
import com.huaweicloud.samples.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RefreshScope
@Slf4j
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FeignConsumerService feignConsumerService;

    @Resource
//    @Qualifier("userServiceImpl")
    private UserService userService;

    @Value("${consumer.config}")
    private String configValue;

    @Resource
    private BeanProperties beanProperties;

    // consumer service which delegate the implementation to provider service.
    @GetMapping("/sayHello")
    public String sayHello(@RequestParam("name") String name) {
        return restTemplate.getForObject("http://basic-provider/sayHello?name={1}", String.class, name);
    }

    @GetMapping("/getConfig")
    public String getConfig() {
        return restTemplate.getForObject("http://basic-provider/sayHello?name={1}", String.class, configValue);
    }

    @GetMapping("/getConfig2")
    public String getConfig2() {
        return configValue;
    }


    @GetMapping("/sayHelloFeign")
    public String sayHelloFeign(@RequestParam("name") String name) {
        return feignConsumerService.sayHelloFeign(name);
    }


    @PostMapping("/postSayHelloFeign")
    public String postSayHelloFeign(@RequestBody User user) {
        return feignConsumerService.sayHelloFeign(user.getName());
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody User user) {
        return feignConsumerService.addStudent(user);
    }


    @PostMapping("/addStudentList")
    public String addStudent(@RequestBody List<User> user) {
        return feignConsumerService.addStudentList(user);
    }

    /**
     * 测试两个实现类的选择示例
     * @param user
     * @return
     */
    @PostMapping("/addStudentLocal")
    public User addStudentLocal(@RequestBody User user) {
        String name = userService.addUser(user);
        user.setName(name);
        user.setAge(80);
        log.info("" + beanProperties.isFlag());
        return user;
    }
}
