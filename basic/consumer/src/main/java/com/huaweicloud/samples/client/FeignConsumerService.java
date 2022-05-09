package com.huaweicloud.samples.client;

import com.huaweicloud.samples.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "feignConsumerService", url = "${provider.url}")
public interface FeignConsumerService {

    @GetMapping("/sayHelloFeign")
    String sayHelloFeign(@RequestParam("name") String name);

    @PostMapping("/addStudent")
    String addStudent(@RequestBody User user);

    @PostMapping("/addStudentList")
    String addStudentList(@RequestBody List<User> user);
}
