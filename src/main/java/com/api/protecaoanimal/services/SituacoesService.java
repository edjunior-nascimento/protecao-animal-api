package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.api.protecaoanimal.dtos.SituacoesDto;
import com.api.protecaoanimal.models.SituacoesModel;
import com.api.protecaoanimal.repositories.SituacoesRepository;

import jakarta.transaction.Transactional;

@Service
public class SituacoesService {
    
    final SituacoesRepository situacoesRepository;

    public SituacoesService(SituacoesRepository situacoesRepository) {
        this.situacoesRepository = situacoesRepository;
    }

    @Transactional
    public SituacoesModel save(SituacoesDto situacoesDto) {
        var situacoesModel = new SituacoesModel();
        BeanUtils.copyProperties(situacoesDto, situacoesModel);
        situacoesModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return situacoesRepository.save(situacoesModel);
    }

    public List<SituacoesModel> findAll() {
        return situacoesRepository.findAll();
    }

    public SituacoesModel findById(UUID id) {
        return situacoesRepository.findById(id).get();
    }

    public void delete(SituacoesModel situacoesModel) {
        situacoesRepository.delete(situacoesModel);
    }

}
