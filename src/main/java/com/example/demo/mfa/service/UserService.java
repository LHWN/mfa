package com.example.demo.mfa.service;

import com.example.demo.mfa.datas.entitties.UserEntity;

public interface UserService {
    UserEntity getUser(String username);

    UserEntity getUser(UserEntity userEntity);
}
