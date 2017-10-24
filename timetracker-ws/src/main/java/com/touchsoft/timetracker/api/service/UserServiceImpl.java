package com.touchsoft.timetracker.api.service;


import com.touchsoft.timetracker.api.dao.UserDao;
import com.touchsoft.timetracker.api.dao.UserNotFoundException;
import com.touchsoft.timetracker.api.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getUserByLogin(String login) throws UserNotFoundException {
        return userDao.getUserByLogin(login);
    }

    @Override
    public String getPasswordHash(CharSequence charSequence) {
        return userDao.getPasswordHash(charSequence);
    }

    @Override
    public List getUsers(){
        return  userDao.getUsers();
    }

    @Override
    public List getUsersByViewFilter(String viewFilter) {
        return userDao.getUserByViewFilter(viewFilter);
    }

    @Override
    public Object getUser(String login) {
        return userDao.getUser(login);
    }

    @Override
    public Object getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public String getViewFilter(String login) {
        return userDao.getViewFilter(login);
    }
}

