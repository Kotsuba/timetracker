package com.touchsoft.timetracker.api.security;

import com.touchsoft.timetracker.api.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MysqlPasswordEncoder implements PasswordEncoder {

    @Resource
    private UserService userService;

    public String encode(CharSequence charSequence) {
        return userService.getPasswordHash(charSequence);
    }

    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(encode(charSequence));
    }


}
