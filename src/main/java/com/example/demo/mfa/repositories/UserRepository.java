package com.example.demo.mfa.repositories;

import com.example.demo.mfa.datas.entitties.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
