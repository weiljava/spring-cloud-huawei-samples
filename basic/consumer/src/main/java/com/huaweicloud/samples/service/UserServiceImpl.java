package com.huaweicloud.samples.service;

import com.huaweicloud.samples.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//@Service("userServiceImpl")
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public String addUser(User user) {
        log.info(UserServiceImpl.class.toString() + " add user");
        log.info(user.toString());
        return user.getName() + "-user";
    }
}
