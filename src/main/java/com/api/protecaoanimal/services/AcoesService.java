package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.protecaoanimal.dtos.AcoesDto;
import com.api.protecaoanimal.exceptions.ItemNotFoundException;
import com.api.protecaoanimal.models.AcoesModel;
import com.api.protecaoanimal.repositories.AcoesRepository;

import jakarta.transaction.Transactional;

@Service
public class AcoesService {
    
    final AcoesRepository acoesRepository;

    public AcoesService(AcoesRepository acoesRepository) {
        this.acoesRepository = acoesRepository;
    }

    @Transactional
    public AcoesModel save(AcoesDto acoesDto) {
        var acoesModel = new AcoesModel();
        BeanUtils.copyProperties(acoesDto, acoesModel);
        acoesModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return acoesRepository.save(acoesModel);
    }

    public List<AcoesModel> findAll() {
        return acoesRepository.findAll();
    }

    public AcoesModel findById(UUID id) {
        Optional<AcoesModel> acoesModel = acoesRepository.findById(id);
        return acoesModel.orElseThrow(() -> new ItemNotFoundException("UUID de ação não existe"));
    }

    public void delete(AcoesModel acoesModel) {
        findById(acoesModel.getId());
        acoesRepository.delete(acoesModel);
    }

}
