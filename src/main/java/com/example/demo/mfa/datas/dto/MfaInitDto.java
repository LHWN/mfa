package com.example.demo.mfa.datas.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MfaInitDto {
    private final String username;
    private final String secretKey;
    private final String type;

    @Builder
    public MfaInitDto(String username, String secretKey, String type) {
        this.username = username;
        this.secretKey = secretKey;
        this.type = type;
    }
}
