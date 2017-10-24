package com.touchsoft.timetracker.api.security;

import com.touchsoft.timetracker.api.dao.UserNotFoundException;
import com.touchsoft.timetracker.api.model.User;
import com.touchsoft.timetracker.api.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String login){
        User user = null;
        try {
            user = userService.getUserByLogin(login);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("No user with username '" + login + "' found!");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> roles = new ArrayList<>();
        if (user.getManager() == null) {
            roles.addAll(AuthorityUtils.createAuthorityList(UserRole.ROLE_MANAGER.name()));
        } else if (user.getCoManager() != null) {
            roles.addAll(AuthorityUtils.createAuthorityList(UserRole.ROLE_COMANAGER.name()));
        }else if (user.getViewManager() != null) {
            roles.addAll(AuthorityUtils.createAuthorityList(UserRole.ROLE_VIEWMANAGER.name()));
        }
        else {
            roles.add(new SimpleGrantedAuthority(UserRole.ROLE_USER.name()));
        }
        return roles;
    }


}