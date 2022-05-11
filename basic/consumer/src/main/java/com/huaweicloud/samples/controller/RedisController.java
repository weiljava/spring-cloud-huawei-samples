package com.huaweicloud.samples.controller;


import com.alibaba.fastjson.JSONObject;
import com.huaweicloud.samples.domain.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;


    @GetMapping("/getKey")
    public Object getKey(@RequestParam("key") String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @PostMapping(path = "/addKV", consumes = "application/x-www-form-urlencoded")
    public String addKV(@RequestParam("key") String key, @RequestParam("value") String value) {
        redisTemplate.opsForValue().set(key, value);
        return "success";
    }


    @PostMapping(path = "/addUser", consumes = "application/json")
    public String addKV(@RequestBody User user) {
        redisTemplate.opsForValue().set(user.getId(), JSONObject.toJSONString(user));
        return "success";
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam("id") String id) {
        return JSONObject.parseObject(redisTemplate.opsForValue().get(id).toString(), User.class);
    }

}
