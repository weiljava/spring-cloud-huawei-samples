package com.huaweicloud.samples;

import com.huaweicloud.samples.client.FeignConsumerService;
import com.huaweicloud.samples.domain.User;
import com.huaweicloud.samples.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RefreshScope
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FeignConsumerService feignConsumerService;

    @Resource
    private UserService userService;

    @Value("${consumer.config}")
    private String configValue;

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


    @PostMapping("/addStudentLocal")
    public User addStudentLocal(@RequestBody User user) {
        userService.addUser(user);
        user.setAge(80);
        return user;
    }

}
