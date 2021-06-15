package com.example.demo.mfa.service;

import com.example.demo.mfa.datas.dto.MfaDto;
import com.example.demo.mfa.datas.dto.MfaInitDto;
import com.example.demo.mfa.datas.dto.MfaProveDto;

public interface MfaService {
    MfaDto getMfa(String username);

    MfaProveDto getMfaSecretKey(String username);

    MfaInitDto setMfa(MfaInitDto mfaInitDto);

    MfaDto setMfa(MfaDto mfaDto);

    void deleteMfa(String username);
}
