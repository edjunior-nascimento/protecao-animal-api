package com.api.protecaoanimal.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

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
    public AnunciosModel save(AnunciosModel anunciosModel) {
        return anunciosRepository.save(anunciosModel);
    }

    public List<AnunciosModel> findAll() {
        return anunciosRepository.findAll();
    }

    public Optional<AnunciosModel> findById(UUID id) {
        return anunciosRepository.findById(id);
    }

    public void delete(AnunciosModel anunciosModel) {
        anunciosRepository.delete(anunciosModel);
    }

}
