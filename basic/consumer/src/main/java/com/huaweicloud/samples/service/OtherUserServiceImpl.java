package com.huaweicloud.samples.service;

import com.huaweicloud.samples.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//@Service("otherUserServiceImpl")
@Slf4j
public class OtherUserServiceImpl implements UserService {
    @Override
    public String addUser(User user) {
        log.info(OtherUserServiceImpl.class.toString() + " add user");
        log.info(user.toString());
        return user.getName() + "-other";
    }
}
