package com.huaweicloud.samples.service;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigBService implements ConfigService{
    @Override
    public String getConfigValue(String key) {
        log.info(key);
        return "this is B";
    }
}
