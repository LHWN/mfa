package com.example.demo.mfa.service;

import com.example.demo.mfa.datas.dto.MfaDto;
import com.example.demo.mfa.datas.dto.MfaInitDto;
import com.example.demo.mfa.datas.dto.MfaProveDto;
import com.example.demo.mfa.datas.entitties.MfaEntity;
import com.example.demo.mfa.repositories.MfaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MfaServiceImpl implements MfaService {
    private final MfaRepository mfaRepository;

    @Autowired
    public MfaServiceImpl(MfaRepository mfaRepository) {
        this.mfaRepository = mfaRepository;
    }

    @Override
    public MfaDto getMfa(String username) {
        return new MfaDto(mfaRepository.findByUsername(username));
    }

    @Override
    public MfaProveDto getMfaSecretKey(String username) {
        return new MfaProveDto(mfaRepository.findByUsername(username));
    }

    @Override
    public MfaInitDto setMfa(MfaInitDto mfaInitDto) {
        mfaRepository.save(new MfaEntity(mfaInitDto));
        return mfaInitDto;
    }

    @Override
    public MfaDto setMfa(MfaDto mfaDto) {
        mfaRepository.save(new MfaEntity(mfaDto));
        return mfaDto;
    }

    @Override
    public void deleteMfa(String username) {
        mfaRepository.delete(mfaRepository.findByUsername(username));
    }
}
