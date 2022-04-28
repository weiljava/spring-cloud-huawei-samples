package com.huaweicloud.samples;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "feignConsumerService",url = "${provider.url}")
public interface FeignConsumerService {

    @GetMapping("/sayHelloFeign")
    String sayHelloFeign(@RequestParam("name") String name);
}
