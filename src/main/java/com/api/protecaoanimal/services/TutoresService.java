package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.protecaoanimal.dtos.TutoresDto;
import com.api.protecaoanimal.exceptions.ItemNotFoundException;
import com.api.protecaoanimal.models.TutoresModel;
import com.api.protecaoanimal.repositories.TutoresRepository;

import jakarta.transaction.Transactional;

@Service
public class TutoresService {
    
    final TutoresRepository tutoresRepository;

    public TutoresService(TutoresRepository tutoresRepository) {
        this.tutoresRepository = tutoresRepository;
    }

    @Transactional
    public TutoresModel save(TutoresDto tutoresDto) {
        var tutoresModel = new TutoresModel();
        BeanUtils.copyProperties(tutoresDto, tutoresModel);
        tutoresModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return tutoresRepository.save(tutoresModel);
    }

    public Page<TutoresModel> findAll(Pageable pageable) {
        return tutoresRepository.findAll(pageable);
    }

    public TutoresModel findById(UUID id) {
        Optional<TutoresModel> acoesModel = tutoresRepository.findById(id);
        return acoesModel.orElseThrow(() -> new ItemNotFoundException("UUID de tutores não existe"));
    }

    public void delete(TutoresModel tutoresModel) {
        findById(tutoresModel.getId());
        tutoresRepository.delete(tutoresModel);
    }

}
