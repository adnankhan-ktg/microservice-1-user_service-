package com.user.services.impl;

import com.user.dao.UserDao;
import com.user.dto.UserDto;
import com.user.models.User;
import com.user.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServieImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDto getUser(int id) {
        User temporaryUser = this.userDao.findById(id);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(temporaryUser,userDto);
        return userDto;

    }

    @Override
    public User addUser(User user) {

             return this.userDao.save(user);
    }
}
