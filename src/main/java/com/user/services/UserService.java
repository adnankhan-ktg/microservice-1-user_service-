package com.user.services;

import com.user.dto.UserDto;
import com.user.models.User;

import java.util.Optional;

public interface UserService  {

      UserDto getUser(int id);
      User addUser(User user);
}
