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
import com.api.protecaoanimal.dtos.SituacoesDto;
import com.api.protecaoanimal.exceptions.ItemIsUsedException;
import com.api.protecaoanimal.exceptions.ItemNotFoundException;
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

    public SituacoesModel update(UUID id, SituacoesDto situacoesDto) {
        var situacoesModel = new SituacoesModel();
        BeanUtils.copyProperties(situacoesDto, situacoesModel);
        situacoesModel.setId(id);
        situacoesModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return situacoesRepository.save(situacoesModel);
    }

    public Page<SituacoesModel> findAll(Pageable pageable) {
        return situacoesRepository.findAll(pageable);
    }

    public SituacoesModel findById(UUID id) {
        Optional<SituacoesModel> situacoesModel = situacoesRepository.findById(id);
        return situacoesModel.orElseThrow(() -> new ItemNotFoundException("UUID de situação não existe"));
    }

    public void delete(SituacoesModel situacoesModel) {
        findById(situacoesModel.getId());
        try{
            situacoesRepository.delete(situacoesModel);
        } catch (DataIntegrityViolationException e) {
            throw new ItemIsUsedException("Situação não pode ser deletada, pois está em uso por algum animal");
        }
    }

}
