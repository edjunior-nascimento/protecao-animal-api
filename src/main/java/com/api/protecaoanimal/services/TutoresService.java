package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.protecaoanimal.dtos.TutoresDto;
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

    public List<TutoresModel> findAll() {
        return tutoresRepository.findAll();
    }

    public Optional<TutoresModel> findById(UUID id) {
        return tutoresRepository.findById(id);
    }

    public void delete(TutoresModel tutoresModel) {
        tutoresRepository.delete(tutoresModel);
    }

}
