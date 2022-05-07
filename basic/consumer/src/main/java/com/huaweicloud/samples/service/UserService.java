package com.huaweicloud.samples.service;

import com.huaweicloud.samples.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    public String addUser(User user) {
        log.info("add user");
        log.info(user.toString());

        return user.getName();
    }
}
