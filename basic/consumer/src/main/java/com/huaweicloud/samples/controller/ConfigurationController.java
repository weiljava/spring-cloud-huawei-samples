package com.huaweicloud.samples.controller;


import com.huaweicloud.samples.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigurationController {


    @Autowired
    private ConfigService configService;


    @GetMapping("/getConfigValue")
    public String getConfigValue(@RequestParam("key") String key) {
        return configService.getConfigValue(key);
    }
}
