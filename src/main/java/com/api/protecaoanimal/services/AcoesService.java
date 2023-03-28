package com.api.protecaoanimal.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

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
    public AcoesModel save(AcoesModel acoesModel) {
        return acoesRepository.save(acoesModel);
    }

    public List<AcoesModel> findAll() {
        return acoesRepository.findAll();
    }

    public Optional<AcoesModel> findById(UUID id) {
        return acoesRepository.findById(id);
    }

    public void delete(AcoesModel acoesModel) {
        acoesRepository.delete(acoesModel);
    }

}
