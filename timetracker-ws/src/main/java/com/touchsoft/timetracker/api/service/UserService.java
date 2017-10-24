package com.touchsoft.timetracker.api.service;


import com.touchsoft.timetracker.api.dao.UserNotFoundException;
import com.touchsoft.timetracker.api.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    @Transactional(readOnly = true)
    User getUserByLogin(String login) throws UserNotFoundException;

    @Transactional(readOnly = true)
    String getPasswordHash(CharSequence charSequence);

    @Transactional(readOnly = true)
    List getUsers();

    @Transactional(readOnly = true)
    Object getUser(String login);

    @Transactional(readOnly = true)
    Object getUser(Long id);

    @Transactional(readOnly = true)
    List getUsersByViewFilter(String viewFilter);

    @Transactional(readOnly = true)
    String getViewFilter(String login);
}
