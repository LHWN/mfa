package com.example.demo.mfa.datas.entitties;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Entity
@ToString
@EqualsAndHashCode(callSuper = false)
@Table(name = "users", schema = "security")
public class UserEntity implements Serializable {
    @Id
    @Column(nullable = false)
    private final long id;

    @Column(length = 50)
    private final String username;

    @Column(length = 512)
    private final String password;

    @Column(length = 1000)
    private final String roles;

    @Builder
    public UserEntity(long id, String username, String password, String roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
