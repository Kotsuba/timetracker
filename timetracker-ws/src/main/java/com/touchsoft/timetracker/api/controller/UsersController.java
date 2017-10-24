package com.touchsoft.timetracker.api.controller;

import com.touchsoft.timetracker.api.security.UserRole;
import com.touchsoft.timetracker.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {

    @Resource
    private UserService usersService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List getUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals(UserRole.ROLE_VIEWMANAGER.name())) {
                String filter = usersService.getViewFilter((String) authentication.getPrincipal());
                if (filter != null) {
                    return usersService.getUsersByViewFilter(filter);
                }
            }
            if (authority.getAuthority().equals(UserRole.ROLE_MANAGER.name()) || authority.getAuthority().equals(UserRole.ROLE_COMANAGER.name())) {
                return usersService.getUsers();
            }
        }
        //for angular page because return exception on frontend if return null
        return new ArrayList();
    }

    @RequestMapping(value = "/user/{loginORId}", method = RequestMethod.GET)
    public Object getUser(@PathVariable(value = "loginORId") String loginORId) {
        try {
            Long id = Long.valueOf(loginORId);
            return usersService.getUser(id);
        } catch (NumberFormatException ex) {
            return usersService.getUser(loginORId);
        }
    }
}
