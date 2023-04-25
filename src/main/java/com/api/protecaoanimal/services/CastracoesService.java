package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.protecaoanimal.dtos.CastracoesDto;
import com.api.protecaoanimal.exceptions.ItemNotFoundException;
import com.api.protecaoanimal.models.CastracoesModel;
import com.api.protecaoanimal.repositories.CastracoesRepository;

import jakarta.transaction.Transactional;

@Service
public class CastracoesService {
    
    final CastracoesRepository castracoesRepository;

    public CastracoesService(CastracoesRepository castracoesRepository) {
        this.castracoesRepository = castracoesRepository;
    }

    @Transactional
    public CastracoesModel save(CastracoesDto castracoesDto) {
        var castracoesModel = new CastracoesModel();
        BeanUtils.copyProperties(castracoesDto, castracoesModel);
        castracoesModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return castracoesRepository.save(castracoesModel);
    }

    public CastracoesModel update(UUID id, CastracoesDto castracoesDto) {
        var castracoesModel = new CastracoesModel();
        BeanUtils.copyProperties(castracoesDto, castracoesModel);
        castracoesModel.setId(id);
        castracoesModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return castracoesRepository.save(castracoesModel);
    }

    public Page<CastracoesModel> findAll(Pageable pageable) {
        return castracoesRepository.findAll(pageable);
    }

    public CastracoesModel findById(UUID id) {
        Optional<CastracoesModel> castracoesModel = castracoesRepository.findById(id);
        return castracoesModel.orElseThrow(() -> new ItemNotFoundException("UUID de castrações não existe"));
        
    }

    public void delete(CastracoesModel castracoesModel) {
        findById(castracoesModel.getId());
        castracoesRepository.delete(castracoesModel);
    }

}
