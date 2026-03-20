package com.backend.backend.mapper;

import com.backend.backend.dto.UserRequest;
import com.backend.backend.model.User;

public class UserMapper {
    public static UserRequest mapToUserRequest(User user){
        UserRequest userRequest=new UserRequest();
        userRequest.setUserName(user.getUserName());
        userRequest.setPassword(user.getPassword());
        return userRequest;
    }
    public static User mapToUser(UserRequest userRequest){
        User user=new User();
        user.setUserName(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());
        return user;
    }
}
