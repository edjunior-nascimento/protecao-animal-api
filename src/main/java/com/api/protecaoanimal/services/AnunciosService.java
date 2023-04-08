package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.protecaoanimal.dtos.AnunciosDto;
import com.api.protecaoanimal.exceptions.ItemNotFoundException;
import com.api.protecaoanimal.models.AnunciosModel;
import com.api.protecaoanimal.repositories.AnunciosRepository;

import jakarta.transaction.Transactional;

@Service
public class AnunciosService {
    
    final AnunciosRepository anunciosRepository;

    public AnunciosService(AnunciosRepository anunciosRepository) {
        this.anunciosRepository = anunciosRepository;
    }

    @Transactional
    public AnunciosModel save(AnunciosDto anunciosDto) {
        var anunciosModel = new AnunciosModel();
        BeanUtils.copyProperties(anunciosDto, anunciosModel);
        anunciosModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return anunciosRepository.save(anunciosModel);
    }

    public List<AnunciosModel> findAll() {
        return anunciosRepository.findAll();
    }

    public AnunciosModel findById(UUID id) {
        Optional<AnunciosModel> anunciosModel = anunciosRepository.findById(id);
        return anunciosModel.orElseThrow(ItemNotFoundException::new);
    }

    public void delete(AnunciosModel anunciosModel) {
        findById(anunciosModel.getId());
        anunciosRepository.delete(anunciosModel);
    }

}
