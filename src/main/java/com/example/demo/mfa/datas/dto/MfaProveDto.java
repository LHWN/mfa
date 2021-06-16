package com.example.demo.mfa.datas.dto;

import com.example.demo.mfa.datas.entitties.MfaEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MfaProveDto implements Serializable {
    private final String secretKey;
    private final String type;

    public MfaProveDto(MfaEntity mfaEntity) {
        this.secretKey = mfaEntity.getSecretKey();
        this.type = mfaEntity.getType();
    }

    @Builder
    public MfaProveDto(String secretKey, String type) {
        this.secretKey = secretKey;
        this.type = type;
    }
}
