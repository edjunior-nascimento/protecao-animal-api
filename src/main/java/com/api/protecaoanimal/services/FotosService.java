package com.api.protecaoanimal.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.protecaoanimal.models.FotosModel;
import com.api.protecaoanimal.repositories.FotosRepository;

import jakarta.transaction.Transactional;

@Service
public class FotosService {

    final FotosRepository fotosRepository;

    public FotosService(FotosRepository fotosRepository) {
        this.fotosRepository = fotosRepository;
    }

    @Transactional
    public FotosModel save(FotosModel fotosModel) {
        return fotosRepository.save(fotosModel);
    }

    public List<FotosModel> findAll() {
        return fotosRepository.findAll();
    }

    public Optional<FotosModel> findById(UUID id) {
        return fotosRepository.findById(id);
    }

    public void delete(FotosModel fotosModel) {
        fotosRepository.delete(fotosModel);
    }
    
}
