package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.protecaoanimal.dtos.CastracoesDto;
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

    public List<CastracoesModel> findAll() {
        return castracoesRepository.findAll();
    }

    public CastracoesModel findById(UUID id) {
        return castracoesRepository.findById(id).get();
    }

    public void delete(CastracoesModel castracoesModel) {
        castracoesRepository.delete(castracoesModel);
    }

}
