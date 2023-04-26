package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.protecaoanimal.dtos.RegrasDto;
import com.api.protecaoanimal.exceptions.ItemIsUsedException;
import com.api.protecaoanimal.exceptions.ItemNotFoundException;
import com.api.protecaoanimal.models.RegrasModel;
import com.api.protecaoanimal.repositories.RegrasRepository;

import jakarta.transaction.Transactional;

@Service
public class RegrasService {
    
    final RegrasRepository regrasRepository;

    public RegrasService(RegrasRepository regrasRepository) {
        this.regrasRepository = regrasRepository;
    }

    @Transactional
    public RegrasModel save(RegrasDto regrasDto) {
        var regrasModel = new RegrasModel();
        BeanUtils.copyProperties(regrasDto, regrasModel);
        regrasModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return regrasRepository.save(regrasModel);
    }

    public RegrasModel update(UUID id, RegrasDto regrasDto) {
        var regrasModel = new RegrasModel();
        BeanUtils.copyProperties(regrasDto, regrasModel);
        regrasModel.setId(id);
        regrasModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return regrasRepository.save(regrasModel);
    }

    public Page<RegrasModel> findAll(Pageable pageable) {
        return regrasRepository.findAll(pageable);
    }

    public RegrasModel findById(UUID id) {
        Optional<RegrasModel> regrasModel = regrasRepository.findById(id);
        return regrasModel.orElseThrow(() -> new ItemNotFoundException("UUID de regra não existe"));
    }

    public void delete(RegrasModel regrasModel) {
        findById(regrasModel.getId());
        try{
            regrasRepository.delete(regrasModel);
        } catch (DataIntegrityViolationException e) {
            throw new ItemIsUsedException("Regra não pode ser deletada, pois está em uso por algum usuário");
        }
    }

}
