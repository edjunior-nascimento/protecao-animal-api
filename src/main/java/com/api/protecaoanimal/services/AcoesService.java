package com.api.protecaoanimal.services;

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
    public Object save(AcoesModel acoesModel) {
        return acoesRepository.save(acoesModel);
    }

}
